package com.example.projetfinal1st;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Class user containing a username and a password from a user
 */
@Entity
public class User {

    @PrimaryKey
    @NonNull
    public String username;

    @ColumnInfo(name="password")
    public String password;

    /**
     * Default constructor
     */
    public User() {
        this.username = "";
        this.password = "";
    }

    /**
     * Constructor with info of user
     * @param username String of username
     * @param password String of password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
