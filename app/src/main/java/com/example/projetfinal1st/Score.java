package com.example.projetfinal1st;

import android.app.Activity;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class that manages the score of the player
 */
public class Score {

    private int score;

    /**
     * Default constructor
     */
    public Score() {
        this.score = 0;
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
    public void updateScore(Activity activity) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView textView = activity.findViewById(R.id.textView);
                        textView.setText(String.valueOf(getScore()));
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

}
