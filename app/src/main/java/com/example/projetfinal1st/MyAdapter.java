package com.example.projetfinal1st;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private final ArrayList<Employee> localDataSet;
    private static String m_money;
    private static int employeeCountNumber;

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
        private String m_money;

        //get the money amount
        @Override
        public void onClick(View view) {
            m_money = MyAdapter.m_money;
            if (view.getId() == employeeBuyButton.getId()) {
                //convert m_money string to int
                int money;//TODO pas hardcode le money
                if(Objects.equals(m_money, "Infinite Money"))
                {
                    money = 99999999;
                }
                else
                {
                    if("$".equals(m_money.substring(m_money.length() - 1)))
                    {
                        money = Integer.parseInt(m_money.substring(0, m_money.length() - 1));
                    }
                    else
                    {
                        money = Integer.parseInt(m_money);
                    }
                }
                if (money < 1000) {
                    //change UI to print a buy error
                    getMissingMoney().setVisibility(View.VISIBLE);
                } else {
                    getEmployeeCountNumberTextView().setText(String.valueOf(Integer.parseInt(String.valueOf(employeeCountNumberTextView.getText())) + 1));
                    money -= 1000;
                    m_money = money + "$";
                    MyAdapter.m_money = m_money;
                    MyAdapter.employeeCountNumber = Integer.parseInt(String.valueOf(getEmployeeCountNumberTextView().getText()));
                }
            }
            else {
                Toast.makeText(view.getContext(), "ROW PRESSED = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
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

        public void setMoney(String money)
        {
            m_money = money;
        }

        public String getMoney()
        {
            return m_money;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     * @param dataSet ArrayList<Employee> containing the data to populate views to be used by RecyclerView.
     * @param money1
     */
    public MyAdapter(ArrayList<Employee> dataSet, String money1) {
        localDataSet = dataSet;
        m_money = money1;
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
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.setMoney(m_money);
        viewHolder.getEmployeeTextView().setText(localDataSet.get(position).getName());
        viewHolder.getEmployeeCountNumberTextView().setText(String.valueOf(localDataSet.get(position).getQuantity()));
        viewHolder.getEmployeeImageView().setImageResource(localDataSet.get(position).getImage());
        viewHolder.getEmployeeBuyButton().setText((localDataSet.get(position).getPrice()) + "$");
        
        // TODO ?? view.Holder.getEmployeeDescriptionButton().set
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
    public String getMoney()
    {
        return m_money;
    }
    public int getEmployeeCountNumber()
    {
        return employeeCountNumber;
    }

}

