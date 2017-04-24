package com.countmein.countmein.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.countmein.countmein.R;
import com.countmein.countmein.SelectedActivity;
import com.countmein.countmein.adapters.RVAdapter;
import com.countmein.countmein.beans.ActivityBean;
import com.countmein.countmein.fragments.AttendingActivitiesFragment;
import com.countmein.countmein.fragments.FriendFragment;
import com.countmein.countmein.fragments.GroupFragment;
import com.countmein.countmein.fragments.MainFragment;
import com.countmein.countmein.listeners.RecyclerItemClickListener;
import com.facebook.CallbackManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView = null;
    Toolbar toolbar = null;

    CallbackManager callbackManager;
    private List<ActivityBean> activities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);

        //set the fragment initially
        MainFragment fragment = new MainFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();


        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle(R.string.home);
        setSupportActionBar(toolbar);



        callbackManager = CallbackManager.Factory.create();

   /*     ChatRoomAdapter customAdapter = new ChatRoomAdapter(this, R.layout.single_card_view, getData());
*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, NewActivityActivity.class);
                startActivity(i);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            MainFragment fragment = new MainFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();

            //set specific floating action
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.show();
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(HomeActivity.this, NewActivityActivity.class);
                    startActivity(i);
                }
            });



        } else if (id == R.id.nav_attending) {

            AttendingActivitiesFragment fragment = new AttendingActivitiesFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

            //set specific floating action
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.show();
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(HomeActivity.this, NewGroupActivity.class);
                    startActivity(i);
                }
            });

        }else if (id == R.id.nav_my_groups){
            GroupFragment fragment = new GroupFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

            //set specific floating action
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.show();
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(HomeActivity.this, NewGroupActivity.class);
                    startActivity(i);
                }
            });
        } else if (id == R.id.nav_my_friends) {
            FriendFragment fragment = new FriendFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

            //set specific floating action
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.hide();
        }
      /*  } else if (id == R.id.nav_my_activities) {

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_settings){

        } else if (id == R.id.logout) {
        */


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
