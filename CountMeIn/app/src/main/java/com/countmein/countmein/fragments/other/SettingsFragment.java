package com.countmein.countmein.fragments.other;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.countmein.countmein.R;
import com.countmein.countmein.activities.HomeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {


    public SettingsFragment() {
        // Required empty public constructor


        HomeActivity.toolbar.setTitle(R.string.action_settings);
        Menu menu = HomeActivity.toolbar.getMenu();

        MenuItem itemSearch = menu.findItem(R.id.action_search);
        itemSearch.setVisible(false);

       // itemSearch.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

}
