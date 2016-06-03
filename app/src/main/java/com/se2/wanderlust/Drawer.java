package com.se2.wanderlust;

import android.app.Activity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Marcus Bätz on 03.06.2016.
 */
public class Drawer{

    public static void setUpDrawer(final AppCompatActivity a){
        String[] mPlanetTitles = a.getResources().getStringArray(R.array.options);
        DrawerLayout mDrawerLayout = (DrawerLayout) a.findViewById(R.id.drawer_layout);
        ListView mDrawerList = (ListView) a.findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(a,
                R.layout.drawer_list_item, mPlanetTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(a, mDrawerLayout,
                R.drawable.ic_menu_camera, R.string.navigation, R.string.action_sign_in) { //Bilder ersetzen

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                a.getActionBar().setTitle("Closed test");

            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                a.getActionBar().setTitle("Opened test");
            }
        };

        // Set the drawer toggle as the DrawerListener
        System.out.println(a.getActionBar());
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        a.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      a.getSupportActionBar().setHomeButtonEnabled(true);

    }
}
