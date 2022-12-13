package com.example.projetfinal1st;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MyViewModelGame extends AndroidViewModel {
    UserRepository userRepository;
    public MyViewModelGame(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public LiveData<Score> getSave(String username)
    {
        return userRepository.getSave(username);
    }
    public void setSave(Save save)
    {
      userRepository.insert(save);
    }
}

