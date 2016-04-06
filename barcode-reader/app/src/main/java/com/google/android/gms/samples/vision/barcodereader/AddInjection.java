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

public class AddInjection extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText editTextInjectionName;
    Spinner spinnerAnimalPicker;
    EditText editTextDateTime;
    Button buttonSaveInjection;

    ArrayAdapter arrayAdapter;

    String injection_name = "";
    String selected_animal_id = "";
    String date_time = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_injection);


        editTextInjectionName = (EditText) findViewById(R.id.editTextInjectionName);
        spinnerAnimalPicker = (Spinner) findViewById(R.id.spinnerAnimalPicker);
        editTextDateTime = (EditText) findViewById(R.id.editTextDateTime);
        buttonSaveInjection = (Button) findViewById(R.id.buttonSaveInjection);


        spinnerAnimalPicker.setOnItemSelectedListener(this);

        final List<String> allAnimalIds = new ArrayList<String>();


        List<String> allLivestockAnimalIds = getAnimalIds(); // get all data from db
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allLivestockAnimalIds);

        // Specify the layout to use when the list of choices appears
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerAnimalPicker.setAdapter(arrayAdapter);




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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        selected_animal_id = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        //Callback method to be invoked when the selection disappears from this view.
        // The selection can disappear for instance when touch is activated or when the adapter becomes empty.
        selected_animal_id = "";
        Toast.makeText(getApplicationContext(),"Please add an animal into the record first.", Toast.LENGTH_SHORT).show();


    }

    public void saveInjection(View view){


        injection_name = editTextInjectionName.getText().toString();
        // we already have selected_animal_id
        date_time = editTextDateTime.getText().toString();

//        Toast.makeText(getApplicationContext(), "Injection name: " + injection_name + " Animal id: " + selected_animal_id + " date/time: " + date_time, Toast.LENGTH_SHORT).show();

        if (!(injection_name.equals("")|| selected_animal_id == "" || date_time == "")) {

            // save to database
            if (saveInjectionToDb(injection_name, date_time, selected_animal_id)) {

                Toast.makeText(getApplicationContext(),"Date saved!", Toast.LENGTH_SHORT).show();
                finish();

            } else {

                Toast.makeText(getApplicationContext(),"Error saving data", Toast.LENGTH_SHORT).show();
                return;

            }

        } else {

            Toast.makeText(getApplicationContext(),"Please enter all data!", Toast.LENGTH_SHORT).show();

        }

    }

    private boolean saveInjectionToDb(final String injection_name, final String date_time, final String animal_id){

        final boolean [] booleanArray = new boolean[1];
        booleanArray[0] = false;
        //boolean saveSuccessful = false;

//        final Injection new_injection = new Injection();
//        new_injection.animal_id = animal_id;
//        new_injection.date_time = date_time;
//        new_injection.injection_name = injection_name;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                // TODO: Check if matching data already exists

                Injection.saveInjection(getApplicationContext(), injection_name, animal_id, date_time);
                Toast.makeText(getApplicationContext(), "New record has been saved!", Toast.LENGTH_SHORT).show();
                booleanArray[0] = true;

            }
        });

        return booleanArray[0];

    }
}
