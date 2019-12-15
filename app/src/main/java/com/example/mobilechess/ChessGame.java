package com.example.mobilechess;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveGenerator;
import com.github.bhlangonijr.chesslib.move.MoveGeneratorException;
import com.github.bhlangonijr.chesslib.move.MoveList;

import java.util.ArrayList;

public class ChessGame extends SurfaceView {

    Paint mPaint, paint;
    Bitmap chessboard = scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.chessboard), 1050, true);
    float x, y;
    boolean touched = false, moved = false, touch = false, move = false, legal = false, check = false, staleMate = false, checkMate = false, draw = false;
    float width = 130;
    float height = 130;
    float boardx = 20, boardy = 200;
    Piece[] tempP;
    Board board;
    Square[][] tiles;
    MoveList moves;

    SurfaceHolder holder;
    ArrayList<Bitmap> pieces = new ArrayList<>();

    public ChessGame(Context context) {
        super(context);
        x = y = 0;
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(75);


        board = new Board();
        try {
            moves = MoveGenerator.generateLegalMoves(board);
        } catch (MoveGeneratorException e) {
            e.printStackTrace();
        }
        //Adding bitmap to each chess piece
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
        tiles = new Square[8][8];
        //initialize tiles


        tiles[0][0] = Square.A1;
        tiles[0][1] = Square.A2;
        tiles[0][2] = Square.A3;
        tiles[0][3] = Square.A4;
        tiles[0][4] = Square.A5;
        tiles[0][5] = Square.A6;
        tiles[0][6] = Square.A7;
        tiles[0][7] = Square.A8;
        tiles[1][0] = Square.B1;
        tiles[1][1] = Square.B2;
        tiles[1][2] = Square.B3;
        tiles[1][3] = Square.B4;
        tiles[1][4] = Square.B5;
        tiles[1][5] = Square.B6;
        tiles[1][6] = Square.B7;
        tiles[1][7] = Square.B8;
        tiles[2][0] = Square.C1;
        tiles[2][1] = Square.C2;
        tiles[2][2] = Square.C3;
        tiles[2][3] = Square.C4;
        tiles[2][4] = Square.C5;
        tiles[2][5] = Square.C6;
        tiles[2][6] = Square.C7;
        tiles[2][7] = Square.C8;
        tiles[3][0] = Square.D1;
        tiles[3][1] = Square.D2;
        tiles[3][2] = Square.D3;
        tiles[3][3] = Square.D4;
        tiles[3][4] = Square.D5;
        tiles[3][5] = Square.D6;
        tiles[3][6] = Square.D7;
        tiles[3][7] = Square.D8;
        tiles[4][0] = Square.E1;
        tiles[4][1] = Square.E2;
        tiles[4][2] = Square.E3;
        tiles[4][3] = Square.E4;
        tiles[4][4] = Square.E5;
        tiles[4][5] = Square.E6;
        tiles[4][6] = Square.E7;
        tiles[4][7] = Square.E8;
        tiles[5][0] = Square.F1;
        tiles[5][1] = Square.F2;
        tiles[5][2] = Square.F3;
        tiles[5][3] = Square.F4;
        tiles[5][4] = Square.F5;
        tiles[5][5] = Square.F6;
        tiles[5][6] = Square.F7;
        tiles[5][7] = Square.F8;
        tiles[6][0] = Square.G1;
        tiles[6][1] = Square.G2;
        tiles[6][2] = Square.G3;
        tiles[6][3] = Square.G4;
        tiles[6][4] = Square.G5;
        tiles[6][5] = Square.G6;
        tiles[6][6] = Square.G7;
        tiles[6][7] = Square.G8;
        tiles[7][0] = Square.H1;
        tiles[7][1] = Square.H2;
        tiles[7][2] = Square.H3;
        tiles[7][3] = Square.H4;
        tiles[7][4] = Square.H5;
        tiles[7][5] = Square.H6;
        tiles[7][6] = Square.H7;
        tiles[7][7] = Square.H8;





        tempP = board.boardToArray();

        holder = getHolder();
        setBackground(getResources().getDrawable(R.drawable.chessbg));

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                board = new Board();
                //Canvas canvas = holder.lockCanvas(null);
                setWillNotDraw(false);
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
        tempP = board.boardToArray();
        boardx = 20;
        boardy = 200;

        canvas.drawText("Turn: " + board.getSideToMove().value(), 20, 100, paint);
        if(checkMate){
            canvas.drawText("CHECKMATE " + board.getSideToMove().flip().value() + " WINS!!", 20, 1500, paint);
            canvas.drawText("GO BACK", 20, 1700, paint);
            Toast.makeText(getContext(), "CHECKMATE", Toast.LENGTH_SHORT).show();
        } else if (draw){
            canvas.drawText("DRAW", 20, 1500, paint);
        } else if (staleMate){
            canvas.drawText("DRAW", 20, 1500, paint);
        } else if(check){
            canvas.drawText("CHECKED", 20, 175, paint);
        }

        canvas.drawBitmap(chessboard,20,200,null);
        //Displaying each chess pieces
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


        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN: {
                if(event.getX() >= 20 && event.getY() >= 200 && event.getX() <= 1060 && event.getY() <= 1220){
                    if(touch){
                        int x1, x2 = 0, y1, y2 = 0;
                        x1 = (int) (x-20)/130;
                        y1 = (int) (y-200)/130;
                        if (event.getX() >= 20 && event.getY() >= 200 && event.getX() <= 1060 && event.getY() <= 1220) {
                            for (int ctrx = 120; ctrx <= 1160; ctrx += width) {
                                for (int ctry = 300; ctry <= 1340; ctry += height) {
                                    if (event.getX() <= ctrx && event.getY() <= ctry && event.getX() >= ctrx - 130 && event.getY() >= ctry - 130) {
                                        x2 = ctrx - 100;
                                        y2 = ctry - 100;
                                    }
                                }
                            }
                        }
                        x2 = (int) (x2-20)/130;
                        y2 = (int) (y2-200)/130;


                        for(Move move : moves){
                            if(new Move(tiles[x1][y1], tiles[x2][y2]).equals(move)){
                                legal = true;
                            }
                        }

                        if(legal){
                            board.doMove(new Move(tiles[x1][y1], tiles[x2][y2]));
                        }

                        try {
                            moves = MoveGenerator.generateLegalMoves(board);
                        } catch (MoveGeneratorException e) {
                            e.printStackTrace();
                        }

                        if(board.isMated()){
                            checkMate = true;
                        }else if(board.isKingAttacked()){
                            check = true;
                        }else if(board.isStaleMate()){
                            staleMate = true;
                        }else if(board.isDraw()){
                            draw = true;
                        } else{
                            check = false;
                        }

                        legal = false;
                        touch = false;
                        touched = true;
                        invalidate();
                        break;
                    }else {
                        if(!(String.valueOf(board.getPiece(tiles[(int) ((event.getX() - 20) / 130)][(int) ((event.getY() - 200) / 130)])) == "NONE")){
                            touched = true;
                            touch = true;
                            x = event.getX();
                            y = event.getY();

                            if (x >= 20 && y >= 200 && x <= 1060 && y <= 1240) {
                                for (int ctrx = 120; ctrx <= 1160; ctrx += width) {
                                    for (int ctry = 300; ctry <= 1340; ctry += height) {
                                        if (x <= ctrx && y <= ctry && x >= ctrx - 130 && y >= ctry - 130) {
                                            x = ctrx - 100;
                                            y = ctry - 100;
                                        }
                                    }
                                }
                                invalidate();
                                break;
                            }
                        }
                    }
                }else if(event.getX() >= 20 && event.getY() >= 1700 && checkMate){
                    ((Activity) getContext()).finish();
                }


            }
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
