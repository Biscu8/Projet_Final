package com.example.projetfinal1st;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

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
               //a mettre lors de lenregistrement preferences.edit().putString("Username", Username).apply();
        myViewModelGame = new ViewModelProvider(this).get(MyViewModelGame.class);
        // Initiate the score of the user
        if(myViewModelGame.getSave(username) != null) {
            score = myViewModelGame.getSave(username);
        }
        else
        {
            Save save = new Save(username);
            myViewModelGame.setSave(save);
            score = new Score(0);
        }
        score.updateScore(this, preferences);

        // Normal, hand clicker
        Score finalScore = score;
        findViewById(R.id.ClickButton).setOnClickListener(view -> {
            finalScore.incrementScore();
            finalScore.updateScore(this, preferences);
        });

        // Open employees tab
        // TODO share score between activities
        Score finalScore1 = score;
        findViewById(R.id.button2).setOnClickListener(view -> {
            Intent intent = new Intent(this, MainEmployee.class);
            intent.putExtra("autoclicker", new AutoClicker(this, finalScore1, 0));
            //intent.putExtra("score", (Serializable) finalScore1);
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