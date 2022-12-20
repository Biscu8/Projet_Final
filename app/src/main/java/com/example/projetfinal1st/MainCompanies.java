package com.example.projetfinal1st;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class MainCompanies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companies);

        // Get Intent
        Intent intent = getIntent();

        // Retrieve recycler view
        RecyclerView recyclerView = findViewById(R.id.reyclerViewCompanies);

        // Retrieve ArrayList
        Bundle bundle = intent.getBundleExtra("bundle");
        ArrayList<String> dataSet = (ArrayList<String>) bundle.getSerializable("arrayList");

        // Initiate recycler view
        AdapterCompanies adapterCompanies = new AdapterCompanies(dataSet);
        recyclerView.setAdapter(adapterCompanies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}