package com.example.galgelegafleveringen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Vundet extends AppCompatActivity {
    String navn, orderet;
    int forsøg;
    TextView spillerenForsøg, tillykke;
    EditText spillerensNavn;
    Button BTscoreBoard;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vundet);
        forsøg = getIntent().getExtras().getInt("forsøg");
        orderet = getIntent().getExtras().getString("orderet");
        spillerenForsøg = findViewById(R.id.TWgættetOrd);
        BTscoreBoard = findViewById(R.id.BTtilScoreBoard);
        spillerensNavn = findViewById(R.id.editTextTextPersonName);
        tillykke = findViewById(R.id.TILLYKKE);

        spillerenForsøg.setText(String.valueOf(forsøg));

        BTscoreBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navn = spillerensNavn.getText().toString();
                if(!(navn.isEmpty())) {
                    saveDataTo();
                } else {
                    tillykke.setText("indtast nu bare dit navn...\n " );
                }

            }
        });
    }

    public void saveDataTo(){
        sp = getSharedPreferences("vinderPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        int gamesCount = sp.getInt("gamesCount", 0);

        editor.putString("navn_gamesCount"+gamesCount, navn);
        editor.putInt("antalForkerte_gamesCount"+gamesCount, forsøg);
        editor.putString("orderet_gameCount"+gamesCount, orderet);
        editor.putInt("gamesCount", gamesCount+1);
        editor.apply();

        Toast.makeText(Vundet.this, navn + " er gemt i score", Toast.LENGTH_LONG).show();

        startActivity(new Intent(Vundet.this, Start.class));
        finish();
    }
}