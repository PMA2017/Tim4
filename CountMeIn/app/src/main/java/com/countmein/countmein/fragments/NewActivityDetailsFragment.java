package com.countmein.countmein.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.countmein.countmein.R;
import com.countmein.countmein.beans.ActivityBean;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewActivityDetailsFragment extends Fragment {

    public  ActivityBean eActivity;
    public int isEdit;
    public static String aName;
    public static String aDesc;
    public static View rootView;

    public NewActivityDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_new_activity_details, container, false);
        Bundle bundle = this.getArguments();

        try {
            eActivity = (ActivityBean) bundle.getSerializable("data");
            isEdit = bundle.getInt("isEdit");

            if (isEdit == 1) {
                ((EditText) rootView.findViewById(R.id.activityName)).setText(eActivity.getName());
                ((EditText) rootView.findViewById(R.id.activityDesc)).setText(eActivity.getDescription());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return rootView;
    }

    public static void fetchData(){
        aName = ((EditText) rootView.findViewById(R.id.activityName)).getText().toString();
        aDesc = ((EditText) rootView.findViewById(R.id.activityDesc)).getText().toString();
    }

}