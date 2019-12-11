package com.example.mobilechess;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.bhlangonijr.chesslib.Board;

public class MakeBoardActivity extends AppCompatActivity {

    private static final int ADD_PIECE_REQUEST_CODE = 90;
    private static final int ADDED_PIECE_RESULT_CODE = 69;
    private LinearLayout PieceLayout;
    private Board board;
    private EditText board_name;
    private Integer quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_board);

        PieceLayout = findViewById(R.id.PieceLayout);
        board = new Board();
        board_name = findViewById(R.id.board_name);
    }

    public void addPiece(View v){
        Intent intent = new Intent(this, AddPieceActivity.class);
        startActivityForResult(intent, ADD_PIECE_REQUEST_CODE);
    }

    public void SaveToDB(View v){
        String FEN = board.getFen();
        String board_name = this.board_name.getText().toString();

        boolean result = DatabaseHelper.getInstance(this).insertData(board_name, FEN);

        if(result){
            Toast.makeText(this, "Saved to Database!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Not saved to Database", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case ADD_PIECE_REQUEST_CODE : checkPiece(resultCode, data);
                    break;
            default : Toast.makeText(this, "whatdog", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkPiece(int resultCode, Intent data){
        String pieceName = data.getStringExtra("NAME");
        String pieceLocation = data.getStringExtra("LOCATION");
        String pieceSide = data.getStringExtra("SIDE");

        if (resultCode == ADDED_PIECE_RESULT_CODE){
//            code to add piece details to layout
        } else {
            Toast.makeText(this, "putragis", Toast.LENGTH_SHORT);
        }
    }
}
