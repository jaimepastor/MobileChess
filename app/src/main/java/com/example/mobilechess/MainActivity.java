package com.example.mobilechess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    ChessBoard chessBoard;
    RelativeLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
