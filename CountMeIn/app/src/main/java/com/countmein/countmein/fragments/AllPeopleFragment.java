package com.countmein.countmein.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.countmein.countmein.R;
import com.countmein.countmein.activities.HomeActivity;
import com.countmein.countmein.beans.PersonInfoBean;
import com.countmein.countmein.beans.User;
import com.countmein.countmein.holders.FriendActivityViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 5/21/2017.
 */

public class AllPeopleFragment  extends Fragment {
    private static final String TAG = "RecyclerViewFragment";

    private List<PersonInfoBean> details;
    protected RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter<User,FriendActivityViewHolder> adapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    public  AllPeopleFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeActivity.toolbar.setTitle("My Friends");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friend, container, false);
        rootView.setTag(TAG);


        return rootView;
    }


}
