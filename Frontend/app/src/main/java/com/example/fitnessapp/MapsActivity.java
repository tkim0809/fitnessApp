package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.fitnessapp.R;
import android.Manifest;
import androidx.core.content.ContextCompat;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Button;

import androidx.core.app.ActivityCompat;






public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 100;






    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Button backBtn = findViewById(R.id.backButton);

        backBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent i = new Intent(MapsActivity.this, NewUserMenu.class);
                startActivity(i);
            }
        });





        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Enable the My Location layer on the map
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }

        // Add markers for the gym locations
        LatLng gym1 = new LatLng(42.030781, -93.631913);
        mMap.addMarker(new MarkerOptions().position(gym1).title("Ames Fitness Center"));

        LatLng gym2 = new LatLng(42.030584, -93.642746);
        mMap.addMarker(new MarkerOptions().position(gym2).title("State Gym"));

        LatLng lied = new LatLng(42.023944, -93.648527);
        mMap.addMarker(new MarkerOptions().position(lied).title("Lied Recreation Center"));

        // Move the camera to the first gym location
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gym1, 13));
    }

}