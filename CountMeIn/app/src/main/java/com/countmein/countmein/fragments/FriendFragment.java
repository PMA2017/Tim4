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
import com.countmein.countmein.holders.FriendViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class FriendFragment extends Fragment {
    private static final String TAG = "RecyclerViewFragment";

    private  List<PersonInfoBean> details;
    protected RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter<User,FriendViewHolder> adapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    public FriendFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeActivity.toolbar.setTitle(R.string.my_friends);

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

        adapter  = new FirebaseRecyclerAdapter<User,FriendViewHolder>(User.class,
                R.layout.friend_card_view,FriendViewHolder.class, FirebaseDatabase.getInstance().getReference().child("userfriends").child(FirebaseAuth.getInstance().getCurrentUser().getUid())) {

            @Override
            protected void populateViewHolder(FriendViewHolder viewHolder, User model, int position) {



                viewHolder.messageUser.setText(model.getUsername());
                viewHolder.userPhoto.setImageURI(model.getPhotoUrl());

            }
        };

        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(adapter);

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
