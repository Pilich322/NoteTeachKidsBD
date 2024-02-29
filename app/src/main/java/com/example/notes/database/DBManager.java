package com.example.notes.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.lights.LightsManager;

import com.example.notes.Notes;

import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private Context context;
    private DBHelper dbHelper;

    SQLiteDatabase db;

    public DBManager(Context context){
        this.context = context;
        dbHelper = new DBHelper(this.context);
    }

    @SuppressLint("Range")
    public List<Notes> getNotes(){
        List<Notes> notes = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from "+ DBConstant.NOTES_TABLE_NAME,null);
        while (cursor.moveToNext()){
            Notes note = new Notes();
            note.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBConstant.NOTES_ID))));
            note.setText(cursor.getString(cursor.getColumnIndex(DBConstant.NOTES_TEXT)));
            note.setTitle(cursor.getString(cursor.getColumnIndex(DBConstant.NOTES_TITLE)));
            notes.add(note);
        }
        cursor.close();
        return notes;
    }

    public void addNotes(Notes notes){
        ContentValues cv = new ContentValues();
        cv.put(DBConstant.NOTES_TITLE,notes.getTitle());
        cv.put(DBConstant.NOTES_TEXT,notes.getText());
        db.insert(DBConstant.NOTES_TABLE_NAME,null,cv);
    }
    public void updateNotes(Notes notes){
        ContentValues cv = new ContentValues();
        cv.put(DBConstant.NOTES_TITLE,notes.getTitle());
        cv.put(DBConstant.NOTES_TEXT,notes.getText());
        db.update(DBConstant.NOTES_TABLE_NAME,cv,
                DBConstant.NOTES_ID + " = " + notes.getId(),null);
    }
    public void deleteNotes(Notes notes){
        db.delete(DBConstant.NOTES_TABLE_NAME,
                DBConstant.NOTES_ID + " = " + notes.getId(),null);
    }

    public void openDb() {
        db = dbHelper.getWritableDatabase();
    }

    public void closeDb() {
        db.close();
    }

}
