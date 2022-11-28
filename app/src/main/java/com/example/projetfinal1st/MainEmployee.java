package com.example.projetfinal1st;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainEmployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);

        // Initiate recycler view
        ArrayList<Employee> dataSet = new ArrayList<>();
        dataSet.add(new Employee("George", "S'amuse au parc", R.drawable.dde9fd7a087fa4eaa70554ed266099dc, 0, 2));
        MyAdapter myAdapter = new MyAdapter(dataSet);
        RecyclerView recyclerView = findViewById(R.id.employeeRecyclerView);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
