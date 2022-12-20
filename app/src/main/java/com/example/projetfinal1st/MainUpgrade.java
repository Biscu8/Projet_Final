package com.example.projetfinal1st;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainUpgrade extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_upgrade);

        // Get Intent
        Intent intent = getIntent();

        // Retrieve recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerViewUpgrade);

        // Retrieve ArrayList
        Bundle bundle = intent.getBundleExtra("bundle");
        ArrayList<String> dataSet = (ArrayList<String>) bundle.getSerializable("arrayList");

        // Initiate recycler view
        AdapterUpgrade adapterUpgrade = new AdapterUpgrade(dataSet);
        recyclerView.setAdapter(adapterUpgrade);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    //on
}