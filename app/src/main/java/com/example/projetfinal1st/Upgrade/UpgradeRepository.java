package com.example.projetfinal1st.Upgrade;

import android.app.Application;

import com.example.projetfinal1st.Companies.CompaniesDao;
import com.example.projetfinal1st.Companies.EntityCompanies;
import com.example.projetfinal1st.UserDatabase;

import java.util.List;
import java.util.concurrent.Executors;

public class UpgradeRepository {
    private final UpgradeDao upgradeDao;

    public UpgradeRepository(Application application) {
        UserDatabase database = UserDatabase.getDatabase(application);
        upgradeDao = database.upgradeDao();
    }

    /**
     * insert the Ugrades in the database
     * @param entityUpgrade
     */
    public void insert(EntityUpgrade entityUpgrade) {
        Executors.newSingleThreadExecutor().execute(() -> {
            upgradeDao.insert(entityUpgrade);
        });
    }
    public List<EntityUpgrade> getAllUpgrades(String id)
    {
        return upgradeDao.getAllUpgrades(id);
    }

    public void update(EntityUpgrade upgrades)
    {
        upgradeDao.udpate(upgrades);
    }
}
