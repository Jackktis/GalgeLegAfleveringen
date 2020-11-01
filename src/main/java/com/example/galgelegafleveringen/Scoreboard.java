package com.example.galgelegafleveringen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Scoreboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        String[] lande = {"Danmark", "Norge", "Sverige", "Finland", "Holland", "Italien", "Tyskland",
                "Frankrig", "Spanien", "Portugal", "Nepal", "Indien", "Kina", "Japan", "Thailand"};


        ArrayAdapter adapter = new ArrayAdapter(Scoreboard.this, android.R.layout.simple_list_item_1, android.R.id.text1, lande);

       // ListView listView = new ListView(Scoreboard.this);
        //listView.setAdapter(adapter);

       // setContentView(listView);
    }


}