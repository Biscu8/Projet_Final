package com.example.projetfinal1st;

import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.TextView;

import androidx.preference.PreferenceManager;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Class that manages the score of the player
 */
public class Score implements Serializable {

    public int score;

    /**
     * Default constructor
     */
    public Score(int scores) {
        this.score = scores;
    }

    /**
     * Getter to get the score
     * @return Int of score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Setter to set the score
     * @param score Int of score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Functon to increment the score by 1
     */
    public void incrementScore() {
        this.score++;
    }

    /**
     * Function to update the score each seconds
     * @param activity Activity
     */
    public void updateScore(Activity activity,SharedPreferences preferences) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView textView = activity.findViewById(R.id.textView);
                        TextView money = activity.findViewById(R.id.MoneyAmount);
                        textView.setText(String.valueOf(getScore()));
                       // score = Integer.valueOf(String.valueOf(textView.getText()));
                        textView.setText(String.valueOf(getScore()));
                        if(preferences.getBoolean("ModeDev", false) && preferences.getBoolean("InfiniteMoney", false))
                        {
                            money.setText("Infinite Money");
                        }
                        else
                        {
                            money.setText(String.valueOf(getScore() * 10 + "$"));
                        }
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

}
