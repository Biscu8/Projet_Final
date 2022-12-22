package com.example.projetfinal1st;

import android.annotation.SuppressLint;
import android.content.Entity;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;

public class MainGame extends AppCompatActivity {
    private SharedPreferences preferences;
    private MyViewModelGame myViewModelGame;
    private Score score;
    private String username;
    private  ArrayList<EntityEmployee> arrayEmployee;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        username = preferences.getString("Username", "");
        myViewModelGame = new ViewModelProvider(this).get(MyViewModelGame.class);
        // ArrayList of employees
         arrayEmployee = new ArrayList<>();

        // Initiate the score of the user


        Executors.newSingleThreadExecutor().execute(() -> {
                    // Verify is user has save
                    if (myViewModelGame.getSave(username) != null) {
                        //Update UI with text database moneyAmount
                        int clickAmount = myViewModelGame.getSave(username).getScore();
                        TextView clickAmountView = findViewById(R.id.clickAmount);
                        clickAmountView.setText(String.valueOf(clickAmount));
                        // Update employees
                        List<EntityEmployee> employees = myViewModelGame.getAllEmployeeWithSameId(username);
                        arrayEmployee = new ArrayList<>();
                        for (int i = 0; i < employees.size(); i++) {
                            EntityEmployee employee = employees.get(i);
                            arrayEmployee.add(employee);
                            new AutoClicker(this, myViewModelGame.getSave(username).getScore(), arrayEmployee.get(i).getRate());
                        }
                        //verify if the user is opening the app or is coming back from the employee tab
                        TextView moneyAmount = findViewById(R.id.moneyAmount);
                        if (getIntent().getStringExtra("MoneyMinusBuy") != null) {

                            runOnUiThread(() -> {
                                //update UI with MoneyAmount
                                moneyAmount.setText(getIntent().getStringExtra("MoneyMinusBuy"));
                            });

                            //remove Extra data
                            getIntent().removeExtra("MoneyMinusBuy");
                            saveGameInDatabase();
                        } else {
                            //Update UI with database data
                            int money = myViewModelGame.getMoneyAmount(username);
                            runOnUiThread(() -> {
                                moneyAmount.setText(String.valueOf(money));
                            });
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
                        //update data with new data
                        saveGameInDatabase();
                        // if preference infinite money is check update UI
                        if (preferences.getBoolean("InfiniteMoney", false)) {
                            moneyAmount.setText("Infinite Money");
                        }

                    } else {
//Initiate the employees with default stats
                        EntityEmployee employee1 = new EntityEmployee(0,"charlo",username ,"desc",2 , 1,R.drawable.george);;
                        EntityEmployee employee2 = new EntityEmployee(0,"noob",username ,"desc",4 , 2000,R.drawable.george);
                        EntityEmployee employee3 = new EntityEmployee(0,"jay",username ,"desc",6 , 3000,R.drawable.george);
                        EntityEmployee employee4 = new EntityEmployee(0,"cody",username ,"desc",8 , 4000,R.drawable.george);
                        EntityEmployee employee5 = new EntityEmployee(0,"brrr",username ,"desc",10 , 5000,R.drawable.george);
                        EntityEmployee employee6 = new EntityEmployee(0,"sims",username ,"desc",12 , 6000,R.drawable.george);
                        EntityEmployee employee7 = new EntityEmployee(0,"qqw",username ,"desc",14 , 7000,R.drawable.george);
                        EntityEmployee employee8 = new EntityEmployee(0,"qwerty",username ,"desc",16 , 4000,R.drawable.george);
                        arrayEmployee.add(employee1);
                        arrayEmployee.add(employee2);
                        arrayEmployee.add(employee3);
                        arrayEmployee.add(employee4);
                        arrayEmployee.add(employee5);
                        arrayEmployee.add(employee6);
                        arrayEmployee.add(employee7);
                        arrayEmployee.add(employee8);
                        //put all preferences to default
                      //  preferences.getAll().clear();//pas sur
                        //if there is no user initiate score to 0 and create a new save
                        score = new Score(0);
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
                        for(int i= 0; i < myViewModelGame.getAllEmployeeWithSameId(username).size(); i++)
                                {
                                      Log.i("name", myViewModelGame.getAllEmployeeWithSameId(username).get(i).getName());
                                      Log.i("desc", myViewModelGame.getAllEmployeeWithSameId(username).get(i).getDescription());
                                      Log.i("size",String.valueOf(myViewModelGame.getAllEmployeeWithSameId(username).size()));
                                }
                    }
            // Normal, hand clicker
            findViewById(R.id.ClickButton).setOnClickListener(view -> {

                //Update clickAmount view + 1
                TextView clickAmount = findViewById(R.id.clickAmount);
                int m_score = Integer.parseInt(clickAmount.getText().toString()) + 1;
                clickAmount.setText(String.valueOf(m_score));
                if (!preferences.getBoolean("InfiniteMoney", false)) {

                    // Update moneyAmount view + 10
                    TextView moneyAmount = findViewById(R.id.moneyAmount);

                    // start a new thread
                    Executors.newSingleThreadExecutor().execute(() -> {

                        //update database with the new data
                        Save save = new Save(username, Integer.parseInt(String.valueOf(clickAmount.getText())), Integer.parseInt(String.valueOf(moneyAmount.getText())));
                        myViewModelGame.updateSave(save);
                    });
                }
            });
            // Open employees tab
            // TODO share score between activities with database
            findViewById(R.id.buttonEmployee).setOnClickListener(view -> {
                Intent intent = new Intent(this, MainEmployee.class);
                TextView money = findViewById(R.id.moneyAmount);
                intent.putExtra("Money", Integer.parseInt(String.valueOf(money.getText())));

                //put money in database
                saveGameInDatabase();
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
                            moneyAmount.setText(String.valueOf(m_score));
                        }
                    });
                }
            };
            timer.scheduleAtFixedRate(timerTask, 0, 1000);
        });
    }
        public void saveGameInDatabase ()
        {
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