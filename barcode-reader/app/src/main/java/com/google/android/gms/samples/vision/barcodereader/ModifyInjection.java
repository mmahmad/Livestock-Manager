package com.google.android.gms.samples.vision.barcodereader;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ModifyInjection extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    EditText editTextInjectionName;
    EditText editTextDateTime;
    Spinner spinnerAnimalId;
    Button buttonModifyInjection;
    Button buttonDeleteInjection;
    Injection currentInjection;
    ArrayAdapter arrayAdapter;

    String selected_animal_id = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_injection);

        editTextInjectionName = (EditText) findViewById(R.id.editTextModifyInjectionName);
        editTextDateTime = (EditText) findViewById(R.id.editTextModifyDateTime);
        spinnerAnimalId = (Spinner) findViewById(R.id.spinnerModifyAnimalPicker);
        buttonModifyInjection = (Button) findViewById(R.id.buttonModifyInjection);
        buttonDeleteInjection = (Button) findViewById(R.id.buttonDeleteInjection);

        spinnerAnimalId.setOnItemSelectedListener(this);

        List<String> allLivestockAnimalIds = getAnimalIds(); // get all data from db
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allLivestockAnimalIds);

        // Specify the layout to use when the list of choices appears
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerAnimalId.setAdapter(arrayAdapter);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            currentInjection = (Injection) extras.getSerializable("modify_this_injection");

//            editTextAnimalId.setText(currentLivestock.animal_id);
//            textViewEartag.setText(currentLivestock.barcode);
//            editTextAnimalType.setText(currentLivestock.type);
//            editTextAnimalSex.setText(currentLivestock.sex);
//            editTextAnimalStatus.setText(currentLivestock.status);
//            editTextLocation.setText(currentLivestock.location);
//            editTextWeight.setText(currentLivestock.weight);
//            editTextSire.setText(currentLivestock.sire);
//            editTextDam.setText(currentLivestock.dam);
//            editTextBirthDate.setText(currentLivestock.birthDate);

            editTextInjectionName.setText(currentInjection.injection_name);
            editTextDateTime.setText(currentInjection.date_time);
            spinnerAnimalId.setSelection(((ArrayAdapter) spinnerAnimalId.getAdapter()).getPosition(currentInjection.animal_id));




            //mySpinner.setSelection(((ArrayAdapter)mySpinner.getAdapter()).getPosition("Value‌​"));

        }

    }

    public void saveModifiedInjection(View view){

        int injection_db_id = currentInjection.db_id;
        String date_time = editTextDateTime.getText().toString();
        String injection_name = editTextInjectionName.getText().toString();

        // and we already have animal_id in selected_animal_id

        final Injection newInjection = new Injection();
        newInjection.animal_id = selected_animal_id;
        newInjection.injection_name = injection_name;
        newInjection.date_time = date_time;
        newInjection.db_id = injection_db_id;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                newInjection.updateInjection(getApplicationContext());
                Toast.makeText(getApplicationContext(), "Updated record.", Toast.LENGTH_SHORT).show();

            }
        });

        finish();


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        selected_animal_id = parent.getItemAtPosition(position).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        //Callback method to be invoked when the selection disappears from this view.
        // The selection can disappear for instance when touch is activated or when the adapter becomes empty.
//        selected_animal_id = "";


    }

    private List<String> getAnimalIds(){



        final List<Livestock> allLivestock = new ArrayList<Livestock>();
        final List<String> allLivestockAnimalIds = new ArrayList<>();

        runOnUiThread(new Runnable() {


            @Override
            public void run() {

                FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
                SQLiteDatabase db = mDbHelper.getReadableDatabase();


                //String selectQuery = "SELECT  * FROM " + FeedReaderContract.FeedEntry.TABLE_NAME;
                // TODO: 4/3/2016 Group by animal_type
//                String selectQuery = "SELECT " + FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_ID + ", " + FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_TYPE + " FROM " + FeedReaderContract.FeedEntry.TABLE_NAME + " GROUP BY " + FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_TYPE;
                String selectQuery = "SELECT " + FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_ID + " FROM " + FeedReaderContract.FeedEntry.TABLE_NAME;
                Cursor cursor = db.rawQuery(selectQuery, null);
                // SELECT animal_id, animal_type FROM livestocktable GROUP BY animal_type


                if (cursor.moveToFirst()) {
                    do {
//                        Livestock livestock = new Livestock();
//                        livestock.db_id = cursor.getInt(0);
//                        livestock.barcode = cursor.getString(1);
//                        livestock.weight = cursor.getString(2);
//                        livestock.animal_id = cursor.getString(3);
//                        livestock.type = cursor.getString(4);
//                        livestock.sex = cursor.getString(5);
//                        livestock.status = cursor.getString(6);
//                        livestock.location = cursor.getString(7);
//                        livestock.sire = cursor.getString(8);
//                        livestock.dam = cursor.getString(9);
//                        livestock.birthDate = cursor.getString(10);
//
//                        allLivestock.add(livestock);

                        allLivestockAnimalIds.add(cursor.getString(0));

                        //Toast.makeText(getApplicationContext(), "Data received from db: " + livestock.db_id + " barcode text: " + livestock.animal_id, Toast.LENGTH_SHORT).show();
                    } while (cursor.moveToNext());
                }

                cursor.close();
                db.close();


            }
        });




//        return allLivestock;
        return allLivestockAnimalIds;

    }

    public void deleteInjection(View view){

        // we have .db_id. Open db, and delete the row.

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                FeedReaderDbHelperInjections mDbHelper = new FeedReaderDbHelperInjections(getApplicationContext());
                SQLiteDatabase db = mDbHelper.getWritableDatabase();

                db.delete(FeedReaderContractInjections.FeedEntry.TABLE_NAME, FeedReaderContract.FeedEntry._ID + "=" + currentInjection.db_id, null);
                db.close();

                Toast.makeText(getApplicationContext(), "Deleted item from records.", Toast.LENGTH_SHORT).show();
            }
        });

        finish();


    }
}
