package com.example.projetfinal1st.Companies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.example.projetfinal1st.MainGame;
import com.example.projetfinal1st.MyViewModelGame;
import com.example.projetfinal1st.R;

import java.util.ArrayList;
import java.util.concurrent.Executors;

public class MainCompanies extends AppCompatActivity {

    MyViewModelCompanies myViewModelCompanies;
    MyViewModelGame myViewModelGame;
    AdapterCompanies adapterCompanies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companies);

        // Get Intent
        Intent intent = getIntent();

        //initate view models
        myViewModelCompanies = new ViewModelProvider(this).get(MyViewModelCompanies.class);
        myViewModelGame = new ViewModelProvider(this).get(MyViewModelGame.class);
        // Retrieve recycler view
        RecyclerView recyclerView = findViewById(R.id.reyclerViewCompanies);

        //retrieve name
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String id = preferences.getString("Username", "");

        // Retrieve ArrayList
        ArrayList<EntityCompanies> dataSet = (ArrayList<EntityCompanies>) getIntent().getSerializableExtra("arrayList");

        // Initiate recycler view
        Executors.newSingleThreadExecutor().execute(() -> {
                     adapterCompanies = new AdapterCompanies(dataSet,myViewModelCompanies, this, myViewModelGame.getMoneyAmount(id), id, this, myViewModelGame);
            runOnUiThread(() -> {
                recyclerView.setAdapter(adapterCompanies);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            });
        });

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
            preferences.edit().putString("NewMoneyCompanies", String.valueOf(adapterCompanies.getViewHolder().getMoney())).apply();
            startActivity(secondIntent);
        });

    }
}