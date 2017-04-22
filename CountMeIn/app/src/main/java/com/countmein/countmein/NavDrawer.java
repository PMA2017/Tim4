package com.countmein.countmein;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NavDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle(R.string.home);
        setSupportActionBar(toolbar);

        callbackManager = CallbackManager.Factory.create();

        List<Model> podaci=getData();

        final ListView lv = (ListView) findViewById(R.id.list);

// get data from the table by the ListAdapter
        ChatRoomAdapter customAdapter = new ChatRoomAdapter(this, R.layout.singlerow, podaci);

        lv .setAdapter(customAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // selected item
                String selected1 = ((TextView) view.findViewById(R.id.naslov)).getText().toString();
                String selected2 = ((TextView) view.findViewById(R.id.opis)).getText().toString();
                String selected3 = ((TextView) view.findViewById(R.id.datum)).getText().toString();


                Toast toast = Toast.makeText(getApplicationContext(), selected1, Toast.LENGTH_SHORT);
                toast.show();
                Intent i = new Intent(NavDrawer.this, SelectedActivity.class);
                i.putExtra("naslov", selected1);
                i.putExtra("opis", selected2);

                i.putExtra("datum", selected3);

                startActivity(i);


            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
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

   /*     if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public List<Model> getData(){

        Model c1=new Model("Rodjendan 1","Ovo je rodjendan", new Date());
        Model c2=new Model("Rodjendan 1","Ovo je rodjendan", new Date());
        Model c3=new Model("Rodjendan 1","Ovo je rodjendan", new Date());
        Model c4=new Model("Rodjendan 1","Ovo je rodjendan", new Date());
        Model c5=new Model("Rodjendan 1","Ovo je rodjendan", new Date());


        Model c11=new Model("Rodjendan 1","Ovo je rodjendan", new Date());

        Model c12=new Model("Rodjendan 1","Ovo je rodjendan", new Date());

        ArrayList<Model> lista= new ArrayList<Model>();
        lista.add(c1);
        lista.add(c2);
        lista.add(c3);
        lista.add(c4);
        lista.add(c5);

        lista.add(c11);
        return lista;

    }
}
