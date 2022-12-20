package com.example.projetfinal1st;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Employee")
public class EntityEmployee {

    @PrimaryKey
    @NonNull
    public String Employeeid;

    @ColumnInfo(name="Employee name")
    public String name;
    @ColumnInfo(name="CountNumber")
    public int number;

    public EntityEmployee()
    {
        this.name = "";
        this.number = 0;
    }
}
