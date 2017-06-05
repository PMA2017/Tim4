package com.countmein.countmein.fragments.group;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.countmein.countmein.R;
import com.countmein.countmein.beans.ActivityBean;
import com.countmein.countmein.beans.GroupBean;
import com.countmein.countmein.holders.GroupViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 6/5/2017.
 */

public class GroupAddActivityFragment extends Fragment {
    private static final String TAG = "RecyclerViewFragment";


    public ActivityBean eActivity;
    public int isEdit;

    public View rootView;

    protected RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter<GroupBean,GroupViewHolder> adapter;
    protected RecyclerView.LayoutManager mLayoutManager;


    List<GroupBean> selectedgroups;

    public List<GroupBean> getSelectedgroups() {
        return selectedgroups;
    }

    public void setSelectedgroups(List<GroupBean> selectedgroups) {
        this.selectedgroups = selectedgroups;
    }

    public GroupAddActivityFragment() {

        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedgroups=new ArrayList<>();
        //HomeActivity.toolbar.setTitle("Search people");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recycle_view, container, false);
        rootView.setTag(TAG);
        Bundle bundle = this.getArguments();
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        isEdit=0;
        try {
            eActivity = (ActivityBean) bundle.getSerializable("data");
            isEdit = bundle.getInt("isEdit");

            /*if (isEdit == 1) {
                ((EditText) rootView.findViewById(R.id.activityName)).setText(eActivity.getName());
                ((EditText) rootView.findViewById(R.id.activityDesc)).setText(eActivity.getDescription());
            }*/

        }catch (Exception e){
            e.printStackTrace();
        }

        adapter  = new FirebaseRecyclerAdapter<GroupBean,GroupViewHolder>(GroupBean.class,
                R.layout.people_card_view,GroupViewHolder.class, FirebaseDatabase.getInstance().getReference().child("usergroup").child(FirebaseAuth.getInstance().getCurrentUser().getUid())){

            @Override
            protected void populateViewHolder(GroupViewHolder viewHolder, GroupBean model, int position) {

                viewHolder.vName.setText(model.getName().toString());
                viewHolder.vDescription.setText(model.getDescription().toString());
                viewHolder.cv.findViewById(R.id.button_view_attending_activity).setVisibility(View.GONE);
               /* viewHolder.checkBox.setTag(model);
                viewHolder.button.setVisibility(View.GONE);
                viewHolder.checkBox.setTag(model);
                if (isEdit == 1) {
                for(int i=0;i<eGroup.getFriends().size();i++){
                    if(eGroup.getFriends().get(i).getId()==model.getId()){
                        viewHolder.checkBox.setChecked(true);
                    }

                 }
                    System.out.print("AAAAAAAAAA");
                }
                viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        User user=(User) v.getTag();

                        boolean checked = ((CheckBox) v).isChecked();
                        if(checked){
                            selectedusers.add(user);
                        }else {
                            for(int i=0;i<selectedusers.size();i++){
                                if(user.getId()==selectedusers.get(i).getId()){
                                    selectedusers.remove(i);
                                }
                            }
                        }


                    }
                });


*/
            }
        };

        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(adapter);

        return rootView;
    }

}
