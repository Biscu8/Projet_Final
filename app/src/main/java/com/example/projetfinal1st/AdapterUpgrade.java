package com.example.projetfinal1st;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterUpgrade extends RecyclerView.Adapter<AdapterUpgrade.ViewHolder> {

    private static ArrayList<EntityUpgrade> localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView imageView;
        private final Button buttonDesc;
        private final Button buttonBuy;

        public ViewHolder(View view) {
            super(view);
            //Define click listener for the ViewHolder's View

            textView = view.findViewById(R.id.textViewCompanies);
            imageView = view.findViewById(R.id.imageViewCompanies);
            buttonDesc = view.findViewById(R.id.buttonUpgradeDesc);
            buttonBuy = view.findViewById(R.id.buttonCompaniesBuy);
        }

        public TextView getTextView() {
            return textView;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public Button getButtonDesc() {
            return buttonDesc;
        }

        public Button getButtonBuy() {
            return buttonBuy;
        }

    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet ArrayList<EntityUpgrade> containing the data to populate views to be used
     *                by RecyclerView
     */
    public AdapterUpgrade(ArrayList<EntityUpgrade> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_upgrade, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView().setText(localDataSet.get(position).getName());
        viewHolder.getImageView().setImageResource(localDataSet.get(position).getImage());
        viewHolder.getButtonDesc().setText(localDataSet.get(position).getDescription());
        viewHolder.getButtonBuy().setText(R.string.upgrade_button_buy + String.valueOf(localDataSet.get(position).getPrice()) + "$");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size(); //TODO FIX THIS TOO
    }

}
