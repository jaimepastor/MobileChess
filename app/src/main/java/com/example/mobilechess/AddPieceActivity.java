package com.example.mobilechess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddPieceActivity extends AppCompatActivity {

    EditText name, location, side;
    Button savePiece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_piece);

        name = findViewById(R.id.Name);
        location = findViewById(R.id.Location);
        side = findViewById(R.id.Side);
    }




}
