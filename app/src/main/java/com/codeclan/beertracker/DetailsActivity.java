package com.codeclan.beertracker;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.codeclan.beertracker.BeerActivity.DETAILS_FRAGMENT_KEY;

/**
 * Created by user on 17/03/2017.
 */

public class DetailsActivity extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.details_tab, container, false);


        Bundle bundle = getArguments();

        String stringToPrint = bundle.getString(DETAILS_FRAGMENT_KEY);

        TextView textview = (TextView) rootView.findViewById(R.id.section_label);
        textview.setText(stringToPrint);

        return rootView;
    }


}
