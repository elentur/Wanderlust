package com.se2.wanderlust;

import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Marcus Bätz on 03.06.2016.
 */
public class Drawer{

    public static void setUpDrawer(Activity a){
        String[] mPlanetTitles = a.getResources().getStringArray(R.array.options);
        DrawerLayout mDrawerLayout = (DrawerLayout) a.findViewById(R.id.drawer_layout);
        ListView mDrawerList = (ListView) a.findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(a,
                R.layout.drawer_list_item, mPlanetTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
    }
}
