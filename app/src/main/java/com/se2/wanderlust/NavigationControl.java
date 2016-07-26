package com.se2.wanderlust;


import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.SupportMapFragment;
import com.se2.wanderlust.Listener.MapCallback;
import com.se2.wanderlust.Support.Picture;

import java.util.Date;

/**
 * This class represents the navigation controller.
 * Its starts the navigation and takes pictures which contains the gps coordinate.
 *
 * Created by
 * Team Wanderlust on 05.06.2016.
 */
public class NavigationControl {
    private final MainActivity act;
    private final MapCallback myCallBack;
    private final ImageView btnStart;
    private boolean isTracking = false;
    private long start;
    private Handler mHandler;

    /**
     * Creates a instance of the NavigationControl object
     * @param mainActivity from activity
     */
    public NavigationControl(MainActivity mainActivity) {
        act = mainActivity;
        btnStart = (ImageView) act.findViewById(R.id.btnStart);
        ImageView btnInfo = (ImageView) act.findViewById(R.id.btnInfo);

        if (btnStart != null) btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
        if (btnInfo != null) btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info();
            }
        });
        myCallBack = new MapCallback();
        SupportMapFragment mapFragment = (SupportMapFragment) act.getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(myCallBack);

    }

    /**
     * shows the route information
     */
    private void info() {
        Log.d(act.TAG, "info");
        Picture.takePicture(act);
    }

    /**
     * starts the tracking mode
     */
    private void start() {


        if (isTracking) {
            isTracking=false;
            new AlertDialog.Builder(act)
                    .setTitle("Wahrnhinweis")
                    .setMessage("Wollen sie das Tracking wirklich beenden?")
                    .setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(act,"Tracking beendet", Toast.LENGTH_SHORT).show();
                    act.closeLocationManager();

                    btnStart.setColorFilter(Color.BLACK);
                }

            }).setNegativeButton(android.R.string.no,null).show();

            Log.d(act.TAG, "stop");
        } else {
            isTracking = true;

            start = new Date().getTime();
            mHandler = new Handler();
           if(act.startLocationManager(myCallBack)){
               mHandler.postDelayed(
                    new Runnable() {
                        @Override
                        public void run() {
                            if(act.locationListener !=null && act.locationListener.location !=null) {
                                TextView trackingInfo = (TextView) act.findViewById(R.id.txtTrackingInfo);
                                long timeInSec = ((new Date().getTime() - start) / 1000);
                                int sec = (int) (timeInSec % 60);
                                int min = (int) ((timeInSec / 60) % 60);
                                int hour = (int) ((timeInSec / 3600) % 25);

                                if (trackingInfo != null) trackingInfo.setText(
                                        act.getString(R.string.walkedWay) + String.format("%.2f", act.locationListener.polyLength()) + "km\n" +
                                                act.getString(R.string.height) + act.locationListener.location.getAltitude() + "\n" +
                                                act.getString(R.string.Duration) +  hour + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec)
                                );
                            }
                                if (isTracking) mHandler.postDelayed(this, 1000);

                        }
                    },1000);
            btnStart.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
           }
            Log.d(act.TAG, "start");
        }
    }


}
