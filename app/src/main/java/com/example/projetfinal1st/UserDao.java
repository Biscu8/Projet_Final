package com.example.projetfinal1st;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

/**
 * Database Access Object to access data from the database
 */
@Dao
public interface UserDao {

    /**
     * Query to get usernames from database
     * @param username String of username
     * @return String of username
     */
    @Query("SELECT username FROM 'User' WHERE username = :username ")
    String getUsername(String username);

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
