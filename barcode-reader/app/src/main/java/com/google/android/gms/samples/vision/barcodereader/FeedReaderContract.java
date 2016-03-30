package com.google.android.gms.samples.vision.barcodereader;

import android.provider.BaseColumns;

/**
 * Created by MMA on 3/28/2016.
 */
public class FeedReaderContract {

    public FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "livestock_table";
        public static final String COLUMN_NAME_BARCODE = "animal_barcode";
        public static final String COLUMN_NAME_WEIGHT = "animal_weight";

        /*
        *
        * EditText editTextAnimalId;
    TextView textViewEartag;
    EditText editTextAnimalType;
    EditText editTextAnimalSex;
    EditText editTextAnimalStatus;
    EditText editTextLocation;
    EditText editTextWeight;
    EditText editTextSire;
    EditText editTextDam;
    EditText editTextBirthDate;
        *
        * */

        public static final String COLUMN_NAME_ANIMAL_ID = "animal_id";
        public static final String COLUMN_NAME_ANIMAL_TYPE = "animal_type";
        public static final String COLUMN_NAME_ANIMAL_SEX = "animal_sex";
        public static final String COLUMN_NAME_ANIMAL_STATUS = "animal_status";
        public static final String COLUMN_NAME_ANIMAL_LOCATION = "animal_location";
        public static final String COLUMN_NAME_ANIMAL_SIRE = "animal_sire";
        public static final String COLUMN_NAME_ANIMAL_DAM = "animal_dam";
        public static final String COLUMN_NAME_BIRTH_DATE = "animal_birth_date";

    }

}
