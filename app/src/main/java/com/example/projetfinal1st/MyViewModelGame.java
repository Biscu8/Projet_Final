package com.example.projetfinal1st;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executors;

import kotlinx.coroutines.flow.Flow;

public class MyViewModelGame extends AndroidViewModel {
    UserRepository userRepository;
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
    public int getMoneyAmount(String username)
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
    public void updateSave(Save save) {
        userRepository.update(save);
    }

    public int getUserCount() {
        return userRepository.getUserCount();
    }


    public void insert(EntityEmployee employee)
    {
        userRepository.insert(employee);
    }
    public List<EntityEmployee> getAllEmployeeWithSameId(String id)
    {
        return userRepository.getAllEmployeeWithSameId(id);
    }
    public void udpateEmployee(EntityEmployee employee)
    {
        userRepository.udpateEmployee(employee);
    }

    public synchronized void increment(String username, int rate) {
        if(getSave(username).getScore() - rate >= rate) {
            Save save = new Save(username, getSave(username).getScore() - rate, getMoneyAmount(username) + rate);
            updateSave(save);
        }
    }
}

