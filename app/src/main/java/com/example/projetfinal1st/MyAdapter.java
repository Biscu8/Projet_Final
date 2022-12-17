package com.example.projetfinal1st;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private final ArrayList<Employee> localDataSet;
    private final ClickListener listener;
    private static String m_money;

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
        private boolean removeDollarsSign = true;
        private WeakReference<ClickListener> listenerWeakReference;

        //get the money amount
        @Override
        public void onClick(View view) {
            if (view.getId() == employeeBuyButton.getId()) {
                //convert m_money string to int
                int money;
                if(m_money == "Infinite Money")
                {
                    money = 99999999;
                }
                else {
                    if(removeDollarsSign) {
                        money = Integer.valueOf(m_money.substring(0, m_money.length() - 1));
                        removeDollarsSign = false;
                    }
                    else
                    {
                        money = Integer.valueOf(m_money);
                    }
                    if (money < 1000) {
                        //change UI to print a buy error
                        getMissingMoney().setVisibility(View.VISIBLE);
                    } else {
                        getEmployeeCountNumberTextView().setText(String.valueOf(Integer.parseInt(String.valueOf(employeeCountNumberTextView.getText())) + 1));
                        money -= 1000;
                        m_money = String.valueOf(money);
                        MyAdapter.m_money = m_money;
                    }
                }
            }
            else {
                Toast.makeText(view.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }
            listenerWeakReference.get().onPositionClicked(getAdapterPosition());
        }

        public ViewHolder(View view, ClickListener listener) {
            super(view);
            // Define click listener for the ViewHolder's View
            listenerWeakReference = new WeakReference<>(listener);

            employeeTextView = (TextView) view.findViewById(R.id.employeeTextView);
            employeeCountNumberTextView = (TextView) view.findViewById(R.id.employeeCountNumber);
            employeeImageView = (ImageView) view.findViewById(R.id.employeeImageView);
            employeeDescriptionButton = (ImageButton) view.findViewById(R.id.employeeDescriptionButton);
            employeeBuyButton = (Button) view.findViewById(R.id.employeeBuyButton);
            employeeBuyButton.setOnClickListener(this);
            missingMoney = (TextView) view.findViewById(R.id.missingMoney);
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
     */
    public MyAdapter(ArrayList<Employee> dataSet, String money, ClickListener listener) {
        localDataSet = dataSet;
        this.listener = listener;
        m_money = money;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_employees, viewGroup, false);

        return new ViewHolder(view, listener);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.setMoney(m_money);
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
         viewHolder.getEmployeeTextView().setText(localDataSet.get(position).getName());
         viewHolder.getEmployeeCountNumberTextView().setText(String.valueOf(localDataSet.get(position).getQuantity()));
        viewHolder.getEmployeeImageView().setImageResource(localDataSet.get(position).getImage());

      //  switch(localDataSet[position].getId())
      //  {
       //     case R.drawable.george:

        //        break;
       // }
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
}

