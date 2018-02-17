package com.example.uplabdhisingh.t95.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.uplabdhisingh.t95.MainActivity;

/**
 * Created by uplabdhisingh on 17/02/18.
 */

public class HealthcareContentProvider extends ContentProvider
{
    public static final int HEALTHCARE = 100; //this is the whole table or directory
    public static final int HEALTHCARE_ID = 101; //this is the specific row of data.

    private static final String TAG = MainActivity.class.getSimpleName();

    private HealthCareDbHelper healthcareDbHelper;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    public static UriMatcher buildUriMatcher()
    {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        //firstly I'll be adding uri for my whole table with its id :
        uriMatcher.addURI(HealthCareContract.CONTENT_AUTHORITY,HealthCareContract.PATH,HEALTHCARE);
        //and now I'll be adding uri for my specific row data :
        uriMatcher.addURI(HealthCareContract.CONTENT_AUTHORITY, HealthCareContract.PATH + "/#", HEALTHCARE_ID );
        return  uriMatcher;
    }

    @Override
    public boolean onCreate()
    {
        Context context = getContext();
        healthcareDbHelper = new HealthCareDbHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder)
    {
        final SQLiteDatabase db = healthcareDbHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);
        Cursor retCursor;
        switch (match)
        {
            case HEALTHCARE:
                retCursor=db.query(HealthCareContract.HealthCareEntry.TABLE_NAME,
                        projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Uri Not Found : " +uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(),uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values)
    {
        final SQLiteDatabase db = healthcareDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);

        Uri returnUri;

        switch(match)
        {
            case HEALTHCARE:
                long id = db.insert(HealthCareContract.HealthCareEntry.TABLE_NAME,null,values);
                if(id > 0)
                {
                    returnUri= ContentUris.withAppendedId(HealthCareContract.HealthCareEntry.CONTENT_URI,id);
                }
                else
                {
                    throw new android.database.SQLException("Failed to add row into : " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("No uri found: " +uri);
        }

        getContext().getContentResolver().notifyChange(uri,null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
