package com.example.projetfinal1st;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainEmployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);
        //retrieve recyclerView
        RecyclerView recyclerView = findViewById(R.id.employeeRecyclerView);
        // Retrieve AutoClicker and Score object from intent
        AutoClicker autoclicker = (AutoClicker) getIntent().getSerializableExtra("autoclicker");

        // Different employees
        Employee employee1 = new Employee("George", "S'amuse au parc", R.drawable.george, 0, 2);

        // Initiate recycler view
        ArrayList<Employee> dataSet = new ArrayList<>();
        dataSet.add(employee1);
        String money =(String) getIntent().getStringExtra("Money");
        MyAdapter myAdapter = new MyAdapter(dataSet, money, new ClickListener() {
            @Override
            public void onPositionClicked(int position) {
                autoclicker.setRate(dataSet.get(position).getRate());
                dataSet.get(position).setQuantity(dataSet.get(position).getQuantity() + 1);
            }
        });
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //connect the back button
        Button back = findViewById(R.id.Back);
        back.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainGame.class);
            intent.putExtra("MoneyMinusBuy", myAdapter.getMoney());
            startActivity(intent);
        });

    }
}
