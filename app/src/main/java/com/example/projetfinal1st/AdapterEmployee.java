package com.example.projetfinal1st;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class AdapterEmployee extends RecyclerView.Adapter<AdapterEmployee.ViewHolder> {

    private static ArrayList<EntityEmployee> localDataSet;
    private static int m_money;
    private static int employeeCountNumber;
    private static int m_position;
    private static Bundle m_bundle;
    private static MyViewModelGame m_myViewModelGame;
    private static String m_username;
    private static Activity m_Activity;
    private static ViewHolder m_viewHolder;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView employeeTextView;
        private final ImageView employeeImageView;
        private final ImageButton employeeDescriptionButton;
        private final Button employeeBuyButton;
        private final TextView employeeCountNumberTextView;
        private final TextView missingMoney;
        private int m_money;
        private MyViewModelGame myViewModelGame;
        private String id;
        private int m_position;

        //get the money amount
        @Override
        public void onClick(View view) {
            setid();
            setViewModel();
            Executors.newSingleThreadExecutor().execute(()-> {
                List<EntityEmployee> employees = getMyViewModelGame().getAllEmployeeWithSameId(getId());
                m_money = AdapterEmployee.m_money;
                int price = employees.get(m_position).getPrice();
                // m_position = AdapterEmployee.m_position;
                if (view.getId() == employeeBuyButton.getId()) {
                    if (m_money < price) {
                        //change UI to print a buy error
                        AdapterEmployee.m_Activity.runOnUiThread(()-> {
                                    getMissingMoney().setVisibility(View.VISIBLE);
                                });
                        Log.i("Not enough money", String.valueOf(m_money));
                    } else {
                        //change Ui
                        AdapterEmployee.m_Activity.runOnUiThread(()-> {
                                    getEmployeeCountNumberTextView().setText(String.valueOf(Integer.parseInt(String.valueOf(employeeCountNumberTextView.getText())) + 1));
                            localDataSet.get(m_position).setQuantity(Integer.parseInt(String.valueOf(employeeCountNumberTextView.getText())));
                            Log.i("getQuantity",String.valueOf(localDataSet.get(m_position).getQuantity()));
                                });
                        //save the moneyCount
                        AdapterEmployee.m_money -= price;
                        setMoney(AdapterEmployee.m_money);

                        Log.i("moneyCount", String.valueOf(AdapterEmployee.m_money));
                    }
                }
            });
        }

        public ViewHolder(View view) {
            super(view);
            employeeTextView = view.findViewById(R.id.employeeTextView);
            //manage to get the intent into this adapter
            employeeCountNumberTextView = view.findViewById(R.id.employeeCountNumber);
            //employeeCountNumberTextView.setText(m_newEmployeeCountNumber);
            employeeImageView = view.findViewById(R.id.employeeImageView);
            employeeDescriptionButton = view.findViewById(R.id.employeeDescriptionButton);
            employeeBuyButton = view.findViewById(R.id.employeeBuyButton);
            employeeBuyButton.setOnClickListener(this);
            missingMoney = view.findViewById(R.id.missingMoney);
        }
        public TextView getMissingMoney()
        {
            return missingMoney;
        }

        public TextView getEmployeeTextView() {
            return employeeTextView;
        }

        public TextView getEmployeeCountNumberTextView() {

            return employeeCountNumberTextView;
        }

        public Button getEmployeeBuyButton() {
            return employeeBuyButton;
        }

        public ImageButton getEmployeeDescriptionButton() {
            return employeeDescriptionButton;
        }

        public ImageView getEmployeeImageView() {
            return employeeImageView;
        }

        public void setMoney(int money)
        {
            this.m_money = money;
        }

        public void setPosition(int position) {
            m_position = position;
        }

        public void setBundle(Bundle bundle) {
            m_bundle = bundle;
        }

        public int getMoney()
        {
            return AdapterEmployee.m_money;
        }

        public Bundle getBundle() {
            return m_bundle;
        }
        public void setViewModel()
        {
            myViewModelGame = AdapterEmployee.m_myViewModelGame;
        }

        public MyViewModelGame getMyViewModelGame() {
            return myViewModelGame;
        }
        public void setid()
        {
            id = AdapterEmployee.m_username;
        }
        public String getId()
        {
            return id;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     * @param dataSet ArrayList<Employee> containing the data to populate views to be used by RecyclerView.
     * @param money
     */
    public AdapterEmployee(ArrayList<EntityEmployee> dataSet, int money, MyViewModelGame viewModelGame, String username, Activity activity) {
        localDataSet = dataSet;
        m_money = money;
        m_myViewModelGame = viewModelGame;
        m_username = username;
        m_Activity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_employees, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.setPosition(position);
        viewHolder.getEmployeeTextView().setText(localDataSet.get(position).getName());
        viewHolder.getEmployeeCountNumberTextView().setText(String.valueOf(localDataSet.get(position).getQuantity()));
        viewHolder.getEmployeeImageView().setImageResource(localDataSet.get(position).getImage());
        viewHolder.getEmployeeBuyButton().setText((localDataSet.get(position).getPrice()) + "$");
        AdapterEmployee.m_viewHolder = viewHolder;
        
        // TODO ?? view.Holder.getEmployeeDescriptionButton().set
    }

    // Get position?
    public int getPosition() {
        return m_position;
    }
    public ViewHolder getViewHolder()
    {
        return m_viewHolder;
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
    public int getMoney()
    {
        return m_money;
    }
    public int getEmployeeCountNumber()
    {
        return employeeCountNumber;
    }

}

