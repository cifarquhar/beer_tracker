package com.codeclan.beertracker;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by user on 17/03/2017.
 */

public class DetailsActivity extends Fragment{

    TextView beerDetails;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.details_tab, container, false);
        return rootView;
    }

}
