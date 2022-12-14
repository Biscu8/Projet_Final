package com.example.projetfinal1st;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import com.example.projetfinal1st.Companies.AdapterCompanies;
import com.example.projetfinal1st.Companies.EntityCompanies;
import com.example.projetfinal1st.Companies.MainCompanies;
import com.example.projetfinal1st.Companies.MyViewModelCompanies;
import com.example.projetfinal1st.MenuFeatures.Settings;
import com.example.projetfinal1st.Upgrade.AdapterUpgrade;
import com.example.projetfinal1st.Upgrade.EntityUpgrade;
import com.example.projetfinal1st.Upgrade.MainUpgrade;
import com.example.projetfinal1st.Upgrade.MyViewModelUpgrade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MainGame extends AppCompatActivity {
    private SharedPreferences preferences;
    private MyViewModelGame myViewModelGame;
    private MyViewModelCompanies myViewModelCompanies;
    private MyViewModelUpgrade myViewModelUpgrade;
    private Score score;
    private String username;
    private ArrayList<EntityEmployee> arrayEmployee;
    private ArrayList<EntityUpgrade> arrayUpgrade;
    private ArrayList<EntityCompanies> arrayCompanies;
    private TextView noMoreEmployee;
    private ArrayList<AutoClicker> arrayAuto;
    private int nbEmployee;
    private Context context;
    @SuppressLint({"SetTextI18n", "CommitPrefEdits"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        username = preferences.getString("Username", "");
        myViewModelGame = new ViewModelProvider(this).get(MyViewModelGame.class);
        myViewModelCompanies = new ViewModelProvider(this).get(MyViewModelCompanies.class);
        myViewModelUpgrade = new ViewModelProvider(this).get(MyViewModelUpgrade.class);
        noMoreEmployee = findViewById(R.id.textViewMainError);
        noMoreEmployee.setVisibility(View.INVISIBLE);
        context = this;
        // ArrayLists
         arrayEmployee = new ArrayList<>();
         arrayUpgrade = new ArrayList<>();
         arrayCompanies = new ArrayList<>();

        // Initiate the score of the user
        arrayAuto = new ArrayList<>();

        Executors.newSingleThreadExecutor().execute(() -> {
                    // Verify is user has save
            TextView clickAmountView = findViewById(R.id.clickAmount);
            TextView moneyAmount = findViewById(R.id.moneyAmount);
            if (myViewModelGame.getSave(username) != null) {

                List<EntityEmployee> employees = myViewModelGame.getAllEmployeeWithSameId(username);
                arrayEmployee = new ArrayList<>();
                for (int i = 0; i < employees.size(); i++) {
                    arrayEmployee.add(employees.get(i));
                    if (arrayEmployee.get(i).getQuantity() != 0) {
                        for (int j = 0; j < arrayEmployee.get(i).getQuantity(); j++) {
                             arrayAuto.add(new AutoClicker(this, myViewModelGame, username, arrayEmployee.get(i).getRate()));
                        }
                    }
                }
                        //verify if the user is opening the app or is coming back from the employee tab
                        if (!String.valueOf(preferences.getString("NewMoney", "")).isEmpty()) {
                             nbEmployee = myViewModelGame.getSave(username).getScore();
                             AtomicInteger click = new AtomicInteger();
                             AtomicInteger money = new AtomicInteger();
                            runOnUiThread(() -> {
                                //update UI with MoneyAmount
                                moneyAmount.setText(preferences.getString("NewMoney", ""));
                                clickAmountView.setText(String.valueOf(nbEmployee));
                                click.set(Integer.parseInt(String.valueOf(clickAmountView.getText())));
                                money.set(Integer.parseInt(String.valueOf(moneyAmount.getText())));
                                //remove Extra data
                                preferences.edit().remove("NewMoney").apply();
                            });

                        } else {
                            //Update UI with database data
                            int money = myViewModelGame.getMoneyAmount(username);
                            Score score = myViewModelGame.getSave(username);
                            runOnUiThread(() -> {
                                moneyAmount.setText(String.valueOf(money));
                            });
                        }
                        //verify if the user is comming back from the companies tab
                        if(!String.valueOf(preferences.getString("NewMoneyCompanies", "")).isEmpty())
                        {
                            //comming back from the tab
                            runOnUiThread(() -> {
                                moneyAmount.setText(preferences.getString("NewMoneyCompanies", ""));
                                preferences.edit().remove("NewMoneyCompanies").apply();
                            });
                        }
                        //verify if the user is comming back from the upgrade tab
                        if(!String.valueOf(preferences.getString("NewMoneyUpgrades", "")).isEmpty())
                        {
                            //update money with new money
                            runOnUiThread(() -> {
                                moneyAmount.setText(preferences.getString("NewMoneyUpgrades", ""));
                                preferences.edit().remove("NewMoneyUpgrades").apply();
                            });
                        }
                        //put data from companies database in the arrayCompanies
                        List<EntityCompanies> companies = myViewModelCompanies.getAllCompanies(username);
                        arrayCompanies.addAll(companies);
                        //put data from upgrade database in the arrayUpgrade
                        List<EntityUpgrade> upgrades = myViewModelUpgrade.getAllUpgrades(username);
                        arrayUpgrade.addAll(upgrades);
                        //TODO set here what all the upgrades does
            } else {
                        //Initiate the employees with default stats
                        EntityEmployee employee1 = new EntityEmployee(0,"George",username ,"desc",1 , 5,R.drawable.george);;
                        EntityEmployee employee2 = new EntityEmployee(0,"Sigma",username ,"desc",4 , 1000,R.drawable.sigma);
                        EntityEmployee employee3 = new EntityEmployee(0,"Orion",username ,"desc",6 , 5000,R.drawable.orion);
                        EntityEmployee employee4 = new EntityEmployee(0,"Steve",username ,"desc",13 , 10000,R.drawable.steve);
                        EntityEmployee employee5 = new EntityEmployee(0,"Vecna",username ,"desc",20 , 15000,R.drawable.vecna);
                        EntityEmployee employee6 = new EntityEmployee(0,"Homer",username ,"desc",37 , 25000,R.drawable.homer);
                        EntityEmployee employee7 = new EntityEmployee(0,"Elon Musk",username ,"desc",52 , 35000,R.drawable.elonmusk);
                        EntityEmployee employee8 = new EntityEmployee(0,"Chtulu",username ,"desc",100 , 50000,R.drawable.chtulu);
                        arrayEmployee.add(employee1);
                        arrayEmployee.add(employee2);
                        arrayEmployee.add(employee3);
                        arrayEmployee.add(employee4);
                        arrayEmployee.add(employee5);
                        arrayEmployee.add(employee6);
                        arrayEmployee.add(employee7);
                        arrayEmployee.add(employee8);
                        //initialise companies
                        EntityCompanies entityCompanies1 = new EntityCompanies(username, "Petite startup", false, 1250, 50, R.drawable.startup);
                        EntityCompanies entityCompanies2 = new EntityCompanies(username, "Petit commerce local", false, 7500, 1000, R.drawable.commercelocal);
                        EntityCompanies entityCompanies3 = new EntityCompanies(username, "resto chinois", false, 15000, 5000, R.drawable.restochinois);
                        EntityCompanies entityCompanies4 = new EntityCompanies(username, "Coco Frutti", false, 20000, 7500, R.drawable.cocofrutti);
                        EntityCompanies entityCompanies5 = new EntityCompanies(username, "Esso", false, 50000, 12500, R.drawable.esso);
                        EntityCompanies entityCompanies6 = new EntityCompanies(username, "Seven Eleven", false, 75000, 30000, R.drawable.seveneleven);
                        EntityCompanies entityCompanies7 = new EntityCompanies(username, "Couche tard", false, 100000,  50000, R.drawable.couchetard);
                        EntityCompanies entityCompanies8 = new EntityCompanies(username, "Louis", false, 200000, 80000, R.drawable.louis);
                        EntityCompanies entityCompanies9 = new EntityCompanies(username, "Dominos Pizza", false, 300000, 100000, R.drawable.dominos);
                        EntityCompanies entityCompanies10 = new EntityCompanies(username, "Starbucks", false, 350000, 150000, R.drawable.starbucks);
                        EntityCompanies entityCompanies11 = new EntityCompanies(username, "Dunkin Donuts", false, 400000, 200000, R.drawable.dunkindonut);
                        EntityCompanies entityCompanies12= new EntityCompanies(username, "Subway", false, 500000, 300000, R.drawable.subway);
                        EntityCompanies entityCompanies13 = new EntityCompanies(username, "Mcdonald", false, 1000000, 400000, R.drawable.mcdonalds);
                        EntityCompanies entityCompanies14 = new EntityCompanies(username, "Amazone", false, 2500000, 750000, R.drawable.amazone);
                        EntityCompanies entityCompanies15 = new EntityCompanies(username, "Facebook", false, 5500000, 2000000, R.drawable.facebook);
                        EntityCompanies entityCompanies16 = new EntityCompanies(username, "Apple", false, 7500000, 3000000, R.drawable.apple);
                        EntityCompanies entityCompanies17 = new EntityCompanies(username, "Google", false, 10000000, 5000000, R.drawable.google);
                        EntityCompanies entityCompanies18 = new EntityCompanies(username, "Tesla", false, 15000000, 7500000, R.drawable.tesla);
                        arrayCompanies.add(entityCompanies1);
                       // arrayCompanies.add(entityCompanies2);
                       // arrayCompanies.add(entityCompanies3);
                      //  arrayCompanies.add(entityCompanies4);
                       // arrayCompanies.add(entityCompanies5);
                       // arrayCompanies.add(entityCompanies6);
                       // arrayCompanies.add(entityCompanies7);
                       // arrayCompanies.add(entityCompanies8);
                      //  arrayCompanies.add(entityCompanies9);
                      //  arrayCompanies.add(entityCompanies10);
                      //  arrayCompanies.add(entityCompanies11);
                      //  arrayCompanies.add(entityCompanies12);
                      //  arrayCompanies.add(entityCompanies13);
                       // arrayCompanies.add(entityCompanies14);
                       // arrayCompanies.add(entityCompanies15);
                       // arrayCompanies.add(entityCompanies16);
                       // arrayCompanies.add(entityCompanies17);
                       // arrayCompanies.add(entityCompanies18);
                        //initate upgrade
                        EntityUpgrade upgrade1 = new EntityUpgrade(username, "Rate upgrade", false, "Make the rate go faster", 10, R.drawable.rate);
                        arrayUpgrade.add(upgrade1);
                        //for the new user intiate click amount at 100
                        clickAmountView.setText("100");
                        //if there is no user initiate score to 0 and create a new save
                        Save save = new Save(username, 100, 0);
                        myViewModelGame.setSave(save);
                        //initialize a save of the compagnies in database
                        for(int i = 0; i < arrayCompanies.size(); i++) {
                            //set companies
                            String name = arrayCompanies.get(i).getName();
                            boolean bought = arrayCompanies.get(i).isBought();
                            int nbEmployees = arrayCompanies.get(i).getNbEmployees();
                            int price = arrayCompanies.get(i).getPrice();
                            int image = arrayCompanies.get(i).getImage();
                            EntityCompanies companies = new EntityCompanies(username, name,bought, nbEmployees,price, image);
                            myViewModelCompanies.insert(companies);
                        }
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
                        //initialize upgrades
                        for(int i = 0; i < arrayUpgrade.size(); i++)
                        {
                            myViewModelUpgrade.insert(arrayUpgrade.get(i));
                        }
                        noMoreEmployee.setTextColor(0);
            }
            saveGameInDatabase();
        });


            // Normal, hand clicker
            findViewById(R.id.ClickButton).setOnClickListener(view -> {
                //Update clickAmount view - 1
                TextView clickAmount = findViewById(R.id.clickAmount);
                if (!"0".equals(String.valueOf(clickAmount.getText()))) {
                    int m_score = Integer.parseInt(clickAmount.getText().toString()) - 1;
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
                }
                else
                {
                    //set visible the no more employee text view
                    noMoreEmployee.setTextColor(Color.RED);
                    noMoreEmployee.setVisibility(View.VISIBLE);
                }
            });
            // Open employees tab
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

                //Send ArrayList to adater
                Executors.newSingleThreadExecutor().execute(()-> {
                    AdapterUpgrade adapterUpgrade = new AdapterUpgrade(arrayUpgrade, myViewModelUpgrade,this, myViewModelGame.getMoneyAmount(username), username, this, myViewModelGame);
                });
                intent.putExtra("arrayListUpgrades", arrayUpgrade);
                startActivity(intent);
            });

            // Open companies tab
            findViewById(R.id.buttonCompanies).setOnClickListener(view -> {
                Intent intent = new Intent(this, MainCompanies.class);

                // Send ArrayList to adapter
                Executors.newSingleThreadExecutor().execute(()-> {
                    AdapterCompanies adapterCompanies = new AdapterCompanies(arrayCompanies,myViewModelCompanies, this, myViewModelGame.getMoneyAmount(username), username, this, myViewModelGame);
                        });
                // Put extras in intent
                intent.putExtra("arrayList", arrayCompanies);
                //update database
                saveGameInDatabase();
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
                            AtomicInteger click = new AtomicInteger();
                            AtomicInteger money = new AtomicInteger();
                            Executors.newSingleThreadExecutor().execute(() -> {
                                int score = myViewModelGame.getSave(username).getScore();
                                if(score != 0) {
                                    click.set(score);
                                    money.set(myViewModelGame.getMoneyAmount(username));
                                    runOnUiThread(() -> {
                                        clickAmount.setText(String.valueOf(click.get()));
                                        moneyAmount.setText(String.valueOf(money.get()));
                                    });
                                }
                                else {
                                    //retrieve all the companies
                                    List<EntityCompanies> companies = myViewModelCompanies.getAllCompanies(username);
                                    boolean gameOver = false;
                                    boolean gameWin = true;
                                        for (int i = 0; i < companies.size(); i++) {
                                            if (!companies.get(i).isBought()) //verify which one is not bought
                                            {
                                                //if the player has enough money to buy one or more companies the game continue
                                                if (companies.get(i).getPrice() <= Integer.parseInt(String.valueOf(moneyAmount.getText()))) {
                                                    gameOver = true;
                                                }
                                            } else {
                                                gameWin = false;
                                            }
                                        }
                                        if (gameWin) {
                                            if (!gameOver) // if the user have 0 employee to fire and dont have enough money to buy a company its game over
                                            {
                                                //its game Over
                                                runOnUiThread(() -> {
                                                    Intent intent = new Intent(context, GameOver.class);
                                                    timer.cancel();
                                                    startActivity(intent);
                                                });
                                            } else {
                                                click.set(score);
                                                money.set(myViewModelGame.getMoneyAmount(username));
                                                runOnUiThread(() -> {
                                                    clickAmount.setText(String.valueOf(click.get()));
                                                    moneyAmount.setText(String.valueOf(money.get()));
                                                });
                                            }
                                        } else {
                                            Intent intent = new Intent(context, GameWon.class);
                                            timer.cancel();
                                            startActivity(intent);
                                        }
                                    }
                            });
                        }
                    });
                }
            };
            timer.scheduleAtFixedRate(timerTask, 0, 500);
    }

    @Override
    protected void onStop() {
        super.onStop();
        for (int i = 0; i < arrayAuto.size(); i++) {
            arrayAuto.get(i).cancel();
        }
        arrayAuto.clear();
    }

    public void saveGameInDatabase ()
        {
            //udpate the save
            AtomicInteger score = new AtomicInteger();
            AtomicInteger moneyAmount = new AtomicInteger();
            Executors.newSingleThreadExecutor().execute(() -> {
                score.set(myViewModelGame.getSave(username).getScore());
                moneyAmount.set(myViewModelGame.getMoneyAmount(username));
                Save save = new Save(preferences.getString("Username", ""), score.get(), moneyAmount.get());
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