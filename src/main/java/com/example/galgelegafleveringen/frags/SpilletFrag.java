package com.example.galgelegafleveringen.frags;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.galgelegafleveringen.MainActivity;
import com.example.galgelegafleveringen.R;

public class SpilletFrag extends Fragment {
    String orderet, synligOrd, bogstav, brugteBogstav, forkertebogstav;
    int nuværende_billede, antalForkerteOrd;
    TextView TVgætOrderet, TVBrugtBogstav, TVForkertBogstav;
    EditText ETbogstav;
    public ImageView imgview;
    int[] billeder = {R.drawable.galge,R.drawable.forkert1, R.drawable.forkert2,R.drawable.forkert3,R.drawable.forkert4,R.drawable.forkert5,R.drawable.forkert6 };
    MainActivity mainActivity;
    MediaPlayer player;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle SavedInstanceState){
        View rod = i.inflate(R.layout.activity_spillet, container, false);

        TVgætOrderet = rod.findViewById(R.id.gaette_ord);
        ETbogstav = rod.findViewById(R.id.ETGætBogstav);
        TVBrugtBogstav = rod.findViewById(R.id.TWgættetOrd);
        TVForkertBogstav = rod.findViewById(R.id.TWForkertOrd);
        imgview = (ImageView) rod.findViewById(R.id.galgen);
        mainActivity = (MainActivity) getActivity();

        // start musikken, når man spiller
        player = MediaPlayer.create(mainActivity, R.raw.bensoundepic);
        player.isLooping();
        player.start();
        // vi starter vores spil
        try {

           // spillet = (GalgeSpilLogikken) b.get("galgeSpilLogik");
            orderet = mainActivity.getContext().getOrdet();

            mainActivity.getContext().opdaterSynligtOrd();
            synligOrd = mainActivity.getContext().getSynligtOrd();
            TVgætOrderet.setText(synligOrd);

        } catch (Exception e) {
            System.out.println("logikken blev ikke taget videre");
        }

        // Når vi tilføjer et ord i edit tektsen
        ETbogstav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bogstav = ETbogstav.getText().toString();

                // tjekker om bogstavet indeholder et tal
                if (!(bogstav.matches(".*\\d.*"))) {
                    mainActivity.getContext().gætBogstav(bogstav);
                    nuværende_billede = mainActivity.getContext().getAntalForkerteBogstaver();

                    if (mainActivity.getContext().erSpilletSlut()) {
                        if (mainActivity.getContext().erSpilletTabt()) {

                            getFragmentManager().popBackStack();
                            Fragment tabt = new TabtFrag();
                            getFragmentManager().beginTransaction().replace(R.id.MainactivityFrame, tabt).addToBackStack(null).commit();
                            mainActivity.getContext().getPrefs().edit().putString("orderet",orderet).apply();
                            player.stop();
                        }
                        if (mainActivity.getContext().erSpilletVundet()) {

                            getFragmentManager().popBackStack();
                            Fragment vundet = new VundetFrag();
                            getFragmentManager().beginTransaction().replace(R.id.MainactivityFrame, vundet).addToBackStack(null).commit();
                            antalForkerteOrd = mainActivity.getContext().getAntalForkerteBogstaver();
                            mainActivity.getContext().getPrefs().edit().putString("orderet",orderet).apply();
                            mainActivity.getContext().getPrefs().edit().putInt("forsøg",antalForkerteOrd).apply();
                            player.stop();
                        }
                    }

                    mainActivity.getContext().opdaterSynligtOrd();
                    synligOrd = mainActivity.getContext().getSynligtOrd();

                    brugteBogstav = mainActivity.getContext().getListStringBrugteOrd();
                    forkertebogstav = mainActivity.getContext().getListStringForkerteOrd();

                    TVBrugtBogstav.setText(brugteBogstav);
                    TVForkertBogstav.setText(forkertebogstav);
                    TVgætOrderet.setText(synligOrd);

                    imgview.setImageResource(billeder[nuværende_billede]);
                    ETbogstav.getText().clear();
                }else {
                    System.out.println("forkert ord");
                }
            }
        });
        return rod;
    }
    @Override
    public void onPause() {
        super.onPause();
        player.pause();
    }
}