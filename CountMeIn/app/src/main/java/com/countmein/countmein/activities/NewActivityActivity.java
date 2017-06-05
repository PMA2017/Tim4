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
import android.widget.EditText;
import android.widget.Toast;

import com.countmein.countmein.R;
import com.countmein.countmein.beans.ActivityBean;
import com.countmein.countmein.fragments.DatePickerFragment;
import com.countmein.countmein.fragments.LocationFragment;
import com.countmein.countmein.fragments.MapFragment;
import com.countmein.countmein.fragments.NewActivityDetailsFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NewActivityActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseUser ccUser;
    public  ActivityBean eActivity;
    public int isEdit;
    private NewActivityActivity.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private NewActivityDetailsFragment newActivityDetailsFragment;
    private LocationFragment mapFragment;
    private DatePickerFragment datePickerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_activity);
        Bundle bundle = getIntent().getExtras();
        newActivityDetailsFragment = new NewActivityDetailsFragment();
        mapFragment = new LocationFragment();
        datePickerFragment = new DatePickerFragment();
        ccUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("useractivities").child(ccUser.getUid());

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle(R.string.new_activity);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        try{
            eActivity = (ActivityBean) bundle.getSerializable("data");
            isEdit = bundle.getInt("isEdit");

            if(isEdit == 1){
                toolbar.setTitle(R.string.edit_activity);
                newActivityDetailsFragment.setArguments(bundle);
                mapFragment.setArguments(bundle);
                datePickerFragment.setArguments(bundle);
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
                String aName = null;
                String aDesc = null;
                DatePicker aDate = null;
                double lLat = 0;
                double lLng = 0;
                ActivityBean newAct;

                if(isEdit != 1) {
                    switch (item.getItemId()) {
                        case R.id.miSave:

                            try {
                                aName = NewActivityDetailsFragment.aName;
                                aDesc = NewActivityDetailsFragment.aDesc;
                                DatePickerFragment.fetchData();
                                aDate = DatePickerFragment.aDate;
                                lLng = MapFragment.mMarker.getPosition().longitude;
                                lLat = MapFragment.mMarker.getPosition().latitude;
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                            newAct = new ActivityBean(aName, aDesc, convertData(aDate), String.valueOf(lLat), String.valueOf(lLng));
                            addNewActivityAsaChild(newAct);

                            Toast.makeText(getApplicationContext(), "Activity was made successfully", Toast.LENGTH_SHORT);
                            Intent i = new Intent(NewActivityActivity.this, HomeActivity_.class);
                            startActivity(i);
                            break;
                    }
                } else
                {
                    switch (item.getItemId()) {
                        case R.id.miSave:

                            try {
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
                            break;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public String convertData(DatePicker aDate){
        int day = aDate.getDayOfMonth();
        int mth = aDate.getMonth() + 1;
        int year = aDate.getYear();

        return new String(day+"-"+mth+"-"+year);
    }

    public void addNewActivityAsaChild(ActivityBean newAct){
        String key;
        Map<String, Object> postValues;
        Map<String, Object> childUpdates;

        key = mDatabase.push().getKey();
        newAct.setId(key);
        postValues = newAct.toMap();
        childUpdates = new HashMap<>();

        childUpdates.put("/"+key, postValues);
        mDatabase.updateChildren(childUpdates);
    }

    public void updateActivity(ActivityBean activity){
        FirebaseDatabase.getInstance().getReference()
                .child("useractivities").child(ccUser.getUid()).child(activity.getId())
                .setValue(activity);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return newActivityDetailsFragment;
                case 1:
                    return datePickerFragment;
                case 2:
                    return mapFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "DETAILS";
                case 1:
                    return "DATE";
                case 2:
                    return "LOCATION";
            }
            return null;
        }
    }
}
