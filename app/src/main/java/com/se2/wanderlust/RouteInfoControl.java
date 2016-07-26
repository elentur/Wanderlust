package com.se2.wanderlust;


import android.widget.HorizontalScrollView;

/**
 * This class represents the route info controller.
 * It shows a route information.
 * Created by
 * Team Wanderlust on 05.06.2016.
 */
public class RouteInfoControl {
    private final MainActivity act;

    /**
     * Creates a instance of the RouteInfoControl object
     * @param mainActivity android activity
     */
    public RouteInfoControl(MainActivity mainActivity) {
        act = mainActivity;
        HorizontalScrollView imgSlider = (HorizontalScrollView)act.findViewById(R.id.imgSlider);
        if(imgSlider !=null){
        }
    }
}
