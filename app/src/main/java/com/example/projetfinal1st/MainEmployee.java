package com.example.projetfinal1st;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

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
        RecyclerView recyclerView = findViewById(R.id.recyclerViewEmployee);

        // Retrieve arrayList
        Bundle args = intent.getBundleExtra("bundle");
        ArrayList<Employee> dataSet = (ArrayList<Employee>) args.getSerializable("arrayList");

        // Initiate recycler view
        String money = (String) getIntent().getStringExtra("Money");

        //get the employee count number from database
        AdapterEmployee myAdapter = new AdapterEmployee(dataSet, money);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //connect the back button
        Button buttonEmployeeBack = findViewById(R.id.buttonEmployeeBack);
        buttonEmployeeBack.setOnClickListener(view -> {
            Intent secondIntent = new Intent(this, MainGame.class);
            //register an id for the employees of the user link to is username
           // Save save = new Save(getIntent().getStringExtra("Username"), );
          //  myViewModelGame.updateSave(save);
            //Use the same id to register each employee in the employee tab
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
