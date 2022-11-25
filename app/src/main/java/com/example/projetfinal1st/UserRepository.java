package com.example.projetfinal1st;

import android.app.Application;

import java.util.concurrent.Executors;

/**
 * Repository class for database
 */
public class UserRepository {

    private UserDao userDao;

    /**
     * Function to set the database to this application
     * @param application Application
     */
    public UserRepository(Application application) {
        UserDatabase database = UserDatabase.getDatabase(application);
        userDao = database.userDao();
    }

    /**
     * Function to insert data in database
     * @param user User object
     */
    public void insert(User user) {
        Executors.newSingleThreadExecutor().execute(() -> {
            userDao.insert(user);
        });
    }

    /**
     * Function to delete data from the database
     * @param user User object
     */
    public void delete(User user) {
        Executors.newSingleThreadExecutor().execute(() -> {
            userDao.delete(user);
        });
    }

}
