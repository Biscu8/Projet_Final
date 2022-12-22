package com.example.projetfinal1st;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Upgrade")
public class EntityUpgrade {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int upgradeId;

    @ColumnInfo(name = "UserId")
    public String userId;

    @ColumnInfo(name = "Upgrade name")
    public String name;

    private boolean bought;
    private String description;
    private int price;
    private int image;

    public EntityUpgrade() {
        this.userId = "";
        this.name = "";
        this.bought = false;
        this.description = "";
        this.price = 0;
        this.image = 0;
    }

    public EntityUpgrade(String userId, String name, boolean bought, String description, int price, int image) {
        this.userId = userId;
        this.name = name;
        this.bought = bought;
        this.description = description;
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

    public boolean getBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setImage(int image) {
        this.image = image;
    }

}
