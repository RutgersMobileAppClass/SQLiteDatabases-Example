package com.example.instructor.sqlexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by instructor on 10/21/2016.
 */
public class DictionaryHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDb.db";
    public static final String TABLE_NAME = "dictionary";
    public static final String DATABASE_COLUMN_ID = "id";
    public static final String DATABASE_COLUMN_WORD = "word";
    public static final String DATABASE_COLUMN_DEFINITION ="definition";


    public DictionaryHelper(Context context) {
        // Superclass method to handle the database construction
        // Don't concern with the null; that's just a CursorFactory we don't use
        // And 1 is the first version number of our database
        // Context is application context of the activity that called us
        super(context, DATABASE_NAME, null, 1);
    }

    public void addEntry(String word, String definition){
        // we are grabbing a reference to a cached version of the DB we created
        // in onCreate
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DATABASE_COLUMN_WORD,word);
        cv.put(DATABASE_COLUMN_DEFINITION,definition);
        db.insert(TABLE_NAME,null,cv);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // In here is where we manage any type of table creation
        // that we need to do

        String query = "CREATE TABLE IF NOT EXISTS " +TABLE_NAME + " (" + DATABASE_COLUMN_ID + "  UNSIGNED NOT NULL PRIMARY KEY," +
                        DATABASE_COLUMN_WORD + " text," + DATABASE_COLUMN_DEFINITION + " text);" ;

        db.execSQL(query);


    }

    public ArrayList<String> getEntireColumn(String columnName){
        ArrayList<String> values = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        do{
            String value = (String) cursor.getString(cursor.getColumnIndex(columnName));
            values.add(value);

        }while(cursor.moveToNext());

        return values;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // To-do: nothing for us
    }
}
