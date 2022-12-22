package com.example.projetfinal1st.Companies;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CompaniesDao {

    /**
     * get a list of the users companies
     * @param id
     * @return
     */
    @Query("SELECT * FROM 'Company' WHERE UserId=:id")
    public List<EntityCompanies> getAllCompanies(String id);
    /**
     * insert the compagnies in database
     */
    @Insert
    void insert(EntityCompanies...entityCompanies);

    /**
     * update the compagnies in database
     * @param entityCompanies
     */
    @Update
    void udpate(EntityCompanies...entityCompanies);
}
