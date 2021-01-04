package com.example.galgelegafleveringen.frags;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import androidx.fragment.app.Fragment;

import com.example.galgelegafleveringen.MainActivity;
import com.example.galgelegafleveringen.R;

public class ScoreFrag extends Fragment {
    SharedPreferences sp;
    String[] highscoreArr;
    ListView listView;
    String navn, orderet;
    int forkerte;

    MainActivity mainActivity;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState) {

        View rod = i.inflate(R.layout.activity_scoreboard, container, false);
        mainActivity = (MainActivity) getActivity();

        sp = mainActivity.getApplicationContext().getSharedPreferences("highscoreVinderPrefs", Context.MODE_PRIVATE);
        int gamesCount = sp.getInt("gamesCount", 0);
        if(gamesCount > 5) {
            gamesCount = 5;
        }
        highscoreArr = new String[gamesCount];
        if(!(gamesCount == 0)){
            for (int j = 0; j <gamesCount ;j++) {


                    navn = sp.getString("navn_gamesCount" + j, "(Navn)");
                    forkerte = sp.getInt("antalForkerte_gamesCount" + j, 0);
                    orderet = sp.getString("orderet_gameCount" + j, "(orderet)");

                    highscoreArr[j] = navn + " havde " + forkerte + " forkerte ord" + "\n" + "Orderet var: " + orderet;
            }
        }

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, highscoreArr);
        listView = rod.findViewById(R.id.Highscoreindhold);
        listView.setAdapter(adapter);

        return rod;
    }
}
