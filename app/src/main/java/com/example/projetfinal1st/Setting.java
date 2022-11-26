package com.example.projetfinal1st;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioGroup;

import java.util.concurrent.locks.Condition;

public class Setting extends AppCompatActivity {
    private RadioGroup group;
    private ImageView imageView;
    //pour le changement de langue
    /**
     * Resources va chercher le fichier ressource de lapplication
     * Config prend la configuration des ressources
     */
   // private Resources res = getApplicationContext().getResources();
   // private Configuration config = res.getConfiguration();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_setting);
        super.onCreate(savedInstanceState);

        imageView = findViewById(R.id.imagedifficulte);
        group = findViewById(R.id.bouttongroup);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            /**
             * la fonction verifie quelle boutton est selectionner pour ensuite changer limage selon la difficulte
             */
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i)
                {
                    case R.id.bouttonFacile:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.easy));
                        break;
                    case R.id.BouttonMedium:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.medium));
                        break;
                    case R.id.bouttondifficile:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.difficile));
                        break;
                }
            }
        });
        /**
         * ouvre la page des politiques prives
         */
        findViewById(R.id.Politique).setOnClickListener(view -> {
            Intent intent = new Intent(this, PolitiqueConfidentalite.class);
            startActivity(intent);
        });
        /**
         * ouvre la page des conditions dutilisations
         */
        findViewById(R.id.Conditions).setOnClickListener(view -> {
            Intent intent = new Intent(this, ConditionsUtilisations.class);
            startActivity(intent);
        });
        //fais un popup des credits
        findViewById(R.id.Credit).setOnClickListener(view -> {
                    AlertDialog.Builder creditpopup = new AlertDialog.Builder(new MainActivity());
                    creditpopup.setTitle("Credit");
                    creditpopup.setMessage("Programmeur \nCharle-Antoine Boudreault\nCody Bilodeau\nJérémy Lagueux");
                    creditpopup.setNeutralButton("fermer", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                        //creditpopup.show() marche pas
                    });
                });
        /**
         * fonction qui verifie si le boutton anglais est utiliser
         * sil est utiliser on change la configuration pour langlais
         * cela va faire reload lapp et nous devrons la relancer avec langlais
         */
        /*
        findViewById(R.id.anglais).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                config.locale = new Locale("en-rCA");
                res.updateConfiguration(config, res.getDisplayMetrics());
            }
        });
        findViewById(R.id.francais).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                config.locale = new Locale("fr-rCA");
                res.updateConfiguration(config, res.getDisplayMetrics());
            }
        });
        */
    }
}
