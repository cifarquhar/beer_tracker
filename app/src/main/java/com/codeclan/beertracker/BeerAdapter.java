package com.codeclan.beertracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 16/03/2017.
 */

public class BeerAdapter extends ArrayAdapter<Beer> {

    public BeerAdapter(Context context, ArrayList<Beer> beers){
        super(context,0,beers);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent){

        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.beer_item,parent,false);
        }

        Beer currentBeer = getItem(position);

        TextView image = (TextView) listItemView.findViewById(R.id.image);
        image.setText(currentBeer.getType());

        TextView name = (TextView) listItemView.findViewById(R.id.name);
        name.setText(currentBeer.getName());

        TextView description = (TextView) listItemView.findViewById(R.id.description);
        description.setText(currentBeer.getDescription());

        return listItemView;
    }

}
