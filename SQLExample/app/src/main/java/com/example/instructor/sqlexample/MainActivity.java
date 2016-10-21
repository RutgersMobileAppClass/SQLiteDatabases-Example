package com.example.instructor.sqlexample;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // our helper, long may he live
        DictionaryHelper helper = new DictionaryHelper(this);

        // This is the only time we will use SQLiteDatabase in an Activity
        SQLiteDatabase db = openOrCreateDatabase(
                "TestDatabase", MODE_PRIVATE, null);

        helper.onCreate(db);
        helper.addEntry("word1","definition1");
        helper.addEntry("word2","definition2");

        ArrayList<String> words = helper.getEntireColumn(helper.DATABASE_COLUMN_WORD);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,words);
        ListView wordsList = (ListView) findViewById(R.id.wordList);
        wordsList.setAdapter(adapter);







    }
}
