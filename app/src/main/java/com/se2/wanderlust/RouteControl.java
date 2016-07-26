package com.se2.wanderlust;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;;
import android.widget.ListView;
import android.widget.SearchView;

/**
 * This class represents the route controller.
 * It shows all available routes.
 * Created by
 * Team Wanderlust on 05.06.2016.
 */
public class RouteControl {
    /**
     * The main activity
     */
    private final MainActivity act;

    /**
     * Its a instance of the RouteControl object
     * @param mainActivity android activity
     */
    public RouteControl(MainActivity mainActivity) {
        act = mainActivity;
        ListView lstRoutes = (ListView) act.findViewById(R.id.lstRoutes);
        SearchView srcRoute = (SearchView) act.findViewById(R.id.srcRoute);
        ImageView btnFilter = (ImageView)act.findViewById(R.id.btnFilter);

        if(lstRoutes!=null)lstRoutes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                routeSelect( view, position,id);
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

    /**
     * hands over the selected rout item
     * @param view
     * @param position
     * @param id
     */
    private void routeSelect(View view,int position, long id) {
        Log.d(act.TAG , "selected " + id + "at " + position);
    }

    /**
     * looks for the searched string in the route list
     * @param query
     */
    private void search(String query) {
        Log.d(act.TAG, " suche " + query);
    }

    /**
     * filter the routes
     */
    private void filter() {
        Log.d(act.TAG, "öffne Filter");
    }
}
