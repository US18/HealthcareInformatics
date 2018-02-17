package com.example.uplabdhisingh.t95;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.content.AsyncTaskLoader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.uplabdhisingh.t95.adapter.ViewRecordsAdapter;
import com.example.uplabdhisingh.t95.data.HealthCareContract;
import com.example.uplabdhisingh.t95.data.HealthCareDbHelper;

public class ViewRecordDetailsActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor>
{

    RecyclerView viewRecordsRecycler;
    private static final int MOVIES_LOADER_ID = 0;
    ViewRecordsAdapter viewRecordsAdapter;
    private static final String TAG = ViewRecordDetailsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record_details);

        viewRecordsRecycler = (RecyclerView) findViewById(R.id.rv_view_records);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        viewRecordsRecycler.setLayoutManager(layoutManager);
        viewRecordsRecycler.setHasFixedSize(true);

        viewRecordsAdapter = new ViewRecordsAdapter(this);
        viewRecordsRecycler.setAdapter(viewRecordsAdapter);
        getSupportLoaderManager().initLoader(MOVIES_LOADER_ID,null,this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, final Bundle args)
    {
        return new AsyncTaskLoader<Cursor>(this)
        {
            Cursor recordsData = null;

            @Override
            protected void onStartLoading()
            {
                if(recordsData!=null)
                {
                    deliverResult(recordsData);
                } else
                {
                    forceLoad();
                }
            }

            @Override
            public Cursor loadInBackground()
            {
                try{
                    return getContentResolver().query(HealthCareContract.HealthCareEntry.CONTENT_URI
                    ,null,null,null,null);
                } catch (Exception e)
                {
                    e.printStackTrace();
                    return null;
                }
            }

            public void deliverResult(Cursor data)
            {
                recordsData = data;
               // recordsData.moveToFirst();
               // Log.d(TAG,"ERROR IS : " +recordsData.getString(1));
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data)
    {
        viewRecordsAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader)
    {
        viewRecordsAdapter.swapCursor(null);
    }
}
