package com.countmein.countmein.holders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.countmein.countmein.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Home on 5/21/2017.
 */

public class PeopleViewHolder extends RecyclerView.ViewHolder{
    public CardView cv;
    public TextView messageUser;

    public SimpleDraweeView userPhoto;

    public PeopleViewHolder(View itemView){
        super(itemView);

        cv = (CardView) itemView.findViewById(R.id.friend_cardview);

        messageUser = (TextView) itemView.findViewById(R.id.messageUser);
        userPhoto=(SimpleDraweeView) itemView.findViewById(R.id.userPhoto);
    }
}