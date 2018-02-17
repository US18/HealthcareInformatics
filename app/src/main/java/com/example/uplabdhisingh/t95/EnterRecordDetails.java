package com.example.uplabdhisingh.t95;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URL;

public class EnterRecordDetails extends AppCompatActivity {

    Button btnVerify,btnYes,btnNo;
    Uri uri = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_record_details);

        btnVerify = (Button) findViewById(R.id.btn_verify_aadhar);
        btnYes = (Button) findViewById(R.id.btn_yes);
        btnNo = (Button) findViewById(R.id.btn_no);


        btnVerify.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intentToRecordDetails = new Intent(Intent.ACTION_VIEW, Uri.parse("https://resident.uidai.gov.in/aadhaarverification"));
                startActivity(intentToRecordDetails);

                //Uri.parse("https://resident.uidai.gov.in/aadhaarverification?p_p_id=aadhaarverification_WAR_AadhaarVerificationportlet&p_p_lifecycle=1&p_p_state=normal&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=1&_aadhaarverification_WAR_AadhaarVerificationportlet_javax.portlet.action=aadhaarVerification")
            }
        });


        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToRecordDetails = new Intent(EnterRecordDetails.this,RecordDetailsActivity.class);
                startActivity(intentToRecordDetails);
            }
        });
    }
}
