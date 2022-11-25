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
     * Function to insert users in the database
     * @param user User object
     */
    public void insert(User user) {
        Executors.newSingleThreadExecutor().execute(() -> {
            userDao.insert(user);
        });
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

    /**
     * Function to delete users from the database
     * @param user User object
     */
    public void delete(User user) {
        Executors.newSingleThreadExecutor().execute(() -> {
            userDao.delete(user);
        });
    }

    /**
     * Function to delete saves from the database
     * @param save Save object
     */
    public void delete(Save save) {
        Executors.newSingleThreadExecutor().execute(() -> {
            userDao.delete(save);
        });
    }

    public Boolean userNameExist(String username) {
        Executors.newSingleThreadExecutor().execute(() -> {
            userDao.getUsername(username);
        });
    }
}
