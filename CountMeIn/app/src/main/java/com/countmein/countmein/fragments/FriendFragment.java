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
import com.countmein.countmein.adapters.FriendAdapter;
import com.countmein.countmein.adapters.RVAdapter;
import com.countmein.countmein.beans.ActivityBean;
import com.countmein.countmein.beans.PersonInfoBean;
import com.countmein.countmein.listeners.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;


public class FriendFragment extends Fragment {
    private static final String TAG = "RecyclerViewFragment";

    private  List<PersonInfoBean> details;
    protected RecyclerView mRecyclerView;
    protected FriendAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    public FriendFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_friend, container, false);
        rootView.setTag(TAG);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


/*        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),mRecyclerView,new RecyclerItemClickListener.OnItemClickListener(){
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
        */
        mAdapter = new FriendAdapter(details);
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);


        return rootView;
    }

    public List<PersonInfoBean> getData(){
        details = new ArrayList<>();
        details.add(new PersonInfoBean("Ivana","Zivic"));
        details.add(new PersonInfoBean("Ivan","Divljak"));
        details.add(new PersonInfoBean("Violeta","Novakovic"));
        return details;
    }
}
