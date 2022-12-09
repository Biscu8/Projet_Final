package com.example.projetfinal1st;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class MyViewModelGame extends AndroidViewModel {
    private int m_score;
    private int m_argent;
    public MyViewModelGame(@NonNull Application application) {
        super(application);
    }

    public int getScore()
    {
        return m_score;
    }
    public int getArgent()
    {
        return m_argent;
    }
    public void setArgent(int argent)
    {
        m_argent = argent;
    }
    public void setScore(int score)
    {
        m_score = score;
    }
}

