package com.example.mobilechess.Load_Board;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilechess.MainGame;
import com.example.mobilechess.R;

public class BoardHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView board_name;
    private TextView fen_string;
    public BoardHolder(View view){
        super(view);

        board_name = view.findViewById(R.id.board_item_name);
        fen_string = view.findViewById(R.id.board_item_fenString);

        view.setOnClickListener(this);
    }

    public void setName(String name) {
        board_name.setText(name);
    }

    public void setFEN(String FEN) {
        fen_string.setText(FEN);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), MainGame.class);
        intent.putExtra("FEN", fen_string.getText().toString());
        v.getContext().startActivity(intent);
    }
}
