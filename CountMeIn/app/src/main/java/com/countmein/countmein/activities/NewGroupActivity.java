package com.countmein.countmein.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.countmein.countmein.R;
import com.countmein.countmein.beans.ActivityBean;
import com.countmein.countmein.beans.GroupInfoBean;
import com.countmein.countmein.fragments.DatePickerFragment;
import com.countmein.countmein.fragments.MapFragment;
import com.countmein.countmein.fragments.NewActivityDetailsFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class NewGroupActivity extends AppCompatActivity {

  //  Bundle bundle = getIntent().getExtras();
    private DatabaseReference mDatabase;
    private FirebaseUser cUser;
    public GroupInfoBean eGroup;
    public int isEdit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);

        cUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("usergroups").child(cUser.getUid());

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_new_group);
        toolbar.setTitle(R.string.new_group);
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


                GroupInfoBean newGroup;
                String aName = null;
                String aDesc = null;
                switch (item.getItemId()) {
                    case R.id.miSave:
                        try {
                            NewActivityDetailsFragment.fetchData();
                            aName = ((EditText) findViewById(R.id.groupName)).getText().toString();
                            aDesc = ((EditText) findViewById(R.id.groupDesc)).getText().toString();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        newGroup = new GroupInfoBean(aName, aDesc, null);
                        addNewGroupAsChild(newGroup);

                        Toast.makeText(getApplicationContext(), "Group was made successfully", Toast.LENGTH_SHORT);
                        Intent i = new Intent(NewGroupActivity.this, HomeActivity_.class);
                  //      i.putExtra("key", "my_groups");
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

    public void addNewGroupAsChild(GroupInfoBean newGroup){
        String key;
        Map<String,Object> postValues;
        Map<String,Object> childUpdates;

        key = mDatabase.push().getKey();
        newGroup.setId(key);
        postValues = newGroup.toMap();
        childUpdates = new HashMap<>();

        childUpdates.put("/"+key, postValues);
        mDatabase.updateChildren(childUpdates);
    }
}
