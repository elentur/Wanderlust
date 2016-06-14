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
 * Created by Marcus Bätz on 05.06.2016.
 */
public class OwnMaterialControl {
    private final MainActivity act;

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

    private void picPicture() {
        Log.d(act.TAG," pic picture");
    }

    private void loadPicture() {
        Log.d(act.TAG, "load Picture");
    }
}
