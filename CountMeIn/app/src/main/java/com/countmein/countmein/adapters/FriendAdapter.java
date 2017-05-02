package com.countmein.countmein.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.countmein.countmein.R;
import com.countmein.countmein.beans.PersonInfoBean;

import java.util.List;

/**
 * Created by sweet_000 on 4/24/2017.
 */

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ActivityViewHolder>  {

    private List<PersonInfoBean> details;


    public FriendAdapter(List<PersonInfoBean> details){
        this.details = details;
    }

    public static class ActivityViewHolder extends RecyclerView.ViewHolder{
        private CardView cv;
        private TextView fName;
        private TextView fSurname;

        public ActivityViewHolder(View itemView){
            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.friend_cardview);
            fName = (TextView) itemView.findViewById(R.id.friend_name);
            fSurname = (TextView) itemView.findViewById(R.id.friend_surname);
        }
    }

    @Override
    public FriendAdapter.ActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.friend_card_view, parent, false);

        FriendAdapter.ActivityViewHolder avh = new ActivityViewHolder(v);
        return avh;
    }

    @Override
    public void onBindViewHolder(FriendAdapter.ActivityViewHolder holder, int i) {
        holder.fName.setText(details.get(i).getName());
        holder.fSurname.setText(details.get(i).getSurname());

    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }
}
