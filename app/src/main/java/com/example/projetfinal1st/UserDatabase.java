package com.example.projetfinal1st;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

/**
 * Database of usernames and passwords
 */
@TypeConverters(TypeConverter.class)
@Database(entities = {User.class, Save.class, EntityEmployee.class}, version = 12)//update database if we change it
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    private static UserDatabase instance;

    /**
     * Function to create database
     * @param context Context of app
     * @return Database instance
     */
    public static UserDatabase getDatabase(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "user-database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
