package com.example.projetfinal1st.Companies;

import android.app.Application;

import com.example.projetfinal1st.UserDatabase;

import java.util.List;
import java.util.concurrent.Executors;

public class CompaniesRepository {

    private final CompaniesDao companiesDao;

    public CompaniesRepository(Application application) {
        UserDatabase database = UserDatabase.getDatabase(application);
        companiesDao = database.companiesDao();
    }

    /**
     * insert the compagnie in the database
     * @param entityCompanies
     */
    public void insert(EntityCompanies entityCompanies) {
        Executors.newSingleThreadExecutor().execute(() -> {
            companiesDao.insert(entityCompanies);
        });
    }
    public List<EntityCompanies> getAllCompanies(String id)
    {
        return companiesDao.getAllCompanies(id);
    }

    public void update(EntityCompanies companies)
    {
        companiesDao.udpate(companies);
    }
}
