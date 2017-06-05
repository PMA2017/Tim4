package com.countmein.countmein.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.countmein.countmein.R;
import com.countmein.countmein.beans.ActivityBean;
import com.countmein.countmein.beans.GroupBean;

/**
 * Created by Home on 6/5/2017.
 */

public class NewGroupDetailsFragment extends android.support.v4.app.Fragment {

    public GroupBean eGroup;
    public int isEdit;
    public static String gName;
    public static String gDesc;
    public static View rootView;

    public NewGroupDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_new_group_details, container, false);
        Bundle bundle = this.getArguments();

        try {
           /* eGroup = (GroupBean) bundle.getSerializable("data");
            isEdit = bundle.getInt("isEdit");

            if (isEdit == 1) {
                ((EditText) rootView.findViewById(R.id.activityName)).setText(eGroup.getName());
                ((EditText) rootView.findViewById(R.id.activityDesc)).setText(eGroup.getDescription());
            }*/

        }catch (Exception e){
            e.printStackTrace();
        }

        return rootView;
    }

    public static void fetchData(){
        gName = ((EditText) rootView.findViewById(R.id.groupName)).getText().toString();
        gDesc = ((EditText) rootView.findViewById(R.id.groupDesc)).getText().toString();
    }

}
