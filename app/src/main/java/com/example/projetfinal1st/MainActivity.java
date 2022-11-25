package com.example.projetfinal1st;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link database via a view model
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        // Connects button to game page
        findViewById(R.id.loginButton).setOnClickListener(view -> { // Listener on the login button
            EditText usernameText = findViewById(R.id.editTextUsername); // Find username and puts it into a string
            String username = usernameText.getText().toString();
            myViewModel.
            Intent intent = new Intent(this, MainPageJeu.class);
            startActivity(intent);
        });//mettre la classe de la deuxieme page



    }

}