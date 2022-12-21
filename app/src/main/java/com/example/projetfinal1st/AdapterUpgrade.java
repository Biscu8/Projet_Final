package com.example.projetfinal1st;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterUpgrade extends RecyclerView.Adapter<AdapterUpgrade.ViewHolder> {

    private final ArrayList<String> localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
      //  private final TextView textView;
       // private final ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

           // textView = view.findViewById(R.id.textViewUpgrade);
            //imageView = view.findViewById(R.id.imageViewUpgrade);
        }

        //public TextView getTextView() {
         //   return textView;
        //}

        //public ImageView getImageView() {
          //  return imageView;
        //}
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     *                by RecyclerView
     */
    public AdapterUpgrade(ArrayList<String> dataSet) {
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
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
      //  viewHolder.getTextView().setText("t");//localDataSet.get(position)); //TODO FIX THIS
        //viewHolder.getImageView().setImageResource(R.drawable.george);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return 1;//localDataSet.size(); //TODO FIX THIS TOO
    }

}
