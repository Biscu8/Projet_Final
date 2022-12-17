package com.example.projetfinal1st;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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

    @Update
    void update(Save... saves);
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
