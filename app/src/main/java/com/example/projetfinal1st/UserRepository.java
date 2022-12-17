package com.example.projetfinal1st;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.concurrent.Executors;

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
     * Function to get the score from the database save row
     * @param username the username of the player
     * @return the score
     */
    public Score getSave(String username) {
            return userDao.getSave(username);
}
    /**
     * Function to insert users in the database
     * @param user User object
     */
    public void registerUser(User user) {
        Executors.newSingleThreadExecutor().execute(() -> {
            userDao.insert(user);//test sinon remettre register user
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
    public void update(Save save) {
        Executors.newSingleThreadExecutor().execute(() -> {
            userDao.update(save);
        });
    }
    /**
     * login the player with the username and the password
     * @param username player username
     * @param password player password
     * @return
     */
    public User loginFromUserAndPassword(String username, String password) {
        return userDao.loginFromUserAndPassword(username, password);
    }

    /**
     * to get the user information from database
     * @param username player username
     * @return the user that fits the username
     */
    public User userInDatabase(String username) {
        return userDao.userInDatabase(username);
    }
}
