package com.example.galgelegafleveringen.frags;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.galgelegafleveringen.MainActivity;
import com.example.galgelegafleveringen.R;

public class VundetFrag extends Fragment {


    String navn, orderet;
    int forsøg;
    TextView spillerenForsøg, tillykke;
    EditText spillerensNavn;
    Button BTscoreBoard;
    SharedPreferences sp;
    MainActivity mainActivity;
    MediaPlayer player;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState) {

        View rod = i.inflate(R.layout.activity_vundet, container, false);
        mainActivity = (MainActivity) getActivity();

        forsøg = mainActivity.getContext().getPrefs().getInt("forsøg", -1);
        orderet = mainActivity.getContext().getPrefs().getString("orderet", "fandt ikke orderet");
        spillerenForsøg = rod.findViewById(R.id.TWgættetOrd);
        BTscoreBoard = rod.findViewById(R.id.BTtilScoreBoard);
        spillerensNavn = rod.findViewById(R.id.editTextTextPersonName);
        tillykke = rod.findViewById(R.id.TILLYKKE);

        spillerenForsøg.setText(String.valueOf(forsøg));
        player = MediaPlayer.create(mainActivity, R.raw.bensoundukulele);
        player.isLooping();
        player.start();

        BTscoreBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navn = spillerensNavn.getText().toString();
                if(!(navn.isEmpty())) {
                    saveDataToHistorik();
                    saveDataToScoreboard();
                    player.stop();
                    getFragmentManager().popBackStack();
                } else {
                    tillykke.setText("indtast nu bare dit navn...\n " );
                }
            }
        });
        return rod;
    }

    public void saveDataToHistorik(){

        sp = mainActivity.getSharedPreferences("vinderPrefs", mainActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        int gamesCount = sp.getInt("gamesCount", 0);

        editor.putString("navn_gamesCount"+gamesCount, navn);
        editor.putInt("antalForkerte_gamesCount"+gamesCount, forsøg);
        editor.putString("orderet_gameCount"+gamesCount, orderet);
        editor.putInt("gamesCount", gamesCount+1);
        editor.apply();

        // startActivity(new Intent(Vundet.this, Start.class));
       // finish();
    }

    public void saveDataToScoreboard(){
        int forkerte0, forkerte1, forkerte2, forkerte3, forkerte4;
        String forrigNavn0, forrigNavn1, forrigNavn2, forrigNavn3, forrigNavn4;
        String forrigOrd0, forrigOrd1, forrigOrd2, forrigOrd3;

        sp = mainActivity.getSharedPreferences("highscoreVinderPrefs", mainActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();



        forkerte0 = sp.getInt("antalForkerte_gamesCount" + 0, forsøg);
        forkerte1 = sp.getInt("antalForkerte_gamesCount" + 1, forsøg);
        forkerte2 = sp.getInt("antalForkerte_gamesCount" + 2, forsøg);
        forkerte3 = sp.getInt("antalForkerte_gamesCount" + 3, forsøg);
        forkerte4 = sp.getInt("antalForkerte_gamesCount" + 4, forsøg);

        forrigNavn0 = sp.getString("navn_gamesCount" + 0, "(Navn)");
        forrigNavn1 = sp.getString("navn_gamesCount" + 1, "(Navn)");
        forrigNavn2 = sp.getString("navn_gamesCount" + 2, "(Navn)");
        forrigNavn3 = sp.getString("navn_gamesCount" + 3, "(Navn)");
        forrigNavn4 = sp.getString("navn_gamesCount" + 4, "(Navn)");


        forrigOrd0 = sp.getString("orderet_gameCount" + 0, "(orderet)");
        forrigOrd1 = sp.getString("orderet_gameCount" + 1, "(orderet)");
        forrigOrd2 = sp.getString("orderet_gameCount" + 2, "(orderet)");
        forrigOrd3 = sp.getString("orderet_gameCount" + 3, "(orderet)");

        for (int i = 0; i < 1; i++) {
            // første plads
            if ((forkerte0 > forsøg) || (forrigNavn0 == null)){
                if(forrigNavn0 != null) {
                    editor.putString("navn_gamesCount" + 1, forrigNavn0);
                    editor.putInt("antalForkerte_gamesCount" + 1, forkerte0);
                    editor.putString("orderet_gameCount" + 1, forrigOrd0);
                    editor.putInt("gamesCount", 1);

                    if(forrigNavn2 != null) {
                        editor.putString("navn_gamesCount" + 2, forrigNavn1);
                        editor.putInt("antalForkerte_gamesCount" + 2, forkerte1);
                        editor.putString("orderet_gameCount" + 2, forrigOrd1);
                        editor.putInt("gamesCount", 2);
                    }

                    if(forrigNavn3 != null) {
                        editor.putString("navn_gamesCount" + 3, forrigNavn2);
                        editor.putInt("antalForkerte_gamesCount" + 3, forkerte2);
                        editor.putString("orderet_gameCount" + 3, forrigOrd2);
                        editor.putString("navn_gamesCount" + 4, forrigNavn3);
                        editor.putInt("antalForkerte_gamesCount" + 4, forkerte3);
                        editor.putString("orderet_gameCount" + 4, forrigOrd3);
                        editor.putInt("gamesCount", 4);
                    }
                }

                editor.putString("navn_gamesCount" + 0, navn);
                editor.putInt("antalForkerte_gamesCount" + 0, forsøg);
                editor.putString("orderet_gameCount" + 0, orderet);
                editor.putInt("gamesCount", 0);
                editor.apply();
                break;
            }

            // anden plads
            if (forkerte1 > forsøg || forrigNavn1 == null){
                if(forrigNavn1 != null) {
                    editor.putString("navn_gamesCount" + 2, forrigNavn1);
                    editor.putInt("antalForkerte_gamesCount" + 2, forkerte1);
                    editor.putString("orderet_gameCount" + 2, forrigOrd1);
                    editor.putInt("gamesCount", 2);

                    if(forrigNavn3 != null) {
                        editor.putString("navn_gamesCount" + 3, forrigNavn2);
                        editor.putInt("antalForkerte_gamesCount" + 3, forkerte2);
                        editor.putString("orderet_gameCount" + 3, forrigOrd2);
                        editor.putString("navn_gamesCount" + 4, forrigNavn3);
                        editor.putInt("antalForkerte_gamesCount" + 4, forkerte3);
                        editor.putString("orderet_gameCount" + 4, forrigOrd3);
                        editor.putInt("gamesCount", 4);
                    }
                }
                editor.putString("navn_gamesCount" + 1, navn);
                editor.putInt("antalForkerte_gamesCount" + 1, forsøg);
                editor.putString("orderet_gameCount" + 1, orderet);
                editor.putInt("gamesCount", 1);
                editor.apply();
                break;
            }

            // tredje plads
            if (forkerte2 > forsøg || forrigNavn2 == null){
                if(forrigNavn2 != null) {
                    editor.putString("navn_gamesCount" + 3, forrigNavn2);
                    editor.putInt("antalForkerte_gamesCount" + 3, forkerte2);
                    editor.putString("orderet_gameCount" + 3, forrigOrd2);
                    editor.putInt("gamesCount", 3);

                    if(forrigNavn3 != null) {
                        editor.putString("navn_gamesCount" + 4, forrigNavn2);
                        editor.putInt("antalForkerte_gamesCount" + 4, forkerte2);
                        editor.putString("orderet_gameCount" + 4, forrigOrd2);
                        editor.putString("navn_gamesCount" + 5, forrigNavn3);
                        editor.putInt("antalForkerte_gamesCount" + 5, forkerte3);
                        editor.putString("orderet_gameCount" + 5, forrigOrd3);
                        editor.putInt("gamesCount", 5);
                    }
                }
                editor.putString("navn_gamesCount" + 2, navn);
                editor.putInt("antalForkerte_gamesCount" + 2, forsøg);
                editor.putString("orderet_gameCount" + 2, orderet);
                editor.putInt("gamesCount", 2);
                editor.apply();
                break;
            }

            // fjerde plads
            if (forkerte3 > forsøg || forrigNavn3 == null){
                if(forrigNavn4 != null) {
                    editor.putString("navn_gamesCount" + 5, forrigNavn3);
                    editor.putInt("antalForkerte_gamesCount" + 5, forkerte3);
                    editor.putString("orderet_gameCount" + 5, forrigOrd3);
                    editor.putInt("gamesCount", 5);
                }
                editor.putString("navn_gamesCount" + 4, navn);
                editor.putInt("antalForkerte_gamesCount" + 4, forsøg);
                editor.putString("orderet_gameCount" + 4, orderet);
                editor.putInt("gamesCount", 4);
                editor.apply();
                break;
            }

            // femte plads
            if (forkerte4 > forsøg || forrigNavn4 == null) {
                editor.putString("navn_gamesCount" + 5, navn);
                editor.putInt("antalForkerte_gamesCount" + 5, forsøg);
                editor.putString("orderet_gameCount" + 5, orderet);
                editor.putInt("gamesCount", 5);
                editor.apply();
                break;
            }
        }



            /*
            int gamesCount = sp.getInt("gamesCount", 0);
            forrigForsøg = sp.getInt("antalForkerte_gamesCount" + (gamesCount - 1), 0);
            forrigNavn = sp.getString("navn_gamesCount" + (gamesCount - 1), "(Navn)");
            forrigOrd = sp.getString("orderet_gameCount" + (gamesCount - 1), "(orderet)");

            if(forrigForsøg < forsøg || forrigForsøg == 0) {
                editor.putString("navn_gamesCount" + gamesCount, navn);
                editor.putInt("antalForkerte_gamesCount" + gamesCount, forsøg);
                editor.putString("orderet_gameCount" + gamesCount, orderet);
                editor.putInt("gamesCount", gamesCount + 1);
                editor.apply();
            }
            if(forrigForsøg > forsøg){
                //forsøg ind i det gamle forsøg
                editor.putString("navn_gamesCount" + (gamesCount-1), navn);
                editor.putInt("antalForkerte_gamesCount" + (gamesCount-1), forsøg);
                editor.putString("orderet_gameCount" + (gamesCount-1), orderet);

                // gamle forsøg ind i den efter
                editor.putString("navn_gamesCount" + gamesCount, forrigNavn);
                editor.putInt("antalForkerte_gamesCount" + gamesCount, forrigForsøg);
                editor.putString("orderet_gameCount" + gamesCount, forrigOrd);
                editor.putInt("gamesCount", gamesCount + 1);
                editor.apply();
            }

             */
    }

    @Override
    public void onPause() {
        super.onPause();
        player.pause();
    }
}

