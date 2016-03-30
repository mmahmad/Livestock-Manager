package com.google.android.gms.samples.vision.barcodereader;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddInformation extends Activity {

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

    Button buttonSave;
    String readBarcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_information);

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

        buttonSave = (Button) findViewById(R.id.buttonSave);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            readBarcode = extras.getString("readBarcode");
            textViewEartag.setText(extras.getString("readBarcode"));

        }



    }

    public void saveData(View view){

        // call addAnimalInDb to save all data

        addAnimalInDb();
        finish();

    }

    private void addAnimalInDb(){

        // Assumption when calling this function: barcode has been scanned, and is in the var String barcode

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

//                if (editTextWeight.getText().toString().equals("")) {
//
//                    textViewError.setVisibility(TextView.VISIBLE);
//                    textViewError.setTextColor(Color.RED);
//                    textViewError.setText("Enter weight!");
//                    return;
//
//                }


                String animalId = editTextAnimalId.getText().toString();
                String Eartag = readBarcode; // barcode
                String animalType = editTextAnimalType.getText().toString();
                String animalSex = editTextAnimalSex.getText().toString();
                String animalStatus = editTextAnimalStatus.getText().toString();
                String animalLocation = editTextLocation.getText().toString();
                String animalWeight = editTextWeight.getText().toString();
                String animalSire = editTextSire.getText().toString();
                String animalDam = editTextDam.getText().toString();
                String animalBirthDate = editTextBirthDate.getText().toString();

                FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
                // Gets the data repository in write mode
                SQLiteDatabase db = mDbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();





                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_ID, animalId);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_BARCODE, Eartag);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_TYPE, animalType);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_SEX, animalSex);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_STATUS, animalStatus);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_LOCATION, animalLocation);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_WEIGHT, animalWeight);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_SIRE, animalSire);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_DAM, animalDam);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_BIRTH_DATE, animalBirthDate);




                long newRowId;
                newRowId = db.insert(
                        FeedReaderContract.FeedEntry.TABLE_NAME,
                        null,
                        values);

                Toast.makeText(getApplicationContext(), "New data entered with row id: " + newRowId, Toast.LENGTH_SHORT).show();

                db.close();
            }
        });

    }
}
