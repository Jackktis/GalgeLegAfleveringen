package com.example.galgelegafleveringen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Tabt extends AppCompatActivity {
    String rigtigtOrd;
    TextView TWRigtigtOrd;
    Button BTPrøvIgen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabt);

        rigtigtOrd = getIntent().getExtras().getString("orderet");
        TWRigtigtOrd = findViewById(R.id.TWDetRigtigOrd);
        BTPrøvIgen = findViewById(R.id.BTPrøvIgen);
        TWRigtigtOrd.setText(rigtigtOrd);

        BTPrøvIgen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tabt.this, Start.class));
                finish();
            }
        });
    }
}