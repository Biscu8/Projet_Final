package com.example.projetfinal1st;

import com.google.gson.Gson;

import java.util.ArrayList;

public class TypeConverter {
    /**
     * convert the score to string to input in database
     * @param score player score
     * @return player score as a string
     */
    @androidx.room.TypeConverter
    public String ScoreToString(Score score)
    {
        return new Gson().toJson(score.getScore());
    }

    /**
     * convert the string value of the score to score to extrude the score
     * @param score player score
     * @return player score
     */
    @androidx.room.TypeConverter
    public Score StringToScore(String score)
    {
            return new Gson().fromJson(score, Score.class);
    }
}
