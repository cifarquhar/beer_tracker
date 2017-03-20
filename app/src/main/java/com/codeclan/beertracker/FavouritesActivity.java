package com.codeclan.beertracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import static com.codeclan.beertracker.BeerActivity.FAVOURITES;

public class FavouritesActivity extends AppCompatActivity {

    ArrayList<Beer> favouriteBeers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SharedPreferences sharedpref = getSharedPreferences(FAVOURITES, Context.MODE_PRIVATE);
        String favourites = sharedpref.getString("favourites","Nothing added to Favourites...yet.");

        Gson gson = new Gson();
        TypeToken<ArrayList<Beer>> beerArrayList = new TypeToken<ArrayList<Beer>>(){};
        favouriteBeers = gson.fromJson(favourites,beerArrayList.getType());

        Log.d("Item",favouriteBeers.get(0).getName().toString());


        BeerAdapter beerAdapter = new BeerAdapter(this,favouriteBeers);

        ListView listview = (ListView) findViewById(R.id.favourite_list);
        listview.setAdapter(beerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_beer, menu);
        return true;
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
        intent.putExtra("favouritesList",favouriteBeers);

        Log.d("Beer selected",selectedBeerDetails);

        startActivity(intent);

    }

}
