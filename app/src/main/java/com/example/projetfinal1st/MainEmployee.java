package com.example.projetfinal1st;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainEmployee extends AppCompatActivity {
    MyViewModelGame myViewModelGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);
        Intent intent = getIntent();
        myViewModelGame = new ViewModelProvider(this).get(MyViewModelGame.class);

        //retrieve recyclerView
        RecyclerView recyclerView = findViewById(R.id.employeeRecyclerView);
        // Retrieve arrayList
        Bundle args = intent.getBundleExtra("bundle");
        ArrayList<Employee> dataSet = (ArrayList<Employee>) args.getSerializable("arrayList");

        // Initiate recycler view
        String money = (String) getIntent().getStringExtra("Money");
        //get the employee count number from database
        MyAdapter myAdapter = new MyAdapter(dataSet, money);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //connect the back button
        Button back = findViewById(R.id.Back);
        back.setOnClickListener(view -> {
            Intent secondIntent = new Intent(this, MainGame.class);
            //register an id for the employees of the user link to is username
           // Save save = new Save(getIntent().getStringExtra("Username"), );
          //  myViewModelGame.updateSave(save);
            //Use the same id to register each employee in the employee tab
            secondIntent.putExtra("MoneyMinusBuy", myAdapter.getMoney());
            secondIntent.putExtra("nbEmployer", myAdapter.getEmployeeCountNumber());
            startActivity(secondIntent);
        });

    }
}
