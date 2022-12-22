package com.example.projetfinal1st;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MainEmployee extends AppCompatActivity {
    MyViewModelGame myViewModelGame;
    public ArrayList<EntityEmployee> m_employees;
    public AdapterEmployee m_myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);
        Intent intent = getIntent();
        myViewModelGame = new ViewModelProvider(this).get(MyViewModelGame.class);

        //retrieve recyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewEmployee);
        // Retrieve arrayList
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String id = preferences.getString("Username", "");
        // Initiate recycler view
        //link the database when page is open
        Executors.newSingleThreadExecutor().execute(() -> {
                //get the employee tab that fits the user into an Array
            m_employees = new ArrayList<>();
            List<EntityEmployee> employees = myViewModelGame.getAllEmployeeWithSameId(id);
            m_employees.addAll(employees);
            //get the employee count number from database
            m_myAdapter = new AdapterEmployee(m_employees, myViewModelGame.getMoneyAmount(id), myViewModelGame, id, this);
            runOnUiThread(() -> {
                recyclerView.setAdapter(m_myAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            });
        });

        //connect the back button
        Button buttonEmployeeBack = findViewById(R.id.buttonEmployeeBack);
        buttonEmployeeBack.setOnClickListener(view -> {
            Intent secondIntent = new Intent(this, MainGame.class);
            ArrayList<EntityEmployee> arrayList = new ArrayList<>();
            AtomicInteger employeeCount = new AtomicInteger();
            Executors.newSingleThreadExecutor().execute(() -> {
                //get all the employee textviews and register them in the database
                employeeCount.set(myViewModelGame.getAllEmployeeWithSameId(id).size());
            });
                for (int i = 0; i < employeeCount.get(); i++) {
                    if (Integer.parseInt(String.valueOf(m_myAdapter.getViewHolder().getEmployeeCountNumberTextView().getText())) != 0) {

                        arrayList.add(new EntityEmployee(id, Integer.parseInt(String.valueOf(m_myAdapter.getViewHolder().getEmployeeCountNumberTextView().getText())),
                                m_myAdapter.getViewHolder().getEmployeeTextView().getText().toString()));
                    }
                }
                //get the money from the adapter and register the new money amount
                preferences.edit().putString("NewMoney", String.valueOf(m_myAdapter.getViewHolder().getMoney())).apply();
            secondIntent.putExtra("secondArrayList", arrayList);

            startActivity(secondIntent);
        });

        // open info popup window
        Button buttonEmployeeInfo = findViewById(R.id.buttonEmployeeInfo);
        buttonEmployeeInfo.setOnClickListener(view -> {

            // Inflate the layout of the popup
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = inflater.inflate(R.layout.popup_window, null);
            TextView textView = popupView.findViewById(R.id.textViewPopup);
            textView.setText(R.string.employee_popup);

            // Create the popup
            int width = LinearLayout.LayoutParams.WRAP_CONTENT;
            int height = LinearLayout.LayoutParams.WRAP_CONTENT;
            boolean focusable = true; // Exit the popup by tapping outside
            final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

            // Show popup
            popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

            // Exit when tapped
            popupView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    popupWindow.dismiss();
                    return true;
                }
            });
        });
    }
  }
