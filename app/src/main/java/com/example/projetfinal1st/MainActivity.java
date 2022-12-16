package com.example.projetfinal1st;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    MyViewModel myViewModel;
    SharedPreferences preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //activation du darkMode si les settings on ete changer auparavant
        preference = PreferenceManager.getDefaultSharedPreferences(this);
        if (preference.getBoolean("DarkMode", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        // Link database via a view model
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        // Connects login button to game page
        findViewById(R.id.loginButton).setOnClickListener(view -> { // Listener on the login button
            EditText usernameText = findViewById(R.id.editTextUsername);
            String username = usernameText.getText().toString();
            EditText passwordText = findViewById(R.id.editTextPassword);
            String password = passwordText.getText().toString();
            Executors.newSingleThreadExecutor().execute(() -> {
                if (myViewModel.userInDatabase(username) != null) {
                    if (myViewModel.loginFromUserPassword(username,password) != null) {
                        preference.edit().putString("Username", username).apply();
                        openPageJeu();
                    }
                } else {
                    runOnUiThread(() -> {
                         TextView textView = findViewById(R.id.loginErrorText);
                        textView.setText("Credentials incorrect");
                         findViewById(R.id.loginErrorText).setVisibility(View.VISIBLE);
                    });
                }
            });
        });
        // Connect signup button to database
        findViewById(R.id.signupButton).setOnClickListener(view -> {
                    Executors.newSingleThreadExecutor().execute(() -> {
                        signup();
                    });
        });
    }

    /**
     * Function to input users in database
     */
    public void signup() {
        EditText usernameText = findViewById(R.id.editTextUsername);
        String username = usernameText.getText().toString();
        EditText passwordText = findViewById(R.id.editTextPassword);
        String password = passwordText.getText().toString();
        if(!"".equals(username) && !"".equals(password))
        {
            if(myViewModel.userInDatabase(username) == null) {
                myViewModel.registerUser(username, password);
                preference.edit().putString("Username", username).apply();
                openPageJeu();
            }
            else
            {
                runOnUiThread(() ->{
                    TextView textView = findViewById(R.id.loginErrorText);
                    textView.setText("Nom dutilisateur deja existant");
                    textView.setVisibility(View.VISIBLE);
                });
            }
        }
        else
        {
            runOnUiThread(() ->{
                TextView textView = findViewById(R.id.loginErrorText);
            textView.setText("Nom et mot de passe necessaire");
            textView.setVisibility(View.VISIBLE);
            });

        }
    }


    /**
     * open the game page
     */
   public void openPageJeu()
   {
   Intent intent = new Intent(this, MainGame.class);
   startActivity(intent);
   }
}
