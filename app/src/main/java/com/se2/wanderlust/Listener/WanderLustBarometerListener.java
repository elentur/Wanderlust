package com.se2.wanderlust.Listener;

import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

import com.se2.wanderlust.MainActivity;
import com.se2.wanderlust.R;

/**
 * Created by Marcus Bätz on 10.07.2016.
 */
public class WanderLustBarometerListener implements SensorEventListener {
    private final MainActivity act;
    public double hPa;
    public double height;
    public WanderLustBarometerListener(MainActivity act){
        this.act =act;
        hPa = getHPa();

        if(hPa <= 0){
            hPa = 1015;
            saveHPa(hPa);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == act.mSensorPressure) {
            height = (288.15/0.0065 )*(1-Math.pow(event.values[0]/hPa,1/5.255));
            TextView hpaText = (TextView) act.findViewById(R.id.pressure);
            hpaText.setText(String.format("%.1f hPa",hPa));

            TextView meterText = (TextView) act.findViewById(R.id.meterValue);
            meterText.setText(String.format("%.0f m" , height));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /**
     * Gets the hPa from the config preference.
     * @return double hPa, retruns 0.0 if no entry found.
     */
    private double getHPa(){
        SharedPreferences config = act.getSharedPreferences("config", act.MODE_PRIVATE);
        String str = config.getString("hPa", "");
        if(str.isEmpty()){
            return 0.0;
        }

        return Double.valueOf(str);
    }

    /**
     * Saves the hPa in the config preference.
     * @param hPa double hPa
     */
    public void saveHPa(double hPa){

        if(hPa < 0) throw new IllegalArgumentException("hPa can't be lower than zero!");

        SharedPreferences.Editor config = act.getSharedPreferences("config", act.MODE_PRIVATE).edit();
        config.clear();
        config.putString( "hPa", String.valueOf(hPa));
        config.apply();
    }
}
