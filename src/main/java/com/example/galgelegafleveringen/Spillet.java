package com.example.galgelegafleveringen;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.galgelegafleveringen.Galgelogik;

public class Spillet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spillet);

        Galgelogik spil = new Galgelogik();
        spil.startNytSpil();
    }
}