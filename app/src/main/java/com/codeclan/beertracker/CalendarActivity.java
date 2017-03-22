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

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String buttonID = extras.getString("filterOn");
        Beer beerObject = (Beer) extras.getSerializable("beerObject");

        if (buttonID.equals("fermentation")){
            calendar.setDate(Long.valueOf(beerObject.details.get("Fermentation completed")));
        }
        else if (buttonID.equals("conditioning")){
            calendar.setDate(Long.valueOf(beerObject.details.get("Conditioning completed")));
        }

    }
}
