package com.google.android.gms.samples.vision.barcodereader;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddAnimalInformation extends AppCompatActivity implements View.OnClickListener{


    EditText editTextAnimalId;
    TextView textViewEartag;
    EditText editTextAnimalType;
    EditText editTextAnimalSex;
    EditText editTextAnimalStatus;
    EditText editTextLocation;
    EditText editTextWeight;
    EditText editTextSire;
    EditText editTextDam;
    EditText editTextBirthDate;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    ImageView mImageView;
    String mCurrentPhotoPath;
    Uri currentImageURI;
    String readBarcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //// TODO: 4/7/2016 Play with toolbar to get a good color. Also, implment toolbar in rest of the activities.

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mImageView = (ImageView) findViewById(R.id.imageView);
        findViewById(R.id.buttonTakePhoto).setOnClickListener(this);

        editTextAnimalId = (EditText) findViewById(R.id.editTextAnimalId);
        textViewEartag = (TextView) findViewById(R.id.textViewEartag);
        editTextAnimalType = (EditText) findViewById(R.id.editTextAnimalType);
        editTextAnimalSex = (EditText) findViewById(R.id.editTextAnimalSex);
        editTextAnimalStatus = (EditText) findViewById(R.id.editTextAnimalStatus);
        editTextLocation = (EditText) findViewById(R.id.editTextLocation);
        editTextWeight = (EditText) findViewById(R.id.editTextWeight);
        editTextSire = (EditText) findViewById(R.id.editTextSire);
        editTextDam = (EditText) findViewById(R.id.editTextDam);
        editTextBirthDate = (EditText) findViewById(R.id.editTextBirthDate);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            readBarcode = extras.getString("readBarcode");
            textViewEartag.setText(extras.getString("readBarcode"));

        }

    }


    public void viewAnimalPhoto(View view) {

        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mCurrentPhotoPath))); /** replace with your own uri */
        //Intent intent = new Intent(this, BarcodeCaptureActivity.class);
        Intent displayImage = new Intent(Intent.ACTION_VIEW, currentImageURI);
        displayImage.setDataAndType(currentImageURI, "image/*");
        startActivity(displayImage);
//        displayImage.startActivity(new Intent(Intent.ACTION_VIEW, currentImageURI));


    }

    @Override
    public void onClick(View v) {

        // TODO: Get camera permission


        if (v.getId() == R.id.buttonTakePhoto) {
            // launch barcode activity.
//            Intent intent = new Intent(this, BarcodeCaptureActivity.class);
//            intent.putExtra(BarcodeCaptureActivity.AutoFocus, autoFocus.isChecked());
//            intent.putExtra(BarcodeCaptureActivity.UseFlash, useFlash.isChecked());
//
//            startActivityForResult(intent, RC_BARCODE_CAPTURE);

            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

                // Create the File where the photo should go
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File
                    //...

                    Log.e("Livestock Manager", "Failed to create image file on device.");
                }

                if (photoFile != null) {
                    Log.e("Before taking picture","Set image URI:" + Uri.fromFile(photoFile));
                    currentImageURI = Uri.fromFile(photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

                }


            }

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            Snackbar.make(findViewById(android.R.id.content).getRootView(), "Image saved", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

//            Bundle extras = data.getExtras();
//
//            if (extras.keySet().contains("data") ){
//                Bitmap thumbnail = (Bitmap) extras.get("data");
//                mImageView.setImageBitmap(thumbnail);
//
//            } else {
//                Uri imageURI = getIntent().getData();
//                //ImageView imageview = (ImageView)findViewById(R.id.imageView1);
//                mImageView.setImageURI(imageURI);
//
//            }




//            Uri imageURI = getIntent().getData();
//            Log.e("Saved image", "Image URI: " + imageURI);
//            mImageView.setImageURI(imageURI);
            //mImageView.setImageBitmap(Bitmap.createBitmap(mCurrentPhotoPath));

//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//
//            mImageView.setImageBitmap(imageBitmap);

            // Check if file has been written



            //writeImage();
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "LivestockManager_" + timeStamp + "_";
        //File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }


}
