package com.example.projetfinal1st;

import androidx.core.location.LocationRequestCompat;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

/**
 * Database Access Object to access data from the database
 */
@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable registerUser(User user);

    @Update
    public Completable updateUser(List<User> users);

    @Delete
    public Completable deleteUser(List<User> users);

    @Query("SELECT * FROM user WHERE username = :username")
    public User userInDatabase(String username);

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    public User loginFromUserAndPassword(String username, String password);

    /**
     * Query to get usernames from database
     * @param username String of username
     * @return String of username
     */
    @Query("SELECT username FROM 'User' WHERE username = :username")
    boolean isUserInDatabase(String username);

    @Query("SELECT password FROM 'User' WHERE username = :username AND password = :password")
    boolean isPasswordCorrect(String password, String username);

    @Query("SELECT score FROM 'Save' WHERE username =:username")
     Score getScore(String username);
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
