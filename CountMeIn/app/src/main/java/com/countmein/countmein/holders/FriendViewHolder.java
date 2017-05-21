package com.countmein.countmein.holders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.countmein.countmein.R;

/**
 * Created by Home on 5/20/2017.
 */

public class FriendViewHolder extends RecyclerView.ViewHolder{
    public CardView cv;
    public TextView fName;
    public TextView fSurname;

    public FriendViewHolder(View itemView){
        super(itemView);

        cv = (CardView) itemView.findViewById(R.id.friend_cardview);
        fName = (TextView) itemView.findViewById(R.id.friend_name);
        fSurname = (TextView) itemView.findViewById(R.id.friend_surname);
    }
}