package com.example.projetfinal1st;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class MainEmployee extends AppCompatActivity {
    MyViewModelGame myViewModelGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);
        Intent intent = getIntent();
        myViewModelGame = new ViewModelProvider(this).get(MyViewModelGame.class);

        //retrieve recyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewEmployee);

        // Retrieve arrayList
        Bundle args = intent.getBundleExtra("bundle");
        ArrayList<Employee> dataSet = (ArrayList<Employee>) args.getSerializable("arrayList");
        //link the database when page is open
        Executors.newSingleThreadExecutor().execute(() -> {
            //register an id for the employees of the user link to is username
            //verify if the player already has an id
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String id = preferences.getString("Username", "");
            if (!myViewModelGame.getAllEmployeeWithSameId(id).isEmpty()) {
                //get the employee tab that fits the user into an Array
                List<EntityEmployee> employees = myViewModelGame.getAllEmployeeWithSameId(id);
                Log.i("name", employees.get(1).getName());
                dataSet.clear();

                for (int i = 0; i < employees.size(); i++) {
                    dataSet.add(employees.get(i));
                }
            }
        });
        // Initiate recycler view
        String money = (String) getIntent().getStringExtra("Money");

        //get the employee count number from database
        AdapterEmployee myAdapter = new AdapterEmployee(dataSet, money, args);
        Log.i("idk", String.valueOf(dataSet.get(1).getQuantity()));
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //connect the back button
        Button buttonEmployeeBack = findViewById(R.id.buttonEmployeeBack);
        buttonEmployeeBack.setOnClickListener(view -> {
            Intent secondIntent = new Intent(this, MainGame.class);
            Executors.newSingleThreadExecutor().execute(() -> {
                ArrayList<Employee> dataSet2 = (ArrayList<Employee>) args.getSerializable("arrayList");
                for (int i = 0; i < dataSet2.size(); i++) {
                    Employee employee = dataSet2.get(i);
                    myViewModelGame.udpateEmployee(new EntityEmployee(employee.getQuantity(), employee.getName(), getIntent().getStringExtra("Username"),
                            employee.getDescription(), employee.getRate(), employee.getPrice(), employee.getImage()));
                }
            });
            secondIntent.putExtra("MoneyMinusBuy", myAdapter.getMoney());
            secondIntent.putExtra("nbEmployer", myAdapter.getEmployeeCountNumber());
            startActivity(secondIntent);
        });

        // open info popup window
        Button buttonEmployeeInfo = findViewById(R.id.buttonEmployeeInfo);
        buttonEmployeeInfo.setOnClickListener(view -> {
            // Inflate the layout of the popup
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = inflater.inflate(R.layout.popup_window, null);

            // Create the popup
            int width = LinearLayout.LayoutParams.WRAP_CONTENT;
            int height = LinearLayout.LayoutParams.WRAP_CONTENT;
            boolean focusable = true; // Exit the popup by tapping outside
            final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

            // Show popup
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

            // Exit when tapped
            /*popupView.setOnTouchListener(new View.OnTouchListener() -> {
                @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                    popupWindow.dismiss();
                    return true;
                }
            });*/
        });

    }
}
