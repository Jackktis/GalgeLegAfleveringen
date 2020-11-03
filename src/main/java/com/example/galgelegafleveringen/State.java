package com.example.galgelegafleveringen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public interface State {
    String getListStringBrugteOrd();

    String getListStringForkerteOrd();

    ArrayList<String> getMuligeOrd();

    ArrayList<String> getBrugteBogstaver();

    ArrayList<String> getForkerteBogstaver();

    String getSynligtOrd();

    String getOrdet();
    int getAntalForkerteBogstaver();

    boolean erSpilletVundet();
    boolean erSpilletTabt();

    boolean erSpilletSlut();

    void onEnterState(Context context);
    void opdaterSynligtOrd();

    void gætBogstav(String bogstav);

    void updatereVinderListe(String Navn, int antalForkerteBogstaver);

    void hentOrdFraRegneark(String sværhedsgrader) throws Exception;
}
