package com.example.galgelegafleveringen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScoreBoardLogikken {
    Map<String, Integer> scoreboard = new HashMap<String, Integer>();
    private String navn;
    private int forkertesvar;

    public void opdaterScoreboard(String navn, int Forkerte) {

        scoreboard.put(navn, forkertesvar);

    }
}
