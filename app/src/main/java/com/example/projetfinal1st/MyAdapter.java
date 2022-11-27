package com.example.projetfinal1st;


import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private String[] localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView employeeTextView;
        private final ImageView employeeImageView;
        private final ImageButton employeeDescriptionButton;
        private final Button employeeBuyButton;
        private final TextView employeeCountTextView;
        private final TextView employeeCountNumberTextView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            employeeTextView = (TextView) view.findViewById(R.id.employeeTextView);
            employeeCountTextView = (TextView) view.findViewById(R.id.employeeCountTextView);
            employeeCountNumberTextView = (TextView) view.findViewById(R.id.employeeCountNumber);
            employeeImageView = (ImageView) view.findViewById(R.id.employeeImageView);
            employeeDescriptionButton = (ImageButton) view.findViewById(R.id.employeeDescriptionButton);
            employeeBuyButton = (Button) view.findViewById(R.id.employeeBuyButton);
        }

        public TextView getEmployeeTextView() {
            return employeeTextView;
        }

        public TextView getEmployeeCountNumberTextView() {
            return employeeCountNumberTextView;
        }

        public TextView getEmployeeCountTextView() {
            return employeeCountTextView;
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
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CustomAdapter(String[] dataSet) {
        localDataSet = dataSet;
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

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getEmployeeTextView().setText(localDataSet[position]);
        viewHolder.getEmployeeCountTextView().setText(localDataSet[position]);
        viewHolder.getEmployeeCountNumberTextView().setText(localDataSet[position]);
        viewHolder.getEmployeeImageView().setImageDrawable(Drawable.createFromPath(localDataSet[position]));
        // TODO ?? viewHolder.getEmployeeBuyButton().set
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}
