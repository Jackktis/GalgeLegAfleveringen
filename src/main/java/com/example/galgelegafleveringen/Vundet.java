package com.example.galgelegafleveringen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Vundet extends AppCompatActivity {
    String spilleren;
    int forsøg;
    TextView spillerenForsøg;
    EditText SpillerensNavn;
    Button tilScoreBoard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vundet);
        forsøg = getIntent().getExtras().getInt("forsøg");
        spillerenForsøg = findViewById(R.id.TWgættetOrd);
        spillerenForsøg.setText(String.valueOf(forsøg));
        tilScoreBoard.findViewById(R.id.BTtilScoreBoard);



    }
}