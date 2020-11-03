package com.example.galgelegafleveringen;

import java.io.IOException;
import java.util.ArrayList;

public class Context {

    private State state;

    String getListStringBrugteOrd() {
       return state.getListStringBrugteOrd();
    }

    String getListStringForkerteOrd(){
        return state.getListStringForkerteOrd();
    }

    ArrayList<String> getMuligeOrd(){
        return state.getMuligeOrd();
    }

    ArrayList<String> getBrugteBogstaver(){
        return state.getBrugteBogstaver();
    }

    ArrayList<String> getForkerteBogstaver(){
        return state.getForkerteBogstaver();
    }

    String getSynligtOrd(){
        return state.getSynligtOrd();
    }

    String getOrdet(){
        return state.getOrdet();
    }
    int getAntalForkerteBogstaver(){
        return state.getAntalForkerteBogstaver();
    }

    boolean erSpilletVundet(){
        return state.erSpilletVundet();
    }
    boolean erSpilletTabt(){
        return state.erSpilletTabt();
    }

    boolean erSpilletSlut(){
        return state.erSpilletSlut();
    }

    void startNytSpil(){
        state.startNytSpil();
    }
    void opdaterSynligtOrd(){
        state.opdaterSynligtOrd();
    }

    void gætBogstav(String bogstav){
        state.gætBogstav(bogstav);
    }

    void updatereVinderListe(String Navn, int antalForkerteBogstaver){
        state.updatereVinderListe(Navn, antalForkerteBogstaver);
    }

    void hentOrdFraRegneark(String sværhedsgrader) throws Exception{
        state.hentOrdFraRegneark(sværhedsgrader);
    }
}
