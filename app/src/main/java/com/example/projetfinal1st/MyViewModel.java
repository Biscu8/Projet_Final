package com.example.projetfinal1st;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

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

    /**
     * Function to check whether the user is already in the database or not
     * @param username String of username
     * @return boolean
     */
    public boolean isUserInDatabase(String username) {
        return userRepository.isUserInDatabase(username);
    }

    /**
     * Function to check whether the password is correct
     * @param password String of password
     * @param username String of username
     * @return boolean
     */
    public boolean isPasswordCorrect(String password, String username) {
        return userRepository.isPasswordCorrect(password, username);
    }
}
