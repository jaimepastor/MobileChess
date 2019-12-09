package com.example.mobilechess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Square;

public class MainActivity extends AppCompatActivity {

    ChessBoard chessBoard;
    RelativeLayout layout;
    TextView boardPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boardPosition = findViewById(R.id.boardPosition);

//        chessBoard = new ChessBoard(this);
//        layout = new RelativeLayout(this);
//
//        layout.addView(chessBoard);
//        setContentView(layout);
    }

    public void startGame(View v){
        Intent intent = new Intent(this, MainGame.class);
        startActivity(intent);
    }

    public void makeBoard(View v){
        Intent intent = new Intent (this, MakeBoardActivity.class);
        startActivity(intent);
    }

    public void test(View v){
        Board board = new Board();
        board.clear();
        board.setPiece(Piece.BLACK_KING, Square.A1);

        boardPosition.setText(board.getFen());
    }
}
