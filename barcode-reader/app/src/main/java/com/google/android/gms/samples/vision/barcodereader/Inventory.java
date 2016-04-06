package com.google.android.gms.samples.vision.barcodereader;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Inventory extends Activity {

    ListView listView;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        listView = (ListView) findViewById(R.id.listView);



        List<String> allLivestockAnimalIds = getAnimalIds(); // get all data from db
        //List<String> liveStockBarcodes = new ArrayList<String>(); // storing all animal_ids

//        for (int i = 0; i < allLivestock.size(); i++){
//            liveStockBarcodes.add(allLivestock.get(i).animal_id);
//        }
//
//        liveStockBarcodes.add("lol");
//        liveStockBarcodes.add("lol");
//        liveStockBarcodes.add("lol");
//        liveStockBarcodes.add("lol");

        // populate barcodes into UI listView

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allLivestockAnimalIds);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(), item , Toast.LENGTH_SHORT).show();
                // get barcode, given animal_id
                Livestock livestock = getLivestock(item);

                //send this livestock to modifying activity, called Modify

                Intent goToNextActivity = new Intent(getApplicationContext(), Modify.class);
                goToNextActivity.putExtra("modify_this_livestock", livestock);
                startActivity(goToNextActivity);
                arrayAdapter.notifyDataSetChanged();



//                view.animate().setDuration(2000).alpha(0)
//                        .withEndAction(new Runnable() {
//                            @Override
//                            public void run() {
////                                list.remove(item);
////                                arrayAdapter.notifyDataSetChanged();
////                                view.setAlpha(1);
//
//                                Toast.makeText(getApplicationContext(), item , Toast.LENGTH_SHORT).show();
//
//                            }
//                        });
            }

        });

    }

    private void updateListView(){

        if (listView != null && arrayAdapter != null){

            listView = (ListView) findViewById(R.id.listView);



            List<String> allLivestockAnimalIds = getAnimalIds(); // get all data from db
            //List<String> liveStockBarcodes = new ArrayList<String>(); // storing all animal_ids

//            for (int i = 0; i < allLivestockAnimalIds.size(); i++){
//                liveStockBarcodes.add(allLivestock.get(i).animal_id);
//            }
//
//        liveStockBarcodes.add("lol");
//        liveStockBarcodes.add("lol");
//        liveStockBarcodes.add("lol");
//        liveStockBarcodes.add("lol");

            // populate barcodes into UI listView

            arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allLivestockAnimalIds);
            listView.setAdapter(arrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, final View view,
                                        int position, long id) {
                    final String item = (String) parent.getItemAtPosition(position);

                    Toast.makeText(getApplicationContext(), item , Toast.LENGTH_SHORT).show();
                    // get barcode, given animal_id
                    Livestock livestock = getLivestock(item);

                    //send this livestock to modifying activity, called Modify

                    Intent goToNextActivity = new Intent(getApplicationContext(), Modify.class);
                    goToNextActivity.putExtra("modify_this_livestock", livestock);
                    startActivity(goToNextActivity);
                    arrayAdapter.notifyDataSetChanged();



//                view.animate().setDuration(2000).alpha(0)
//                        .withEndAction(new Runnable() {
//                            @Override
//                            public void run() {
////                                list.remove(item);
////                                arrayAdapter.notifyDataSetChanged();
////                                view.setAlpha(1);
//
//                                Toast.makeText(getApplicationContext(), item , Toast.LENGTH_SHORT).show();
//
//                            }
//                        });
                }

            });

        }
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

    private Livestock getLivestock(final String animal_id){

        final Livestock livestock = new Livestock();

        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
                SQLiteDatabase db = mDbHelper.getReadableDatabase();



                String selectQuery = "SELECT  * FROM " + FeedReaderContract.FeedEntry.TABLE_NAME + " WHERE " + FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_ID + "=\"" + animal_id + "\";";
                Cursor cursor = db.rawQuery(selectQuery, null);



                if (cursor.moveToFirst()) {

                    livestock.db_id = cursor.getInt(0);
                        livestock.barcode = cursor.getString(1);
                        livestock.weight = cursor.getString(2);
                        livestock.animal_id = cursor.getString(3);
                        livestock.type = cursor.getString(4);
                        livestock.sex = cursor.getString(5);
                        livestock.status = cursor.getString(6);
                        livestock.location = cursor.getString(7);
                        livestock.sire = cursor.getString(8);
                        livestock.dam = cursor.getString(9);
                        livestock.birthDate = cursor.getString(10);




                }

                cursor.close();
                db.close();



            }
        });




        return livestock;

    }

    @Override
    protected void onResume() {

        super.onResume();

        if (arrayAdapter!=null){
            listView.setAdapter(arrayAdapter);
            arrayAdapter.notifyDataSetChanged();
            updateListView();
        }


    }
}
