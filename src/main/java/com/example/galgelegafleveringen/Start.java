package com.example.galgelegafleveringen;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Start extends AppCompatActivity  {
    Button b;
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

        b = (Button) findViewById(R.id.start);
        b.setOnClickListener(v -> StartSide());
    }
    public void StartSide(){
        Sværhed = sværhedgrad.getText().toString();
        TVSværdhedsSkala.setText("henter...\n" );

        bgThread.execute(() -> {
            try {
                GalgeSpilLogikken spillet = new GalgeSpilLogikken();
                spillet.hentOrdFraRegneark(Sværhed);

                if(!(spillet.getMuligeOrd().isEmpty())) {
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