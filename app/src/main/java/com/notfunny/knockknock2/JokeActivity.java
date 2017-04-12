package com.notfunny.knockknock2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class JokeActivity extends AppCompatActivity {

    private MediaPlayer mySound;
    private ImageButton door;
    private int knockCount = 0;
    private int knockCountResetMil = 2000;
    private long startKnockTime;
    private boolean opened = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_joke);

        door = (ImageButton) findViewById(R.id.door);
        OnClickButtonListener();
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
                                playSound(R.raw.door_creak);
                                opened = true;
                            } else {
                                door.setBackgroundResource(R.drawable.animated_door_background2);
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
