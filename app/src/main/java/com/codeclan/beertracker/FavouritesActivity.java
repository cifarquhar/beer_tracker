package com.codeclan.beertracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import static com.codeclan.beertracker.BeerActivity.FAVOURITES;

public class FavouritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        SharedPreferences sharedpref = getSharedPreferences(FAVOURITES, Context.MODE_PRIVATE);
        String favourites = sharedpref.getString("favourites","Nothing added to Favourites...yet.");

        Gson gson = new Gson();
        TypeToken<ArrayList<Beer>> beerArrayList = new TypeToken<ArrayList<Beer>>(){};
        ArrayList<Beer> favouriteBeers = gson.fromJson(favourites,beerArrayList.getType());

        Log.d("Item",favouriteBeers.get(0).getName().toString());

//        BeerList beerList = new BeerList(favouriteBeers);
//        ArrayList<Beer> beers = beerList.getBeerList();

        BeerAdapter beerAdapter = new BeerAdapter(this,favouriteBeers);

        ListView listview = (ListView) findViewById(R.id.favourite_list);
        listview.setAdapter(beerAdapter);

    }
}
