package com.notfunny.knockknock2;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;

public class JokeActivity extends AppCompatActivity {

    private String arg;

    TextView[] tvJokes;

    private MediaPlayer mySound;
    private ImageButton door;
    private int knockCount = 0;
    private int knockCountResetMil = 650;
    private long startKnockTime;
    private boolean opened = false;

    private Animation[] animFadeIns;
    private Animation animFadeOut;

    private JokeDBHandler handler;

    // The index of the TextView currently fading in
    private int currentTv = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_joke);

        door = (ImageButton) findViewById(R.id.door);
        OnClickButtonListener();

        animFadeIns = new Animation[5];
        for (int i = 0; i < animFadeIns.length; i++) {
            animFadeIns[i] = AnimationUtils.loadAnimation(this, R.anim.fade_in);

            if (i == animFadeIns.length - 1) continue;

            final int finalI = i;
            animFadeIns[i].setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    tvJokes[finalI + 1].setAlpha(1);
                    tvJokes[finalI + 1].startAnimation(animFadeIns[finalI + 1]);
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }
            });
        }

        animFadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);

        // Set up the Joke Database handler
        File f = new File(getBaseContext().getExternalCacheDir() + "/jokesDB.sqlite");
        //if (f.exists()) f.delete();
        handler = new JokeDBHandler(this, null, null, 1, f.exists());

        // Get argument from intent
        Bundle b = getIntent().getExtras();
        arg = "animals";
        if (b != null)
            arg = b.getString("arg");

        // If argument given is a number, find the joke with that ID
        String jokeStr;
        if (arg.matches("[0-9]+"))
            jokeStr = handler.getJoke(Integer.parseInt(arg));
            // Else, the argument is a category
        else
            jokeStr = handler.getJoke(arg);

        tvJokes = new TextView[5];
        tvJokes[0] = (TextView) findViewById(R.id.tvJoke1);
        tvJokes[1] = (TextView) findViewById(R.id.tvJoke2);
        tvJokes[2] = (TextView) findViewById(R.id.tvJoke3);
        tvJokes[3] = (TextView) findViewById(R.id.tvJoke4);
        tvJokes[4] = (TextView) findViewById(R.id.tvJoke5);

        for (int i = 0; i < tvJokes.length; i++)
            tvJokes[i].setAlpha(0);
    }

    public void newJokeClickListener(View view) {
        newJoke();
    }

    private void newJoke() {
        // Make the TextViews dissappear
        for (int i = 1; i < tvJokes.length; i++)
            tvJokes[i].setAlpha(0);

        String jokeStr;
        if (arg.matches("[0-9]+"))
            jokeStr = handler.getJoke(Integer.parseInt(arg));
            // Else, the argument is a category
        else {
            do
                jokeStr = handler.getJoke(arg);
            while (jokeStr.equals("0"));
        }

        // Fill the textboxes with funny jokes haha
        if (jokeStr != null)
        {
            String[] jokeParts = jokeStr.split(";");
            tvJokes[2].setText(jokeParts[0]);
            tvJokes[3].setText(jokeParts[1]);
            tvJokes[4].setText(jokeParts[2]);
        }

        // Fade the TextViews in, (one after the other)
        tvJokes[0].setAlpha(1);
        tvJokes[0].startAnimation(animFadeIns[0]);
    }

    private void playSound(int resid) {
        mySound = MediaPlayer.create(this, resid);
        mySound.start();
    }

    private void OnClickButtonListener() {
        door.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (knockCount == 0) {
                            startKnockTime = System.currentTimeMillis();
                        } else if (knockCount == 1 && (System.currentTimeMillis() - startKnockTime) > knockCountResetMil) { // if the person waited too long between their knocks, their knock count resets
                            startKnockTime = System.currentTimeMillis();
                            knockCount = 0;
                        }

                        playSound(R.raw.knock_once);
                        knockCount++;

                        if (knockCount >= 2) {
                            if (!opened) {
                                door.setBackgroundResource(R.drawable.animated_door_background);
                                door.startAnimation(animFadeOut);
                                playSound(R.raw.door_creak);
                                opened = true;
                                door.setClickable(false);
                                newJoke();
                            } else {
                                door.setBackgroundResource(R.drawable.animated_door_background2);
                                door.startAnimation(animFadeIns[4]);
                                playSound(R.raw.door_close);
                                opened = false;
                            }

                            AnimationDrawable anim = (AnimationDrawable) door.getBackground();
                            anim.start();

                            knockCount = 0;
                        }
                    }
                }
        );
    }


}
