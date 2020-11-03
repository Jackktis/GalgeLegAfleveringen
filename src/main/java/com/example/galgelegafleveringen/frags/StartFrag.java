package com.example.galgelegafleveringen.frags;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.galgelegafleveringen.states.GalgeSpilLogikkenState;
import com.example.galgelegafleveringen.MainActivity;
import com.example.galgelegafleveringen.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class StartFrag extends Fragment {
    Button b, BThighscore;
    String Sværhed;
    EditText sværhedgrad;
    TextView TVSværdhedsSkala;
    Executor bgThread = Executors.newSingleThreadExecutor(); // håndtag til en baggrundstråd
    Handler uiThread = new Handler(Looper.getMainLooper());

    MainActivity mainActivity;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState) {

        View rod = i.inflate(R.layout.activity_start, container, false);
        sværhedgrad =(EditText) rod.findViewById(R.id.sværhedsgrad);
        TVSværdhedsSkala = rod.findViewById(R.id.txtSværhedSkala);
        BThighscore = (Button) rod.findViewById(R.id.BTScore);
        mainActivity =(MainActivity) getActivity();


        BThighscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment historikFrag = new HistorikFrag();
                getFragmentManager().beginTransaction().replace(R.id.MainactivityFrame, historikFrag ).addToBackStack(null).commit();
            }
        });




        b = (Button) rod.findViewById(R.id.start);
        b.setOnClickListener(v -> StartSide());

        return rod;
    }
    public void StartSide(){
        Sværhed = sværhedgrad.getText().toString();
        TVSværdhedsSkala.setText("henter...\n" );

        // baground thread der henter dataen fra vores regneark
        bgThread.execute(() -> {
            try {
                mainActivity.getContext().setState(new GalgeSpilLogikkenState());
                mainActivity.getContext().hentOrdFraRegneark(Sværhed);


                if(!(mainActivity.getContext().getMuligeOrd().isEmpty())) {
                    // Hvis der er et ord for denne sværhedsgrad, køre den dette.
                    uiThread.post(() -> {
                        mainActivity.getContext().onEnterState();

                        Fragment spilletFrag = new SpilletFrag();
                        getFragmentManager().beginTransaction().replace(R.id.MainactivityFrame, spilletFrag ).addToBackStack(null).commit();


                       // Intent intent = new Intent(this, Spillet.class);
                       // intent.putExtra("galgeSpilLogik", spillet);
                       // startActivity(intent);
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


