package com.pathmapper.prototypemapapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        PolylineOptions polylineOptions = new PolylineOptions()
                .add(new LatLng(-45.865706, 170.519403)) // Point A.
                .add(new LatLng(-45.866434, 170.517721)); // Point B.

        Polyline polyline = mMap.addPolyline(polylineOptions);

        // Add a marker in Sydney and move the camera
        LatLng polytech = new LatLng(-45.865706, 170.519403);
        mMap.addMarker(new MarkerOptions().position(polytech).title("Marker Tech D-Block"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(polytech));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(16));
    }
}