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

    public NewActivityDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_new_activity_details, container, false);
        Bundle bundle = this.getArguments();

        try {
            eActivity = (ActivityBean) bundle.getSerializable("data");
            isEdit = bundle.getInt("isEdit");


            if (isEdit == 1) {
                String[] eDate = eActivity.getDate().split("-");
                ((EditText) rootView.findViewById(R.id.activityName)).setText(eActivity.getName());
                ((EditText) rootView.findViewById(R.id.activityDesc)).setText(eActivity.getDescription());
                ((DatePicker) rootView.findViewById(R.id.new_activity_date))
                        .init(Integer.valueOf(eDate[2]), Integer.valueOf(eDate[1]) - 1, Integer.valueOf(eDate[0]), null);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return rootView;
    }

}
