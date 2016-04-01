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
 * Created by MMA on 3/28/2016.
 */
public class Livestock implements Serializable{

    public int db_id;
    public String animal_id;
    public String barcode;
    public String weight;
    public String type;
    public String sex;
    public String status;
    public String location;
    public String sire;
    public String dam;
    public String birthDate;

    public void updateDb(Context applicationContext){

        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(applicationContext);
        // Gets the data repository in write mode


        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_ID, this.animal_id);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_BARCODE, this.barcode);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_TYPE, this.type);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_SEX, this.sex);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_STATUS, this.status);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_LOCATION, this.location);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_WEIGHT, this.weight);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_SIRE, this.sire);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_DAM, this.dam);
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_BIRTH_DATE, this.birthDate);


        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.update(FeedReaderContract.FeedEntry.TABLE_NAME, values, FeedReaderContract.FeedEntry._ID + "=" + this.db_id, null);

        //Toast.makeText(applicationContext, "Updated table.", Toast.LENGTH_SHORT).show();
        db.close();


    }

    public Livestock getLivestockFromBarcode(final Context applicationContext, final String receivedBarcode){

        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(applicationContext);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();



        String selectQuery = "SELECT  * FROM " + FeedReaderContract.FeedEntry.TABLE_NAME + " WHERE " + FeedReaderContract.FeedEntry.COLUMN_NAME_BARCODE + "=\"" + receivedBarcode  + "\";";
        Cursor cursor = db.rawQuery(selectQuery, null);



        if (cursor.moveToFirst()) {

            this.db_id = cursor.getInt(0);
            this.barcode = cursor.getString(1);
            this.weight = cursor.getString(2);
            this.animal_id = cursor.getString(3);
            this.type = cursor.getString(4);
            this.sex = cursor.getString(5);
            this.status = cursor.getString(6);
            this.location = cursor.getString(7);
            this.sire = cursor.getString(8);
            this.dam = cursor.getString(9);
            this.birthDate = cursor.getString(10);




        }

        cursor.close();
        db.close();

        return this;

    }

    public Livestock getLivestockFromAnimalId(final Context applicationContext, final String receivedAnimalId){

        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(applicationContext);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();



        String selectQuery = "SELECT  * FROM " + FeedReaderContract.FeedEntry.TABLE_NAME + " WHERE " + FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_ID + "=\"" + receivedAnimalId  + "\";";
        Cursor cursor = db.rawQuery(selectQuery, null);



        if (cursor.moveToFirst()) {

            this.db_id = cursor.getInt(0);
            this.barcode = cursor.getString(1);
            this.weight = cursor.getString(2);
            this.animal_id = cursor.getString(3);
            this.type = cursor.getString(4);
            this.sex = cursor.getString(5);
            this.status = cursor.getString(6);
            this.location = cursor.getString(7);
            this.sire = cursor.getString(8);
            this.dam = cursor.getString(9);
            this.birthDate = cursor.getString(10);




        }

        cursor.close();
        db.close();

        return this;

    }

    public static boolean checkLivestockFromAnimaId(final Context applicationContext, final String receivedAnimalId){

        boolean exists = false;
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(applicationContext);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();



        String selectQuery = "SELECT  * FROM " + FeedReaderContract.FeedEntry.TABLE_NAME + " WHERE " + FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_ID + "=\"" + receivedAnimalId  + "\";";
        Cursor cursor = db.rawQuery(selectQuery, null);



        if (cursor.moveToFirst()) {

            // exists
            exists = true;


        }

        cursor.close();
        db.close();

        return exists;

    }

    public static List<Livestock> getAllLivestock (Context applicationContext){


        final List<Livestock> allLivestock = new ArrayList<Livestock>();

        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(applicationContext);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();



        String selectQuery = "SELECT  * FROM " + FeedReaderContract.FeedEntry.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);



        if (cursor.moveToFirst()) {
            do {
                Livestock livestock = new Livestock();
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

                allLivestock.add(livestock);

                //Toast.makeText(getApplicationContext(), "Data received from db: " + livestock.db_id + " barcode text: " + livestock.animal_id, Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return allLivestock;

    }




}
