package com.google.android.gms.samples.vision.barcodereader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class RetrieveInformation extends Activity {

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

    Livestock currentLivestock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_information);

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
            currentLivestock = (Livestock) extras.getSerializable("read_this_livestock");

            editTextAnimalId.setText("ID: " + currentLivestock.animal_id);
            textViewEartag.setText("Barcode: " + currentLivestock.barcode);
            editTextAnimalType.setText("Type: " + currentLivestock.type);
            editTextAnimalSex.setText("Sex: " + currentLivestock.sex);
            editTextAnimalStatus.setText("Status: " + currentLivestock.status);
            editTextLocation.setText("Location: " + currentLivestock.location);
            editTextWeight.setText("Weight: " + currentLivestock.weight);
            editTextSire.setText("Sire: " + currentLivestock.sire);
            editTextDam.setText("Dam: " + currentLivestock.dam);
            editTextBirthDate.setText("Birth Date: " + currentLivestock.birthDate);


        }


    }
}
