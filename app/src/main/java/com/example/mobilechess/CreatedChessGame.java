package com.example.mobilechess;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
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

import com.example.mobilechess.Load_Board.BoardModel;
import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;
import com.github.bhlangonijr.chesslib.move.MoveGenerator;
import com.github.bhlangonijr.chesslib.move.MoveGeneratorException;
import com.github.bhlangonijr.chesslib.move.MoveList;

import java.util.ArrayList;

import static com.example.mobilechess.ChessGame.scaleDown;

public class CreatedChessGame extends SurfaceView {

    Paint mPaint, paint;
    Bitmap chessboard = scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.chessboard), 600, true);
    float x, y;
    float width = 130;
    float height = 130;
    float boardx = 11, boardy = 25;
    Piece[] tempP;
    Board board;
    Square[][] tiles;
    Board newBoard= new Board();

    private DatabaseHelper db;

    SurfaceHolder holder;
    ArrayList<Bitmap> pieces = new ArrayList<>();

    public CreatedChessGame(Context context) {
        super(context);
        x = y = 0;
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);

        paint = new Paint();
        paint.setColor(Color.BLACK);

        db = DatabaseHelper.getInstance(context);
        Cursor res = db.getAllData();
        newBoard.clear();
        if (res.getCount() > 0){
            while(res.moveToNext()){
                newBoard.loadFromFen(res.getString(2));
            }
        }

        //Adding bitmap to each chess piece
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.black_bishop), 74, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.black_king), 74, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.black_knight), 74, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.black_pawn), 74, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.black_queen), 74, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.black_rook), 74, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.white_bishop), 74, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.white_king), 74, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.white_knight), 74, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.white_pawn), 74, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.white_queen), 74, true));
        pieces.add(scaleDown(BitmapFactory.decodeResource(getResources(), R.drawable.white_rook), 74, true));
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


        //tempP = board.boardToArray();

        holder = getHolder();
        setBackgroundColor(Color.WHITE);

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

    public void onDraw(Canvas canvas) {
        tempP = newBoard.boardToArray();
        boardx = 0;
        boardy = 7;

        canvas.drawBitmap(chessboard, 0, 0, null);
        //Displaying each chess pieces
        for (int x = 0; x < 64; x++) {
            if (x % 8 == 0 && x != 0) {
                boardx = 0;
                boardy += 74;
            }

            if (tempP[x].value() == "WHITE_PAWN") {
                canvas.drawBitmap(pieces.get(9), boardx, boardy, null);
            } else if (tempP[x].value() == "BLACK_PAWN") {
                canvas.drawBitmap(pieces.get(3), boardx, boardy, null);
            } else if (tempP[x].value() == "WHITE_ROOK") {
                canvas.drawBitmap(pieces.get(11), boardx, boardy, null);
            } else if (tempP[x].value() == "BLACK_ROOK") {
                canvas.drawBitmap(pieces.get(5), boardx, boardy, null);
            } else if (tempP[x].value() == "WHITE_KNIGHT") {
                canvas.drawBitmap(pieces.get(8), boardx, boardy, null);
            } else if (tempP[x].value() == "BLACK_KNIGHT") {
                canvas.drawBitmap(pieces.get(2), boardx, boardy, null);
            } else if (tempP[x].value() == "WHITE_BISHOP") {
                canvas.drawBitmap(pieces.get(6), boardx, boardy, null);
            } else if (tempP[x].value() == "BLACK_BISHOP") {
                canvas.drawBitmap(pieces.get(0), boardx, boardy, null);
            } else if (tempP[x].value() == "WHITE_QUEEN") {
                canvas.drawBitmap(pieces.get(10), boardx, boardy, null);
            } else if (tempP[x].value() == "BLACK_QUEEN") {
                canvas.drawBitmap(pieces.get(4), boardx, boardy, null);
            } else if (tempP[x].value() == "WHITE_KING") {
                canvas.drawBitmap(pieces.get(7), boardx, boardy, null);
            } else if (tempP[x].value() == "BLACK_KING") {
                canvas.drawBitmap(pieces.get(1), boardx, boardy, null);
            }
            boardx += 75;
        }
    }

    public void update(Piece piece, Square square){
        newBoard.setPiece(piece, square);
        invalidate();
    }
}

