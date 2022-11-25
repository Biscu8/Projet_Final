package com.example.projetfinal1st;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class to save data and write it to a folder under username and password
 */
public class SaveData {

    String username;
    String password;
    boolean isDarkModeOn;

    /**
     * Default constructor
     */
    SaveData(){
        this.username = null;
        this.password = null;
        this.isDarkModeOn = true;
    }

    /**
     * Constructor with username and password
     * @param username String username inputted
     * @param password String password inputted
     */
    SaveData(String username, String password) {
        readSaveFile(username, password);
    }

    /**
     * Function to get users username
     * @return String of username
     */
    public String getUserName() {
        return this.username;
    }

    /**
     * Function to get users password
     * @return String of password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Function to get if dark mode is enabled or not
     * @return Boolean of dark mode
     */
    public boolean getIsDarkModeOn() {
        return this.isDarkModeOn;
    }

    /**
     * Function to set users username
     * @param username String of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Function to set users password
     * @param password String of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Function to set dark mode enable or not
     * @param isDarkModeOn Boolean of dark mode
     */
    public void setIsDarkModeOn(boolean isDarkModeOn) {
        this.isDarkModeOn = isDarkModeOn;
    }

    /**
     * Read the save file and checking if username and password are correct
     * @param username String of users username
     * @param password String of users password
     */
    public void readSaveFile(String username, String password) {
        File file = new File("saveFile.txt"); // Look for file saveFile.txt
        try (Scanner scanner = new Scanner(file)) {
            while (!username.equals(scanner.nextLine()) && scanner.hasNextLine()) { // Look for username in database
                scanner.nextLine();
            }
            if (password.equals(scanner.nextLine())) { // Look if password matches username in database
                scanner.nextLine();
                this.isDarkModeOn = Boolean.parseBoolean(scanner.nextLine()); // User preference
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}