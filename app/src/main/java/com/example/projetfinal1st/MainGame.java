package com.example.projetfinal1st;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;

public class MainGame extends AppCompatActivity {
    private SharedPreferences preferences;
    private MyViewModelGame myViewModelGame;
    private Score score;
    private String username;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        username = preferences.getString("Username", "");
        myViewModelGame = new ViewModelProvider(this).get(MyViewModelGame.class);

        // ArrayList of employees
        ArrayList<Employee> arrayEmployee = new ArrayList<>();

        // Initiate the score of the user
        Executors.newSingleThreadExecutor().execute(() -> {

            //Initiate the employees with default stats
            Employee employee1 = new Employee("George", "desc", R.drawable.george, 0, 2, 1);
            Employee employee2 = new Employee("cody", "desc", R.drawable.george, 0, 4, 2000);
            Employee employee3 = new Employee("marie", "desc", R.drawable.george, 0, 6, 3000);
            Employee employee4 = new Employee("qwerty", "desc", R.drawable.george, 0, 8, 4000);
            Employee employee5 = new Employee("jay", "desc", R.drawable.george, 0, 10, 5000);
            Employee employee6 = new Employee("brrrr", "desc", R.drawable.george, 0, 12, 6000);
            Employee employee7 = new Employee("black", "desc", R.drawable.george, 0, 14, 7000);
            Employee employee8 = new Employee("white", "desc", R.drawable.george, 0, 16, 8000);
            Employee employee9 = new Employee("sffsfdsf", "desc", R.drawable.george, 0, 16, 8000);
            arrayEmployee.add(employee1); arrayEmployee.add(employee2); arrayEmployee.add(employee3); arrayEmployee.add(employee4);
            arrayEmployee.add(employee5); arrayEmployee.add(employee6); arrayEmployee.add(employee7); arrayEmployee.add(employee8);arrayEmployee.add(employee9);

            // Verify is user has save
            if(myViewModelGame.getSave(username) != null) {

                //Update UI with text database moneyAmount
                int clickAmount = myViewModelGame.getSave(username).getScore();
                TextView clickAmountView = findViewById(R.id.clickAmount);
                clickAmountView.setText(String.valueOf(clickAmount));

                // Update employees
                for (int i = 0; i < arrayEmployee.size(); i++) {
                    //int nb = myViewModelGame.getSave(username).getEmployeeNb(); //TODO
                    int nb = 0;
                    arrayEmployee.get(i).setQuantity(nb);
                    for (int j = 0; j <= nb; j ++) {
                        new AutoClicker(this, myViewModelGame.getSave(username).getScore(), arrayEmployee.get(i).getRate());
                    }
                }

                //verify if the user is opening the app or is coming back from the employee tab
                TextView moneyAmount = findViewById(R.id.moneyAmount);
                if(getIntent().getStringExtra("MoneyMinusBuy") != null) {

                    //update UI with MoneyAmount
                    moneyAmount.setText(getIntent().getStringExtra("MoneyMinusBuy"));

                    //remove Extra data
                    getIntent().removeExtra("MoneyMinusBuy");
                    saveGameInDatabase();
                }

                // Verify if user is coming back from upgrade tab
                /*else if () {
                    for (int i = 0; i <= arrayEmployee.size(); i++) {
                    int nb = myViewModelGame.getSave(username).getEmployeeNb();
                    arrayEmployee.get(i).setQuantity(nb);
                        for (int j = 0; j <= nb; j ++) {
                            new AutoClicker(this, myViewModelGame.getSave(username).getScore(), arrayEmployee.get(i).getRate());
                        }
                    }
                }*/

                // if user is loading
                else {

                    //Update UI with database data
                    moneyAmount.setText(myViewModelGame.getMoneyAmount(username));
                }

                //update data with new data
                saveGameInDatabase();

                // if preference infinite money is check update UI
                if(preferences.getBoolean("InfiniteMoney", false)) {
                    moneyAmount.setText("Infinite Money");
                }
            }
            else {

                //put all preferences to default
                preferences.getAll().clear();//pas sur
                //if there is no user initiate score to 0 and create a new save
                score = new Score(0);
                Save save = new Save(username);
                myViewModelGame.setSave(save);
            }
        });

