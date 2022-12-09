package com.example.projetfinal1st;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

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
            if (findUsername()) { // Checks for correct credentials
                if(verifyPassword()) {
                    Intent intent = new Intent(this, MainGame.class);
                    startActivity(intent);
                }
                else
                {
                    TextView textView = findViewById(R.id.loginErrorText);
                    textView.setText("Mauvais mot de passe");
                    textView.setVisibility(View.VISIBLE);
                }
            } else {
                TextView textView = findViewById(R.id.loginErrorText);
                textView.setText("Nom dutilisateur non trouver enregistrer-vous");
                findViewById(R.id.loginErrorText).setVisibility(View.VISIBLE);
            }
        });

        // Connect signup button to database
        findViewById(R.id.signupButton).setOnClickListener(view -> {
            signup();
        });


    }

    /**
     * look in the database if the username enter exist
     * @return
     */
    public Boolean findUsername()
{
    EditText usernameText = findViewById(R.id.editTextUsername); // Find username and puts it into a string
    String username = usernameText.getText().toString();
    if(!"".equals(username))
    {
        return myViewModel.isUserInDatabase(username);
         //retourne la valeur si le compte existe
    }
    else
    {
        return false;
    }
}

    /**
     * look in the database if the password fits the username
     * @return true if it fits and false if it doesnt
     */
    public Boolean verifyPassword()
{
    EditText usernameText = findViewById(R.id.editTextUsername); // Find username and puts it into a string
    String username = usernameText.getText().toString();
    EditText passwordText = findViewById(R.id.editTextPassword); // Find password and puts it into a string
    String password = passwordText.getText().toString();
    return myViewModel.isPasswordCorrect(username, password);
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
            myViewModel.registerUser(username, password);
            Intent intent = new Intent(this, MainGame.class);
            startActivity(intent);
        }
        else
        {
            TextView textView = findViewById(R.id.loginErrorText);
            textView.setText("Nom et mot de passe necessaire");
            textView.setVisibility(View.VISIBLE);
        }
    }

}