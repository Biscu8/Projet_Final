package com.example.projetfinal1st;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainPageJeu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_jeu);

        // Initiate the score of the user
        Score score = new Score();

        // Normal, hand clicker
        findViewById(R.id.button).setOnClickListener(view -> {
            score.incrementScore();
            score.updateScore(this);
        });

        // How to implement autoclickers
        // TODO delete before sending this shit
        findViewById(R.id.button2).setOnClickListener(view -> {
            AutoClicker autoClicker = new AutoClicker(1000, this, score);
            autoClicker.start();
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

                break;
            case R.id.menu_quit:
                //TODO menu Quit
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}