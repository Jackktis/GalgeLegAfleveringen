package com.example.galgelegafleveringen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.example.galgelegafleveringen.GalgeSpilLogikken;
import java.util.ArrayList;

public class Spillet extends AppCompatActivity {


    Executor bgThread = Executors.newSingleThreadExecutor(); // håndtag til en baggrundstråd
    Handler handler = new Handler();
    String svaerhedsgrad, gaetOrdet;
    TextView TVSvaerhedsgrad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spillet);
        GalgeSpilLogikken spillet = new GalgeSpilLogikken();
        svaerhedsgrad = getIntent().getExtras().getString("sværhed");
        TVSvaerhedsgrad = findViewById(R.id.gaette_ord);


/*
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String gamleTitler = prefs.getString("titler", "(henter, vent et øjeblik)"); // Hent fra prefs
        textView.setText("henter... her er foreløbig de gamle titler:\n" + gamleTitler);

        bgThread.execute(() -> {
            try {
                String rssdata = hentUrl("https://www.version2.dk/it-nyheder/rss");
                String titler = findTitler(rssdata);
                Galgelogik gl = new Galgelogik();
                gl.hentOrdFraDr();  // lidt mere netværk, for et syns skyld

                prefs.edit().putString("titler", titler).apply(); // Gem de nyligt hentede data i prefs
                uiThread.post(() -> {
                    textView.setText("nyeste titler: \n" + titler);
                    progressBar.setVisibility(View.GONE);
                });
            } catch (Exception e) {
                e.printStackTrace();
                uiThread.post(() -> {
                    textView.setText("Der opstod en fejl: \n" + e.getLocalizedMessage());
                    progressBar.setVisibility(View.GONE);
                });
            }
        });
        */
        bgThread.execute(() ->{
        try {
            opgave
            handler.post(spillet.hentOrdFraRegneark(svaerhedsgrad));

          spillet.startNytSpil();
          gaetOrdet = spillet.getOrdet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        TVSvaerhedsgrad.setText(gaetOrdet);



        //Galgelogik spil = new Galgelogik();
       // spil.startNytSpil();
    }
}