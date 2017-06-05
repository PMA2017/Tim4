package com.countmein.countmein.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.countmein.countmein.R;
import com.countmein.countmein.activities.HomeActivity;
import com.countmein.countmein.activities.NewActivityActivity;
import com.countmein.countmein.activities.SelectedActivity;
import com.countmein.countmein.beans.ActivityBean;
import com.countmein.countmein.holders.ActivityViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.

 */
public class ActivitiesFragment extends Fragment {
    private static final String TAG = "RecyclerViewFragment";

    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    View rootView;
    private FirebaseRecyclerAdapter<ActivityBean,ActivityViewHolder> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        HomeActivity.toolbar.setTitle(R.string.my_activites);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_recycle_view, container, false);
        rootView.setTag(TAG);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapter  = new FirebaseRecyclerAdapter<ActivityBean,ActivityViewHolder >(ActivityBean.class,
                R.layout.single_card_view,ActivityViewHolder.class, FirebaseDatabase.getInstance().getReference().child("useractivities").child(FirebaseAuth.getInstance().getCurrentUser().getUid())) {

            @Override
            protected void populateViewHolder(ActivityViewHolder viewHolder, ActivityBean model, int position) {
                viewHolder.vName.setText(model.name);
                viewHolder.vDescription.setText(model.description);
                viewHolder.vDate.setText(model.date);

                //viewHolder.vDate.setVisibility(View.GONE);
                viewHolder.cv.findViewById(R.id.button_view_attending_activity).setVisibility(View.GONE);
                viewHolder.cv.findViewById(R.id.layout_checkbox).setVisibility(View.GONE);

                ImageButton btnEdit = (ImageButton) viewHolder.cv.findViewById(R.id.activity_edit);
                ImageButton btnDelete = (ImageButton) viewHolder.cv.findViewById(R.id.activity_delete);
                LinearLayout ln = (LinearLayout) viewHolder.cv.findViewById(R.id.text_container);
                btnEdit.setTag(model);
                btnDelete.setTag(model);
                ln.setTag(model);

                ln.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view){
                        ActivityBean activity=(ActivityBean) view.getTag();
                        Intent i = new Intent(view.getContext(), SelectedActivity.class);
                        Bundle data= new Bundle();
                        data.putSerializable("data",activity);
                        i.putExtras(data);
                        view.getContext().startActivity(i);
                    }
                });

                btnEdit.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        ActivityBean activity=(ActivityBean) view.getTag();
                        Intent i = new Intent(view.getContext(), NewActivityActivity.class);
                        Bundle data= new Bundle();
                        data.putSerializable("data",activity);
                        data.putInt("isEdit", 1);
                        i.putExtras(data);
                        view.getContext().startActivity(i);
                    }

                });

                btnDelete.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        ActivityBean activity=(ActivityBean) view.getTag();
                        FirebaseDatabase.getInstance().getReference()
                                .child("useractivities").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .child(activity.getId()).removeValue();
                    }
                });
            }
        };

        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(adapter);

        return rootView;
    }
}