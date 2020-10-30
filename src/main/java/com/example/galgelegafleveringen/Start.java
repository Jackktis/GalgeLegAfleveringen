package com.example.galgelegafleveringen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Start extends AppCompatActivity {
    Button b;
    String Sværhed;
    EditText sværhedgrad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        sværhedgrad =(EditText) findViewById(R.id.sværhedsgrad);

        b = (Button) findViewById(R.id.start);
        b.setOnClickListener(v -> StartSide());
    }
    public void StartSide(){
        Sværhed = sværhedgrad.getText().toString();
        Intent intent = new Intent(this, Spillet.class);
        intent.putExtra("sværhed",Sværhed);
        startActivity(intent);
    }
}
