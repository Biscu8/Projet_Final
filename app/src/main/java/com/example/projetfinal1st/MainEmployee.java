package com.example.projetfinal1st;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
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
import java.util.concurrent.atomic.AtomicReference;

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
            Executors.newSingleThreadExecutor().execute(() -> {
                        //get all the employee textviews and register them in the database

                        int EmployeeCount = myViewModelGame.getAllEmployeeWithSameId(id).size();
                        for(int i= 0; i < EmployeeCount; i++) {
                            if(Integer.parseInt(String.valueOf(m_myAdapter.getViewHolder().getEmployeeCountNumberTextView().getText())) != 0)
                            {
                                EntityEmployee employee = new EntityEmployee(id, Integer.parseInt(String.valueOf(m_myAdapter.getViewHolder().getEmployeeCountNumberTextView().getText())));
                                myViewModelGame.udpateEmployee(employee);
                            }
                        }
                        //get the money from the adapter and register the new money amount
                Log.i("Money",String.valueOf(m_myAdapter.getViewHolder().getMoney()));
                        Save save = new Save(id, m_myAdapter.getViewHolder().getMoney());
                        myViewModelGame.updateSave(save);
                    });
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
