package com.example.projetfinal1st;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

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
        userRepository.registerUser(new User(username, password));
    }
    /**
     * Function to check whether the user is already in the database or not
     * @param username String of username
     * @return boolean
     */
    public User userInDatabase(String username) {
        return userRepository.userInDatabase(username);
    }

    public User loginFromUserPassword(String username, String password) {
        return userRepository.loginFromUserAndPassword(username, password);
    }

}
