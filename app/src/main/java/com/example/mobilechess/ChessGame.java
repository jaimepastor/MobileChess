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

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;

import java.util.ArrayList;

public class ChessGame extends SurfaceView {

    Paint mPaint;
    Game_Class game = new Game_Class();
    Bitmap chessboard = scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.chessboard), 1050, true);
    float x, y;
    Board_Class board_obj;
    boolean touched = false;
    float width = 130;
    float height = 130;
    float boardx = 20, boardy = 200;
    Piece_Class[][] temp;
    Piece[] tempP;
    Board board;

    SurfaceHolder holder;
    ArrayList<Bitmap> pieces = new ArrayList<>();

    public ChessGame(Context context){
        super(context);
        x = y = 0;
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);

        board = new Board();

        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.black_bishop), 130, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.black_king), 130, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.black_knight), 130, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.black_pawn), 130, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.black_queen), 130, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.black_rook), 130, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.white_bishop), 130, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.white_king), 130, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.white_knight), 130, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.white_pawn), 130, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.white_queen), 130, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.white_rook), 130, true));
        board_obj = new Board_Class();

        board_obj.set_chess_board();
        board_obj.show_board();
        temp = board_obj.get_board();
        tempP = board.boardToArray();

        holder = getHolder();
        setBackground(getResources().getDrawable(R.drawable.chessbg));

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                //Canvas canvas = holder.lockCanvas(null);
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




        for(int x = 0; x<64; x++){
            if(x%8 == 0 && x != 0){
                boardx = 20;
                boardy += 130;
            }

            if(tempP[x].value() == "WHITE_PAWN"){
                canvas.drawBitmap(pieces.get(9),boardx,boardy,null);
            }else if(tempP[x].value() == "BLACK_PAWN"){
                canvas.drawBitmap(pieces.get(3),boardx,boardy,null);
            }else if(tempP[x].value() == "WHITE_ROOK"){
                canvas.drawBitmap(pieces.get(11),boardx,boardy,null);
            }else if(tempP[x].value() == "BLACK_ROOK"){
                canvas.drawBitmap(pieces.get(5),boardx,boardy,null);
            }else if(tempP[x].value() == "WHITE_KNIGHT"){
                canvas.drawBitmap(pieces.get(8),boardx,boardy,null);
            }else if(tempP[x].value() == "BLACK_KNIGHT"){
                canvas.drawBitmap(pieces.get(2),boardx,boardy,null);
            }else if(tempP[x].value() == "WHITE_BISHOP"){
                canvas.drawBitmap(pieces.get(6),boardx,boardy,null);
            }else if(tempP[x].value() == "BLACK_BISHOP"){
                canvas.drawBitmap(pieces.get(0),boardx,boardy,null);
            }else if(tempP[x].value() == "WHITE_QUEEN"){
                canvas.drawBitmap(pieces.get(10),boardx,boardy,null);
            }else if(tempP[x].value() == "BLACK_QUEEN"){
                canvas.drawBitmap(pieces.get(4),boardx,boardy,null);
            }else if(tempP[x].value() == "WHITE_KING"){
                canvas.drawBitmap(pieces.get(7),boardx,boardy,null);
            }else if(tempP[x].value() == "BLACK_KING"){
                canvas.drawBitmap(pieces.get(1),boardx,boardy,null);
            }
            boardx += 130;
        }



        if(touched){
            canvas.drawRect(x , y, x+width, y+height, mPaint);
            boardx = 20;
            boardy = 200;
            setWillNotDraw(false);
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        touched = true;
        x = event.getX();
        y = event.getY();

        if(x >= 20 && y >= 200 && x <= 1060 && y <= 1240){
            for(int ctrx = 120; ctrx <= 1160; ctrx+=width){
                for(int ctry = 300; ctry <= 1340; ctry+=height){
                    if(x <= ctrx && y <= ctry && x >= ctrx-130 && y >= ctry-130){
                        x = ctrx - 100;
                        y = ctry - 100;
                    }
                }
            }
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
