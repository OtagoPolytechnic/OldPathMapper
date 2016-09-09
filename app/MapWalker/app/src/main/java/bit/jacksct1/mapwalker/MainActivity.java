package bit.jacksct1.mapwalker;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    GoogleMap mGoogleMap;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Check if google services is currently installed
        if(googleServicesAvailable())
        {
            Toast.makeText(this, "Google Service is Available", Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_main);
            initMap();
        }
        else
        {
            Toast.makeText(this, "Please install Google Play Services", Toast.LENGTH_LONG).show();
        }

    }

    //Initialise map fragment
    private void initMap()
    {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }

    //Check the availability of Google Play Services on phone
    public boolean googleServicesAvailable()
    {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS)
        {
            return true;
        }
        else if (api.isUserResolvableError(isAvailable))
        {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        }
        else
        {
            Toast.makeText(this, "Can't get Map", Toast.LENGTH_LONG).show();
        }
        return false;


    }

    //
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap = googleMap;
        //Set map type to hybrid
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //Set the bounds of where the overlay will be
        LatLngBounds polyBounds = new LatLngBounds(
                new LatLng(-45.8670197,170.5181700),       // South west corner
                new LatLng(-45.8655809,170.519785));      // North east corner

        //Create the ground the groundoverlay options
        GroundOverlayOptions newarkMap = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.poloyyyyy))
                .positionFromBounds(polyBounds);

        //Set the overly to the map
        mGoogleMap.addGroundOverlay(newarkMap);

        //build the map
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                                .addApi(LocationServices.API)
                                .addConnectionCallbacks(this)
                                .addOnConnectionFailedListener(this)
                                .build();
        mGoogleApiClient.connect();

    }


    @Override
    public void onConnected(Bundle bundle) {

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
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    //When location is changed
    @Override
    public void onLocationChanged(Location location) {
        //Check if location is available
        if(location == null)
        {
            Toast.makeText(this, "Can't find Location", Toast.LENGTH_LONG).show();
        }
        else
        {
            //Remove previous marker
            if (marker != null)
            {
                marker.remove();
            }

            //Create latlng from location
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            //Change camera position to location and zoom level
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, 18);
            //update camera
            mGoogleMap.animateCamera(update);

            //Set marker options
            MarkerOptions options = new MarkerOptions()
                                    .position(new LatLng(location.getLatitude(), location.getLongitude()));
            //Add marker
            marker = mGoogleMap.addMarker(options);

        }

    }



}
