package com.google.android.gms.samples.vision.barcodereader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MMA on 4/3/2016.
 */
public class Injection implements Serializable{

    public int db_id;
    public String injection_name;
    public String animal_id;
    public String date_time;


//    public static boolean checkInjectionExists(final Context applicationContext, final String receivedAnimalId, final String receivedDateTime, final String receivedInjectionName){
//
//        boolean exists = false;
//        FeedReaderDbHelperInjections mDbHelper = new FeedReaderDbHelperInjections(applicationContext);
//        SQLiteDatabase db = mDbHelper.getReadableDatabase();
//
//
//
//        //String selectQuery = "SELECT  * FROM " + FeedReaderContractInjections.FeedEntry.TABLE_NAME + " WHERE " + FeedReaderContractInjections.FeedEntry.COLUMN_NAME_ANIMAL_ID + "=\"" + receivedAnimalId  + "\";";
//        //Cursor cursor = db.rawQuery(selectQuery, null);
//
//
//
//        if (cursor.moveToFirst()) {
//
//            // exists
//            exists = true;
//
//
//        }
//
//        cursor.close();
//        db.close();
//
//        return exists;
//
//    }

    public static void saveInjection(Context applicationContext, String receivedInjectionName, String receivedAnimalId, String receivedDateTime){



        FeedReaderDbHelperInjections mDbHelper = new FeedReaderDbHelperInjections(applicationContext);
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(FeedReaderContractInjections.FeedEntry.COLUMN_NAME_ANIMAL_ID, receivedAnimalId);
        values.put(FeedReaderContractInjections.FeedEntry.COLUMN_NAME_DATE, receivedDateTime);
        values.put(FeedReaderContractInjections.FeedEntry.COLUMN_NAME_INJECTION_NAME, receivedInjectionName);

        long newRowId;
        newRowId = db.insert(
                FeedReaderContractInjections.FeedEntry.TABLE_NAME,
                null,
                values);



        db.close();

    }

    public void updateInjection(Context applicationContext){

        FeedReaderDbHelperInjections mDbHelper = new FeedReaderDbHelperInjections(applicationContext);
        // Gets the data repository in write mode


        ContentValues values = new ContentValues();
        values.put(FeedReaderContractInjections.FeedEntry.COLUMN_NAME_ANIMAL_ID, this.animal_id);
        values.put(FeedReaderContractInjections.FeedEntry.COLUMN_NAME_DATE, this.date_time);
        values.put(FeedReaderContractInjections.FeedEntry.COLUMN_NAME_INJECTION_NAME, this.injection_name);


        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.update(FeedReaderContractInjections.FeedEntry.TABLE_NAME, values, FeedReaderContractInjections.FeedEntry._ID + "=" + this.db_id, null);

        //Toast.makeText(applicationContext, "Updated table.", Toast.LENGTH_SHORT).show();
        db.close();

    }

    public static List<String> getAllInjections(Context applicationContext){

        List<String> allAnimalIdsWithInjections = new ArrayList<String>();

        FeedReaderDbHelperInjections mDbHelper = new FeedReaderDbHelperInjections(applicationContext);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String selectQuery = "SELECT " + FeedReaderContractInjections.FeedEntry.COLUMN_NAME_ANIMAL_ID + " FROM " + FeedReaderContractInjections.FeedEntry.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {


                allAnimalIdsWithInjections.add(cursor.getString(0));

                //Toast.makeText(getApplicationContext(), "Data received from db: " + livestock.db_id + " barcode text: " + livestock.animal_id, Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return allAnimalIdsWithInjections;

    }

    public static Injection getInjectionAtPositionInDb(Context applicationContext, int position){

        Injection getInjection = new Injection();

        FeedReaderDbHelperInjections mDbHelper = new FeedReaderDbHelperInjections(applicationContext);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + FeedReaderContractInjections.FeedEntry.TABLE_NAME + " WHERE _ID=" + (position+1) + ";";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                getInjection.db_id = cursor.getInt(0);
                getInjection.injection_name = cursor.getString(1);
                getInjection.animal_id = cursor.getString(2);
                getInjection.date_time = cursor.getString(3);

                //Toast.makeText(getApplicationContext(), "Data received from db: " + livestock.db_id + " barcode text: " + livestock.animal_id, Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();


        return getInjection;


    }

}
