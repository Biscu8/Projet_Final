package com.example.projetfinal1st;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;
//Orde a faire : finir ce qui a été déja commmencer, puis faire cette liste en ordre
//TODO faire un bouton pour retourner a la page de jeu avec les settings sauvegarder

//TODO trouver quoi mettre dans le haut de la page a droite

//TODO trouver uen utilité au niveau de difficulté ou le changer

//TODO desactiver/activer la volume bar selon la Switch musique
//TODO faire jouer la musique plus ou moins fort selon la VolumeBar
//TODO Implementer une musique

//TODO Switch basse qualite qui reduit les graphiques si elle est activer
//TODO implement le mode Sombre avec la Switch DarkMode
public class Settings extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private SharedPreferences Preferences;
    private SettingsFragment settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        Preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

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
         */
        findViewById(R.id.Conditions).setOnClickListener(view -> {
            Intent intent = new Intent(this, ConditionsUtilisations.class);
            startActivity(intent);
        });
        //fais un popup des credits //TODO faire fonctionner le popup et mettre un bouton ok sur le popup
        findViewById(R.id.Credit).setOnClickListener(view -> {
            AlertDialog.Builder creditpopup = new AlertDialog.Builder(new Settings());
            creditpopup.setTitle("Credit");
            creditpopup.setMessage("Programmeur \nCharle-Antoine Boudreault\nCody Bilodeau\nJérémy Lagueux");
            creditpopup.show();
        });

//ce que les boutton dans les settings font

    }
    //TODO faire les options du DevMode


    @Override
    protected void onResume() {
        super.onResume();
        Preferences.registerOnSharedPreferenceChangeListener(this);
        settings = (SettingsFragment) getSupportFragmentManager().findFragmentById(R.id.settings);
        if (Preferences.getBoolean("ModeDev", false)) {
            settings.findPreference("SettingDev").setVisible(true);
        } else {
            settings.findPreference("SettingDev").setVisible(false);
        }
    }

    @Override
    protected void onPause() {
        Preferences.unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences preference, String key) {
        switch (key) {
            case "ModeDev":
                if (preference.getBoolean(key, false)) {
                    settings.findPreference("SettingDev").setVisible(true);
                } else {
                    settings.findPreference("SettingDev").setVisible(false);
                }
                break;
            case "DarkMode":
                if (preference.getBoolean(key, false)) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                break;
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }
    //pour le changement de langue //TODO faire fonctionner le changement de langue
    /**
     * Resources va chercher le fichier ressource de lapplication
     * Config prend la configuration des ressources
     */
    // private Resources res = getApplicationContext().getResources();
    // private Configuration config = res.getConfiguration();

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