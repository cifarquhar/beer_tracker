package com.codeclan.beertracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity {

    public static final String BEERLIST = "beerList";

    ArrayList<Beer> beers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beer_list);

        SharedPreferences sharedPref = getSharedPreferences(BEERLIST, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        String importedBeerList = sharedPref.getString("beerList","");

        Gson gson = new Gson();

        if(importedBeerList.equals("")) {
            Log.d("OnCreate","If called");
            BeerList beerList = new BeerList();
            beers = beerList.getBeerList();
            editor.putString("beerList",gson.toJson(beers));
            editor.apply();
        }
        else{
            Log.d("OnCreate","Else called");
            TypeToken<ArrayList<Beer>> beerArrayList = new TypeToken<ArrayList<Beer>>(){};
            beers = gson.fromJson(importedBeerList,beerArrayList.getType());
        }



        BeerAdapter beerAdapter = new BeerAdapter(this,beers);

        ListView listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(beerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        getMenuInflater().inflate(R.menu.menu_filter, menu);
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
        else if (id == R.id.action_filter) {
            Intent intent = new Intent(this,FilterActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void itemClicked(View item) {
        TextView textView = (TextView) item;
        Beer selectedBeer = (Beer) textView.getTag();

        String selectedBeerDetails = selectedBeer.parseDetailsHash();
        String selectedBeerIngredients = selectedBeer.parseIngredientsHash();
        String selectedBeerSteps = selectedBeer.parseStepsHash();

        Intent intent = new Intent(this, BeerActivity.class);
        intent.putExtra("beerDetails",selectedBeerDetails);
        intent.putExtra("beerIngredients",selectedBeerIngredients);
        intent.putExtra("beerSteps",selectedBeerSteps);
        intent.putExtra("beerObject",selectedBeer);

        startActivity(intent);

    }


}
