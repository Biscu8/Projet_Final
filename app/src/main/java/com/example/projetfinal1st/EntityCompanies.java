package com.example.projetfinal1st;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Company")
public class EntityCompanies implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int companyId;

    @ColumnInfo(name = "UserId")
    public String userId;

    @ColumnInfo(name = "Company name")
    public String name;

    private boolean bought;
    private int nbEmployees;
    private int price;
    private int image;

    public EntityCompanies() {
        this.userId = "";
        this.name = "";
        this.bought = false;
        this.nbEmployees = 0;
        this.price = 0;
        this.image = 0;
    }

    public EntityCompanies(String userId, String name, boolean bought, int nbEmployees, int price, int image) {
        this.userId = userId;
        this.name = name;
        this.bought = bought;
        this.nbEmployees = nbEmployees;
        this.price = price;
        this.image = image;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNbEmployees() {
        return nbEmployees;
    }

    public void setNbEmployees(int nbEmployees) {
        this.nbEmployees = nbEmployees;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
