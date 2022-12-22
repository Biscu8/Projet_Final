package com.example.projetfinal1st.Companies;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import java.io.Closeable;
import java.util.List;

public class MyViewModelCompanies extends AndroidViewModel {

    CompaniesRepository companiesRepository;
    public MyViewModelCompanies(@NonNull Application application) {
        super(application);
        companiesRepository = new CompaniesRepository(application);
    }

    /**
     * insert companies in database
     * @param entityCompanies
     */
    public void insert(EntityCompanies entityCompanies)
    {
        companiesRepository.insert(entityCompanies);
    }

    public List<EntityCompanies> getAllCompanies(String id)
    {
        return companiesRepository.getAllCompanies(id);
    }

    public void udpate(EntityCompanies companies)
    {
        companiesRepository.update(companies);
    }
}
