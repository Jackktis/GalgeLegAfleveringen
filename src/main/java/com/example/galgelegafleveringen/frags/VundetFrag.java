package com.example.galgelegafleveringen.frags;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.galgelegafleveringen.MainActivity;
import com.example.galgelegafleveringen.R;
import com.example.galgelegafleveringen.frags.StartFrag;

public class VundetFrag extends Fragment {


    String navn, orderet;
    int forsøg;
    TextView spillerenForsøg, tillykke;
    EditText spillerensNavn;
    Button BTscoreBoard;
    SharedPreferences sp;
    MainActivity mainActivity;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState) {

        View rod = i.inflate(R.layout.activity_vundet, container, false);
        mainActivity = (MainActivity) getActivity();

        forsøg = mainActivity.getContext().getPrefs().getInt("forsøg", -1);
       orderet= mainActivity.getContext().getPrefs().getString("orderet", "fandt ikke orderet");
        spillerenForsøg = rod.findViewById(R.id.TWgættetOrd);
        BTscoreBoard = rod.findViewById(R.id.BTtilScoreBoard);
        spillerensNavn = rod.findViewById(R.id.editTextTextPersonName);
        tillykke = rod.findViewById(R.id.TILLYKKE);

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
        return rod;
    }

    public void saveDataTo(){

        sp = mainActivity.getSharedPreferences("vinderPrefs", mainActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        int gamesCount = sp.getInt("gamesCount", 0);

        editor.putString("navn_gamesCount"+gamesCount, navn);
        editor.putInt("antalForkerte_gamesCount"+gamesCount, forsøg);
        editor.putString("orderet_gameCount"+gamesCount, orderet);
        editor.putInt("gamesCount", gamesCount+1);
        editor.apply();

        Fragment start = new StartFrag();
        getFragmentManager().beginTransaction().replace(R.id.MainactivityFrame, start).addToBackStack(null).commit();
        // startActivity(new Intent(Vundet.this, Start.class));
       // finish();
    }
}

