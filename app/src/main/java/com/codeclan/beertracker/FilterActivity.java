package com.codeclan.beertracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class FilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
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
