package com.countmein.countmein.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.countmein.countmein.R;
import com.countmein.countmein.beans.ActivityBean;

import java.util.List;

/**
 * Created by zivic on 4/23/2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ActivityViewHolder> {

    public static class ActivityViewHolder extends RecyclerView.ViewHolder{
        private CardView cv;
        private TextView vName;
        private TextView vDescription;
        private TextView vDate;

        public ActivityViewHolder(View itemView){
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardview);
            vName = (TextView) itemView.findViewById(R.id.activity_name);
            vDescription = (TextView) itemView.findViewById(R.id.activity_description);
            vDate = (TextView) itemView.findViewById(R.id.activity_date);
        }
    }

    private List<ActivityBean> activities;


    public RVAdapter(List<ActivityBean> activities){
        this.activities = activities;
    }



    @Override
    public ActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.single_card_view, parent, false); //check whether single_card_view should be here

        ActivityViewHolder avh = new ActivityViewHolder(v);
        return avh;
    }


    @Override
    public void onBindViewHolder(ActivityViewHolder holder, int i) {
        holder.vName.setText(activities.get(i).name);
        holder.vDescription.setText(activities.get(i).description);
        holder.vDate.setText((CharSequence) activities.get(i).date);

    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }


}
