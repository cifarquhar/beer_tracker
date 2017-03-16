package com.codeclan.beertracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

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
        Intent intent = new Intent(this,BeerActivity.class);

        startActivity(intent);
    }
}
