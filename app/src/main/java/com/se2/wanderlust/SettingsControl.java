package com.se2.wanderlust;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.se2.wanderlust.Support.GPX;

/**
 * Created by Marcus Bätz on 05.06.2016.
 */
public class SettingsControl {
    private final MainActivity act;
    public SettingsControl(MainActivity mainActivity) {
        act = mainActivity;
        SeekBar sldTrackRate = (SeekBar) act.findViewById(R.id.sldTrackRate);
        final TextView lblTrackrate = (TextView) act.findViewById(R.id.lblTrackRate);
        if(sldTrackRate!=null)sldTrackRate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if(lblTrackrate !=null )lblTrackrate.setText(progress +"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                GPX.trackTime = seekBar.getProgress();

                Log.d("DEBUGGER", GPX.trackTime +"");
            }
        });
    }
}
