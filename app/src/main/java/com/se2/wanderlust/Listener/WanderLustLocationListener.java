package com.se2.wanderlust.Listener;

import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.se2.wanderlust.MainActivity;
import com.se2.wanderlust.R;
import com.se2.wanderlust.Support.GPX;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Marcus Bätz on 30.06.2016.
 */
public class WanderLustLocationListener implements LocationListener {
    private final MapCallback myCallBack;
    private final WanderLustBarometerListener baro;
    private static Marker marker = null;
    private static Polyline polyLine = null;
    private PolylineOptions polylineOptions = new PolylineOptions().width(5).color(Color.RED);
    private boolean markerAdded = false;
    private List<Location> locations = new ArrayList<>();

    public Location location;

    public WanderLustLocationListener(MapCallback myCallBack, WanderLustBarometerListener baro) {
        this.myCallBack = myCallBack;
        this.baro = baro;
        if(marker !=null)marker.remove();
        if(polyLine!=null)polyLine.remove();

    }

    @Override
    public void onLocationChanged(Location location) {
        GoogleMap map = myCallBack.getMap();

        if (baro != null)location.setAltitude(baro.height);

        this.location = location;

        if (map != null) {

            locations.add(location);

            if (locations.size() % 10 == 0)
                GPX.writePath(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "actualRoot.gpx"), "Route", locations);

            LatLng myPos = new LatLng(location.getLatitude(), location.getLongitude());

            Log.d("DEBUGGER", myPos + "");
            Log.d("DEBUGGER", location.toString());

            if (marker != null) marker.setPosition(myPos);

            if (!markerAdded) {

                marker = map.addMarker(new MarkerOptions().position(myPos).title("Meine Position"));
                markerAdded = true;
                map.moveCamera(CameraUpdateFactory.zoomTo(14.0f));
                polyLine = map.addPolyline(polylineOptions);
                Log.d("DEBUGGER", polyLine + "");

            }
            polylineOptions.add(myPos);
            if (polyLine != null) polyLine.setPoints(polylineOptions.getPoints());

            Log.d("DEBUGGER", polylineOptions.getPoints() + "");

            map.moveCamera(CameraUpdateFactory.newLatLng(myPos));


        }
    }

    public double polyLength() {
        double polylineLength = 0.0;
        for (int i = 1; i < locations.size(); i++) {
            polylineLength += locations.get(i-1).distanceTo(locations.get(i));
        }
        return polylineLength/1000.0;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
    }


    public Location getLocation() {
        return location;
    }

}
