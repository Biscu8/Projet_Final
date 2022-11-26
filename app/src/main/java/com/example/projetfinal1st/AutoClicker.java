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

    public AutoClicker(int timer, Activity activity, Score score) {
        this.timer = timer;
        this.activity = activity;
        this.score = score;
    }

    public void start(Score score) {
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
    }

}
