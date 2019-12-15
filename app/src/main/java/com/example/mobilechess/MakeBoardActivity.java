package com.example.mobilechess;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Square;

import static com.example.mobilechess.ChessGame.scaleDown;

public class MakeBoardActivity extends AppCompatActivity {

    private static final int ADD_PIECE_REQUEST_CODE = 90;
    private static final int ADDED_PIECE_RESULT_CODE = 69;
    private LinearLayout PieceLayout;
    private RelativeLayout boardLayout;
    private Board board;
    private EditText board_name;
    private Integer quantity;
    CreatedChessGame creation;
    Board newBoard = new Board();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_board);

        PieceLayout = findViewById(R.id.PieceLayout);
        board = new Board();
        board_name = findViewById(R.id.board_name);
        boardLayout = findViewById(R.id.boardLayout);
        creation = new CreatedChessGame(this);
        boardLayout.addView(creation);
    }


    public void addPiece(View v){
        Intent intent = new Intent(this, AddPieceActivity.class);
        startActivityForResult(intent, ADD_PIECE_REQUEST_CODE);
    }

    public void SaveToDB(View v){
        String FEN = newBoard.getFen();
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
        newBoard.clear();
        if (requestCode == ADD_PIECE_REQUEST_CODE)
        {
            if(resultCode == ADDED_PIECE_RESULT_CODE)
            {
                String piece = data.getStringExtra("NAME");
                String location = data.getStringExtra("LOCATION");
                String pieceSide = data.getStringExtra("SIDE");
                Toast.makeText(this, "OH YEH ADDED " + piece +" to " + location, Toast.LENGTH_SHORT).show();

                newBoard.setPiece(Piece.valueOf(piece), Square.valueOf(location));
            }
        }
    }

    public void savePiece(int resultCode, Intent data){
        String pieceName = data.getStringExtra("NAME");
        String pieceLocation = data.getStringExtra("LOCATION");



        if (resultCode == ADDED_PIECE_RESULT_CODE){
//            code to add piece details to layout

        } else {
            Toast.makeText(this, "putragis", Toast.LENGTH_SHORT);
        }
    }
}
