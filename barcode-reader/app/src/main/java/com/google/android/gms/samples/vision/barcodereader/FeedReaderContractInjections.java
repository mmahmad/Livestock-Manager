package com.google.android.gms.samples.vision.barcodereader;

import android.provider.BaseColumns;

/**
 * Created by MMA on 4/3/2016.
 */
public class FeedReaderContractInjections {

    public FeedReaderContractInjections() {}

    /* Inner class that defines the table contents */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "injections_table";
        public static final String COLUMN_NAME_INJECTION_NAME = "injection_name";
        public static final String COLUMN_NAME_ANIMAL_ID = "animal_id";
        public static final String COLUMN_NAME_DATE = "animal_birth_date";

    }


}
