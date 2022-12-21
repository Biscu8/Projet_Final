package com.example.projetfinal1st;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.projetfinal1st.Employee;

@Entity(tableName = "Employee")
public class EntityEmployee  extends Employee {

        @PrimaryKey(autoGenerate = true)
        @NonNull
        public int Employeeid;

        @ColumnInfo(name="Userid")
        public String Userid;
        @ColumnInfo(name="Employee name")
        public String name;

        public EntityEmployee()
        {
            this.name = "";
            this.setQuantity(0);
        }
        public EntityEmployee(int number, String name,String id)
        {
            this.name = name;
            this.Userid = id;
            this.setQuantity(number);
        }
    }
