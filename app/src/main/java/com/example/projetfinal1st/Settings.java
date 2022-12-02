package com.example.projetfinal1st;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;

public class Settings extends AppCompatActivity {
  private SharedPreferences PreferencesDevMode;
  private SettingsFragment settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        PreferencesDevMode =  PreferenceManager.getDefaultSharedPreferences(this /* Activity context */);;
        if (savedInstanceState == null) {
            settings = new SettingsFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, settings)
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
        //page du mode developpeur

        PreferencesDevMode.registerOnSharedPreferenceChangeListener((sharedPreferences, key) ->{
            switch (key)
            {
                case "ModeDev" :
                    ShowDevModeSetting(sharedPreferences, key);
                    break;
            }

        } );
              //TODO faire le bouton pour ouvrir les options du devMode
                        //TODO deverrouiller le bouton des options de la page developpeur
            /*
                Intent intent = new Intent(this, PageDev.class);//TODO faire la page DevMode
                startActivity(intent);
                else {
                        //TODO verrouiller le bouton des options de la page developpeur
                        //TODO remmettre les options a la normal
                     }
             */
    }
public void ShowDevModeSetting(SharedPreferences preference, String key)
{
    if(preference.getBoolean(key, false))
    {
        settings.findPreference(key).setVisible(true);
    }
    else
    {
        settings.findPreference(key).setVisible(false);
    }
}
    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }
}