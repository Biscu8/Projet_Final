package com.example.projetfinal1st;

import com.example.projetfinal1st.Score;
import com.google.gson.Gson;

public class TypeConverter {
    @androidx.room.TypeConverter
    public String ScoreToString(Score score)
    {
        return new Gson().toJson(score.getScore());
    }
    @androidx.room.TypeConverter
    public Score StringToScore(String score)
    {
        return new Gson().fromJson(score, Score.class);
    }
}
