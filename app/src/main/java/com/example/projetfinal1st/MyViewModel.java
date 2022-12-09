package com.example.projetfinal1st;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.concurrent.atomic.AtomicBoolean;

public class MyViewModel extends AndroidViewModel {

    UserRepository userRepository;

    /**
     * Links repo with app
     * @param application Application of main app
     */
    public MyViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }
   public void registerUser(String username, String password)
   {
    userRepository.insert(new User(username, password));
   }
    /**
     * Function to check whether the user is already in the database or not
     * @param username String of username
     * @return boolean
     */
    public Boolean isUserInDatabase(String username) {
        return userRepository.isUserInDatabase(username).getValue();
    }

    /**
     * Function to check whether the password is correct
     * @param password String of password
     * @param username String of username
     * @return boolean
     */
    public boolean isPasswordCorrect(String password, String username) {
        return userRepository.isPasswordCorrect(password, username).getValue();
    }
}
