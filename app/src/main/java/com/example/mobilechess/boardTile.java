package com.example.mobilechess;

import android.graphics.Bitmap;
import android.content.Context;
import android.graphics.BitmapFactory;

public class boardTile {
    Bitmap whiteTile;
    Bitmap blackTile;
    String coordinates;

    public boardTile(Context context){
        whiteTile = scaleDown(BitmapFactory.decodeResource(context.getResources(), R.drawable.whitetile), 150, true);
        blackTile = scaleDown(BitmapFactory.decodeResource(context.getResources(), R.drawable.blacktile), 150, true);
    }

    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }
}
