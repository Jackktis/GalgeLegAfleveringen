package com.example.galgelegafleveringen;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.galgelegafleveringen.GalgeSpilLogikken;


import java.util.ArrayList;

public class Spillet extends AppCompatActivity {

    String svaerhedsgrad, gaetOrdet;
    TextView TVSvaerhedsgrad;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getI;
        setContentView(R.layout.activity_spillet);
        svaerhedsgrad = getIntent().getExtras().getString("sværhed");

        try {
          spillet.hentOrdFraRegneark(svaerhedsgrad);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //Galgelogik spil = new Galgelogik();
       // spil.startNytSpil();
    }
}