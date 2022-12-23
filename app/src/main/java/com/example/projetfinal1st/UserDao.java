package com.example.projetfinal1st;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import kotlinx.coroutines.flow.Flow;

/**
 * Database Access Object to access data from the database
 */
@Dao
public interface UserDao {
    /**
     * get the user that fits the username
     * @param username player username
     * @return the user that fits the name
     */
    @Query("SELECT * FROM user WHERE username = :username")
    public User userInDatabase(String username);

    /**
     *
     * @param username player username
     * @param password player password
     * @return the user
     */
    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    public User loginFromUserAndPassword(String username, String password);

    @Insert
    void registerUser(User user);

    @Query("SELECT score FROM 'Save' WHERE username =:username")
    public Score getSave(String username);

    @Query("SELECT money FROM 'Save' WHERE username =:username")
    public int getMoneyAmount(String username);
    /**
     * Add a user to the database
     * @param users User to insert in database
     */
    @Insert
    void insert(User... users);

    /**
     * Add a save to the database
     * @param saves to insert in database
     */
    @Insert
    void insert(Save... saves);

    @Query("SELECT * FROM 'Save'")
    public static ArrayList<Save> getAllUser() {
        return null;
    }

    @Query("SELECT * FROM 'Employee' WHERE Userid =:id")
    public List<EntityEmployee> getAllEmployeeWithSameId(String id);
    @Update
    void update(Save... saves);
    @Insert
    void insert(EntityEmployee... EntityEmployees);

    @Update
    void updateEmployee(EntityEmployee... EntityEmployees);
    /**
     * Remove a user from the database
     * @param user User to remove from database
     */
    @Delete
    void delete(User user);

    /**
     * Remove a save from the database
     * @param save Save to remove from database
     */
    @Delete
    void delete(Save save);
}
