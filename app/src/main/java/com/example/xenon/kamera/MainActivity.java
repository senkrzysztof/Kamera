package com.example.xenon.kamera;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
    private int fileUri;
    private int fileUri2;

    private Button b1;
    private Button b2;
    public File imagesFolder;
    public File moviesFolder;
    public Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);

        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imagesFolder = new File(Environment.getExternalStorageDirectory(), "MyImages");
        imagesFolder.mkdirs();

        moviesFolder = new File(Environment.getExternalStorageDirectory(), "MyMovies");
        moviesFolder.mkdirs();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                File image = new File(imagesFolder, "zdjecie_testowe_" + timeStamp + ".jpg");
                Uri uriSavedImage = Uri.fromFile(image);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                File movie = new File(moviesFolder, "film_testowy_" + timeStamp + ".avi");
                Uri uriSavedMovie = Uri.fromFile(movie);
                intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedMovie);
                startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
            }
        });

    }
}
