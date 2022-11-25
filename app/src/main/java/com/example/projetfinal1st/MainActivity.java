package com.example.projetfinal1st;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
        * Button to change background from dark mode to white mode / white mode to dark mode
        */
        Button button = findViewById(R.id.colorButton)
        button.setOnClickListener(new View.OnClickListener() {}

    }


}