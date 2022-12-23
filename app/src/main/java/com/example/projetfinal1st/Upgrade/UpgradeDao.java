package com.example.projetfinal1st.Upgrade;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.projetfinal1st.Companies.EntityCompanies;

import java.util.List;

@Dao
public interface UpgradeDao {

    @Query("SELECT * FROM 'Upgrade' WHERE UserId=:id")
    public List<EntityUpgrade> getAllUpgrades(String id);
    /**
     * Insert Upgrade in database
     * @param entityUpgrades
     */
    @Insert
    void insert(EntityUpgrade... entityUpgrades);

    /**
     * update the Upgrades in database
     * @param entityUpgrades
     */
    @Update
    void udpate(EntityUpgrade... entityUpgrades);
}
