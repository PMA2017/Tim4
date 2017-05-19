package com.countmein.countmein.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.countmein.countmein.R;
import com.countmein.countmein.beans.ActivityBean;
import com.countmein.countmein.beans.ChatMessageBean;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

//@EActivity(R.layout.activity_selected)
public class SelectedActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FirebaseListAdapter<ChatMessageBean> adapter;
    private static final int MY_LOCATION_REQUEST_CODE = 1;
    private static final int SIGN_IN_REQUEST_CODE = 1;

    public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";
    public final static String AUTH_KEY_FCM = "Your api key";
    TextView mTextView1;
    TextView mTextView2;
    TextView mTextView3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Bundle bundle = getIntent().getExtras();
        ActivityBean activity=(ActivityBean) bundle.getSerializable("data");

        String naslov=activity.getName();
        String opis= activity.getDescription();
        String datum=activity.getDate();

        mTextView1 = (TextView) findViewById(R.id.naslov);
        mTextView2 = (TextView) findViewById(R.id.opis);
        mTextView3 = (TextView) findViewById(R.id.datum);
        mTextView1.setText(naslov);
        mTextView2.setText(opis);
        mTextView3.setText(datum);


        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            // Start sign in/sign up activity
            startActivityForResult(
                    AuthUI.getInstance().createSignInIntentBuilder().build(),
                    SIGN_IN_REQUEST_CODE
            );
        } else {
            // User is already signed in. Therefore, display
            // a welcome Toast


            displayChatMessages();
        }




        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText)findViewById(R.id.input);

                // Read the input field and push a new instance
                // of ChatMessageBean to the Firebase database
                FirebaseDatabase.getInstance()
                        .getReference().child("chatrooms").child(mTextView1.getText().toString())
                        .push()
                        .setValue(new ChatMessageBean(input.getText().toString(),
                                FirebaseAuth.getInstance()
                                        .getCurrentUser()
                                        .getDisplayName())
                        );

               // searchAsync();
                // Clear the input

                input.setText("");
            }
        });



    }



    private void displayChatMessages() {

        ListView listOfMessages = (ListView)findViewById(R.id.list_of_messages);
        listOfMessages.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listOfMessages.setStackFromBottom(true);

        adapter = new FirebaseListAdapter<ChatMessageBean>(this, ChatMessageBean.class,
                R.layout.message, FirebaseDatabase.getInstance().getReference().child("chatrooms").child(mTextView1.getText().toString())) {
            @Override
            protected void populateView(View v, ChatMessageBean model, int position) {
                // Get references to the views of message.xml
                TextView messageText = (TextView)v.findViewById(R.id.message_text);
                TextView messageUser = (TextView)v.findViewById(R.id.message_user);
                TextView messageTime = (TextView)v.findViewById(R.id.message_time);

                // Set their text
                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
            }
        };

        listOfMessages.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if (permissions.length == 1 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            // Show rationale and request permission.
        }
        // Add a marker in Sydney, Australia, and move the camera.
        // LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SIGN_IN_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(this,
                        "Successfully signed in. Welcome!",
                        Toast.LENGTH_LONG)
                        .show();

                displayChatMessages();
            } else {
                Toast.makeText(this,
                        "We couldn't sign you in. Please try again later.",
                        Toast.LENGTH_LONG)
                        .show();
                finish();
            }
        }
    }
}