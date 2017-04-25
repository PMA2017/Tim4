package com.countmein.countmein.fragments;

import android.content.Intent;
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
import com.countmein.countmein.activities.SelectedActivity;
import com.countmein.countmein.adapters.RVAdapter;
import com.countmein.countmein.beans.ActivityBean;
import com.countmein.countmein.listeners.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;


public class AttendingActivitiesFragment extends Fragment {

        private static final String TAG = "RecyclerViewFragment";
        private static final String KEY_LAYOUT_MANAGER = "layoutManager";
        private static final int SPAN_COUNT = 2;
        private static final int DATASET_COUNT = 60;

        private enum LayoutManagerType {
            GRID_LAYOUT_MANAGER,
            LINEAR_LAYOUT_MANAGER
        }

        protected RecyclerView mRecyclerView;
        protected RVAdapter mAdapter;
        protected RecyclerView.LayoutManager mLayoutManager;
        protected List<ActivityBean> activities;

        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);

            getData();

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_attending_activities, container, false);
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
                    Intent i = new Intent(getActivity(), SelectedActivity.class);
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

            act1.add(new ActivityBean("Bleja 1","Ovo je bleja 1", new Date().toString()));
            act1.add(new ActivityBean("Bleja 2","Ovo je bleja 2", new Date().toString()));
            act1.add(new ActivityBean("Bleja 3","Ovo je bleja 3", new Date().toString()));
            act1.add(new ActivityBean("Bleja 4","Ovo je bleja 4", new Date().toString()));
            act1.add(new ActivityBean("Bleja 5","Ovo je bleja 5", new Date().toString()));
            act1.add(new ActivityBean("Bleja 6","Ovo je bleja 6", new Date().toString()));
            act1.add(new ActivityBean("Bleja 7","Ovo je bleja 7", new Date().toString()));


            activities = act1;

        }
}
