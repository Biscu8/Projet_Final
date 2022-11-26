package com.example.projetfinal1st;

/**
 * Class that manages the score of the player
 */
public class Score {

    private int score;

    public Score() {
        this.score = 0;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore() {
        this.score++;
    }

}
