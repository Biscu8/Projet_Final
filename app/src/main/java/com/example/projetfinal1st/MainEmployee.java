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

        // Retrieve AutoClicker and Score object from intent
        AutoClicker autoclicker = (AutoClicker) getIntent().getSerializableExtra("autoclicker");

        // Different employees
        Employee employee1 = new Employee("George", "S'amuse au parc", R.drawable.dde9fd7a087fa4eaa70554ed266099dc, 0, 2);

        // Initiate recycler view
        ArrayList<Employee> dataSet = new ArrayList<>();
        dataSet.add(employee1);
        MyAdapter myAdapter = new MyAdapter(dataSet, new ClickListener() {
            @Override
            public void onPositionClicked(int position) {
                autoclicker.setRate(dataSet.get(position).getRate());
                dataSet.get(position).setQuantity(dataSet.get(position).getQuantity() + 1);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.employeeRecyclerView);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Back button
        findViewById(R.id.employeeButtonBack).setOnClickListener(view -> {
            finish();
        });

        // Buy button
        /**.setOnClickListener(view -> {
            TextView textView = findViewById(R.id.employeeCountNumber);
            employee1.setQuantity(employee1.getQuantity() + 1);
            textView.setText(String.valueOf(employee1.getQuantity()));
        });*/

    }
}
