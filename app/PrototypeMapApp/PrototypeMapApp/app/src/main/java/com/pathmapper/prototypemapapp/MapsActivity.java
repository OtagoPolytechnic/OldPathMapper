package com.pathmapper.prototypemapapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.clustering.ClusterManager;

import org.json.JSONException;

import java.io.InputStream;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
{
    private ClusterManager<MarkerPrototype> mClusterManager;
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
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

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
                .add(new LatLng(-45.8683551, 170.51512202 ))
                .add(new LatLng(-45.86753009, 170.5155919 ))
                .add(new LatLng(-45.86739763, 170.51673611 ))
                .add(new LatLng(-45.86715364, 170.51759911));

        Polyline polyline = mMap.addPolyline(polylineOptions);

        // Add a marker in Sydney and move the camera
        LatLng polytech = new LatLng(-45.8717394, 170.5052885);
        mMap.addMarker(new MarkerOptions().position(polytech).title("Marker Tech D-Block"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(polytech));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(16));

        mClusterManager = new ClusterManager<MarkerPrototype>(this, mMap);
        mMap.setOnCameraIdleListener(mClusterManager);

        try {
            readItems();
        } catch (JSONException e) {
            Toast.makeText(this, "Problem reading list of markers.", Toast.LENGTH_LONG).show();
        }
    }

    private void readItems() throws JSONException {
        InputStream inputStream = getResources().openRawResource(R.raw.poly_areas);
        List<MarkerPrototype> items = new MyItemReader().read(inputStream);
        mClusterManager.addItems(items);
    }
}
