package com.se2.wanderlust.Listener;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Team Wanderlust on 30.06.2016.
 *
 * Every time when the map is changed it calls this mapp call back and
 * refreshes the mMap object
 */
public class MapCallback implements OnMapReadyCallback {
    /**
     * The Map-Object
     */
    private GoogleMap mMap;

    /**
     * refreshes the map
     * @param googleMap The Googlemap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera

    }

    /**
     * geter for the map Object
     * @return map Object
     */
    public GoogleMap getMap() {
        return mMap;
    }
}
