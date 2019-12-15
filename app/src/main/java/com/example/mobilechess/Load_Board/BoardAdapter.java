package com.example.mobilechess.Load_Board;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilechess.DatabaseHelper;
import com.example.mobilechess.R;
import com.github.bhlangonijr.chesslib.Board;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardHolder> {

    private DatabaseHelper db;
    private ArrayList<BoardModel> listOfBoards;

    public BoardAdapter(Context context){
        //add default board
        listOfBoards = new ArrayList<>();

        BoardModel defaultBoard = new BoardModel("0", "Default", new Board().getFen());
        listOfBoards.add(defaultBoard);
        notifyItemInserted(listOfBoards.size() - 1);

        BoardModel hatdog = new BoardModel("1", "testing", new Board().getFen());
        listOfBoards.add(hatdog);
        notifyItemInserted(listOfBoards.size() - 1);


        db = DatabaseHelper.getInstance(context);
        Cursor res = db.getAllData();

        if (res.getCount() > 0){
            while(res.moveToNext()){
                listOfBoards.add(new BoardModel(res.getString(0), res.getString(1), res.getString(2)));
                notifyItemInserted(listOfBoards.size() - 1);
            }
        }
    }


    @NonNull
    @Override
    public BoardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.board_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        BoardHolder holder = new BoardHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BoardHolder holder, int position) {
        holder.setName(listOfBoards.get(position).getName());
        holder.setFEN(listOfBoards.get(position).getFen());
    }

    @Override
    public int getItemCount() {
        return listOfBoards.size();
    }
}
