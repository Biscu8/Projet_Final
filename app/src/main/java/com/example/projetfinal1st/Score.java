package com.example.projetfinal1st;

import java.io.Serializable;
/**
 * Class that manages the score of the player
 */
public class Score implements Serializable {

    public int score;

    /**
     * Default constructor
     */
    public Score(int scores) {
        this.score = scores;
    }

    public Score() {
        this.score = 0;
    }

    /**
     * Getter to get the score
     * @return Int of score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Setter to set the score
     * @param score Int of score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Functon to increment the score by 1
     */
    public void incrementScore() {
        this.score++;
    }

}
