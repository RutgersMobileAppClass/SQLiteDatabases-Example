package com.example.lanceuppercut.sqlexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
/**
 * Created by lanceuppercut on 10/21/2016.
 */


// Our helper class used to make interacting with the DB easier
public class SQLExampleHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ExampleDB.db";
    public static final String DICTIONARY_TABLE_NAME = "dictionary";
    public static final String DICTIONARY_COLUMN_ID = "id";
    public static final String DICTIONARY_COLUMN_WORD =  "word";
    public static final String DICTIONARY_COLUMN_DEFINTION = "definition";


    // Superclass constructor that requires the application context.
    // Note: you can modify it and pass your own DB name.
    // However, I didn't do it that way. I left it embedded in the helper.
    public SQLExampleHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    // OnCreate method. Called when helper is created for the first time.
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Note: CREATE TABLE IF NOT EXISTS might be better, right?
        // Otherwise, run risk of deleting your table.
        // Think about that whne doing your HW.

        // Note: do not use AUTO INCREMENT on a primary key integer
        db.execSQL("CREATE TABLE " + DICTIONARY_TABLE_NAME + " (" + DICTIONARY_COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                    DICTIONARY_COLUMN_WORD + " text, " + DICTIONARY_COLUMN_DEFINTION + " text);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Do stuff
        // Generally in here you want some event handling for the new versions
        // However, I do not anticipate needing this for your assignment
        // For your project, maybe? Look into it if need be.
    }

    // Custom method to insert stuff
    public void insertEntry(String word, String definition){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(DICTIONARY_COLUMN_WORD,word);
        content.put(DICTIONARY_COLUMN_DEFINTION,definition);
        db.insert(DICTIONARY_TABLE_NAME,null,content);
    }

    // Custom method to return entries
    public Cursor getEntryById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DICTIONARY_TABLE_NAME + " WHERE " + DICTIONARY_COLUMN_ID + "=" + id,null);
        return cursor;
    }

    // Custom method to retrieve data
    public ArrayList<String> getEntireColumn(String columnName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DICTIONARY_TABLE_NAME,null);
        cursor.moveToFirst();

        ArrayList<String> values = new ArrayList<>();

        do{
            String value = (String) cursor.getString(cursor.getColumnIndex(columnName));
            values.add(value);


        }while (cursor.moveToNext());

        return values;
    }

    // Custom SQL query
    public Cursor customQuery(String query){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

}
