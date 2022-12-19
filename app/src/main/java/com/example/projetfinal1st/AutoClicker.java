package com.example.projetfinal1st;

import android.app.Activity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class to create an auto clicker with parameters
 */
public class AutoClicker {

    private transient Activity activity;
    private transient int score;
    private int rate;

    /**
     * Constructor for an auto clicker
     * @param activity Activity
     * @param score Score
     * @param rate Int rate at which the score increases
     */
    public AutoClicker(Activity activity, int score, int rate) {
        this.activity = activity;
        this.score = score;
        this.rate = rate;
        start();
    }

    /**
     * Setter of the rate of the autoclicker
     * @param rate Rate of the autoclicker
     */
    public void setRate(int rate) {
        this.rate = rate;
    }

    public void incrementScore(Score score, int rate) {
        //score.setScore(rate + score.getScore());
        //TODO regler cette fonction qui fait crash(setScore)
    }

    /**
     * Function to start the timer after creating an auto clicker
     */
    public void start() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //incrementScore(score, rate);
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

}
