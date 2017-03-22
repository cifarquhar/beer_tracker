package com.codeclan.beertracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class CalendarCheckActivity extends AppCompatActivity {

    String buttonID;
    Beer beerObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_check);

        Intent intent = getIntent();
        beerObject = (Beer) intent.getSerializableExtra("beerObject");
    }

    public void dateFilterClick (View view){

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.calendarCheckFermentation:
                if (checked)
                    buttonID = "fermentation";
                break;
            case R.id.calendarCheckConditioning:
                if (checked)
                    buttonID = "conditioning";
                break;
        }
    }

    public void confirmDateFilterClick (View view){
        Intent intent = new Intent(this,CalendarActivity.class);
        intent.putExtra("filterOn",buttonID);
        intent.putExtra("beerObject",beerObject);
        startActivity(intent);
    }

}
