package com.example.mobilechess;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChessGame extends SurfaceView {

    Paint mPaint;
    Bitmap chessboard = scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.chessboard), 1050, true);
    float x, y;
    boolean touched = false;
    float width = 130;
    float height = 130;

    SurfaceHolder holder;
    ArrayList<Bitmap> pieces = new ArrayList<>();

    public ChessGame(Context context){
        super(context);
        x = y = 0;
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);





        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.black_bishop), 250, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.black_king), 250, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.black_knight), 250, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.black_pawn), 250, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.black_queen), 250, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.black_rook), 250, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.white_bishop), 250, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.white_king), 250, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.white_knight), 250, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.white_pawn), 250, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.white_queen), 250, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.white_rook), 250, true));
        Board_Class board_obj = new Board_Class();

        board_obj.set_chess_board();
        board_obj.show_board();



        holder = getHolder();
        setBackground(getResources().getDrawable(R.drawable.chessbg));

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });





    }

    public void onDraw(Canvas canvas){

        canvas.drawBitmap(chessboard,20,200,null);
        if(touched){
            canvas.drawRect(x , y, x+width, y+height, mPaint);
        }





    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        touched = true;
        x = event.getX();
        y = event.getY();

        if(x >= 20 && y >= 200){
            invalidate();
        }





        return true;
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
