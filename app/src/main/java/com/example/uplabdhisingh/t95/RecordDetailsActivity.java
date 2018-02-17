package com.example.uplabdhisingh.t95;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.uplabdhisingh.t95.data.HealthCareContract;

import java.time.Clock;
import java.util.Calendar;

public class RecordDetailsActivity extends AppCompatActivity
{
    EditText edtDate;
    EditText edtTime;
    EditText edtPincode;
    EditText edtAadhar;
    RadioGroup rgCategories;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
   // CheckBox cbFood, cbClothes, cbVaccinations, cbOthers;
    EditText edtFood, edtClothes, edtVaccine, edtOthers;
    public String categoryRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_details);

        edtDate = (EditText) findViewById(R.id.edt_date);
        edtTime = (EditText) findViewById(R.id.edt_time);
        edtFood = (EditText) findViewById(R.id.edt_food);
        edtAadhar = (EditText) findViewById(R.id.edt_aadhar_number);
        edtClothes = (EditText) findViewById(R.id.edt_clothes);
        edtVaccine = (EditText) findViewById(R.id.edt_vaccine);
        edtOthers = (EditText) findViewById(R.id.edt_others);
        edtPincode = (EditText) findViewById(R.id.edt_pincode);
        rgCategories = (RadioGroup) findViewById(R.id.radio_categories);
        ((RadioButton) findViewById(R.id.rb_six_month_childs)).setChecked(true);
        categoryRadio="six months";

       /* cbFood = (CheckBox) findViewById(R.id.cb_food);
        cbFood.setOnClickListener(this);
        cbClothes = (CheckBox) findViewById(R.id.cb_clothes);
        cbClothes.setOnClickListener(this);
        cbVaccinations = (CheckBox) findViewById(R.id.cb_vaccine);
        cbVaccinations.setOnClickListener(this);
        cbOthers = (CheckBox) findViewById(R.id.cb_other);
        cbOthers.setOnClickListener(this); */

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(RecordDetailsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                edtDate.setText(dayOfMonth + "/"
                                        + month + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        edtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar time = Calendar.getInstance();
                int hour = time.get(Calendar.HOUR_OF_DAY);
                int minutes = time.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(RecordDetailsActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                edtTime.setText(hourOfDay + ":" + minute);
                            }
                        }, hour, minutes, true);
                timePickerDialog.show();
            }
        });
    }

    public void onRadioButtonSelected(View view)
    {
        if (((RadioButton) findViewById(R.id.rb_six_month_childs)).isChecked()) {
            categoryRadio="6 months - 3 years";
        } else if (((RadioButton) findViewById(R.id.rb_three_years_child)).isChecked()) {
           categoryRadio = "3 year - 6 year";
        } else if (((RadioButton) findViewById(R.id.rb_pregnant_women)).isChecked()) {
            categoryRadio = "Pregnant Women";
        } else if (((RadioButton) findViewById(R.id.rb_lactating_mothers)).isChecked()) {
            categoryRadio = "Lactating Mothers";
        } else if (((RadioButton) findViewById(R.id.rb_eleven_years_girls)).isChecked()) {
            categoryRadio = "11-14 year Girls";
        }
    }

    public void addDetailsToRecord(View view)
    {
        String date = edtDate.getText().toString();
        String time = edtTime.getText().toString();
        String pincode = edtPincode.getText().toString();
        String aadhar = edtAadhar.getText().toString();
        String food = edtFood.getText().toString();
        String clothes = edtClothes.getText().toString();
        String vaccine = edtVaccine.getText().toString();
        String other = edtVaccine.getText().toString();

        if (edtDate.getText().length() == 0 ||
                edtTime.getText().length() == 0 ||
                edtAadhar.getText().length() == 0 ||
                edtPincode.getText().length() == 0 ||
                edtFood.getText().length() == 0 || edtClothes.getText().length()==0 ||
                edtVaccine.getText().length() == 0 || edtOthers.getText().length() == 0||
                ((RadioButton) this.findViewById(rgCategories.getCheckedRadioButtonId())).getText().length() == 0)
        {
            return;
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(HealthCareContract.HealthCareEntry.COLUMN_DATE, date);
        contentValues.put(HealthCareContract.HealthCareEntry.COLUMN_TIME, time);
        contentValues.put(HealthCareContract.HealthCareEntry.COLUMN_PINCODE, pincode);
        contentValues.put(HealthCareContract.HealthCareEntry.COLUMN_AADHAR,aadhar);
        contentValues.put(HealthCareContract.HealthCareEntry.COLUMN_CATEGORIES, categoryRadio);
        contentValues.put(HealthCareContract.HealthCareEntry.COLUMN_FOOD, food);
        contentValues.put(HealthCareContract.HealthCareEntry.COLUMN_CLOTHES, clothes);
        contentValues.put(HealthCareContract.HealthCareEntry.COLUMN_VACCINE, vaccine);
        contentValues.put(HealthCareContract.HealthCareEntry.COLUMN_OTHER, other);

        Uri uri = getContentResolver().insert(HealthCareContract.HealthCareEntry.CONTENT_URI, contentValues);
        if (uri != null)
        {
            Toast.makeText(this, uri.toString(), Toast.LENGTH_LONG).show();
        }
        finish();
    }

    /* @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.cb_food:
                if (cbFood.isChecked())
                    Toast.makeText(getApplicationContext(), "Marked Food", Toast.LENGTH_LONG).show();
                break;
            case R.id.cb_clothes:
                if (cbClothes.isChecked())
                    Toast.makeText(getApplicationContext(), "Marked Clothes", Toast.LENGTH_LONG).show();
                break;
            case R.id.cb_vaccine:
                if (cbVaccinations.isChecked())
                    Toast.makeText(getApplicationContext(), "Marked Vaccinations", Toast.LENGTH_LONG).show();
                break;
            case R.id.cb_other:
                if (cbOthers.isChecked())
                    Toast.makeText(getApplicationContext(), "Edited Others", Toast.LENGTH_LONG).show();
                break;
        }
    } */
}
