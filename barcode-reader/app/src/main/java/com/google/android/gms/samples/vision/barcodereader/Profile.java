package com.google.android.gms.samples.vision.barcodereader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

public class Profile extends Activity {

    TextView textViewTimestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textViewTimestamp = (TextView) findViewById(R.id.textViewTimestamp);

        Date date = new Date();
        textViewTimestamp.setText(date.toString());

    }

    public void buttonLogout(View view){
        Intent myIntent = new Intent(this, Login.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// clear back stack
        startActivity(myIntent);
        finish();
    }
}
