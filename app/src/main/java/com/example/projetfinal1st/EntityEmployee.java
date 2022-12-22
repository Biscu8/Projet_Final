package com.example.projetfinal1st;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Employee")
public class EntityEmployee {

        @PrimaryKey(autoGenerate = true)
        @NonNull
        public int Employeeid;

        @ColumnInfo(name="Userid")
        public String Userid;
        @ColumnInfo(name="Employee name")
        public String name;

         private String description;
         private int image;
          private int quantity;
        private int rate;
         private int price;

        public EntityEmployee()
        {
            //this.name = "";
            this.setQuantity(0);
        }
        public EntityEmployee(String username, int quantity)
        {
            this.Userid = username;
            this.setQuantity(quantity);
        }
        //public EntityEmployee()
        public EntityEmployee(int quantity, String name, String username, String description, int rate, int price, int image)
        {
            this.name = name;
            this.Userid = username;
            this.setName(name);
            this.setQuantity(quantity);
            this.setDescription(description);
            this.setImage(image);
            this.setPrice(price);
            this.setRate(rate);
        }
    public String getName() {
        return name;
    }

    /**
     * Setter of the name of the employee
     * @param name String of the name of the employee
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of the description of the employee
     * @return String of the description of the employee
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter of the description of the employee
     * @param description String of the description of the employee
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter of the image of the employee
     * @return Int of the R.drawable.image of the employee
     */
    public int getImage() {
        return image;
    }

    /**
     * Setter of the image of the employee
     * @param image Int of the R.drawable.image of the employee
     */
    public void setImage(int image) {
        this.image = image;
    }

    /**
     * Getter of the quantity of employees
     * @return Int of the quantity of employees
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter of the quantity of employees
     * @param quantity Int of the quantity of employees
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Getter of the rate of the employee
     * @return Int of the rate in clicks per operation
     */
    public int getRate() {
        return rate;
    }

    /**
     * Setter of the rate of the employee
     * @param rate Int of the rate in clicks per operation
     */
    public void setRate(int rate) {
        this.rate = rate;
    }

    /**
     * Getter of the price of the employee
     * @return Int of the price of the employee
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Setter of the price of the employee
     * @param price Int of the price of the employee
     */
    public void setPrice(int price) {
        this.price = price;
    }

    }
