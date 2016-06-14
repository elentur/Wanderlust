package com.se2.wanderlust;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by Marcus Bätz on 05.06.2016.
 */
public class NavigationControl {
    private final MainActivity act;

    public NavigationControl(MainActivity mainActivity) {
        act = mainActivity;
        ImageView btnStart = (ImageView)act.findViewById(R.id.btnStart);
        ImageView btnInfo = (ImageView)act.findViewById(R.id.btnInfo);

        if(btnStart!=null)btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
        if(btnInfo !=null)btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info();
            }
        });

    }

    private void info() {
        Log.d(act.TAG, "info");
    }

    private void start() {
        Log.d(act.TAG, "start");
    }
}
