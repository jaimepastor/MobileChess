package com.example.mobilechess.Load_Board;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.mobilechess.R;

public class LoadBoardActivity extends AppCompatActivity {

    private LinearLayout innerLayout;
    private RecyclerView list_of_boards;
    private RecyclerView.LayoutManager manager;
    private BoardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_board);

        list_of_boards = findViewById(R.id.listOfBoards);

        manager = new LinearLayoutManager(this);
        list_of_boards.setLayoutManager(manager);

        adapter = new BoardAdapter(this);
        list_of_boards.setAdapter(adapter);

    }



}
