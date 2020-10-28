package com.example.galgelegafleveringen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Start extends AppCompatActivity {
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        b = (Button) findViewById(R.id.start);
        b.setOnClickListener(v -> StartSide());
    }
    public void StartSide(){
        Intent intent = new Intent(this, Spillet.class);
        startActivity(intent);
    }
}