        // Normal, hand clicker
        findViewById(R.id.ClickButton).setOnClickListener(view -> {

            //Update clickAmount view + 1
            TextView clickAmount = findViewById(R.id.clickAmount);
            int m_score = Integer.parseInt(clickAmount.getText().toString()) + 1;
            clickAmount.setText(String.valueOf(m_score));
            if(!preferences.getBoolean("InfiniteMoney", false)) {

                // Update moneyAmount view + 10
                TextView moneyAmount = findViewById(R.id.moneyAmount);

                // start a new thread
                Executors.newSingleThreadExecutor().execute(() -> {

                    //update database with the new data
                    Save save = new Save(username, Integer.parseInt(String.valueOf(clickAmount.getText())), String.valueOf(moneyAmount.getText()));
                    myViewModelGame.updateSave(save);
                });
            }
        });

        // Open employees tab
        // TODO share score between activities with database
        findViewById(R.id.buttonEmployee).setOnClickListener(view -> {
            Intent intent = new Intent(this, MainEmployee.class);
            TextView money = findViewById(R.id.moneyAmount);

            // Sends employees to adapter
            String money1 = (String) money.getText();
            AdapterEmployee adapter = new AdapterEmployee(arrayEmployee, money1);

            // Sends employees to activity
            Bundle args = new Bundle();
            args.putSerializable("arrayList", (Serializable) arrayEmployee);
            intent.putExtra("arrayList", arrayEmployee);
            intent.putExtra("bundle", args);

            //put money in database
            saveGameInDatabase();

            intent.putExtra("Money", money1);
            startActivity(intent);
        });

        // Open upgrades tab
        findViewById(R.id.buttonUpgrade).setOnClickListener(view -> {
            Intent intent = new Intent(this, MainUpgrade.class);

            // ArrayList of strings TODO change it for upgrades or smt
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("Hello");
            arrayList.add("Aurevoir");

            // Sends ArrayList to adapter
            AdapterUpgrade adapterUpgrade = new AdapterUpgrade(arrayList);

            // Sends strings to activity
            Bundle bundle = new Bundle();
            bundle.putSerializable("bundle", (Serializable) arrayList);

            // Put extas in intent
            intent.putExtra("arrayList", arrayList);
            intent.putExtra("bundle", bundle);

            startActivity(intent);
        });

        // Open companies tab
        findViewById(R.id.buttonCompanies).setOnClickListener(view -> {
            Intent intent = new Intent(this, MainCompanies.class);

            // ArrayList of strings TODO change it for companies or smt
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("hello");
            arrayList.add("yo");

            // Send ArrayList to adapter
            AdapterCompanies adapterCompanies = new AdapterCompanies(arrayList);

            // Send strings to activity
            Bundle bundle = new Bundle();
            bundle.putSerializable("bundle", (Serializable) arrayList);

            // Put extras in intent
            intent.putExtra("arrayList", arrayList);
            intent.putExtra("bundle", bundle);

            startActivity(intent);
        });

        // Update score each seconds
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView clickAmount = findViewById(R.id.clickAmount); // Updates from the click amount, Score = click amount
                        TextView moneyAmount = findViewById(R.id.moneyAmount);
                        int m_score = Integer.parseInt(clickAmount.getText().toString());
                        Executors.newSingleThreadExecutor().execute(() -> {
                            if (myViewModelGame.getSave(username) != null) {
                                myViewModelGame.getSave(username).setScore(m_score);
                            }
                        });
                        clickAmount.setText(String.valueOf(m_score));
                        moneyAmount.setText(String.valueOf(m_score) + "$");
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);

    }
    public void saveGameInDatabase()
    {
        TextView viewScore = findViewById(R.id.clickAmount);
        String stringScore =(String) viewScore.getText();
        TextView viewAmount = findViewById(R.id.moneyAmount);
        String moneyAmount = (String) viewAmount.getText();
        Save save = new Save(preferences.getString("Username", ""), Integer.parseInt(stringScore), moneyAmount);
        Executors.newSingleThreadExecutor().execute(() -> {
            myViewModelGame.updateSave(save);
        });
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
                //TODO menu About
                break;
            case R.id.menu_disconnect:
                Intent intentMainPage = new Intent(this, MainActivity.class);
                saveGameInDatabase();
                startActivity(intentMainPage);
                break;
            case R.id.menu_setting:
                //TODO menu Settings
                Intent intent = new Intent(this, Settings.class);
                startActivity(intent);
                break;
            case R.id.menu_quit:
                //TODO menu Quit
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}