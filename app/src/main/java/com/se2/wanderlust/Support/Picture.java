package com.se2.wanderlust.Support;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.se2.wanderlust.MainActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represents the Picture object.
 * Starts the Camera and saves a picture with the gps coordinates.
 * Created by
 * Team Wanderlust on 02.07.2016.
 */
public class Picture {
    static final int REQUEST_TAKE_PHOTO = 1;
    private static String mCurrentPhotoPath;

    /**
     * starts the camera on the device.
     * @param act
     */
    public static void takePicture(MainActivity act ) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(act.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
                GPX.setPicture(act.locationListener.getLocation(),photoFile.getPath());
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.e("PictureException", "Fehler beim specichern der Bildes");
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                act.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }

        }
    }

    /**
     * Creates a photo file.
     * @return File
     * @throws IOException
     */
    private static File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName,".jpg", storageDir);
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }
}
