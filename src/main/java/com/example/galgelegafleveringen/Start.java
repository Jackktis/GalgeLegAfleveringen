package com.example.galgelegafleveringen;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Start extends AppCompatActivity  {
    Button b, BThighscore;
    String Sværhed, orderet;
    EditText sværhedgrad;
    TextView TVSværdhedsSkala;
    Executor bgThread = Executors.newSingleThreadExecutor(); // håndtag til en baggrundstråd
    Handler uiThread = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        sværhedgrad =(EditText) findViewById(R.id.sværhedsgrad);
        TVSværdhedsSkala = findViewById(R.id.txtSværhedSkala);

        BThighscore = (Button) findViewById(R.id.BTScore);
        BThighscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Start.this, Scoreboard.class);
                startActivity(intent);
            }
        });




        b = (Button) findViewById(R.id.start);
        b.setOnClickListener(v -> StartSide());
    }
    public void StartSide(){
        Sværhed = sværhedgrad.getText().toString();
        TVSværdhedsSkala.setText("henter...\n" );

        // baground thread der henter dataen fra vores regneark
        bgThread.execute(() -> {
            try {
                GalgeSpilLogikken spillet = new GalgeSpilLogikken();
                spillet.hentOrdFraRegneark(Sværhed);

                if(!(spillet.getMuligeOrd().isEmpty())) {
                    // Hvis der er et ord for denne sværhedsgrad, køre den dette.
                    uiThread.post(() -> {
                        spillet.startNytSpil();
                        Intent intent = new Intent(this, Spillet.class);
                        intent.putExtra("galgeSpilLogik", spillet);
                        startActivity(intent);
                    });
                }else{
                    TVSværdhedsSkala.setText("Ingen ord i denne sværhedsgrad...\n " );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}