package com.se2.wanderlust;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Marcus Bätz on 05.06.2016.
 */
public class NavigationControl {
    private final MainActivity act;
    static final int REQUEST_TAKE_PHOTO = 1;
    private String mCurrentPhotoPath;

    public NavigationControl(MainActivity mainActivity) {
        act = mainActivity;
        ImageView btnStart = (ImageView)act.findViewById(R.id.btnStart);
        ImageView btnInfo = (ImageView)act.findViewById(R.id.btnInfo);

        if(btnStart!=null)btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
        if(btnInfo !=null)btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info();
            }
        });

    }

    private void info() {
        Log.d(act.TAG, "info");
    }

    private void start() {
        Log.d(act.TAG, "start");

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(act.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
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
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName,".jpg", storageDir);
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

}
