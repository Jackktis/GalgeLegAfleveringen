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

import java.io.Serializable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.example.galgelegafleveringen.GalgeSpilLogikken;
import java.util.ArrayList;

public class Spillet extends AppCompatActivity {

    String orderet, synligOrd;
    TextView TVgætOrderet;
    GalgeSpilLogikken spillet;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntent().getSerializableExtra("galgeSpilLogik");
        setContentView(R.layout.activity_spillet);
         TVgætOrderet = findViewById(R.id.gaette_ord);

         // vi hiver vores information fra Start ind her.
         Bundle b = getIntent().getExtras();

         try {
             spillet = (GalgeSpilLogikken) b.get("galgeSpilLogik");
             orderet = spillet.getOrdet();

             spillet.opdaterSynligtOrd();
             synligOrd = spillet.getSynligtOrd();
             TVgætOrderet.setText(synligOrd);


             System.out.println("vi har taget dette ord videre: "+ orderet);

         }catch (Exception e){
             System.out.println("logikken blev ikke taget videre");
         }


    }
}