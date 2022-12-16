package com.example.projetfinal1st;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Class save containing a save game tied to a username
 */
@Entity
public class Save {

    @PrimaryKey
    @NonNull
    public String username;

    @ColumnInfo(name="score")
    public Score score;

    /**
     * Default constructor
     */
    public Save() {
        this("");
    }

    /**
     * Constructor with info of save
     * @param username String of username
     */
    public Save(String username) {
        this.username = username;
        this.score = new Score(0);
    }

}
