package com.example.galgelegafleveringen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.galgelegafleveringen.states.GalgeSpilLogikkenState;

public class Spillet extends AppCompatActivity {

    String orderet, synligOrd, bogstav, brugteBogstav, forkertebogstav;

    int nuværende_billede, antalForkerteOrd;
    TextView TVgætOrderet, TVBrugtBogstav, TVForkertBogstav;
    EditText ETbogstav;
    GalgeSpilLogikkenState spillet;
    public ImageView imgview;
    int[] billeder = {R.drawable.galge,R.drawable.forkert1, R.drawable.forkert2,R.drawable.forkert3,R.drawable.forkert4,R.drawable.forkert5,R.drawable.forkert6 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntent().getSerializableExtra("galgeSpilLogik");
        setContentView(R.layout.activity_spillet);


        TVgætOrderet = findViewById(R.id.gaette_ord);
        ETbogstav = findViewById(R.id.ETGætBogstav);
        TVBrugtBogstav = findViewById(R.id.TWgættetOrd);
        TVForkertBogstav = findViewById(R.id.TWForkertOrd);
        imgview = (ImageView) findViewById(R.id.galgen);

        // vi hiver vores information fra Start ind her.
        Bundle b = getIntent().getExtras();

        // vi starter vores spil
        try {
            spillet = (GalgeSpilLogikkenState) b.get("galgeSpilLogik");
            orderet = spillet.getOrdet();

            spillet.opdaterSynligtOrd();
            synligOrd = spillet.getSynligtOrd();
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
                    spillet.gætBogstav(bogstav);
                nuværende_billede = spillet.getAntalForkerteBogstaver();

                if (spillet.erSpilletSlut()) {
                    if (spillet.erSpilletTabt()) {
                        Intent intent = new Intent(Spillet.this, Tabt.class);
                        intent.putExtra("orderet", orderet);
                        startActivity(intent);
                        finish();
                    }
                    if (spillet.erSpilletVundet()) {
                        Intent intent = new Intent(Spillet.this, Vundet.class);
                        antalForkerteOrd = spillet.getAntalForkerteBogstaver();
                        intent.putExtra("orderet", orderet);
                        intent.putExtra("forsøg", antalForkerteOrd);
                        startActivity(intent);
                        finish();
                    }
                }

                spillet.opdaterSynligtOrd();
                synligOrd = spillet.getSynligtOrd();

                brugteBogstav = spillet.getListStringBrugteOrd();
                forkertebogstav = spillet.getListStringForkerteOrd();

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
        }
    }