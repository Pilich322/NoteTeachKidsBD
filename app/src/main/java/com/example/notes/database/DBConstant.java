package com.example.notes.database;

public class DBConstant {
    public static final String DATABASE_NAME = "notes.db";
    public static final int DATABASE_VERSION = 1;

    public static final String NOTES_TABLE_NAME = "notes";
    public static final String NOTES_ID = "id";
    public static final String NOTES_TITLE = "title";
    public static final String NOTES_TEXT = "text";

    public static final String NOTES_CREATE_TABLE = "create table if not exists "
            + NOTES_TABLE_NAME+ " ( " + NOTES_ID + " integer primary key autoincrement, "+
            NOTES_TITLE +" Text, " + NOTES_TEXT +" text ) ";

    public static final String NOTES_DELETE_TABLE = "delete table if exists "+ NOTES_TABLE_NAME;
}
