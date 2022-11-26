package com.example.projetfinal1st;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.TextClassifierEvent;
import android.widget.ImageView;
import android.widget.RadioGroup;

import java.util.Locale;

public class Setting extends AppCompatActivity {
    private RadioGroup group;
    private ImageView imageView;
    //pour le changement de langue
    /**
     * Resources va chercher le fichier ressource de lapplication
     * Config prend la configuration des ressources
     */
    private Resources res = getApplicationContext().getResources();
    private Configuration config = res.getConfiguration();
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
         * fonction qui verifie si le boutton anglais est utiliser
         * sil est utiliser on change la configuration pour langlais
         * cela va faire reload lapp et nous devrons la relancer avec langlais
         */
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
    }


}