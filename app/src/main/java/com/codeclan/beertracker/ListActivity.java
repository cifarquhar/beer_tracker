package com.codeclan.beertracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beer_list);

        BeerList beerList = new BeerList();
        ArrayList<Beer> beers = beerList.getBeerList();

        BeerAdapter beerAdapter = new BeerAdapter(this,beers);

        ListView listview = (ListView) findViewById(R.id.list);
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

        Log.d("Beer selected",selectedBeerDetails);

        startActivity(intent);

    }


}
