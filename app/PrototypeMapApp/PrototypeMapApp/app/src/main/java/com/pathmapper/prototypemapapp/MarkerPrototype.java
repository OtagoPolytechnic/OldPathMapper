package com.pathmapper.prototypemapapp;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by tsgar on 21/09/2016.
 */

public class MarkerPrototype implements ClusterItem {
    private LatLng position;

    public MarkerPrototype(double lat, double lng){
        position = new LatLng(lat, lng);
    }

    @Override
    public LatLng getPosition() {
        return position;
    }
}
