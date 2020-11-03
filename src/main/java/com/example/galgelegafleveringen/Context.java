package com.example.galgelegafleveringen;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.galgelegafleveringen.states.StartState;

import java.util.ArrayList;

public class Context {
    private MainActivity ui;
    private State state;
    private SharedPreferences prefs;


    public Context(MainActivity ui){

        this.ui = ui;
        prefs = PreferenceManager.getDefaultSharedPreferences(ui);
        state = new StartState();
        state.onEnterState(this);

    }

    public void setState(State state){
        this.state = state;
    }

    public SharedPreferences getPrefs(){return prefs;}



   public String getListStringBrugteOrd() {
       return state.getListStringBrugteOrd();
    }

    public String getListStringForkerteOrd(){
        return state.getListStringForkerteOrd();
    }

    public ArrayList<String> getMuligeOrd(){
        return state.getMuligeOrd();
    }

    public ArrayList<String> getBrugteBogstaver(){
        return state.getBrugteBogstaver();
    }

    public ArrayList<String> getForkerteBogstaver(){
        return state.getForkerteBogstaver();
    }

    public String getSynligtOrd(){
        return state.getSynligtOrd();
    }

    public String getOrdet(){
        return state.getOrdet();
    }
    public int getAntalForkerteBogstaver(){
        return state.getAntalForkerteBogstaver();
    }

    public boolean erSpilletVundet(){
        return state.erSpilletVundet();
    }
    public boolean erSpilletTabt(){
        return state.erSpilletTabt();
    }

    public boolean erSpilletSlut(){
        return state.erSpilletSlut();
    }

    public void onEnterState(){
        state.onEnterState(this);
    }
    public void opdaterSynligtOrd(){
        state.opdaterSynligtOrd();
    }

    public void gætBogstav(String bogstav){
        state.gætBogstav(bogstav);
    }

    public void updatereVinderListe(String Navn, int antalForkerteBogstaver){
        state.updatereVinderListe(Navn, antalForkerteBogstaver);
    }

    public void hentOrdFraRegneark(String sværhedsgrader) throws Exception{
        state.hentOrdFraRegneark(sværhedsgrader);
    }
}
