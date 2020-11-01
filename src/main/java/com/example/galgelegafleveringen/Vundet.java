package com.example.galgelegafleveringen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Vundet extends AppCompatActivity {
    String navn;
    int forsøg;
    TextView spillerenForsøg, tillykke;
    EditText spillerensNavn;
    Button BTscoreBoard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vundet);
        forsøg = getIntent().getExtras().getInt("forsøg");
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
                    Intent intent = new Intent(Vundet.this, Scoreboard.class);
                    intent.putExtra("Name", navn);
                    startActivity(intent);
                } else {
                    tillykke.setText("indtast nu bare dit navn...\n " );
                }

            }
        });

    }
}