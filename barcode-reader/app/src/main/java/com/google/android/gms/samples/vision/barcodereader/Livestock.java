package com.google.android.gms.samples.vision.barcodereader;

import java.io.Serializable;

/**
 * Created by MMA on 3/28/2016.
 */
public class Livestock implements Serializable{


    /*
    *
    * private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_BARCODE + TEXT_TYPE + COMMA_SEP +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_WEIGHT + TEXT_TYPE + COMMA_SEP +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_ID + TEXT_TYPE + COMMA_SEP +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_TYPE + TEXT_TYPE + COMMA_SEP +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_SEX + TEXT_TYPE + COMMA_SEP +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_STATUS + TEXT_TYPE + COMMA_SEP +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_LOCATION + TEXT_TYPE + COMMA_SEP +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_SIRE + TEXT_TYPE + COMMA_SEP +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_ANIMAL_DAM + TEXT_TYPE + COMMA_SEP +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_BIRTH_DATE + TEXT_TYPE +
                    " )";
    *
    *
    *
    * */
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


}
