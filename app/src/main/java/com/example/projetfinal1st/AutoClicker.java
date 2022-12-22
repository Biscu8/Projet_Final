package com.example.projetfinal1st;

import android.app.Activity;
import android.view.View;

import androidx.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;

/**
 * Class to create an auto clicker with parameters
 */
public class AutoClicker {

    private transient Activity activity;
    private MyViewModelGame viewModel;
    private transient String username;
    private int rate;

    /**
     * Constructor for an auto clicker
     * @param activity Activity
     * @param username String of username for database
     * @param rate Int rate at which the score increases
     */
    public AutoClicker(Activity activity, MyViewModelGame viewModel, String username, int rate) {
        this.activity = activity;
        this.viewModel = viewModel;
        this.username = username;
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
                Executors.newSingleThreadExecutor().execute(() -> {
                    viewModel.getSave(username).setScore(viewModel.getSave(username).getScore() - rate);
                    //viewModel.setMoneyAmount(username, viewModel.getMoneyAmount(username) + 1);
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

}
