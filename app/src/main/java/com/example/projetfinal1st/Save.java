package com.example.projetfinal1st;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.util.ArrayList;

/**
 * Class save containing a save game tied to a username
 */
@Entity
public class Save {

    @PrimaryKey
    @NonNull
    public String username;

    @ColumnInfo(name = "score")
    public int score;

    @ColumnInfo(name = "money")
    public int money;

    @ColumnInfo(name ="EmployeeIdlink")
    public String entitytab;


    /**
     * Default constructor
     */
    public Save() {
        this("");
    }

    /**
     * Constructor with info of save
     *
     * @param username String of username
     */
    public Save(String username) {
        this.username = username;
        this.score = 0;
    }

    public Save(String username, int scores, int money) {
        this.username = username;
        this.score = scores;
        this.money = money;
    }
    public Save(String username, String id)
    {
        this.username = username;
        this.entitytab = id;
    }
    public Save(String username, int money)
    {
        this.username = username;
        this.money = money;
    }
}
