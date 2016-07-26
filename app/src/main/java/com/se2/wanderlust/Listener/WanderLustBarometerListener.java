package com.se2.wanderlust.Listener;

import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

import com.se2.wanderlust.MainActivity;
import com.se2.wanderlust.R;

/**
 * Created by Team Wanderlust on 10.07.2016.
 *
 * The Barometrical SensorListener that calculates the height above ms
 */
public class WanderLustBarometerListener implements SensorEventListener {
    private final MainActivity act;
    public double hPa;
    public double height;

    /**
     * sets hpa, if it is negativ it will get a default value
     * @param act The MainActivity
     */
    public WanderLustBarometerListener(MainActivity act){
        this.act =act;
        hPa = getHPa();
        if(hPa <= 0){
            hPa = 1015;
            saveHPa(hPa);
        }
    }

    /**
     * On sensor changed it calculates the actual hight
     * @param event SensorChanged Event
     */
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

    /**
     * Not used
     * @param sensor
     * @param accuracy
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /**
     * Gets the hPa from the config preference.
     * @return double hPa, retruns 0.0 if no entry found.
     */
    private double getHPa(){
        return act.user.getHpa();
    }

    /**
     * Saves the hPa in the config preference.
     * @param hPa double hPa
     */
    public void saveHPa(double hPa){

        if(hPa < 0) throw new IllegalArgumentException("hPa can't be lower than zero!");

        act.user.setHpa(hPa);

        updateDataBase();
    }

    /**
     * Updates the database
     */
    private void updateDataBase(){
        act.userDao.updateUser(act.user);
    }
}
