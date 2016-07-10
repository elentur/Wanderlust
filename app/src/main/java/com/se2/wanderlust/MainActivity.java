package com.se2.wanderlust;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.SupportMapFragment;
import com.se2.wanderlust.Listener.MapCallback;
import com.se2.wanderlust.Listener.WanderLustLocationListener;
import com.se2.wanderlust.Support.GPX;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    protected View actualView;
    protected final static String TAG = MainActivity.class.getSimpleName();
    public WanderLustLocationListener locationListener;
    private LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        actualView = findViewById(R.id.navigation_layout);
        new NavigationControl(this);
        new OwnMaterialControl(this);
        new ProfileControl(this);
        new RouteControl(this);
        new RouteInfoControl(this);
        new SettingsControl(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Das ist ein Kommentar
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (actualView != null) actualView.setVisibility(View.INVISIBLE);
        if (id == R.id.nav_navigation) {
            View v = findViewById(R.id.navigation_layout);
            v.setVisibility(View.VISIBLE);
            actualView = v;
        } else if (id == R.id.nav_route) {
            View v = findViewById(R.id.route_layout);
            v.setVisibility(View.VISIBLE);
            actualView = v;
            //Test
        } else if (id == R.id.nav_route_info) {
            View v = findViewById(R.id.route_info_layout);
            v.setVisibility(View.VISIBLE);
            actualView = v;
        } else if (id == R.id.nav_own_material) {
            View v = findViewById(R.id.own_material_layout);
            v.setVisibility(View.VISIBLE);
            actualView = v;
        } else if (id == R.id.nav_settings) {
            View v = findViewById(R.id.settings_layout);
            v.setVisibility(View.VISIBLE);
            actualView = v;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onProfileClick(View view) {
        if (actualView != null) actualView.setVisibility(View.INVISIBLE);
        View v = findViewById(R.id.profile_layout);
        v.setVisibility(View.VISIBLE);
        actualView = v;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    public void startLocationManager(MapCallback myCallBack) {
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        this.locationListener = new WanderLustLocationListener(myCallBack);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, GPX.trackTime, 0, this.locationListener);
    }

    public void closeLocationManager() {
        if (locationManager != null)
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }locationManager.removeUpdates(locationListener);
    }
}
