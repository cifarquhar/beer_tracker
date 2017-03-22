package com.codeclan.beertracker;

import android.content.Intent;
import android.icu.util.Calendar;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

       calendar = (CalendarView) findViewById(R.id.calendar);

//        Calendar calendar = Calendar.getInstance();

//        Intent calIntent = new Intent(Intent.ACTION_INSERT);
//        calIntent.setData(CalendarContract.Events.CONTENT_URI);
//        startActivity(calIntent);
    }
}
