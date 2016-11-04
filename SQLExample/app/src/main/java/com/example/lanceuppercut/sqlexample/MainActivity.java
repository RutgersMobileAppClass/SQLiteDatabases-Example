package com.example.lanceuppercut.sqlexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate our helper
        // NOTE: There is no DB reference!
        SQLExampleHelper helper = new SQLExampleHelper(this);
        helper.insertEntry("test1","test2");
        helper.insertEntry("test3","test4");

        // This is for testing if the objects are printing to the screen properly

        ArrayList<String> array = helper.getEntireColumn(SQLExampleHelper.DICTIONARY_COLUMN_ID);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
        ListView wordList = (ListView) findViewById(R.id.wordList);
        wordList.setAdapter(adapter);




    }

    public void startClick(View view) {
    }


}
