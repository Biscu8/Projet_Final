package com.example.projetfinal1st;

import android.app.Activity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class to create an auto clicker with parameters
 */
public class AutoClicker {

    private int timer;
    private Activity activity;
    private Score score;

    /**
     * Constructor for an auto clicker
     * @param timer Int in ms, rate at which the clicker adds 1 to the score
     * @param activity Activity
     * @param score Score
     */
    public AutoClicker(int timer, Activity activity, Score score) {
        this.timer = timer;
        this.activity = activity;
        this.score = score;
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
                        score.incrementScore();
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, this.timer);
    }

}
