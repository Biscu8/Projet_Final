package com.example.projetfinal1st;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

/**
 * Database Access Object to access data from the database
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM user WHERE username = :username")
    public User userInDatabase(String username);

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    public LiveData<User> loginFromUserAndPassword(String username, String password);

    @Insert
    void registerUser(User user);

    @Query("SELECT score FROM 'Save' WHERE username =:username")
    public LiveData<Score> getSave(String username);

    /**
     * Add a user to the database
     * @param users User to insert in database
     */
    @Insert
    void insert(User... users);

    @Insert
    void insert(Save... saves);

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
