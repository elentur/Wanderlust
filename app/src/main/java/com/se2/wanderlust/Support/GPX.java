package com.se2.wanderlust.Support;

import android.location.Location;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * This class represents the GPX object.
 * This class is overwritten to save pictures in the gpx format.
 * Created by
 * Team Wanderlust on 30.06.2016.
 */
public class GPX {
    private static final String TAG = GPX.class.getName();
    private static HashMap<Location,String> picture = new HashMap<>();
    public static int trackTime = 3000;

    /**
     * writes the data in the gpx file
     * @param file of gpx
     * @param n name of the route
     * @param points list of points
     */
    public static void writePath(File file, String n, List<Location> points) {

        String header = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?><gpx xmlns=\"http://www.topografix.com/GPX/1/1\" creator=\"MapSource 6.15.5\" version=\"1.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"  xsi:schemaLocation=\"http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd\"><trk>\n";
        String name = "<name>" + n + "</name><trkseg>\n";

        String segments = "";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        for (Location l : points) {
            segments += "<trkpt lat=\"" + l.getLatitude() + "\" lon=\"" + l.getLongitude() + "  alt=\"" + l.getAltitude() + "\"><time>" + df.format(new Date(l.getTime())) + "</time>";
            String pic = picture.get(l);
            if(pic != null){
                segments += "<picture>" + pic + "</picture>\n";
            }
            segments +=    "</trkpt>\n";
        }

        String footer = "</trkseg></trk></gpx>";

        try {
            FileWriter writer = new FileWriter(file, false);
            writer.append(header);
            writer.append(name);
            writer.append(segments);
            writer.append(footer);
            writer.flush();
            writer.close();
                Log.i(TAG, "Saved " + points.size() + " points in " + file.getPath());
        } catch (IOException e) {
            Log.e(TAG, "Error Writting Path",e);
        }
    }

    /**
     * sets the picture
     * @param loc location of the picture
     * @param path of the picture
     */
    public static void setPicture(Location loc, String path ) {
        picture.put(loc,path);
    }
}
