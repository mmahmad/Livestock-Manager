package com.google.android.gms.samples.vision.barcodereader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MMA on 4/3/2016.
 */
public class FeedReaderDbHelperInjections extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    // TODO: Save in same db as livestock

    public static final String DATABASE_NAME = "FeedReaderInjections.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContractInjections.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContractInjections.FeedEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    FeedReaderContractInjections.FeedEntry.COLUMN_NAME_INJECTION_NAME + TEXT_TYPE + COMMA_SEP +
                    FeedReaderContractInjections.FeedEntry.COLUMN_NAME_ANIMAL_ID + TEXT_TYPE + COMMA_SEP +
                    FeedReaderContractInjections.FeedEntry.COLUMN_NAME_DATE + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContractInjections.FeedEntry.TABLE_NAME;

    public FeedReaderDbHelperInjections(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
