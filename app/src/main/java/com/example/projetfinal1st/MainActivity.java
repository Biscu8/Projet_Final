package com.example.projetfinal1st;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
        * Button to change background from dark mode to white mode / white mode to dark mode
        *///TODO
        Button button = findViewById(R.id.colorButton);
        int i = 0;
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            public void onClick(View view) { // this doesnt work **
                RelativeLayout layout = findViewById(R.id.relativeLayout);
                if (i ==    0) {
                    layout.setBackgroundColor(R.color.main_color_black);
                    i = 1;
                } else {
                    layout.setBackgroundColor(R.color.main_color_white);
                    i = 0;
                }
            }
        });

    }

}