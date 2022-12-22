package com.example.projetfinal1st;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.time.format.TextStyle;
import java.util.ArrayList;

public class AdapterCompanies extends RecyclerView.Adapter<AdapterCompanies.ViewHolder> {

    private final ArrayList<EntityCompanies> localDataSet;
    private final Context context;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final ImageView imageView;
        private final TextView textViewNb;
        private final Button button;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = (TextView) view.findViewById(R.id.textViewCompanies);
            imageView = (ImageView) view.findViewById(R.id.imageViewCompanies);
            textViewNb = (TextView) view.findViewById(R.id.textViewCompaniesNbEmployee);
            button = (Button) view.findViewById(R.id.buttonCompaniesBuy);
        }

        public TextView getTextView() {
            return textView;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getTextViewNb() {
            return textViewNb;
        }

        public Button getButton() {
            return button;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     *                by RecyclerView
     */
    public AdapterCompanies(ArrayList<EntityCompanies> dataSet, Context context) {
        localDataSet = dataSet;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_companies, viewGroup, false);

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
        viewHolder.getTextViewNb().setText(String.valueOf(localDataSet.get(position).getNbEmployees()));
        String buy = context.getResources().getString(R.string.companies_button_buy);
        viewHolder.getButton().setText(buy + String.valueOf(localDataSet.get(position).getPrice()) + "$");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

}