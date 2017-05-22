package com.countmein.countmein.fragments;



import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;


import com.countmein.countmein.R;
import com.countmein.countmein.activities.HomeActivity;
import com.countmein.countmein.activities.NewActivityActivity;
import com.countmein.countmein.beans.ChatMessageBean;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.messaging.FirebaseMessaging;

import org.androidannotations.annotations.EBean;

import static com.countmein.countmein.R.id.map;


/**
 * A simple {@link Fragment} subclass.
 */

@EBean
public class MapFragment extends Fragment implements OnMapReadyCallback {


    private GoogleMap mMap;

    private FirebaseListAdapter<ChatMessageBean> adapter;
    private static final int MY_LOCATION_REQUEST_CODE = 1;
    private MarkerOptions marker=null;
    public static Marker mMarker;
    public SupportMapFragment  mMapFragment;

    public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
    public final static String AUTH_KEY_FCM = "Your api key";

    public MapFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        mMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(map);
        mMapFragment.getMapAsync(this);

        ((WorkaroundMapFragment)mMapFragment).setListener(new WorkaroundMapFragment.OnTouchListener() {
            @Override
            public void onTouch() {
                NewActivityActivity.mScrollView.requestDisallowInterceptTouchEvent(true);
            }
        });

        return v;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if (permissions.length == 1 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);

                    return;
                }

            } else {
                // Permission was denied. Display an error message.
            }
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(HomeActivity.hLat,HomeActivity.hLog)));
        } else {
            // Show rationale and request permission.
        }


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                                       @Override
                                       public void onMapClick(LatLng latLng) {

                                           FirebaseMessaging.getInstance().subscribeToTopic("blaaaa");


                                           if(marker!=null){ //if marker exists (not null or whatever)
                                               mMarker.setPosition(latLng);
                                           }
                                           else{
                                               marker=new MarkerOptions();
                                               mMarker = mMap.addMarker(marker.position(latLng).draggable(true));
                                           }

                                       }
                                   }
        );
        // Add a marker in Sydney, Australia, and move the camera.
        // LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

    }
}
