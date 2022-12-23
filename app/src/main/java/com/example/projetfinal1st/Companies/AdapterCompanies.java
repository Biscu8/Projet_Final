package com.example.projetfinal1st.Companies;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetfinal1st.AdapterEmployee;
import com.example.projetfinal1st.MyViewModelGame;
import com.example.projetfinal1st.R;
import com.example.projetfinal1st.Save;
import com.example.projetfinal1st.Score;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class AdapterCompanies extends RecyclerView.Adapter<AdapterCompanies.ViewHolder> {

    private final ArrayList<EntityCompanies> localDataSet;
    private static Context context;
    private static int m_money;
    private static int m_position;
    public static MyViewModelCompanies myViewModelCompanies;
    public static MyViewModelGame myViewModelGame;
    private static String m_id;
    private static Activity m_activity;
    private static ViewHolder m_viewHolder;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView textView;
        private final ImageView imageView;
        private final TextView textViewNb;
        private final Button button;
        private final TextView missingMoney;
        private  int m_money;
        private int m_position;
        private boolean bought;
        private MyViewModelCompanies myViewModelCompanies;
        private String m_id;
        private int m_price;
        private SharedPreferences preferences;

        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View view) {
            //retrieve money amount from the adapter
        m_money = AdapterCompanies.m_money;
        preferences = PreferenceManager.getDefaultSharedPreferences(AdapterCompanies.context);
            Executors.newSingleThreadExecutor().execute(()-> {
            //get which companie was clicked
                m_position = getAdapterPosition();
                myViewModelCompanies = AdapterCompanies.myViewModelCompanies;
                m_id = AdapterCompanies.m_id;
                List<EntityCompanies> companies = myViewModelCompanies.getAllCompanies(m_id);
                m_price = companies.get(m_position).getPrice();
                if (view.getId() == button.getId()) {
                //verify if the companie is arleady bought
                    if(!companies.get(m_position).isBought())
                    {
                        //verify if the user has enough money
                        if(m_money < m_price)
                        {
                            //make a red text appear that tel the user that he dont have enough money
                            AdapterCompanies.m_activity.runOnUiThread(()-> {
                                missingMoney.setTextColor(Color.RED);
                                missingMoney.setVisibility(View.VISIBLE);
                            });
                        }
                        else
                        {
                            //substract money
                            AdapterCompanies.m_money -= m_price;
                            //set the button text to sold out in red and enable it
                            AdapterCompanies.m_activity.runOnUiThread(()-> {
                                button.setText("Own");
                                button.setTextColor(Color.GREEN);
                                button.animate().rotationX(360);
                                Log.i("own bug", String.valueOf((m_position)));
                            });
                            //register in database
                            EntityCompanies newCompanie = companies.get(m_position);
                            newCompanie.setBought(true);
                            myViewModelCompanies.udpate(newCompanie);
                            //register score in database
                            Save save = new Save(m_id,newCompanie.getNbEmployees() + myViewModelGame.getSave(m_id).getScore(), m_money);
                            myViewModelGame.updateSave(save);
                        }
                    }
                }
            });
        }
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = view.findViewById(R.id.textViewCompanies);
            imageView = view.findViewById(R.id.imageViewCompanies);
            textViewNb = view.findViewById(R.id.textViewCompaniesNbEmployee);
            button = view.findViewById(R.id.buttonCompaniesBuy);
            button.setOnClickListener(this);
            missingMoney = view.findViewById(R.id.moneyMissing);

        }

        public TextView getTextView() {return textView;
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

        public void setPosition(int position) {
            m_position = position;
        }
        public int getMoney()
        {
            return AdapterCompanies.m_money;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     *                by RecyclerView
     */
    public AdapterCompanies(ArrayList<EntityCompanies> dataSet, MyViewModelCompanies myViewModelCompanies, Context context, int money, String id, Activity activity, MyViewModelGame myViewModelGame) {
        localDataSet = dataSet;
        this.context = context;
        m_money = money;
        this.myViewModelCompanies = myViewModelCompanies;
        m_id = id;
        m_activity = activity;
        this. myViewModelGame = myViewModelGame;
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
        viewHolder.setPosition(position);
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView().setText(localDataSet.get(position).getName());
        viewHolder.getImageView().setImageResource(localDataSet.get(position).getImage());
        viewHolder.getTextViewNb().setText(String.valueOf(localDataSet.get(position).getNbEmployees()));
        Executors.newSingleThreadExecutor().execute(()-> {

            if (!AdapterCompanies.myViewModelCompanies.getAllCompanies(m_id).get(position).isBought()) {
                AdapterCompanies.m_activity.runOnUiThread(()-> {
                    String buy = context.getResources().getString(R.string.companies_button_buy);
                    viewHolder.getButton().setText(buy + String.valueOf(localDataSet.get(position).getPrice()) + "$");
                });
            } else {
                AdapterCompanies.m_activity.runOnUiThread(()-> {
            viewHolder.getButton().setText("Own");
            viewHolder.getButton().setTextColor(Color.GREEN);
                });
        }
        });
        AdapterCompanies.m_viewHolder = viewHolder;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public ViewHolder getViewHolder()
    {
        return m_viewHolder;
    }

}