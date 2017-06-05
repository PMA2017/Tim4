package com.countmein.countmein.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.countmein.countmein.R;
import com.countmein.countmein.beans.ActivityBean;
import com.countmein.countmein.beans.GroupBean;
import com.countmein.countmein.beans.User;
import com.countmein.countmein.fragments.AllPeopleFragment;
import com.countmein.countmein.fragments.DatePickerFragment;
import com.countmein.countmein.fragments.GroupFriendFragment;
import com.countmein.countmein.fragments.MapFragment;
import com.countmein.countmein.fragments.NewActivityDetailsFragment;
import com.countmein.countmein.fragments.NewGroupDetailsFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class NewGroupActivity extends AppCompatActivity {

    NewGroupDetailsFragment newGroupDetailsFragment;
    GroupFriendFragment peopleFragment;


    private FirebaseDatabase mDatabase;
    private FirebaseUser ccUser;
    public GroupBean eGroup;
    public int isEdit;
    private NewGroupActivity.SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);
        Bundle bundle = getIntent().getExtras();
        newGroupDetailsFragment=new NewGroupDetailsFragment();
        peopleFragment=new GroupFriendFragment();

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle(R.string.new_group);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mSectionsPagerAdapter = new NewGroupActivity.SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        try{
            eGroup = (GroupBean) bundle.getSerializable("data");
            isEdit = bundle.getInt("isEdit");

            if(isEdit == 1){
                toolbar.setTitle(R.string.edit_group);
                newGroupDetailsFragment.setArguments(bundle);
                peopleFragment.setArguments(bundle);

            }
        }catch(Exception e){
            e.printStackTrace();
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                setResult(RESULT_CANCELED, returnIntent);
                finish();
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                String gName = null;
                String gDesc = null;
                List<User> selectedusers=null;
                GroupBean group;

                GroupBean newGroup;

                if(isEdit != 1) {
                    switch (item.getItemId()) {
                        case R.id.miSave:

                            try {

                               selectedusers=new ArrayList<User>();
                                gDesc=newGroupDetailsFragment.gDesc.getText().toString();
                                gName=newGroupDetailsFragment.gName.getText().toString();
                                selectedusers=peopleFragment.getSelectedusers();
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                            group = new GroupBean(gName,gDesc,selectedusers);
                            addNewGroup(group);

                            Toast.makeText(getApplicationContext(), "Group was made successfully", Toast.LENGTH_SHORT);
                           // finish();
                            break;
                    }
                } else
                {
                    switch (item.getItemId()) {
                        case R.id.miSave:

                         /*   try {
                                NewActivityDetailsFragment.fetchData();
                                aName = NewActivityDetailsFragment.aName;
                                aDesc = NewActivityDetailsFragment.aDesc;
                                DatePickerFragment.fetchData();
                                aDate = DatePickerFragment.aDate;
                                lLng = MapFragment.mMarker.getPosition().longitude;
                                lLat = MapFragment.mMarker.getPosition().latitude;
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                            newAct = new ActivityBean(eActivity.getId(), aName, aDesc, convertData(aDate), String.valueOf(lLat), String.valueOf(lLng));
                            updateActivity(newAct);

                            Toast.makeText(getApplicationContext(), "Activiti was edited successfuly", Toast.LENGTH_SHORT);
                            Intent i = new Intent(NewActivityActivity.this, HomeActivity_.class);
                            startActivity(i);
                            break;*/
                            Toast.makeText(getApplicationContext(), "Group was made successfully", Toast.LENGTH_SHORT);

                    }
                }
                return false;
            }
        });


    }

    private void addNewGroup(GroupBean group) {

        String key=mDatabase.getInstance().getReference().child("usergroup").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push().getKey();
        group.setId(key);
        mDatabase.getInstance().getReference().child("usergroup").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(key).setValue(group);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onStart(){
        super.onStart();
    }



    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return newGroupDetailsFragment;
                case 1:
                    return peopleFragment;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {

            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "DETAILS";
                case 1:
                    return "FRIENDS";

            }
            return null;
        }
    }
}
