package com.example.projetfinal1st;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import kotlinx.coroutines.flow.Flow;

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
    public int getMoneyAmount(String username)
    {
        return userDao.getMoneyAmount(username);
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
            userDao.update(save);
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

    public int getUserCount()
    {
        ArrayList<Save> userList = UserDao.getAllUser();//???
        return userList.size();
    }
    public void insert(EntityEmployee employee)
    {
        userDao.insert(employee);
    }
    public List<EntityEmployee> getAllEmployeeWithSameId(String id)
    {
        return userDao.getAllEmployeeWithSameId(id);
    }
    public void udpateEmployee(EntityEmployee employee)
    {
        userDao.updateEmployee(employee);
    }
}
