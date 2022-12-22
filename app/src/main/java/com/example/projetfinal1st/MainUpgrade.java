package com.example.projetfinal1st;

import android.annotation.SuppressLint;
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


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainUpgrade extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);

        // Get Intent
        Intent intent = getIntent();

        // Retrieve recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerViewUpgrade);

        // Retrieve ArrayList
        ArrayList<EntityUpgrade> dataSet = (ArrayList<EntityUpgrade>) intent.getSerializableExtra("arrayList");

        // Initiate recycler view
        AdapterUpgrade adapterUpgrade = new AdapterUpgrade(dataSet);
        recyclerView.setAdapter(adapterUpgrade);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // open info popup
        Button buttonInfo = findViewById(R.id.buttonUpgradeInfo);
        buttonInfo.setOnClickListener(view -> {

            // Inflate the layout of the popup
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View popupView = inflater.inflate(R.layout.popup_window, null);
            TextView textView = popupView.findViewById(R.id.textViewPopup);
            textView.setText(R.string.upgrade_popup);

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
        Button buttonBack = findViewById(R.id.buttonUpgradeBack);
        buttonBack.setOnClickListener(view -> {

            // Intent to return data to main game
            Intent secondIntent = new Intent(this, MainGame.class);

            startActivity(secondIntent);
        });

    }

}