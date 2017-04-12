package com.notfunny.knockknock2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mySound;
    private ImageButton upperLeftDoor;
    private ImageButton upperRightDoor;
    private ImageButton lowerLeftDoor;
    private ImageButton lowerRightDoor;
    private ImageButton heart;

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

        OnClickButtonListener();
    }

    private void playSound(int resid) {
        mySound = MediaPlayer.create(this, resid);
        mySound.start();
    }

    private void OnClickButtonListener() {
        upperLeftDoor.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playSound(R.raw.knock_once);
                        Intent intent = new Intent(MainActivity.this, JokeActivity.class);
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
                        startActivity(intent);
                    }
                }
        );

        lowerLeftDoor.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playSound(R.raw.knock_once);
                        Intent intent = new Intent(MainActivity.this, JokeActivity2.class);
                        intent.putExtra("arg", "music");
                        startActivity(intent);
                    }
                }
        );

        lowerRightDoor.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playSound(R.raw.knock_once);
                        Intent intent = new Intent(MainActivity.this, JokeActivity2.class);
                        intent.putExtra("arg", "nerdy");
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
                        startActivity(intent);
                    }
                }
        );
    }
}
