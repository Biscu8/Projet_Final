package com.example.projetfinal1st;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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
    @androidx.room.TypeConverter
    public String ArrayListEmployeeToString(ArrayList<EntityEmployee> EmployeeArrayList){
        Gson gson = new Gson();
        String json = gson.toJson(EmployeeArrayList);
        return json;
    }

    @androidx.room.TypeConverter
    public ArrayList<EntityEmployee> StringToArrayListEmployee(String EmployeeString){
        Type listType = new TypeToken<ArrayList<EntityEmployee>>() {}.getType();
        return new Gson().fromJson(EmployeeString, listType);
    }
}
