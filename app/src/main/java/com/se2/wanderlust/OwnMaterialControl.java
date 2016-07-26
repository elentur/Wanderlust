package com.se2.wanderlust;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

/**
 * This class represents the own material controller.
 * It contains a photo which can map over the original map material.
 * Created by
 * Team Wanderlust on 05.06.2016.
 */
public class OwnMaterialControl {
    private final MainActivity act;

    /**
     * Creates a instance of the OwnMaterialControl object.
     * @param mainActivity
     */
    public OwnMaterialControl(MainActivity mainActivity) {
        act = mainActivity;
        ImageButton btnPicPicture = (ImageButton) act.findViewById(R.id.btnPicPicture);
        ImageButton btnLoadPicture = (ImageButton) act.findViewById(R.id.btnLoadPicture);

        if (btnLoadPicture != null)btnLoadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {loadPicture();
            }
        });
        if(btnPicPicture != null) btnPicPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {picPicture();
            }
        });
    }

    /**
     * takes a picture for the map
     */
    private void picPicture() {
        Log.d(act.TAG," pic picture");
    }

    /**
     * loads the picture
     */
    private void loadPicture() {
        Log.d(act.TAG, "load Picture");
    }
}
