package com.pathmapper.prototypemapapp;
import android.os.Vibrator;

import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.clustering.ClusterManager;

import org.json.JSONException;

import java.io.InputStream;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,  GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {
    private ClusterManager<MarkerPrototype> mClusterManager;
    private GoogleMap mMap;
    LocationManager locationManager;
    GoogleApiClient mGoogleApiClient;
    String locationProvider;
    LocationRequest mLocationRequest;
    List<MarkerPrototype> items = null;
    Marker marker;


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


            mMap.setMyLocationEnabled(true);




        PolylineOptions polylineOptions = new PolylineOptions()
                .add(new LatLng(-45.8717394, 170.5052885))
                .add(new LatLng(-45.87194246, 170.50623422))
                .add(new LatLng(-45.87218882, 170.5074134))
                .add(new LatLng(-45.87181774, 170.5081125))
                .add(new LatLng(-45.87153469, 170.50902052))
                .add(new LatLng(-45.8716645, 170.51009266))
                .add(new LatLng(-45.87121341, 170.51085667))
                .add(new LatLng(-45.87106118, 170.51205239))
                .add(new LatLng(-45.87004808, 170.51278776))
                .add(new LatLng(-45.86977145, 170.51349811))
                .add(new LatLng(-45.86964466, 170.51452877))
                .add(new LatLng(-45.8683551, 170.51512202))
                .add(new LatLng(-45.86753009, 170.5155919))
                .add(new LatLng(-45.86739763, 170.51673611))
                .add(new LatLng(-45.86715364, 170.51759911));

        Polyline polyline = mMap.addPolyline(polylineOptions);

        // Add a marker in Sydney and move the camera
        LatLng polytech = new LatLng(-45.8717394, 170.5052885);
        mMap.addMarker(new MarkerOptions().position(polytech).title("Marker Tech D-Block"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(polytech));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(16));

        mClusterManager = new ClusterManager<MarkerPrototype>(this, mMap);
        mMap.setOnCameraIdleListener(mClusterManager);
        this.initializeLocationManager();

        try {
            readItems();
        } catch (JSONException e) {
            Toast.makeText(this, "Problem reading list of markers.", Toast.LENGTH_LONG).show();
        }

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mGoogleApiClient.connect();
    }

    private void readItems() throws JSONException {
        InputStream inputStream = getResources().openRawResource(R.raw.poly_areas);
        List<MarkerPrototype> items = new MyItemReader().read(inputStream);
        mClusterManager.addItems(items);
    }

    private void initializeLocationManager() {

        //get the location manager
        this.locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);

        //define the location manager criteria
        Criteria criteria = new Criteria();

        this.locationProvider = locationManager.getBestProvider(criteria, false);


        Location location = locationManager.getLastKnownLocation(locationProvider);


        //initialize the location
        if(location != null) {

            onLocationChanged(location);
        }
    }


    @Override
    public void onLocationChanged(Location location) {

        if (marker != null)
        {
            marker.remove();
        }

        //Create latlng from location
        LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
        //Change camera position to location and zoom level

        //Set marker options
        MarkerOptions options = new MarkerOptions()
                .position(new LatLng(location.getLatitude(), location.getLongitude()))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        //Add marker
        marker = mMap.addMarker(options);

        checkNeaby(location);


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //Request user location
        mLocationRequest = LocationRequest.create();
        //Set location priority to high. Use all available assets to get the most accurate location. GPS, Mobile data (If available)
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        //Set location update interval
        mLocationRequest.setInterval(100);

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void checkNeaby(Location location)
    {
        InputStream inputStream = getResources().openRawResource(R.raw.poly_areas);
        List<MarkerPrototype> items2 = null;
        try {
            items2 = new MyItemReader().read(inputStream);
            Location target = new Location("target");
            for(LatLng point : new LatLng[]{items2.get(0).getPosition(), items2.get(1).getPosition(), items2.get(2).getPosition(), items2.get(3).getPosition()}) {
                target.setLatitude(point.latitude);
                target.setLongitude(point.longitude);
                if(location.distanceTo(target) < 20) {
                    Vibrator v = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    v.vibrate(new long[]{0, 500, 110, 500, 110, 450, 110, 200, 110, 170, 40, 450, 110, 200, 110, 170, 40, 500}, -1);
                    mMap.addMarker(new MarkerOptions().position(new LatLng(target.getLatitude(), target.getLongitude())).title("Near").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
