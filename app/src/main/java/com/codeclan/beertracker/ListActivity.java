package com.codeclan.beertracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

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

    public void itemClicked(View item){
        int selectedBeerId = item.getId();
        BeerList beerList = new BeerList();
        ArrayList<Beer> beers = beerList.getBeerList();
        Beer selectedBeer = beers.get(selectedBeerId);
        HashMap<String,String> selectedBeerDetails = selectedBeer.getDetails();
        HashMap<String,Integer> selectedBeerIngredients = selectedBeer.getIngredients();
        HashMap<String,Integer> selectedBeerSteps = selectedBeer.getSteps();
        
        Intent intent = new Intent(this,BeerActivity.class);
        intent.putExtra("selectedBeerDetails",selectedBeerDetails);
        intent.putExtra("selectedBeerIngredients",selectedBeerIngredients);
        intent.putExtra("selectedBeerSteps",selectedBeerSteps);
        startActivity(intent);
    }
}
