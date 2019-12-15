package com.example.mobilechess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mobilechess.Load_Board.LoadBoardActivity;
import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Square;

public class MainActivity extends AppCompatActivity {


    TextView boardPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//        layout.addView(chessBoard);
        setContentView(R.layout.activity_main);
//        setContentView(layout);
    }

    public void startGame(View v){
        Intent intent = new Intent(this, LoadBoardActivity.class);
        startActivity(intent);
    }

    public void makeBoard(View v){
        Intent intent = new Intent (this, MakeBoardActivity.class);
        startActivity(intent);
    }

}
