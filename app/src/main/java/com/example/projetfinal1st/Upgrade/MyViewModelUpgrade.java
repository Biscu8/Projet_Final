package com.example.projetfinal1st.Upgrade;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.projetfinal1st.Companies.CompaniesRepository;
import com.example.projetfinal1st.Companies.EntityCompanies;

import java.util.List;

public class MyViewModelUpgrade extends AndroidViewModel {

    UpgradeRepository upgradeRepository;
    public MyViewModelUpgrade(@NonNull Application application) {
        super(application);
        upgradeRepository = new UpgradeRepository(application);
    }

    /**
     * insert Upgrades in database
     * @param entityUpgrade
     */
    public void insert(EntityUpgrade entityUpgrade)
    {
        upgradeRepository.insert(entityUpgrade);
    }

    public List<EntityUpgrade> getAllUpgrades(String id)
    {
        return upgradeRepository.getAllUpgrades(id);
    }

    public void udpate(EntityUpgrade upgrade)
    {
        upgradeRepository.update(upgrade);
    }
}
