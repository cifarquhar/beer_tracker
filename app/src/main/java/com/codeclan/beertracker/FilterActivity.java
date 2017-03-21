package com.codeclan.beertracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class FilterActivity extends AppCompatActivity {

    String buttonID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
    }


    public void radioClick (View view){

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.filter_style:
                if (checked)
                    buttonID = "style";
                    break;
            case R.id.filter_ingredient:
                if (checked)
                    buttonID = "ingredient";
                    break;
            case R.id.filter_conditioning:
                if (checked)
                    buttonID = "conditioning";
                break;
        }
    }


    public void filterClick (View view){
        EditText input = (EditText) findViewById(R.id.filter_input);

        String inputString = input.getText().toString();

        Intent intent = new Intent(this,FilterListActivity.class);
        intent.putExtra("filterOn",buttonID);
        intent.putExtra("filterFor",inputString);
        Log.d("In Click","Starting activity");
        startActivity(intent);
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        getMenuInflater().inflate(R.menu.menu_beer, menu);
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_favourites) {
            Intent intent = new Intent(this,FavouritesActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_add) {
            Intent intent = new Intent(this,AddActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_home) {
            Intent intent = new Intent(this,ListActivity.class);
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
