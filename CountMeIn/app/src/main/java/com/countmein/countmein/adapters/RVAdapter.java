package com.countmein.countmein.adapters;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.countmein.countmein.R;
import com.countmein.countmein.activities.MainActivity;
import com.countmein.countmein.activities.SelectedActivity;
import com.countmein.countmein.beans.ActivityBean;
import com.countmein.countmein.fragments.MainFragment;
import com.countmein.countmein.listeners.RecyclerItemClickListener;

import java.util.Collection;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by zivic on 4/23/2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ActivityViewHolder> {

    private List<ActivityBean> activities;

    public RVAdapter(Collection<List<ActivityBean>> values) {
    }

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
        LinearLayout ln = (LinearLayout) holder.cv.findViewById(R.id.text_container);
        ln.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                String selectedTitle = ((TextView)view.findViewById(R.id.activity_name)).getText().toString();
                String selectedDescription = ((TextView)view.findViewById(R.id.activity_description)).getText().toString();
                String selectedDate = ((TextView)view.findViewById(R.id.activity_date)).getText().toString();

                Toast toast = Toast.makeText(getApplicationContext(), selectedTitle, Toast.LENGTH_SHORT);
                toast.show();
                Intent i = new Intent(view.getContext(), SelectedActivity.class);
                i.putExtra("naslov", selectedTitle);
                i.putExtra("opis", selectedDescription);
                i.putExtra("datum", selectedDate);

                view.getContext().startActivity(i);
            }
        });
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
