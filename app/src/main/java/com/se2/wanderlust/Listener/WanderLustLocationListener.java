package com.se2.wanderlust.Listener;

import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.se2.wanderlust.Support.GPX;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcus Bätz on 30.06.2016.
 */
public class WanderLustLocationListener implements LocationListener {
    private final MapCallback myCallBack;
    private final MarkerOptions marker = new MarkerOptions().position(new LatLng(0,0)).title("Meine Position");
    Polyline polyLine = null ;
    PolylineOptions polylineOptions =new PolylineOptions().width(5).color(Color.RED);
    private boolean markerAdded = false;
    private List<Location> locations = new ArrayList<>();

//test
    private int timer = 0;
    private LatLng[] lt = {new LatLng(52,13),new LatLng(52,15),new LatLng(55,15)};

    public WanderLustLocationListener(MapCallback myCallBack) {
        this.myCallBack=myCallBack;
    }

    @Override
    public void onLocationChanged(Location location) {
        GoogleMap map = myCallBack.getMap();
        if(map!=null){
            //LatLng myPos = new LatLng(0,0);
            //if(timer <lt.length)myPos=lt[timer];
            //timer++;
            locations.add(location);
            if(locations.size()%10==0) GPX.writePath(new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOCUMENTS),"test.gpx" ),"test1",locations);
            LatLng myPos = new LatLng(location.getLatitude(), location.getLongitude());
            Log.d("DEBUGGER",myPos+"");
            marker.position(myPos);
           if(!markerAdded){
               map.addMarker(marker);
               markerAdded=true;
               map.moveCamera(CameraUpdateFactory.zoomTo(14.0f));
               polyLine =  map.addPolyline(polylineOptions);
               Log.d("DEBUGGER",polyLine+"");

           }
            polylineOptions.add(myPos);
            if(polyLine != null)polyLine.setPoints(polylineOptions.getPoints());

            Log.d("DEBUGGER",polylineOptions.getPoints()+"");

            map.moveCamera(CameraUpdateFactory.newLatLng(myPos));

        }
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
}