package com.example.projetfinal1st;

import android.app.Application;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

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

    /**
     * Function to check whether the user is already in the database or not
     * @param username String of username
     * @return
     */
    public boolean isUserInDatabase(String username) {
        AtomicBoolean bool = new AtomicBoolean(false);
        Executors.newSingleThreadExecutor().execute(() -> {
            bool.set(userDao.isUserInDatabase(username));
        });
        return bool.get();
    }

    /**
     * Function to check whether the password is correct
     * @param password String of password
     * @param username String of password
     * @return boolean
     */
    public boolean isPasswordCorrect(String password, String username) {
        AtomicBoolean bool = new AtomicBoolean(false);
        Executors.newSingleThreadExecutor().execute(() -> {
            bool.set(userDao.isPasswordCorrect(password, username));
        });
        return bool.get();
    }
}
