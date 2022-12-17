package com.example.projetfinal1st;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.LocaleList;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;

import java.util.Locale;
//Orde a faire : finir ce qui a été déja commmencer, puis faire cette liste en ordre
//TODO trouver une meilleur musique pour le jeu
//TODO faire les soundeffects

public class Settings extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private LocaleListCompat appLocale;
    private Configuration config;
    private MediaPlayer music;
    private SharedPreferences Preferences;
    private Resources res;
    private SettingsFragment settings;

    /**
     * set the page on oppening of the page
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        //set the music
       music = MediaPlayer.create(this, R.raw.bodybreakdown);
       //get the last preferences
        Preferences = PreferenceManager.getDefaultSharedPreferences(this);
        //set the page with the last setting
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

        //click listener link and button link
        /**
         * open private policy page
         */
        findViewById(R.id.Politique).setOnClickListener(view -> {
            Intent intent = new Intent(this, PolitiqueConfidentalite.class);
            startActivity(intent);
        });
        /**
         * open terms of use
         */
        findViewById(R.id.Conditions).setOnClickListener(view -> {
            Intent intent = new Intent(this, ConditionsUtilisations.class);
            startActivity(intent);
        });
        /**
         * open a popup for the credentials
         */
        //TODO faire fonctionner le popup et mettre un bouton ok sur le popup
        findViewById(R.id.Credit).setOnClickListener(view -> {
            AlertDialog.Builder creditpopup = new AlertDialog.Builder(new Settings());
            creditpopup.setTitle("Credit");
            creditpopup.setMessage("Programmeur \nCharle-Antoine Boudreault\nCody Bilodeau\nJérémy Lagueux");
            creditpopup.show();
        });
        // language change
/**
 * Res go search the resource file form application
 * Config take the configuration from the resource file
 */
        res = getApplicationContext().getResources();
        config = res.getConfiguration();

        //TODO faire fonctionner le changement de langue
        /**
         * change the language to english
         */
        findViewById(R.id.bouttonAnglais).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Locale locale = new Locale("en-rCA");
                config.setLocale(locale);
                res.updateConfiguration(config, res.getDisplayMetrics());
            }
        });
        /**
         * change the language to french
         */
        findViewById(R.id.bouttonFrancais).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Locale locale = new Locale("fr-rCA");
                config.setLocale(locale);
                res.updateConfiguration(config, res.getDisplayMetrics());
            }
        });
    }
    //TODO faire les options du DevMode

    @Override
    /**
     * is call when the page is resume
     * get the preferences and set the settings to the settings before the state on Resume
     */
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
    /**
     * is call if the page is the on pause
     * set the preferences
     */
    protected void onPause() {
        Preferences.unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

    @Override
    /**
     * tells what each preferences does
     * @param preference previous shared preferences
     * @param key key to know which preference is which
     */
    public void onSharedPreferenceChanged(SharedPreferences preference, String key) {
        switch (key) {
            //Devlopper mode
            case "ModeDev":
                if (preference.getBoolean(key, false)) {
                    settings.findPreference("SettingDev").setVisible(true);
                } else {
                    settings.findPreference("SettingDev").setVisible(false);
                }
                break;
                //dark mode
            case "DarkMode":
                if (preference.getBoolean(key, false)) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                break;
                //music
            case "Musique":
                if(preference.getBoolean(key, false))
                {
                    //start music
                        music.start();
                }
                else
                {
                    //stop music
                        music.stop();
                        music.release();
                    music = MediaPlayer.create(this, R.raw.bodybreakdown);
                }
                    break;
        }
    }

    /**
     * to help with setting managment and preference managment
     */
    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }

}