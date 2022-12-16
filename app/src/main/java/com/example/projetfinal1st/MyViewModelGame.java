package com.example.projetfinal1st;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.Executors;

public class MyViewModelGame extends AndroidViewModel {
    UserRepository userRepository;
    Score score;
    public MyViewModelGame(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public Score getSave(String username)
    {
            return userRepository.getSave(username);
    }
    public void setSave(Save save)
    {
      userRepository.insert(save);
    }
}

