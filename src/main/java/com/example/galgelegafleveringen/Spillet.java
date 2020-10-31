package com.example.galgelegafleveringen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.example.galgelegafleveringen.GalgeSpilLogikken;
import java.util.ArrayList;

public class Spillet extends AppCompatActivity {


    Executor bgThread = Executors.newSingleThreadExecutor(); // håndtag til en baggrundstråd
    Handler uiThread = new Handler(Looper.getMainLooper());
    String svaerhedsgrad, gaetOrdet;
    TextView TVSvaerhedsgrad;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spillet);

        svaerhedsgrad = getIntent().getExtras().getString("sværhed");
        TVSvaerhedsgrad = findViewById(R.id.gaette_ord);
        TVSvaerhedsgrad.setText("henter...\n" );

        bgThread.execute(() -> {
            try {
                GalgeSpilLogikken spillet = new GalgeSpilLogikken();
                spillet.hentOrdFraRegneark(svaerhedsgrad);

                uiThread.post(() -> {
                    spillet.startNytSpil();
                    spillet.opdaterSynligtOrd();
                    spillet.logStatus();
                    TVSvaerhedsgrad.setText(gaetOrdet);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


    }
}