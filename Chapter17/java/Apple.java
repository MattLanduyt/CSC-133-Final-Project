package com.gamecodeschool.c17snake;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Random;

class Apple {
    private boolean isBapple;
    // The location of the apple on the grid
    // Not in pixels
    private Point location = new Point();

    // The range of values we can choose from
    // to spawn an apple
    private Point mSpawnRange;
    private int mSize;

    // An image to represent the apple
    private Bitmap mBitmapApple;
    private Bitmap mBitmapBapple;

    /// Set up the apple in the constructor
    Apple(Context context, Point sr, int s){

        // Make a note of the passed in spawn range
        mSpawnRange = sr;
        // Make a note of the size of an apple
        mSize = s;
        // Hide the apple off-screen until the game starts
        location.x = -10;

        // Load the image to the bitmap
        mBitmapApple = BitmapFactory.decodeResource(context.getResources(), R.drawable.apple);
        mBitmapBapple = BitmapFactory.decodeResource(context.getResources(), R.drawable.bapple);

        // Resize the bitmap
        mBitmapApple = Bitmap.createScaledBitmap(mBitmapApple, s, s, false);
        mBitmapBapple = Bitmap.createScaledBitmap(mBitmapBapple, s, s, false);
    }

    // This is called every time an apple is eaten
    boolean spawn() {
        // Choose a random value
        Random random = new Random();
        int randomNumber = random.nextInt(2); // Generates a random number between 0 and 2

        // 1 in 3 chance of spawning a Bapple
        if (randomNumber == 1) {
            // Spawn Bapple
            location.x = random.nextInt(mSpawnRange.x) + 1;
            location.y = random.nextInt(mSpawnRange.y - 1) + 1;
            mBitmapApple = Bitmap.createScaledBitmap(mBitmapBapple, mSize, mSize, false);
            isBapple = true;
        } else {
            // Spawn Apple
            location.x = random.nextInt(mSpawnRange.x) + 1;
            location.y = random.nextInt(mSpawnRange.y - 1) + 1;
            mBitmapApple = Bitmap.createScaledBitmap(mBitmapApple, mSize, mSize, false);
            isBapple = false;
        }
        return isBapple;
    }

    // Let SnakeGame know where the apple is
    // SnakeGame can share this with the snake
    Point getLocation(){
        return location;
    }

    // Draw the apple
    void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(mBitmapApple,
                location.x * mSize, location.y * mSize, paint);

    }

}