package com.example.projetfinal1st;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executors;

import kotlinx.coroutines.flow.Flow;

public class MyViewModelGame extends AndroidViewModel {
    UserRepository userRepository;
    Score score;
    public MyViewModelGame(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    /**
     * get the score from the database
     * @param username player username
     * @return the score
     */
    public Score getSave(String username)
    {
            return userRepository.getSave(username);
    }
    public String getMoneyAmount(String username)
    {
        return userRepository.getMoneyAmount(username);
    }
    /**
     * register the save in the database
     * @param save user save
     */
    public void setSave(Save save)
    {
      userRepository.insert(save);
    }
    public void updateSave(Save save)
    {
        userRepository.update(save);
    }
}

