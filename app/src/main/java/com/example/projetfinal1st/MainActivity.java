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
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link database via a view model
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        // Connects login button to game page
        findViewById(R.id.loginButton).setOnClickListener(view -> { // Listener on the login button
            if (findUsername()) { // Checks for correct credentials
                Intent intent = new Intent(this, MainPageJeu.class);
                startActivity(intent);
            }
            else {
                findViewById(R.id.loginErrorText).setVisibility(View.VISIBLE);
            }
        }); // mettre la classe de la deuxieme page

        // Connect signup button to database
        findViewById(R.id.signupButton).setOnClickListener(view -> {
            if (findUsername()) {
                TextView textView = findViewById(R.id.loginErrorText);
                textView.setText(R.string.main_login_account_already_exists);
                textView.setVisibility(View.VISIBLE);
            }
            else signup();
        });


    }

    /**
     * Function to check if entered info is correct
     * @return boolean
     */
    public boolean findUsername() {
        EditText usernameText = findViewById(R.id.editTextUsername); // Find username and puts it into a string
        String username = usernameText.getText().toString();
        EditText passwordText = findViewById(R.id.editTextPassword); // Find password and puts it into a string
        String password = passwordText.getText().toString();
        if (myViewModel.isUserInDatabase(username)) { // Checks if credentials are correct
            if (myViewModel.isPasswordCorrect(password, username)) return true;
            else return false;
        }
        else return false;
    }

    /**
     * Function to input users in database
     */
    public void signup() {
        EditText usernameText = findViewById(R.id.editTextUsername);
        String username = usernameText.getText().toString();
        EditText passwordText = findViewById(R.id.editTextPassword);
        String password = passwordText.getText().toString();
        User user = new User(username, password);
        myViewModel.userRepository.insert(user);
    }

}