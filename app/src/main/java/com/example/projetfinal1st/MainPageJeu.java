package com.example.projetfinal1st;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainPageJeu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_jeu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.menu_about:
                //TODO ce que va faire about
                break;
            case R.id.menu_disconnect:
                //TODO deconnecter
                break;
            case R.id.menu_setting:
                //TODO parametre du jeu
                break;
            case R.id.menu_quit:
                //TODO fermer lapp
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}