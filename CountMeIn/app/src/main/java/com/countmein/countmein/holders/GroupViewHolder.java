package com.countmein.countmein.holders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.countmein.countmein.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Home on 6/5/2017.
 */

public class GroupViewHolder  extends RecyclerView.ViewHolder{
    public CardView cv;
    public TextView vName;
    public TextView vDescription;


    public SimpleDraweeView userPhoto;

    public GroupViewHolder(View itemView){
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardview);
        vName = (TextView) itemView.findViewById(R.id.activity_name);
        vDescription = (TextView) itemView.findViewById(R.id.activity_description);
    }
}