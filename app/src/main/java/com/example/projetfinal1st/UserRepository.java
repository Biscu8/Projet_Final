package com.example.projetfinal1st;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executors;

import io.reactivex.rxjava3.core.Single;

/**
 * Repository class for database
 */
public class UserRepository {

    private final UserDao userDao;

    /**
     * Function to set the database to this application
     * @param application Application
     */
    public UserRepository(Application application) {
        UserDatabase database = UserDatabase.getDatabase(application);
        userDao = database.userDao();
    }

    /**
     * Function to insert users in the database
     * @param user User object
     */
    public void registerUser(User user) {
        userDao.registerUser(user);
    }

    /**
     * Function to insert saves in the database
     * @param save Save object
     */
    public void insert(Save save) {
        Executors.newSingleThreadExecutor().execute(() -> {
            userDao.insert(save);
        });
    }

    public User loginFromUserAndPassword(String username, String password) {
        return userDao.loginFromUserAndPassword(username, password);
    }

    public User userInDatabase(String username) {
        return userDao.userInDatabase(username);
    }
}
