package com.example.uplabdhisingh.t95;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class ViewRecordsActivity extends AppCompatActivity
{

    private GestureDetectorCompat gestureDetectorCompat;
    ImageView viewRecordsImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);

        viewRecordsImageView = (ImageView) findViewById(R.id.iv_view_records);
        gestureDetectorCompat = new GestureDetectorCompat(this,new ViewRecordsGestureListener());


        viewRecordsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToViewRecordsDetail = new Intent(ViewRecordsActivity.this,ViewRecordDetailsActivity.class);
                startActivity(intentToViewRecordsDetail);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class ViewRecordsGestureListener extends GestureDetector.SimpleOnGestureListener
    {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
        {

            if(e2.getX()>e1.getX())
            {
                finish();
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
            return true;
        }
    }

}
