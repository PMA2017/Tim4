package com.countmein.countmein.activities;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class NewActivityActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseUser ccUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_activity);

        ccUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("useractivities").child(ccUser.getUid());

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle(R.string.new_activity);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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
                String aName;
                String aDesc;
                DatePicker aDate;
                ActivityBean newAct;

                switch (item.getItemId()){
                    case  R.id.miSave:

                        aName = ((EditText) findViewById(R.id.activityName)).getText().toString();
                        aDesc = ((EditText) findViewById(R.id.activityDesc)).getText().toString();
                        aDate = ((DatePicker) findViewById(R.id.new_activity_date));

                        newAct = new ActivityBean(aName, aDesc, convertData(aDate));
                        addNewActivityAsaChild(newAct);

                        Toast.makeText(getApplicationContext(),"Activiti was made successfully", Toast.LENGTH_SHORT);
                        Intent i = new Intent(NewActivityActivity.this, HomeActivity.class);
                        startActivity(i);
                        break;
                }
                return false;
            }
        });
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
        postValues = newAct.toMap();
        childUpdates = new HashMap<>();

        childUpdates.put("/"+key, postValues);
        mDatabase.updateChildren(childUpdates);
    }
}
