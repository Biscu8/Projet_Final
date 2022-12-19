package com.example.projetfinal1st;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;

public class MainGame extends AppCompatActivity {
    private SharedPreferences preferences;
    private MyViewModelGame myViewModelGame;
    private Score score;
    private String username;
    @SuppressLint("SetTextI18n")
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
            //Update UI with text database moneyAmount
               int clickAmount = myViewModelGame.getSave(username).getScore();
               TextView clickAmountView = findViewById(R.id.clickAmount);
               clickAmountView.setText(String.valueOf(clickAmount));
               //verify if the user is oppening the app or is coming back from the employee tab
            TextView moneyAmount = findViewById(R.id.moneyAmount);
                if(getIntent().getStringExtra("MoneyMinusBuy") != null)
                {
                    //update UI with MoneyAmount
                    moneyAmount.setText(getIntent().getStringExtra("MoneyMinusBuy"));
                    //remove Extra data
                    getIntent().removeExtra("MoneyMinusBuy");
                }
                else
                {
                    //Update UI with database data
                    moneyAmount.setText(myViewModelGame.getMoneyAmount(username));
                }
                //update data with new data
            saveGameInDatabase();
            // if preference infinite money is check udpate UI
            if(preferences.getBoolean("InfiniteMoney", false))
            {
                moneyAmount.setText("Infinite Money");
            }
        }
        else
        {
            //put all preferences to default
            preferences.getAll().clear();//pas sur
            //if there is no user initiate score to 0 and create a new save
            score = new Score(0);
                Save save = new Save(username);
                myViewModelGame.setSave(save);
        }
        });

        // Normal, hand clicker
        findViewById(R.id.ClickButton).setOnClickListener(view -> {
            //Update clickAmount view + 1
            TextView clickAmount = findViewById(R.id.clickAmount);
            int m_score = Integer.parseInt(clickAmount.getText().toString()) + 1;
            clickAmount.setText(String.valueOf(m_score));
            if(!preferences.getBoolean("InfiniteMoney", false)) {
                //Update moneyAmount view + 10
                TextView moneyAmount = findViewById(R.id.moneyAmount);
                String moneyAmountString = String.valueOf(moneyAmount.getText()).substring(0, String.valueOf(moneyAmount.getText()).length() -1);
                int moneyAmountPlusTen = Integer.parseInt(moneyAmountString) + 10;
                moneyAmount.setText(String.valueOf(moneyAmountPlusTen) + "$");
                //start a new thread
                Executors.newSingleThreadExecutor().execute(() -> {
                    //update database with the new data
                    Save save = new Save(username, Integer.parseInt(String.valueOf(clickAmount.getText())), String.valueOf(moneyAmount.getText()));
                    myViewModelGame.updateSave(save);
                });
            }
        });

        // Open employees tab
        // TODO share score between activities with database
        findViewById(R.id.button2).setOnClickListener(view -> {
            Intent intent = new Intent(this, MainEmployee.class);
            intent.putExtra("autoclicker", new AutoClicker(this, score, 0));
            TextView money = findViewById(R.id.moneyAmount);
            //put score in database
            saveGameInDatabase();
            String money1 = (String) money.getText();
            intent.putExtra("Money", money1);
            startActivity(intent);
        });

        // Open upgrades tab
        findViewById(R.id.button3).setOnClickListener(view -> {
            Intent intent = new Intent(this, MainUpgrade.class);
            startActivity(intent);
        });

        // Update score each seconds
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView clickAmount = findViewById(R.id.clickAmount);
                        TextView moneyAmount = findViewById(R.id.moneyAmount);
                        int m_score = Integer.parseInt(clickAmount.getText().toString());
                        Executors.newSingleThreadExecutor().execute(() -> {
                            myViewModelGame.getSave(username).setScore(m_score);
                        });
                        clickAmount.setText(String.valueOf(m_score));
                        moneyAmount.setText(String.valueOf(m_score));
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);

    }
    public void saveGameInDatabase()
    {
        TextView viewScore = findViewById(R.id.clickAmount);
        String stringScore =(String) viewScore.getText();
        TextView viewAmount = findViewById(R.id.moneyAmount);
        String moneyAmount = (String) viewAmount.getText();
        Save save = new Save(preferences.getString("Username", ""), Integer.parseInt(stringScore), moneyAmount);
        Executors.newSingleThreadExecutor().execute(() -> {
            myViewModelGame.updateSave(save);
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
                Intent intentMainPage = new Intent(this, MainActivity.class);
                saveGameInDatabase();
                startActivity(intentMainPage);
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