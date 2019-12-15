package com.example.mobilechess;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainGame extends AppCompatActivity {

    ChessGame chessBoard;
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        chessBoard = new ChessGame(this);
        layout = new RelativeLayout(this);

        layout.addView(chessBoard);
        setContentView(layout);
    }
}
