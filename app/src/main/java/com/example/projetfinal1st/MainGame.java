package com.example.projetfinal1st;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import java.util.concurrent.Executors;

public class MainGame extends AppCompatActivity {
    private SharedPreferences preferences;
    private MyViewModelGame myViewModelGame;
    private Score score;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        username = preferences.getString("Username", "");
        myViewModelGame = new ViewModelProvider(this).get(MyViewModelGame.class);
        // Initiate the score of the user
        Executors.newSingleThreadExecutor().execute(() -> {
        if(myViewModelGame.getSave(username) != null) {
                score = myViewModelGame.getSave(username);
            score.updateScore(this, preferences);
        }
        else
        {
            score = new Score(0);
                Save save = new Save(username);
                myViewModelGame.setSave(save);
            score.updateScore(this, preferences);

        }
        });

        // Normal, hand clicker
        findViewById(R.id.ClickButton).setOnClickListener(view -> {
            if(score != null) {
                score.incrementScore();
                score.updateScore(this, preferences);
            }
        });

        // Open employees tab
        // TODO share score between activities with database
        Score finalScore1 = score;
        findViewById(R.id.button2).setOnClickListener(view -> {
            Intent intent = new Intent(this, MainEmployee.class);
            intent.putExtra("autoclicker", new AutoClicker(this, finalScore1, 0));
            startActivity(intent);
        });

        // Open upgrades tab
        findViewById(R.id.button3).setOnClickListener(view -> {
            Intent intent = new Intent(this, MainUpgrade.class);
            startActivity(intent);
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.menu_about:
                //TODO menu About
                break;
            case R.id.menu_disconnect:
                System.exit(0);
                //TODO sauvegarder la progression
                break;
            case R.id.menu_setting:
                //TODO menu Settings
                Intent intent = new Intent(this, Settings.class);
                startActivity(intent);
                break;
            case R.id.menu_quit:
                //TODO menu Quit
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}