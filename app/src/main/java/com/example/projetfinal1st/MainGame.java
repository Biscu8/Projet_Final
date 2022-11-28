package com.example.projetfinal1st;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        // Initiate the score of the user
        Score score = new Score();

        // Normal, hand clicker
        findViewById(R.id.button).setOnClickListener(view -> {
            score.incrementScore();
            score.updateScore(this);
        });

        // Open employees tab
        findViewById(R.id.button2).setOnClickListener(view -> {
            Intent intent = new Intent(this, MainEmployee.class); // TODO CHARLE FIX CA STP
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
                Intent intent = new Intent(this,Setting.class);
                startActivity(intent);
                break;
            case R.id.menu_quit:
                //TODO menu Quit
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}