package com.se2.wanderlust;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.se2.wanderlust.Support.GPX;

/**
 * Created by Marcus Bätz on 05.06.2016.
 */
public class SettingsControl{
    private final MainActivity act;
    private SensorManager mSensorManager;
    private Sensor mSensorPressure;

    private Button addPressure;
    private Button subPressure;

    public SettingsControl(MainActivity mainActivity) {

        act = mainActivity;

        Switch chkPuplicPictures = (Switch) act.findViewById(R.id.chkPuplicPictures);
        assert chkPuplicPictures != null;
        chkPuplicPictures.setChecked(act.user.isPublicPhotos());

        // TODO speichern des value bei Änderung

        SeekBar sldTrackRate = (SeekBar) act.findViewById(R.id.sldTrackRate);
        assert sldTrackRate != null;
        sldTrackRate.setProgress(act.user.getTracking_rate());
        final TextView lblTrackrate = (TextView) act.findViewById(R.id.lblTrackRate);
        assert lblTrackrate != null;
        lblTrackrate.setText(act.user.getTracking_rate() + "");

        if(sldTrackRate!=null) sldTrackRate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
                act.user.setTracking_rate(seekBar.getProgress());
                act.userDao.updateUser(act.user);

                Log.d("DEBUGGER", GPX.trackTime +"");
            }
        });

        if(act.barometerListener !=null) {

            addPressure = (Button) act.findViewById(R.id.addButton);
            subPressure = (Button) act.findViewById(R.id.subButton);

            addPressure.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    onButtonClick(v);
                }
            });

            subPressure.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    onButtonClick(v);
                }
            });
        }else{
            LinearLayout heightLL = (LinearLayout) act.findViewById(R.id.heightArea);
            heightLL.setEnabled(false);
            LinearLayout pressureLL = (LinearLayout) act.findViewById(R.id.pressureArea);
            pressureLL.setEnabled(false);
        }

    }

    /**
     * This method hanldles the button events from the xml methid calls.
     * It increases or decreases the hPa value.
     * @param view
     */
    public void onButtonClick(View view) {
        if (act.barometerListener !=null) {
            if (view.getId() == R.id.addButton) {

                act.barometerListener.hPa = act.barometerListener.hPa + 0.1;
            } else if (view.getId() == R.id.subButton) {

                act.barometerListener.hPa = act.barometerListener.hPa - 0.1
                ;
            }

            act.barometerListener.saveHPa(act.barometerListener.hPa);
        }
    }
}
