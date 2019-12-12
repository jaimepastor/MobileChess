package com.example.mobilechess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddPieceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int ADDED_PIECE_RESULT_CODE = 69;
    String piece_name, piece_location, piece_side;
    Button savePiece;
    Spinner piece_name_spinner, piece_location_spinner, piece_side_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_piece);

        piece_name_spinner = findViewById(R.id.piece_name_spinner);
        ArrayAdapter<CharSequence> piece_name_adapter = ArrayAdapter.createFromResource(this, R.array.piece_names, android.R.layout.simple_spinner_item);
        piece_name_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        piece_name_spinner.setAdapter(piece_name_adapter);
        piece_name_spinner.setOnItemSelectedListener(this);

        piece_location_spinner = findViewById(R.id.piece_location_spinner);
        ArrayAdapter<CharSequence> piece_location_adapter = ArrayAdapter.createFromResource(this, R.array.piece_locations, android.R.layout.simple_spinner_item);
        piece_location_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        piece_location_spinner.setAdapter(piece_location_adapter);
        piece_location_spinner.setOnItemSelectedListener(this);

        piece_side_spinner = findViewById(R.id.piece_side_spinner);
        ArrayAdapter<CharSequence> piece_side_adapter = ArrayAdapter.createFromResource(this, R.array.piece_sides, android.R.layout.simple_spinner_item);
        piece_location_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        piece_side_spinner.setAdapter(piece_side_adapter);
        piece_side_spinner.setOnItemSelectedListener(this);
    }

    public void addPiece(View v){

        Intent intent = new Intent();
        intent.putExtra("NAME", piece_name);
        intent.putExtra("LOCATION", piece_location);
        intent.putExtra("SIDE", piece_side);

        setResult(ADDED_PIECE_RESULT_CODE, intent);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() == R.id.piece_name_spinner){
            piece_name = parent.getItemAtPosition(position).toString();
            Toast.makeText(this, piece_name, Toast.LENGTH_LONG).show();
        } else if(parent.getId() == R.id.piece_location_spinner){
            piece_location = parent.getItemAtPosition(position).toString();
            Toast.makeText(this, piece_location, Toast.LENGTH_LONG).show();
        } else if(parent.getId() == R.id.piece_side_spinner){
            piece_side = parent.getItemAtPosition(position).toString();
            Toast.makeText(this, piece_side, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
