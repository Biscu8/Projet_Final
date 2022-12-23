package com.example.projetfinal1st.Upgrade;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetfinal1st.Companies.AdapterCompanies;
import com.example.projetfinal1st.MyViewModelGame;
import com.example.projetfinal1st.R;
import com.example.projetfinal1st.Save;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class AdapterUpgrade extends RecyclerView.Adapter<AdapterUpgrade.ViewHolder> {

    private final ArrayList<EntityUpgrade> localDataSet;
    private static Context context;
    private static int m_money;
    private static int m_position;
    public static MyViewModelUpgrade myViewModelUpgrade;
    public static MyViewModelGame myViewModelGame;
    private static String m_id;
    private static Activity m_activity;
    private static ViewHolder m_viewHolder;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        private final TextView textView;
        private final ImageView imageView;
        private final Button buttonDesc;
        private final Button buttonBuy;
        private int m_money;
        private int m_position;
        private boolean bought;
        private MyViewModelUpgrade myViewModelUpgrade;
        private String m_id;
        private int m_price;
        private SharedPreferences preferences;

        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View view) {
        //retrieve money amount from adapter
            m_money = AdapterUpgrade.m_money;
            preferences = PreferenceManager.getDefaultSharedPreferences(AdapterUpgrade.context);
            Executors.newSingleThreadExecutor().execute(()-> {
                //get which upgrade was click
                m_position = getAdapterPosition();
                myViewModelUpgrade = AdapterUpgrade.myViewModelUpgrade;
                m_id = AdapterUpgrade.m_id;
                List<EntityUpgrade> upgrades = myViewModelUpgrade.getAllUpgrades(m_id);
                m_price = upgrades.get(m_position).getPrice();
                if(view.getId() == buttonBuy.getId()) {
                    //verify if the upgrade is already bought
                    if (!upgrades.get(m_position).getBought()) {
                        //verify if the user has enough money
                        if (m_money < m_price) {
                            //TODO make a red text appear that tel the user that he dont have enough money
                        } else {
                            //substract money
                            AdapterUpgrade.m_money -= m_price;
                            //set the button text to sold out in red and enable it
                            AdapterUpgrade.m_activity.runOnUiThread(() -> {
                                buttonBuy.setText("Sold out");
                                buttonBuy.setTextColor(Color.GREEN);
                                buttonBuy.animate().rotationX(360);
                            });
                            //register in database
                            EntityUpgrade newUpgrade = upgrades.get(m_position);
                            newUpgrade.setBought(true);
                            myViewModelUpgrade.udpate(newUpgrade);
                            //register score in database
                            Save save = new Save(m_id, myViewModelGame.getSave(m_id).getScore(), m_money);
                            myViewModelGame.updateSave(save);
                        }
                    }
                }
            });
        }
        public ViewHolder(View view) {
            super(view);
            //Define click listener for the ViewHolder's View

            textView = view.findViewById(R.id.textViewCompanies);
            imageView = view.findViewById(R.id.imageViewCompanies);
            buttonDesc = view.findViewById(R.id.buttonUpgradeDesc);
            buttonDesc.setOnClickListener(this);
            buttonBuy = view.findViewById(R.id.buttonCompaniesBuy);
            buttonBuy.setOnClickListener(this);
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

        public int getMoney()
        {
            return AdapterUpgrade.m_money;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet ArrayList<EntityUpgrade> containing the data to populate views to be used
     *                by RecyclerView
     */
    public AdapterUpgrade(ArrayList<EntityUpgrade> dataSet, MyViewModelUpgrade myViewModelUpgrade, Context context, int money, String id, Activity activity, MyViewModelGame myViewModelGame) {
        localDataSet = dataSet;
        this.context = context;
        m_money = money;
        this.myViewModelUpgrade = myViewModelUpgrade;
        m_id = id;
        m_activity = activity;
        this.myViewModelGame = myViewModelGame;
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
        Executors.newSingleThreadExecutor().execute(()-> {
            if(!AdapterUpgrade.myViewModelUpgrade.getAllUpgrades(m_id).get(m_position).getBought()) {
                AdapterUpgrade.m_activity.runOnUiThread(()-> {
                    String buy = context.getResources().getString(R.string.companies_button_buy);
                    viewHolder.getButtonBuy().setTextColor(Color.GRAY);
                    viewHolder.getButtonBuy().setText(buy + String.valueOf(localDataSet.get(position).getPrice()) + "$");
                });
            }
            else
            {
                AdapterUpgrade.m_activity.runOnUiThread(()->{
                    viewHolder.getButtonBuy().setText("Sold out");
                    viewHolder.getButtonBuy().setTextColor(Color.GREEN);
                });
            }
        });
        AdapterUpgrade.m_viewHolder = viewHolder;
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
