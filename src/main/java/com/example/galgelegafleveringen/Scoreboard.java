package com.example.galgelegafleveringen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

public class Scoreboard extends AppCompatActivity  {
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    SharedPreferences sp;
    String[] highscoreArr;
    ListView listView;
    String navn;
    int forkerte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);


        sp = getApplicationContext().getSharedPreferences("vinderPrefs", Context.MODE_PRIVATE);
        int gamesCount = sp.getInt("gamesCount", 0);

        highscoreArr = new String[gamesCount];
        System.out.println(sp.getAll());


        if(!(gamesCount == 0)){
            for (int i = 0; i <gamesCount ; i++) {
                navn = sp.getString("navn_gamesCount" + i, "(Navn)");
                System.out.println(navn);
                forkerte = sp.getInt("antalForkerte_gamesCount"+i, 0);
                System.out.println(forkerte);

               highscoreArr[i] = navn + " har så mange forkerte: " + forkerte;

            }
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, highscoreArr);
        listView = findViewById(R.id.Highscoreindhold);
        listView.setAdapter(adapter);


    }

}