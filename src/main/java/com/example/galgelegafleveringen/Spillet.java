package com.example.galgelegafleveringen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Spillet extends AppCompatActivity {

    String orderet, synligOrd, bogstav, brugteBogstav, forkertebogstav;

    int nuværende_billede;
    TextView TVgætOrderet, TVBrugtBogstav, TVForkertBogstav;
    EditText ETbogstav;
    GalgeSpilLogikken spillet;
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

        try {
            spillet = (GalgeSpilLogikken) b.get("galgeSpilLogik");
            orderet = spillet.getOrdet();

            spillet.opdaterSynligtOrd();
            synligOrd = spillet.getSynligtOrd();
            TVgætOrderet.setText(synligOrd);


            System.out.println("vi har taget dette ord videre: " + orderet);
        } catch (Exception e) {
            System.out.println("logikken blev ikke taget videre");
        }


        ETbogstav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spillet.erSpilletSlut()){
                System.out.println("spillet er slut");
                return;
            }
                bogstav = ETbogstav.getText().toString();

                spillet.gætBogstav(bogstav);
                nuværende_billede = spillet.getAntalForkerteBogstaver();

                if(spillet.erSpilletSlut()){
                    if (spillet.erSpilletTabt()){
                        Intent intent = new Intent(Spillet.this, Tabt.class);
                        startActivity(intent);
                    }
                    if (spillet.erSpilletVundet()){
                        Intent intent = new Intent(Spillet.this, Vundet.class);
                        startActivity(intent);
                    }
                }

                spillet.opdaterSynligtOrd();
                synligOrd = spillet.getSynligtOrd();

                brugteBogstav = spillet.getListStringBrugteOrd();
                forkertebogstav = spillet.getListStringForkerteOrd();

                TVBrugtBogstav.setText(brugteBogstav);
                TVForkertBogstav.setText(forkertebogstav);
                TVgætOrderet.setText(synligOrd);


                System.out.println(nuværende_billede);
                imgview.setImageResource(billeder[nuværende_billede]);


            }
        });

    }
}