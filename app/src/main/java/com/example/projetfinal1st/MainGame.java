package com.example.projetfinal1st;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import com.example.projetfinal1st.MenuFeatures.Settings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.stream.LongStream;

public class MainGame extends AppCompatActivity {
    private SharedPreferences preferences;
    private MyViewModelGame myViewModelGame;
    private Score score;
    private String username;
    private ArrayList<EntityEmployee> arrayEmployee;
    private ArrayList<EntityUpgrade> arrayUpgrade;
    private ArrayList<EntityCompanies> arrayCompanies;
    @SuppressLint({"SetTextI18n", "CommitPrefEdits"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        username = preferences.getString("Username", "");
        myViewModelGame = new ViewModelProvider(this).get(MyViewModelGame.class);
        // ArrayList of employees
         arrayEmployee = new ArrayList<>();
         arrayUpgrade = new ArrayList<>();
         arrayCompanies = new ArrayList<>();

        // Initiate the score of the user


        Executors.newSingleThreadExecutor().execute(() -> {
                    // Verify is user has save
            TextView clickAmountView = findViewById(R.id.clickAmount);
            TextView moneyAmount = findViewById(R.id.moneyAmount);
            if (myViewModelGame.getSave(username) != null) {

                //Update UI with text database clickAmount
                Log.i("Passe par ici", "1");
                int clickAmount = myViewModelGame.getSave(username).getScore();
                clickAmountView.setText(String.valueOf(clickAmount));
                // Update employees
                List<EntityEmployee> employees = myViewModelGame.getAllEmployeeWithSameId(username);
                arrayEmployee = new ArrayList<>();
                for (int i = 0; i < employees.size(); i++) {
                    arrayEmployee.add(employees.get(i));
                    // new AutoClicker(this, myViewModelGame.getSave(username).getScore(), arrayEmployee.get(i).getRate());
                }
                ArrayList<EntityEmployee> tempArrayList = new ArrayList<>();
                if (getIntent().hasExtra("secondArrayList")) {
                    tempArrayList.addAll((Collection<? extends EntityEmployee>) getIntent().getSerializableExtra("secondArrayList"));
                    Log.i("size", String.valueOf(tempArrayList.size()));
                    for (int i = 0; i < tempArrayList.size(); i++) {
                        for (int j = 0; j < arrayEmployee.size(); j++) {
                            if (tempArrayList.get(i).getName().equals(arrayEmployee.get(j).getName())) {
                                arrayEmployee.get(j).setQuantity(tempArrayList.get(i).getQuantity());
                                Log.i("employee?", String.valueOf(arrayEmployee.get(0).getQuantity()));
                                myViewModelGame.udpateEmployee(arrayEmployee.get(j));
                                Executors.newSingleThreadExecutor().execute(() -> {
                                });
                                Log.i("employee", String.valueOf(myViewModelGame.getAllEmployeeWithSameId(username).get(0).getQuantity()));
                            }
                        }
                    }
                }
                //verify if the user is opening the app or is coming back from the employee tab
                if (!String.valueOf(preferences.getString("NewMoney", "")).isEmpty()) {
                    Log.i("Passe par ici", "2");
                    runOnUiThread(() -> {
                        //update UI with MoneyAmount
                        moneyAmount.setText(preferences.getString("NewMoney", ""));
                        //remove Extra data
                        preferences.edit().remove("NewMoney").apply();
                    });

                } else {
                    //Update UI with database data
                    Log.i("Passe par ici", "3");
                    int money = myViewModelGame.getMoneyAmount(username);
                    Score score = myViewModelGame.getSave(username);
                    runOnUiThread(() -> {
                        moneyAmount.setText(String.valueOf(money));
                    });
                }

            } else {
//Initiate the employees with default stats
                Log.i("Passe par ici", "4");
                EntityEmployee employee1 = new EntityEmployee(2, "George", username, "desc", 2, 1, R.drawable.george);
                EntityEmployee employee2 = new EntityEmployee(0, "Sigma", username, "desc", 4, 2000, R.drawable.sigma);
                EntityEmployee employee3 = new EntityEmployee(0, "Orion", username, "desc", 6, 3000, R.drawable.orion);
                EntityEmployee employee4 = new EntityEmployee(0, "Steve", username, "desc", 8, 4000, R.drawable.steve);
                EntityEmployee employee5 = new EntityEmployee(0, "Vecna", username, "desc", 10, 5000, R.drawable.vecna);
                EntityEmployee employee6 = new EntityEmployee(0, "Homer", username, "desc", 12, 6000, R.drawable.homer);
                EntityEmployee employee7 = new EntityEmployee(0, "Elon Musk", username, "desc", 14, 7000, R.drawable.elonmusk);
                EntityEmployee employee8 = new EntityEmployee(0, "Chtulu", username, "desc", 16, 8000, R.drawable.chtulu);
                arrayEmployee.add(employee1);
                arrayEmployee.add(employee2);
                arrayEmployee.add(employee3);
                arrayEmployee.add(employee4);
                arrayEmployee.add(employee5);
                arrayEmployee.add(employee6);
                arrayEmployee.add(employee7);
                arrayEmployee.add(employee8);
                //initialise companies
                EntityCompanies entityCompanies1 = new EntityCompanies(username, "depanneur", false, 1000, 10000, R.drawable.couchetard);
                arrayCompanies.add(entityCompanies1);
                EntityUpgrade upgrade = new EntityUpgrade(username, "name", false, "desc", 0, 0);
                arrayUpgrade.add(upgrade);

                //put all preferences to default
                //if there is no user initiate score to 0 and create a new save
                Save save = new Save(username);
                myViewModelGame.setSave(save);
                //Use the same id to create each employee in the employee tab
                for (int i = 0; i < arrayEmployee.size(); i++) {

                    // Set employees
                    String name = arrayEmployee.get(i).getName();
                    int image = arrayEmployee.get(i).getImage();
                    int rate = arrayEmployee.get(i).getRate();
                    String desc = arrayEmployee.get(i).getDescription();
                    int price = arrayEmployee.get(i).getPrice();
                    int quantity = arrayEmployee.get(i).getQuantity();
                    //register name in database with the id and the amount of 0 employee
                    EntityEmployee employee = new EntityEmployee(quantity, name, username, desc, rate, price, image);
                    myViewModelGame.insert(employee);
                }
            }
            saveGameInDatabase();
        });


            // Normal, hand clicker
            findViewById(R.id.ClickButton).setOnClickListener(view -> {
                //Update clickAmount view + 1
                TextView clickAmount = findViewById(R.id.clickAmount);
                int m_score = Integer.parseInt(clickAmount.getText().toString()) + 1;
                clickAmount.setText(String.valueOf(m_score));
                if (!preferences.getBoolean("InfiniteMoney", false)) {

                    TextView newMoneyAmount = findViewById(R.id.moneyAmount);
                    newMoneyAmount.setText(String.valueOf(Integer.parseInt(String.valueOf(newMoneyAmount.getText())) + 1));

                    // start a new thread
                    Executors.newSingleThreadExecutor().execute(() -> {

                        //update database with the new data
                        Save newsave = new Save(username, Integer.parseInt(String.valueOf(clickAmount.getText())), Integer.parseInt(String.valueOf(newMoneyAmount.getText())));
                        myViewModelGame.updateSave(newsave);
                    });
                }
            });
            // Open employees tab
            // TODO share score between activities with database
            findViewById(R.id.buttonEmployee).setOnClickListener(view -> {
                Intent intent = new Intent(this, MainEmployee.class);
                TextView money = findViewById(R.id.moneyAmount);
                intent.putExtra("Money", Integer.parseInt(String.valueOf(money.getText())));
                saveGameInDatabase();
                startActivity(intent);
            });
            // Open upgrades tab
            findViewById(R.id.buttonUpgrade).setOnClickListener(view -> {
                Intent intent = new Intent(this, MainUpgrade.class);

                ArrayList<EntityUpgrade> tempArrayList = new ArrayList<>();
                tempArrayList.addAll(arrayUpgrade);

                for (int i = 0; i < tempArrayList.size(); i++) {
                    if (tempArrayList.get(i).getBought()) {
                        tempArrayList.remove(i);
                    }
                }

                // Put extas in intent
                intent.putExtra("arrayList", (Serializable) tempArrayList);

                startActivity(intent);
            });
            // Open companies tab
            findViewById(R.id.buttonCompanies).setOnClickListener(view -> {
                Intent intent = new Intent(this, MainCompanies.class);

                // Send ArrayList to adapter
                AdapterCompanies adapterCompanies = new AdapterCompanies(arrayCompanies, this);

                // Put extras in intent
                intent.putExtra("arrayList", arrayCompanies);

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
                        }
                    });
                }
            };
            timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }
        public void saveGameInDatabase ()
        {
            //udpate the save
            Log.i("Passe par ici", "5");
            TextView viewScore = findViewById(R.id.clickAmount);
            String stringScore = (String) viewScore.getText();
            TextView viewAmount = findViewById(R.id.moneyAmount);
            int moneyAmount = Integer.parseInt((String) viewAmount.getText());
            Save save = new Save(preferences.getString("Username", ""), Integer.parseInt(stringScore), moneyAmount);
            Executors.newSingleThreadExecutor().execute(() -> {
                myViewModelGame.updateSave(save);
            });
        }
        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            getMenuInflater().inflate(R.menu.menu, menu);
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected (@NonNull MenuItem item){
            switch (item.getItemId()) {
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