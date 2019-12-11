package com.example.mobilechess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPieceActivity extends AppCompatActivity {

    private static final int ADDED_PIECE_RESULT_CODE = 69;
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

    public void addPiece(View v){
        String pieceName = name.getText().toString();
        String pieceLocation = location.getText().toString();
        String pieceSide = side.getText().toString();

//        validate(pieceName, pieceLocation, pieceSide);

        Intent intent = new Intent();
        intent.putExtra("NAME", pieceName);
        intent.putExtra("LOCATION", pieceLocation);
        intent.putExtra("SIDE", pieceSide);

        setResult(ADDED_PIECE_RESULT_CODE, intent);
        finish();
    }
}
