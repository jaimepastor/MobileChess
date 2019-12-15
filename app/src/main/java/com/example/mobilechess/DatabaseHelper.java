package com.example.mobilechess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper db_instance;
    private static final String DATABASE_NAME = "boards.db";
    private static final String BOARDS_TABLE = "Boards";
    private static final String COL_1 = "board_ID";
    private static final String COL_2 = "board_name";
    private static final String COL_3 = "fen_STRING";

    public static synchronized DatabaseHelper getInstance(Context context){
        if(db_instance == null){
            db_instance = new DatabaseHelper(context.getApplicationContext());
        }
        return db_instance;
    }

    private DatabaseHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + BOARDS_TABLE + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_2 + " TEXT, " + COL_3 + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BOARDS_TABLE);
        onCreate(db);
    }

    public boolean insertData(String board_name, String fen_STRING){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, board_name);
        contentValues.put(COL_3, fen_STRING);

        long result = db.insert(BOARDS_TABLE, null, contentValues);
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + BOARDS_TABLE, null);
        return result;
    }

    public boolean updateData(String board_id, String board_name, String fen_string){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, board_id);
        contentValues.put(COL_2, board_name);
        contentValues.put(COL_3, fen_string);

        db.update(BOARDS_TABLE, contentValues, "user_ID = ?", new String[]{board_id});
        return true;
    }

    public Integer deleteData(String user_id){
        SQLiteDatabase db = this.getWritableDatabase();

        Integer rows = db.delete(BOARDS_TABLE, "board_ID = ?", new String[]{user_id});

        return rows;
    }
}
