package com.example.galgelegafleveringen.states;

import com.example.galgelegafleveringen.Adapter;
import com.example.galgelegafleveringen.Context;
import com.example.galgelegafleveringen.State;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class GalgeSpilLogikkenState extends Adapter implements Serializable {

        /** AHT afprøvning er muligeOrd synlig på pakkeniveau */
        ArrayList<String> muligeOrd = new ArrayList<String>();
        private String ordet;
        private ArrayList<String> brugteBogstaver = new ArrayList<String>();
        private ArrayList<String> Vinderliste = new ArrayList<String>();
        private ArrayList<String> forkerteBogstaver = new ArrayList<String>();
        private String synligtOrd;
        private String listStringForkerteOrd;
        private String listStringBrugteOrd;
        private int antalForkerteBogstaver = 0;
        private boolean sidsteBogstavVarKorrekt;
        private boolean spilletErVundet =false;
        private boolean spilletErTabt = false;


    public String getListStringBrugteOrd() {
        listStringBrugteOrd = "";

        for (String s : getBrugteBogstaver())
        {
            listStringBrugteOrd += s + "\t";
        }
        return listStringBrugteOrd;
    }

    public String getListStringForkerteOrd() {
        listStringForkerteOrd= "";

        for (String s : getForkerteBogstaver())
        {
            listStringForkerteOrd += s + "\t";
        }
        return listStringForkerteOrd;
    }

    public ArrayList<String> getMuligeOrd() {
        return muligeOrd;
    }

    public ArrayList<String> getBrugteBogstaver() { return brugteBogstaver; }

    public ArrayList<String> getForkerteBogstaver() { return forkerteBogstaver; }

    public String getSynligtOrd() {
            return synligtOrd;
        }

        public String getOrdet() {
            return ordet;
        }

        public int getAntalForkerteBogstaver() {

        if(antalForkerteBogstaver == 6){
            spilletErTabt = true;
        }

        return antalForkerteBogstaver;
        }

        public boolean erSpilletVundet() {
            return spilletErVundet;
        }

        public boolean erSpilletTabt() {
            return spilletErTabt;
        }

        public boolean erSpilletSlut() {
            return spilletErTabt || spilletErVundet;
        }

    public void onEnterState(Context context) {
            brugteBogstaver.clear();
            antalForkerteBogstaver = 0;
            spilletErVundet = false;
            spilletErTabt = false;

            if (muligeOrd.isEmpty()) {
                System.out.println("ingen ord under dette sværhedsgrad ");
            } else {
                ordet = muligeOrd.get(new Random().nextInt(muligeOrd.size()));
            System.out.println("Nyt spil - det skjulte ord er: " + ordet);
        }
        }
    public void opdaterSynligtOrd() {
            synligtOrd = "";
            spilletErVundet = true;
            for (int n = 0; n < ordet.length(); n++) {
                String bogstav = ordet.substring(n, n + 1);
                if (brugteBogstaver.contains(bogstav)) {
                    synligtOrd = synligtOrd + bogstav;
                } else {
                    synligtOrd = synligtOrd + "*";
                    spilletErVundet = false;
                }
            }
        }

        public void gætBogstav(String bogstav) {
            if (bogstav.length() != 1) return;
            System.out.println("Der gættes på bogstavet: " + bogstav);
            if (brugteBogstaver.contains(bogstav)) return;
            if (spilletErVundet || spilletErTabt) return;

            brugteBogstaver.add(bogstav);

            if (ordet.contains(bogstav)) {
                sidsteBogstavVarKorrekt = true;
                System.out.println("Bogstavet var korrekt: " + bogstav);
            } else {
                // Vi gættede på et bogstav der ikke var i ordet.
                sidsteBogstavVarKorrekt = false;
                System.out.println("Bogstavet var IKKE korrekt: " + bogstav);
                antalForkerteBogstaver = antalForkerteBogstaver + 1;
                forkerteBogstaver.add(bogstav);
                if (antalForkerteBogstaver > 6) {
                    spilletErTabt = true;
                }
            }
            opdaterSynligtOrd();
        }

        public static String hentUrl(String url) throws IOException {
            System.out.println("Henter data fra " + url);
            BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
            StringBuilder sb = new StringBuilder();
            String linje = br.readLine();
            while (linje != null) {
                sb.append(linje + "\n");
                linje = br.readLine();
            }
            return sb.toString();
        }

        //TODO få lavet denne Vinderliste
        public void updatereVinderListe(String Navn, int antalForkerteBogstaver){
        Vinderliste.add(Navn);
        }

        /**
         * Hent ord og sværhedsgrad fra et online regneark. Du kan redigere i regnearket, på adressen
         * https://docs.google.com/spreadsheets/d/1RnwU9KATJB94Rhr7nurvjxfg09wAHMZPYB3uySBPO6M/edit?usp=sharing
         * @param sværhedsgrader en streng med de tilladte sværhedsgrader - f.eks "3" for at medtage kun svære ord, eller "12" for alle nemme og halvsvære ord
         * @throws Exception
         */

        public void hentOrdFraRegneark(String sværhedsgrader) throws Exception {
            String id = "1RnwU9KATJB94Rhr7nurvjxfg09wAHMZPYB3uySBPO6M";

            System.out.println("Henter data som kommasepareret CSV fra regnearket https://docs.google.com/spreadsheets/d/"+id+"/edit?usp=sharing");

            String data = hentUrl("https://docs.google.com/spreadsheets/d/" + id + "/export?format=csv&id=" + id);
            int linjeNr = 0;

            muligeOrd.clear();
            for (String linje : data.split("\n")) {
                if (linjeNr<20) System.out.println("Læst linje = " + linje); // udskriv de første 20 linjer
                if (linjeNr++ < 1 ) continue; // Spring første linje med kolonnenavnene over
                String[] felter = linje.split(",", -1);// -1 er for at beholde tomme indgange, f.eks. bliver ",,," splittet i et array med 4 tomme strenge
                String sværhedsgrad = felter[0].trim();
                String ordet = felter[1].trim().toLowerCase();
                if (sværhedsgrad.isEmpty() || ordet.isEmpty()) continue; // spring over linjer med tomme ord
                if (!sværhedsgrader.contains(sværhedsgrad)) continue; // filtrér på sværhedsgrader
                System.out.println("Tilføjer "+ordet+", der har sværhedsgrad "+sværhedsgrad);
                muligeOrd.add(ordet);
            }
            System.out.println("muligeOrd = " + muligeOrd);
        }
}
