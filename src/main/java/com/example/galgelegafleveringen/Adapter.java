package com.example.galgelegafleveringen;

import java.util.ArrayList;

public abstract class Adapter implements State {

    @Override
    public String getListStringBrugteOrd() {
        return null;
    }

    @Override
    public String getListStringForkerteOrd() {
        return null;
    }

    @Override
    public ArrayList<String> getMuligeOrd() {
        return null;
    }

    @Override
    public ArrayList<String> getBrugteBogstaver() {
        return null;
    }

    @Override
    public ArrayList<String> getForkerteBogstaver() {
        return null;
    }

    @Override
    public String getSynligtOrd() {
        return null;
    }

    @Override
    public String getOrdet() {
        return null;
    }

    @Override
    public int getAntalForkerteBogstaver() {
        return 0;
    }

    @Override
    public boolean erSpilletVundet() {
        return false;
    }

    @Override
    public boolean erSpilletTabt() {
        return false;
    }

    @Override
    public boolean erSpilletSlut() {
        return false;
    }

    @Override
    public void onEnterState(Context context) {

    }

    @Override
    public void opdaterSynligtOrd() {

    }

    @Override
    public void gætBogstav(String bogstav) {

    }

    @Override
    public void updatereVinderListe(String Navn, int antalForkerteBogstaver) {

    }

    @Override
    public void hentOrdFraRegneark(String sværhedsgrader) throws Exception {

    }
}
