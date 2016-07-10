package com.se2.wanderlust;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.location.LocationManager;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;
import com.se2.wanderlust.Listener.MapCallback;
import com.se2.wanderlust.Listener.WanderLustLocationListener;
import com.se2.wanderlust.Support.GPX;
import com.se2.wanderlust.Support.Picture;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Marcus Bätz on 05.06.2016.
 */
public class NavigationControl {
    private final MainActivity act;
    private final MapCallback myCallBack;
    private final ImageView btnStart;
    private boolean isTracking = false;


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

    private void info() {
        Log.d(act.TAG, "info");
        Picture.takePicture(act);
    }

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


            act.startLocationManager(myCallBack);

            btnStart.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            Log.d(act.TAG, "start");
        }
    }


}
