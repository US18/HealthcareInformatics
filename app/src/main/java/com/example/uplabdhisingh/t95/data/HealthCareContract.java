package com.example.uplabdhisingh.t95.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by uplabdhisingh on 17/02/18.
 */

public class HealthCareContract
{
    public static final String CONTENT_AUTHORITY = "com.example.uplabdhisingh.t95";
    public static final String SCHEME = "content://";
    public static final Uri BASE_CONTENT_URI = Uri.parse(SCHEME + CONTENT_AUTHORITY);
    public static final String PATH = "healthcare";

    public static final class HealthCareEntry implements BaseColumns
    {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();

        public static final String TABLE_NAME="healthcare";

        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_AADHAR = "aadhar";
        public static final String COLUMN_PINCODE = "pincode";
        public static final String COLUMN_CATEGORIES = "category"; //radiogroups
        public static final String COLUMN_FOOD = "food";  //checkboxes
        public static final String COLUMN_CLOTHES = "clothes";
        public static final String COLUMN_VACCINE = "vaccinations";
        public static final String COLUMN_OTHER = "other";
    }
}
