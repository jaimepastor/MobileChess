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

import java.util.ArrayList;

import static com.example.mobilechess.ChessGame.scaleDown;

public class MakeBoardActivity extends AppCompatActivity {

    private static final int ADD_PIECE_REQUEST_CODE = 90;
    private static final int ADDED_PIECE_RESULT_CODE = 69;
    private LinearLayout PieceLayout;
    private RelativeLayout boardLayout;
    private EditText board_name;
    private Integer quantity;
    CreatedChessGame creation;
    private Board newBoard;
    ArrayList<String> locList = new ArrayList<>();
    int wKing = 0;
    int bKing = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_board);

        PieceLayout = findViewById(R.id.PieceLayout);
        newBoard = new Board();
        newBoard.clear();
        board_name = findViewById(R.id.board_name);
        boardLayout = findViewById(R.id.boardLayout);
        boardLayout.invalidate();
        creation = new CreatedChessGame(this);
        boardLayout.addView(creation);
    }


    public void addPiece(View v) {
        Intent intent = new Intent(this, AddPieceActivity.class);
        startActivityForResult(intent, ADD_PIECE_REQUEST_CODE);
    }

    public void SaveToDB(View v) {
        String FEN = newBoard.getFen();
        String board_name = this.board_name.getText().toString();

        if(wKing == 0 && bKing == 1){
            Toast.makeText(this, "You are missing the White King", Toast.LENGTH_SHORT).show();
        }
        else if(bKing == 0 && wKing == 1){
            Toast.makeText(this, "You are missing the Black King", Toast.LENGTH_SHORT).show();
        }
        else if(bKing+wKing == 0){
            Toast.makeText(this, "You are missing both kings", Toast.LENGTH_SHORT).show();
        }
        else if(bKing + wKing == 2) {
            boolean result = DatabaseHelper.getInstance(this).insertData(board_name, FEN);

            if (result) {
                Toast.makeText(this, "Saved to Database!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Not saved to Database", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_PIECE_REQUEST_CODE) {
            if (resultCode == ADDED_PIECE_RESULT_CODE) {
                String piece = data.getStringExtra("NAME");
                String location = data.getStringExtra("LOCATION");

                if(locList.contains(location)){
                    Toast.makeText(this, "There is a piece already at "+location, Toast.LENGTH_SHORT).show();
                }
                else if(checkOver(piece)){
                    Toast.makeText(this, "You are gonna add too many " + piece+"S", Toast.LENGTH_SHORT).show();
                }
                else {
                    locList.add(location);
                    Toast.makeText(this, "OH YEH ADDED " + piece + " to " + location, Toast.LENGTH_SHORT).show();
                    newBoard.setPiece(Piece.valueOf(piece), Square.valueOf(location));
                    creation.update(Piece.valueOf(piece), Square.valueOf(location));
                }
            }
        }
    }
    protected boolean checkOver(String name){
        int wPawns = 0;
        int bPawns = 0;
        int wKnight = 0;
        int bKnight = 0;
        int wBishop = 0;
        int bBishop = 0;
        int wRook = 0;
        int bRook = 0;
        int wQueen = 0;
        int bQueen = 0;

        if(name.equals("WHITE_PAWN") && wPawns<8){
            wPawns++;
            return false;
        }
        else if(name.equals("BLACK_PAWN") && bPawns<8){
            bPawns++;
            return false;
        }
        else if(name.equals("WHITE_KNIGHT") && wKnight<2){
            wKnight++;
            return false;
        }
        else if(name.equals("BLACK_KNIGHT") && bKnight<2){
            bKnight++;
            return false;
        }
        else if(name.equals("WHITE_BISHOP") && wBishop<2){
            wBishop++;
            return false;
        }
        else if(name.equals("BLACK_BISHOP") && bBishop<2){
            bBishop++;
            return false;
        }
        else if(name.equals("WHITE_ROOK") && wRook<2){
            wRook++;
            return false;
        }
        else if(name.equals("BLACK_ROOK") && bRook<1){
            bRook++;
            return false;
        }
        else if(name.equals("WHITE_QUEEN") && wQueen<1){
            wQueen++;
            return false;
        }
        else if(name.equals("BLACK_QUEEN") && bQueen<1){
            bQueen++;
            return false;
        }
        else if(name.equals("WHITE_KING") && wKing<1){
            wKing++;
            return false;
        }
        else if(name.equals("BLACK_KING") && bKing<1){
            bKing++;
            return false;
        }
        else {
            return true;
        }
    }
}
