package com.countmein.countmein.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.countmein.countmein.R;
import com.countmein.countmein.activities.HomeActivity;
import com.countmein.countmein.beans.User;
import com.countmein.countmein.holders.PeopleViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 6/5/2017.
 */

public class GroupFriendFragment extends Fragment {
    private static final String TAG = "RecyclerViewFragment";


    protected RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter<User,PeopleViewHolder> adapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    public List<User> getSelectedusers() {
        return selectedusers;
    }

    public void setSelectedusers(List<User> selectedusers) {
        this.selectedusers = selectedusers;
    }

    List<User> selectedusers;

    public  GroupFriendFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedusers=new ArrayList<>();
        //HomeActivity.toolbar.setTitle("Search people");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_all_people, container, false);
        rootView.setTag(TAG);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapter  = new FirebaseRecyclerAdapter<User,PeopleViewHolder>(User.class,
                R.layout.people_card_view,PeopleViewHolder.class, FirebaseDatabase.getInstance().getReference().child("userfriends").child(FirebaseAuth.getInstance().getCurrentUser().getUid())){

            @Override
            protected void populateViewHolder(PeopleViewHolder viewHolder, User model, int position) {
                viewHolder.messageUser.setText(model.getUsername());
                viewHolder.userPhoto.setImageURI(model.getPhotoUrl());
                viewHolder.button.setVisibility(View.GONE);
                viewHolder.checkBox.setTag(model);
                viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        User user=(User) v.getTag();

                        boolean checked = ((CheckBox) v).isChecked();
                        if(checked){
                            selectedusers.add(user);
                        }else {
                            for(int i=0;i<selectedusers.size();i++){
                                if(user.getId()==selectedusers.get(i).getId()){
                                    selectedusers.remove(i);
                                }
                            }
                        }


                    }
                });



            }
        };

        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(adapter);

        return rootView;
    }

}
