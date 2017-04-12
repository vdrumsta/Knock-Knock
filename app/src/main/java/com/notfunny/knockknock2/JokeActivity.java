package com.notfunny.knockknock2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class JokeActivity extends AppCompatActivity {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();;
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    class GameView extends SurfaceView implements Runnable {

        private Thread gameThread;
        private SurfaceHolder ourHolder;
        private volatile boolean playing;
        private Canvas canvas;
        private Bitmap door;
        private boolean isPlaying;
        private boolean moving = false;
        private boolean opened = false;
        private float runSpeedPerSecond = 250;
        private int frameWidth = 304, frameHeight = 554;
        private float doorXPos = 10, doorYPos = 10;
        private int frameCount = 10;
        private int currentFrame = 0;
        private long fps;
        private long timeThisFrame;
        private long lastFrameChangeTime = 0;
        private int frameLengthMillisecond = 25;

        private Rect frameToDraw = new Rect(0, 0, frameWidth, frameHeight);

        private Rect whereToDraw = new Rect((int) doorXPos, (int) doorYPos, (int) doorXPos + frameWidth, (int) doorYPos + frameHeight);

        public GameView(Context context) {
            super(context);
            ourHolder = getHolder();
            door = BitmapFactory.decodeResource(getResources(), R.drawable.door_spritesheet);
            door = Bitmap.createScaledBitmap(door, frameWidth * frameCount, frameHeight, false);
        }

        @Override
        public void run() {
            while (playing) {
                long startFrameTime = System.currentTimeMillis();
                draw();

                timeThisFrame = System.currentTimeMillis() - startFrameTime;

                if (timeThisFrame >= 1) {
                    fps = 1000 / timeThisFrame;
                }
            }
        }

        public void manageCurrentFrame() {
            long time = System.currentTimeMillis();

            if (moving) {                                      // if it's unpaused and the door is set to move
                if (time > lastFrameChangeTime + frameLengthMillisecond) {  // change the frame from a spritesheet
                    lastFrameChangeTime = time;

                    if (!opened) {                  // next frame, until it's opened
                        currentFrame++;
                        if (currentFrame >= 9) {
                            opened = true;
                            moving = false;
                        }
                    } else {                        // previous frame, until it's closed
                        currentFrame--;
                        if (currentFrame <= 0) {
                            opened = false;
                            moving = false;
                        }
                    }
                }
            }

            // select the frame from a spritesheet
            frameToDraw.left = currentFrame * frameWidth;
            frameToDraw.right = frameToDraw.left + frameWidth;
        }

        public void draw() {
            if (ourHolder.getSurface().isValid()) {
                canvas = ourHolder.lockCanvas();
                canvas.drawColor(Color.WHITE);
                whereToDraw.set((int) (getWidth() / 2 - frameWidth), (int) (getHeight() - (frameHeight * 2)), (int) (getWidth() / 2 + frameWidth), (int) getHeight());

                manageCurrentFrame();
                canvas.drawBitmap(door, frameToDraw, whereToDraw, null);
                ourHolder.unlockCanvasAndPost(canvas);
            }
        }

        public void pause() {
            playing = false;

            try {
                gameThread.join();
            } catch(InterruptedException e) {
                Log.e("ERR", "Joining Thread");
            }
        }

        public void resume() {
            playing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    isPlaying = !isPlaying;
                    moving = true;
                    break;
            }

            return true;
        }
    }
}
