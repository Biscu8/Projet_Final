package com.example.projetfinal1st;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class Setting extends AppCompatActivity {
    private RadioGroup group;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_setting);
        super.onCreate(savedInstanceState);

        imageView = findViewById(R.id.imagedifficulte);
        group = findViewById(R.id.bouttongroup);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i)
                {
                    case R.id.bouttonFacile:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.easy));
                        break;
                    case R.id.BouttonMedium:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.medium));
                        break;
                    case R.id.bouttondifficile:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.difficile));
                        break;
                }
            }
        });



    }


}