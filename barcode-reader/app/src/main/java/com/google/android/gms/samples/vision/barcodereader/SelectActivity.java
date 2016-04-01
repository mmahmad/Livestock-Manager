package com.google.android.gms.samples.vision.barcodereader;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectActivity extends Activity {

    Button buttonProfile;
    Button buttonAddAnimal;
    Button buttonScanEartag;
    Button buttonLivestockInventory;
    Button buttonInjections;
    Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        buttonProfile = (Button) findViewById(R.id.buttonProfile);
        buttonAddAnimal = (Button) findViewById(R.id.buttonAddAnimal);
        buttonScanEartag = (Button) findViewById(R.id.buttonScanEarTag);
        buttonLivestockInventory = (Button) findViewById(R.id.buttonLivestockInventory);
        buttonInjections = (Button) findViewById(R.id.buttonInjections);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);


    }

    public void ScanEartag(View view){

        Intent goToNextActivity = new Intent(getApplicationContext(), MainActivity.class);
        String scan = "scan_tag";
        goToNextActivity.putExtra("scan_tag",scan);

        startActivity(goToNextActivity);

    }

    public void Logout(View view){

        Intent myIntent = new Intent(this, Login.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// clear back stack
        startActivity(myIntent);
        finish();

    }

    public void buttonProfile(View view){

        Intent goToNextActivity = new Intent(getApplicationContext(), Profile.class);
        startActivity(goToNextActivity);

    }

    public void buttonAddAnimal(View view){

        Intent goToNextActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(goToNextActivity);

    }

    public void buttonGetInventory(View view){

        Intent goToNextActivity = new Intent(getApplicationContext(), Inventory.class);
        startActivity(goToNextActivity);

    }

}
