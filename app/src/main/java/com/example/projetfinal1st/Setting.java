package com.example.projetfinal1st;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Switch;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
//Orde a faire : finir ce qui a été déja commmencer, puis faire cette liste en ordre
//TODO faire un bouton pour retourner a la page de jeu avec les settings sauvegarder

//TODO trouver quoi mettre dans le haut de la page a droite

//TODO trouver uen utilité au niveau de difficulté ou le changer

//TODO desactiver/activer la volume bar selon la Switch musique
//TODO faire jouer la musique plus ou moins fort selon la VolumeBar
//TODO Implementer une musique

//TODO Switch basse qualite qui reduit les graphiques si elle est activer
//TODO implement le mode Sombre avec la Switch DarkMode
public class Setting extends AppCompatActivity {
    private RadioGroup group;
    private ImageView imageView;
    private Switch ModeDev = findViewById(R.id.devMod);

//pour le changement de langue //TODO faire fonctionner le changement de langue
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
        //page du mode developpeur
        ModeDev.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                // si la switch est active, le bouton optionDevMode sactive , sinon il se desactive//TODO faire le bouton pour ouvrir les options du devMode
                if(isChecked)
                {
                    //TODO deverrouiller le bouton des options de la page developpeur
                    /*
                Intent intent = new Intent(this, PageDev.class);//TODO faire la page DevMode
                startActivity(intent);
                */
                }
                else
                {
                    //TODO verrouiller le bouton des options de la page developpeur
                    //TODO remmettre les options a la normal
                }
            }
        });
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
        //TODO rendre le retour vers les settings possible pour la page de politique et la page Conditions
        findViewById(R.id.Politique).setOnClickListener(view -> {
            Intent intent = new Intent(this, PolitiqueConfidentalite.class);
            startActivity(intent);
        });
        /**
         * ouvre la page des conditions dutilisations
         * ne marche pas
         */
        findViewById(R.id.Conditions).setOnClickListener(view -> {
            Intent intent = new Intent(this, ConditionsUtilisations.class);
            startActivity(intent);
        });
        //fais un popup des credits //TODO faire fonctionner le popup et mettre un bouton ok sur le popup
        findViewById(R.id.Credit).setOnClickListener(view -> {
                    AlertDialog.Builder creditpopup = new AlertDialog.Builder(new Setting());
                    creditpopup.setTitle("Credit");
                    creditpopup.setMessage("Programmeur \nCharle-Antoine Boudreault\nCody Bilodeau\nJérémy Lagueux");
                    creditpopup.show();
                });
        //TODO faire fonctionner le changement de langue
        /**
         *
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
