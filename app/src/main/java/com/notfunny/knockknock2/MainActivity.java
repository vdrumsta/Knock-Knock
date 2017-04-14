package com.notfunny.knockknock2;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mySound;
    private Vibrator vib;
    private ImageButton upperLeftDoor;
    private ImageButton upperRightDoor;
    private ImageButton lowerLeftDoor;
    private ImageButton lowerRightDoor;
    private ImageButton heart;
    private ToggleButton speaker;

    private boolean muted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        upperLeftDoor = (ImageButton) findViewById(R.id.UpperLeftDoor);
        upperRightDoor = (ImageButton) findViewById(R.id.UpperRightDoor);
        lowerLeftDoor = (ImageButton) findViewById(R.id.LowerLeftDoor);
        lowerRightDoor = (ImageButton) findViewById(R.id.LowerRightDoor);
        heart = (ImageButton) findViewById(R.id.Heart);
        speaker = (ToggleButton) findViewById(R.id.chkState);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        OnClickButtonListener();
    }

    private void playSound(int resid) {
        if (!muted) {
            mySound = MediaPlayer.create(this, resid);
            mySound.start();
            vib.vibrate(200);
        }
    }

    private void OnClickButtonListener() {
        upperLeftDoor.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playSound(R.raw.knock_once);
                        Intent intent = new Intent(MainActivity.this, JokeActivity.class);

                        Bundle extras = new Bundle();
                        extras.putBoolean("EXTRA_MUTED", muted);
                        extras.putString("arg","animals");
                        intent.putExtras(extras);

                        startActivity(intent);
                    }
                }
        );

        upperRightDoor.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playSound(R.raw.knock_once);
                        Intent intent = new Intent(MainActivity.this, JokeActivity.class);

                        Bundle extras = new Bundle();
                        extras.putBoolean("EXTRA_MUTED", muted);
                        extras.putString("arg","animals");
                        intent.putExtras(extras);

                        startActivity(intent);
                    }
                }
        );

        lowerLeftDoor.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playSound(R.raw.knock_once);
                        Intent intent = new Intent(MainActivity.this, JokeActivity.class);

                        Bundle extras = new Bundle();
                        extras.putBoolean("EXTRA_MUTED", muted);
                        extras.putString("arg","music");
                        intent.putExtras(extras);

                        startActivity(intent);
                    }
                }
        );

        lowerRightDoor.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playSound(R.raw.knock_once);
                        Intent intent = new Intent(MainActivity.this, JokeActivity.class);

                        Bundle extras = new Bundle();
                        extras.putBoolean("EXTRA_MUTED", muted);
                        extras.putString("arg","nerdy");
                        intent.putExtras(extras);

                        startActivity(intent);
                    }
                }
        );

        heart.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playSound(R.raw.phone_tap);
                        Intent intent = new Intent(MainActivity.this, FavouritesActivity.class);

                        Bundle extras = new Bundle();
                        extras.putBoolean("EXTRA_MUTED", muted);
                        intent.putExtras(extras);

                        startActivity(intent);
                    }
                }
        );

        speaker.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        muted = speaker.isChecked();
                    }
                }
        );
    }
}
