package com.countmein.countmein.activities;

import com.bugsnag.android.Bugsnag;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.countmein.countmein.R;
import com.countmein.countmein.beans.ActivityBean;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private DatabaseReference mDatabase;
    private FirebaseUser currentUser;
    public static List<ActivityBean> activities = new ArrayList<>();
    final Handler mHandler = new Handler();

    private static final Class[] CLASSES = new Class[]{
            GoogleSignInActivity.class,
            //FacebookLoginActivity.class,
            //TwitterLoginActivity.class,
            EmailPasswordActivity.class,

    };

    private static final int[] DESCRIPTION_IDS = new int[] {
            R.string.desc_google_sign_in,
            //R.string.desc_facebook_login,
            // R.string.desc_twitter_login,
            R.string.desc_emailpassword,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);

        Bugsnag.init(this);

        // Set up ListView and Adapter
        ListView listView = (ListView) findViewById(R.id.list_view);

        MainActivity.MyArrayAdapter adapter = new MainActivity.MyArrayAdapter(this, android.R.layout.simple_list_item_2, CLASSES);
        adapter.setDescriptionIds(DESCRIPTION_IDS);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("useractivities").child(currentUser.getUid());
        // mDatabase.removeValue(); //Help while developing xD
        getData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Class clicked = CLASSES[position];
        startActivity(new Intent(this, clicked));
    }

    private static class MyArrayAdapter extends ArrayAdapter<Class> {

        private Context mContext;
        private Class[] mClasses;
        private int[] mDescriptionIds;

        private MyArrayAdapter(Context context, int resource, Class[] objects) {
            super(context, resource, objects);

            mContext = context;
            mClasses = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(android.R.layout.simple_list_item_2, null);
            }

            ((TextView) view.findViewById(android.R.id.text1)).setText(mClasses[position].getSimpleName());
            ((TextView) view.findViewById(android.R.id.text2)).setText(mDescriptionIds[position]);

            return view;
        }

        public void setDescriptionIds(int[] descriptionIds) {
            mDescriptionIds = descriptionIds;
        }

    }

    private void getData(){

        (new Thread(new Runnable() {

            @Override
            public void run() {
                final ValueEventListener valueEventListener = new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        activities = new ArrayList<>();
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            ActivityBean child = childSnapshot.getValue(ActivityBean.class);
                            activities.add(child);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("failRead", "Failed to read value.", error.toException());
                    }
                };

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mDatabase.addValueEventListener(valueEventListener);
                    }
                });
            }
        })).start();
    }
}
