package com.se2.wanderlust;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

/**
 * Created by Marcus Bätz on 05.06.2016.
 */
public class RouteControl {
    private final MainActivity act;
    public RouteControl(MainActivity mainActivity) {
        act = mainActivity;
        ListView lstRoutes = (ListView) act.findViewById(R.id.lstRoutes);
        SearchView srcRoute = (SearchView) act.findViewById(R.id.srcRoute);
        ImageView btnFilter = (ImageView)act.findViewById(R.id.btnFilter);

        if(lstRoutes!=null)lstRoutes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {routeSelect( view, position,id);
            }
        });
        if(srcRoute !=null)srcRoute.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                search(newText);
                return false;
            }
        });
        if(btnFilter !=null)btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter();
            }
        });
    }

    private void routeSelect(View view,int position, long id) {
        Log.d(act.TAG , "selected " + id + "at " + position);
    }

    private void search(String query) {
        Log.d(act.TAG, " suche " + query);
    }

    private void filter() {
        Log.d(act.TAG, "öffne Filter");
    }
}
