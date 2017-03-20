package com.codeclan.beertracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity extends AppCompatActivity {

    ArrayList<Beer> favouritesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beer_list);

        BeerList beerList = new BeerList();
        ArrayList<Beer> beers = beerList.getBeerList();

        BeerAdapter beerAdapter = new BeerAdapter(this,beers);

        ListView listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(beerAdapter);

//        Log.d("OnCreate",getIntent().getSerializableExtra("favouritesList").toString());
//
//           if  (getIntent().getSerializableExtra("favouritesList") != null) {
//               favouritesList = (ArrayList<Beer>) getIntent().getSerializableExtra("favouritesList");
//               Log.d("OnCreate","If called");
//           }
//        else{
//            favouritesList = new ArrayList<Beer>();
//    Log.d("OnCreate","Else called");
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_beer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_favourites) {
            Intent intent = new Intent(this,FavouritesActivity.class);
            intent.putExtra("favouritesList",favouritesList);
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
//        intent.putExtra("favouritesList",favouritesList);

//        Log.d("favouritesExtras",intent.getSerializableExtra("favouriteList").toString());

        startActivity(intent);

    }


}
