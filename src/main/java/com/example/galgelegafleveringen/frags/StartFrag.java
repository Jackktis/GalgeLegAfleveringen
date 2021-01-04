package com.example.galgelegafleveringen.frags;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.galgelegafleveringen.states.GalgeSpilLogikkenState;
import com.example.galgelegafleveringen.MainActivity;
import com.example.galgelegafleveringen.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class StartFrag extends Fragment implements AdapterView.OnItemSelectedListener{
    Button b, BTHistorik, BTScore;
    String Sværhed;
    TextView TVSværdhedsSkala;
    Executor bgThread = Executors.newSingleThreadExecutor(); // håndtag til en baggrundstråd
    Handler uiThread = new Handler(Looper.getMainLooper());

    MainActivity mainActivity;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState)  {

        View rod = i.inflate(R.layout.activity_start, container, false);
        TVSværdhedsSkala = rod.findViewById(R.id.txtSværhedSkala);

        BTHistorik = (Button) rod.findViewById(R.id.BTHistorik);
        BTScore = (Button) rod.findViewById(R.id.BTScoreboard);
        mainActivity =(MainActivity) getActivity();

        Spinner spinner = rod.findViewById(R.id.spinner_af_spilsværhed);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mainActivity,R.array.sværhedsgrad, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        BTHistorik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment historikFrag = new HistorikFrag();
                getFragmentManager().beginTransaction().replace(R.id.MainactivityFrame, historikFrag ).addToBackStack(null).commit();
            }
        });

        BTScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment historikFrag = new ScoreFrag();
                getFragmentManager().beginTransaction().replace(R.id.MainactivityFrame, historikFrag ).addToBackStack(null).commit();
            }
        });

        b = (Button) rod.findViewById(R.id.start);
        b.setOnClickListener(v -> StartSide());

        return rod;
    }



    public void StartSide(){
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Sværhed = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Sværhed = parent.getItemAtPosition(1).toString();
    }
}


