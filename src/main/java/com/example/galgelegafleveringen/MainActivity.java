package com.example.galgelegafleveringen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.galgelegafleveringen.frags.StartFrag;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = new Context(this);

        FrameLayout frameLayout = findViewById(R.id.MainactivityFrame);
        Fragment startFragment = new StartFrag();

        if (savedInstanceState==null) getSupportFragmentManager().beginTransaction().add(R.id.MainactivityFrame,startFragment).commit();
    }

    public Context getContext(){return context;}
}