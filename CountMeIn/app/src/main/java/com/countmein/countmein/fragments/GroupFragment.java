package com.countmein.countmein.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.countmein.countmein.R;
import com.countmein.countmein.SelectedActivity;
import com.countmein.countmein.activities.NewGroupActivity;
import com.countmein.countmein.adapters.RVAdapter;
import com.countmein.countmein.beans.ActivityBean;
import com.countmein.countmein.listeners.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class GroupFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "RecyclerViewFragment";

    protected RecyclerView mRecyclerView;
    protected RVAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<ActivityBean> activities;


    public GroupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group, container, false);
        rootView.setTag(TAG);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),mRecyclerView,new RecyclerItemClickListener.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position){
                //selected item
                String selectedTitle = ((TextView)view.findViewById(R.id.activity_name)).getText().toString();
                String selectedDescription = ((TextView)view.findViewById(R.id.activity_description)).getText().toString();
                String selectedDate = ((TextView)view.findViewById(R.id.activity_date)).getText().toString();

                Toast toast = Toast.makeText(getApplicationContext(), selectedTitle, Toast.LENGTH_SHORT);
                toast.show();
                Intent i = new Intent(getActivity(), NewGroupActivity.class);
                i.putExtra("naslov", selectedTitle);
                i.putExtra("opis", selectedDescription);
                i.putExtra("datum", selectedDate);

                startActivity(i);
            }
        }));

        mAdapter = new RVAdapter(activities);
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);


        return rootView;
    }

    public void getData(){
        ArrayList<ActivityBean> act1 = new ArrayList<>();

        act1.add(new ActivityBean("grupa 1","Ovo je grupa 1", new Date().toString()));
        act1.add(new ActivityBean("grupa 2","Ovo je grupa 2", new Date().toString()));
        act1.add(new ActivityBean("grupa 3","Ovo je grupa 3", new Date().toString()));
        act1.add(new ActivityBean("grupa 4","Ovo je grupa 4", new Date().toString()));

        activities = act1;

    }
}
