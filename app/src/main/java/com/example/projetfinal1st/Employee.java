package com.example.projetfinal1st;

public class Employee {

    private String name;
    private String description;
    private int image;
    private int quantity;
    private int rate;
    private Score score;

    /**
     * Default constructor
     */
    public Employee() {
        this.name = "";
        this.description = "";
        this.image = 0;
        this.quantity = 0;
        this.rate = 0;
    }

    /**
     * Constructor for an employee (autoclicker)
     * @param name String of the name of the employee
     * @param description String of the description of the employee
     * @param image Int of the R.drawable.image of the employee
     * @param quantity Int of the quantity of employees
     * @param rate Int of the rate of the employee (clicks per operations)
     */
    public Employee(String name, String description, int image, int quantity, int rate) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.quantity = quantity;
        this.rate = rate;
    }

    /**
     * Getter of the name of the employee
     * @return String of name
     */
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

}
