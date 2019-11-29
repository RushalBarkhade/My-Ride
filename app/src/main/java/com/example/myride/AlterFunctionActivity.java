package com.example.myride;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public abstract class AlterFunctionActivity extends AbstractActivity {

    protected abstract Context getActivity();
    protected abstract void setData(int id,int year,int month,int datOfMonth);
    protected abstract void setTime(int id,int hour,int min);


    private void setData(final int id){
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH)+1;
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DatePickerDialog datePickerDialog =
                    new DatePickerDialog(getActivity(),
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    setData(id,year,month,dayOfMonth);
                                }
                            },mYear,mMonth,mDay);
            datePickerDialog.show();
        }
    }

    private void setTime(final int id){
        Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog =
                new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Log.v("TAGG",String.valueOf(id));
                                setTime(id,hourOfDay,minute);
                            }
                        },mHour,mMinute,false);
        timePickerDialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.source_time)
           setTime(v.getId());
        if (v.getId()==R.id.destination_time)
            setTime(v.getId());
        if (v.getId()==R.id.journey_date)
            setData(v.getId());
    }
}