package com.google.android.gms.samples.vision.barcodereader;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Modify extends Activity {

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

    Button buttonUpdate;
    Button buttonDelete;

    Livestock currentLivestock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

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

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);



        // received item in Bundle

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentLivestock = (Livestock) extras.getSerializable("modify_this_livestock");

            editTextAnimalId.setText(currentLivestock.animal_id);
            textViewEartag.setText(currentLivestock.barcode);
            editTextAnimalType.setText(currentLivestock.type);
            editTextAnimalSex.setText(currentLivestock.sex);
            editTextAnimalStatus.setText(currentLivestock.status);
            editTextLocation.setText(currentLivestock.location);
            editTextWeight.setText(currentLivestock.weight);
            editTextSire.setText(currentLivestock.sire);
            editTextDam.setText(currentLivestock.dam);
            editTextBirthDate.setText(currentLivestock.birthDate);


        }

    }

    public void deleteData(View view){

        // we have .db_id. Open db, delete the row

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
                SQLiteDatabase db = mDbHelper.getWritableDatabase();

                db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, FeedReaderContract.FeedEntry._ID + "=" + currentLivestock.db_id, null);
                db.close();

                Toast.makeText(getApplicationContext(), "Deleted item from db.", Toast.LENGTH_SHORT).show();



            }
        });


        finish();


    }

    public void updateData(View view){
        // gather new data.

        final int animal_dbId = currentLivestock.db_id; // to be checked later
        final String animalId = editTextAnimalId.getText().toString();
        final String Eartag = textViewEartag.getText().toString(); // barcode
        final String animalType = editTextAnimalType.getText().toString();
        final String animalSex = editTextAnimalSex.getText().toString();
        final String animalStatus = editTextAnimalStatus.getText().toString();
        final String animalLocation = editTextLocation.getText().toString();
        final String animalWeight = editTextWeight.getText().toString();
        final String animalSire = editTextSire.getText().toString();
        final String animalDam = editTextDam.getText().toString();
        final String animalBirthDate = editTextBirthDate.getText().toString();

        final Livestock updatedLivestock = new Livestock();
        updatedLivestock.db_id = animal_dbId;
        updatedLivestock.animal_id = animalId;
        updatedLivestock.barcode = Eartag;
        updatedLivestock.type = animalType;
        updatedLivestock.sex = animalSex;
        updatedLivestock.status = animalStatus;
        updatedLivestock.location = animalLocation;
        updatedLivestock.weight = animalWeight;
        updatedLivestock.sire = animalSire;
        updatedLivestock.dam = animalDam;
        updatedLivestock.birthDate = animalBirthDate;

        // update sqlite

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                // make sure new animal_id isn't already present in db

                if (Livestock.checkLivestockFromAnimaId(getApplicationContext(),updatedLivestock.animal_id)){
                    Toast.makeText(getApplicationContext(), "ANIMAL_ID already exists. Please try another one.", Toast.LENGTH_SHORT).show();
                    return;
                }

                updatedLivestock.updateDb(getApplicationContext());
                Toast.makeText(getApplicationContext(), "Updated record.", Toast.LENGTH_SHORT).show();



//                ContentValues values = new ContentValues();
//                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_ID, animalId);
//                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_BARCODE, Eartag);
//                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_TYPE, animalType);
//                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_SEX, animalSex);
//                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_STATUS, animalStatus);
//                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_LOCATION, animalLocation);
//                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_WEIGHT, animalWeight);
//                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_SIRE, animalSire);
//                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_DAM, animalDam);
//                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_BIRTH_DATE, animalBirthDate);
//
//
//                SQLiteDatabase db = mDbHelper.getWritableDatabase();
//                db.update(FeedReaderContract.FeedEntry.TABLE_NAME, values, FeedReaderContract.FeedEntry._ID + "=" + animal_dbId, null);
//
//                Toast.makeText(getApplicationContext(), "Updated table.", Toast.LENGTH_SHORT).show();
//                db.close();

            }
        });

        finish();

    }
}
