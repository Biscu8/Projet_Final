package com.example.projetfinal1st;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;

public class Settings extends AppCompatActivity {
  private SharedPreferences Preferences;
  private SharedPreferences PreferencesDarkMode;
  private SharedPreferences.Editor editor;
  private Boolean nightMODE;
  private SettingsFragment settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        Preferences =  PreferenceManager.getDefaultSharedPreferences(this);

        PreferencesDarkMode = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMODE = PreferencesDarkMode.getBoolean("light", false);
        if(nightMODE)
        {
            //mettre que la switch change
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        //Pour voir si le devMode est activer lors de louverture
      // ShowDevModeSetting(Preferences, "ModeDev");
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

//ce que les boutton dans les settings font
        Preferences.registerOnSharedPreferenceChangeListener((sharedPreferences, key) ->{
            switch (key)
            {
                case "ModeDev" :
                    ShowDevModeSetting(sharedPreferences, key);
                    break;
                case "DarkMode":
                    if(nightMODE)
                    {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        editor =  PreferencesDarkMode.edit();
                        editor.putBoolean("night", false);
                    }
                    else
                    {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        editor =  PreferencesDarkMode.edit();
                        editor.putBoolean("night", true);
                    }
                    editor.apply();
                    break;

            }

        } );

                //TODO faire les options du DevMode
    }
public void ShowDevModeSetting(SharedPreferences preference, String key)
{
    if(preference.getBoolean(key, false))
    {
        settings.findPreference("SettingDev").setVisible(true);
    }
    else
    {
        settings.findPreference("SettingDev").setVisible(false);
    }
}
    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }
}