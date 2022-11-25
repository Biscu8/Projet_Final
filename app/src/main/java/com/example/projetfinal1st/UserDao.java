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
     * Add a user to the database
     * @param users User to insert in database
     */
    @Insert
    void insert(User... users);

    /**
     * Remove a user from the database
     * @param user User to remove from database
     */
    @Delete
    void delete(User user);
}
