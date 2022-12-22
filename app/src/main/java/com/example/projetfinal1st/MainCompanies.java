package com.example.projetfinal1st;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

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

        // Open info popup
        Button buttonInfo = findViewById(R.id.buttonCompaniesInfo);
        buttonInfo.setOnClickListener(view -> {

            // Inflate the layout of the popup
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = inflater.inflate(R.layout.popup_window, null);
            TextView textView = popupView.findViewById(R.id.textViewPopup);
            textView.setText(R.string.companies_popup);

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

        // Setup back button
        Button buttonBack = findViewById(R.id.buttonCompaniesBack);
        buttonBack.setOnClickListener(view -> {

            // Set intent to return info to main game
            Intent secondIntent = new Intent(this, MainGame.class);

            startActivity(secondIntent);
        });

    }
}