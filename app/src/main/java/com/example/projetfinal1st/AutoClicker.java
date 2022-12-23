package com.example.projetfinal1st;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class to create an auto clicker with parameters
 */
public class AutoClicker {

    private transient Activity activity;
    private MyViewModelGame viewModel;
    private transient String username;
    private int rate;
    private Timer timer;

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


    /**
     * Function to start the timer after creating an auto clicker
     */
    public void start() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Executors.newSingleThreadExecutor().execute(() -> {
                    viewModel.increment(username, rate);

                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    public void cancel() {
        timer.cancel();
    }

}
