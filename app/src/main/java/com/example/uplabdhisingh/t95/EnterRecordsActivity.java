package com.example.uplabdhisingh.t95;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class EnterRecordsActivity extends AppCompatActivity
{
    ImageView enterRecordsImageView;
    Button btnNext;

    private GestureDetectorCompat gestureDetectorCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_records);

        enterRecordsImageView = (ImageView) findViewById(R.id.iv_enter_records);
        btnNext = (Button) findViewById(R.id.btn_next);

        gestureDetectorCompat = new GestureDetectorCompat(this,new MyGestureListener());
        enterRecordsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToRecordDetails = new Intent(EnterRecordsActivity.this,EnterRecordDetails.class);
                startActivity(intentToRecordDetails);
            }
        });

        
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            if(e2.getX() < e1.getX())
            {
                Intent intentToViewRecords = new Intent(EnterRecordsActivity.this,ViewRecordsActivity.class);
                startActivity(intentToViewRecords);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
            return true;
        }
    }
}
