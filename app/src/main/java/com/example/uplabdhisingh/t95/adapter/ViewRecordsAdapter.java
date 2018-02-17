package com.example.uplabdhisingh.t95.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uplabdhisingh.t95.R;
import com.example.uplabdhisingh.t95.data.HealthCareContract;

/**
 * Created by uplabdhisingh on 17/02/18.
 */

public class ViewRecordsAdapter
        extends RecyclerView.Adapter<ViewRecordsAdapter.ViewRecordsAdapterViewHolder>
{
    private final Context mContext;
    private Cursor mCursor;

    public ViewRecordsAdapter(Context context)
    {
        mContext=context;
    }

    @Override
    public ViewRecordsAdapter.ViewRecordsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context=parent.getContext();
        int layoutID= R.layout.records;
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(layoutID,parent,false);
        return new ViewRecordsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewRecordsAdapter.ViewRecordsAdapterViewHolder holder, int position)
    {
        int idIndex = mCursor.getColumnIndex(HealthCareContract.HealthCareEntry._ID);
        int dateIndex = mCursor.getColumnIndex(HealthCareContract.HealthCareEntry.COLUMN_DATE);
        int timeIndex = mCursor.getColumnIndex(HealthCareContract.HealthCareEntry.COLUMN_TIME);
        int pincodeIndex = mCursor.getColumnIndex(HealthCareContract.HealthCareEntry.COLUMN_PINCODE);
        int aadharIndex = mCursor.getColumnIndex(HealthCareContract.HealthCareEntry.COLUMN_AADHAR);
        int categoryIndex = mCursor.getColumnIndex(HealthCareContract.HealthCareEntry.COLUMN_CATEGORIES);
        int foodIndex = mCursor.getColumnIndex(HealthCareContract.HealthCareEntry.COLUMN_FOOD);
        int clothesIndex = mCursor.getColumnIndex(HealthCareContract.HealthCareEntry.COLUMN_CLOTHES);
        int vaccineIndex = mCursor.getColumnIndex(HealthCareContract.HealthCareEntry.COLUMN_VACCINE);
        int otherIndex = mCursor.getColumnIndex(HealthCareContract.HealthCareEntry.COLUMN_OTHER);

        mCursor.moveToPosition(position);

        final int id = mCursor.getInt(idIndex);
        String date = mCursor.getString(dateIndex);
        String time = mCursor.getString(timeIndex);
        String pincode = mCursor.getString(pincodeIndex);
        String aadhar = mCursor.getString(aadharIndex);
        String category = mCursor.getString(categoryIndex);
        String food = mCursor.getString(foodIndex);
        String clothes = mCursor.getString(clothesIndex);
        String vaccine = mCursor.getString(vaccineIndex);
        String other = mCursor.getString(otherIndex);

        holder.itemView.setTag(id);
        holder.dateTV.setText(date);
        holder.timeTV.setText(time);
        holder.pincodeTV.setText(pincode);
        holder.aadharTV.setText(aadhar);
        holder.categoriesTV.setText(category);
        holder.itemsTV.setText(food+","+clothes+","+vaccine+","+other);

    }

    @Override
    public int getItemCount() {
        if(mCursor==null)
        {
            return 0;
        }
        return mCursor.getCount();
    }

    public Cursor swapCursor(Cursor newCursor)
    {
        if(mCursor==newCursor)
        {
            return null;
        }
        Cursor temp=mCursor;
        this.mCursor=newCursor;
        if(newCursor!=null)
        {
            this.notifyDataSetChanged();
        }
        return temp;
    }

    public class ViewRecordsAdapterViewHolder extends RecyclerView.ViewHolder
    {
        public TextView dateTV, timeTV, pincodeTV, aadharTV, categoriesTV, itemsTV;

        public ViewRecordsAdapterViewHolder(View itemView)
        {
            super(itemView);
            dateTV=(TextView) itemView.findViewById(R.id.tv_date);
            timeTV=(TextView) itemView.findViewById(R.id.tv_time);
            pincodeTV=(TextView) itemView.findViewById(R.id.tv_pincode);
            aadharTV = (TextView) itemView.findViewById(R.id.tv_aadhar);
            categoriesTV=(TextView) itemView.findViewById(R.id.tv_categories);
            itemsTV=(TextView) itemView.findViewById(R.id.tv_items);
        }
    }
}
