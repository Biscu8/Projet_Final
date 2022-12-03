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

        // Retrieve Score object
        Score score = (Score) getIntent().getSerializableExtra("score");

        // Initiate the score of the user
        if (score == null) {
            score = new Score();
        }
        score.updateScore(this);

        // Normal, hand clicker
        Score finalScore = score;
        findViewById(R.id.ClickButton).setOnClickListener(view -> {
            finalScore.incrementScore();
            finalScore.updateScore(this);
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