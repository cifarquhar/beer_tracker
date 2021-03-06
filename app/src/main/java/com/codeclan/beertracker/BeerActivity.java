package com.codeclan.beertracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static com.codeclan.beertracker.ListActivity.BEERLIST;


public class BeerActivity extends AppCompatActivity {

    String detailsString;
    String ingredientsString;
    String stepsString;
    Beer beerObject;

    ArrayList<Beer> favouritesList;

    int indexToReplace;



    public static final String DETAILS_FRAGMENT_KEY = "Details Fragment";
    public static final String INGREDIENTS_FRAGMENT_KEY = "Ingredients Fragment";
    public static final String STEPS_FRAGMENT_KEY = "Steps Fragment";
    public static final String FAVOURITES = "FavouriteBeers";
    public static final String BEERLIST = "beerList";

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        Log.d("extrasOnCreate",extras.toString());
        detailsString = extras.getString("beerDetails");
        ingredientsString = extras.getString("beerIngredients");
        stepsString = extras.getString("beerSteps");
        beerObject = (Beer) extras.getSerializable("beerObject");

        setTitle(beerObject.getName());


    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {




        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }



        @Override
        public Fragment getItem(int position) {



            switch (position) {
                case 0:
                    Bundle detailsBundle = new Bundle();
                    detailsBundle.putString(DETAILS_FRAGMENT_KEY, detailsString);
                    DetailsActivity beerDetails = new DetailsActivity();
                    beerDetails.setArguments(detailsBundle);
                    return beerDetails;
                case 1:
                    Bundle ingredientsBundle = new Bundle();
                    ingredientsBundle.putString(INGREDIENTS_FRAGMENT_KEY, ingredientsString);
                    IngredientsActivity beerIngredients = new IngredientsActivity();
                    beerIngredients.setArguments(ingredientsBundle);
                    return beerIngredients;
                case 2:
                    Bundle stepsBundle = new Bundle();
                    stepsBundle.putString(STEPS_FRAGMENT_KEY, stepsString);
                    StepsActivity beerSteps = new StepsActivity();
                    beerSteps.setArguments(stepsBundle);
                    return beerSteps;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Details";
                case 1:
                    return "Ingredients";
                case 2:
                    return "Steps";
            }
            return null;
        }
    }



    public void favouriteClick (View view) {
        SharedPreferences sharedPref = getSharedPreferences(FAVOURITES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        String favourites = sharedPref.getString("favourites", "Adding something...");


        Gson gson = new Gson();
        TypeToken<ArrayList<Beer>> beerArrayList = new TypeToken<ArrayList<Beer>>() {
        };
        favouritesList = gson.fromJson(favourites, beerArrayList.getType());

        ArrayList<String> favouriteBeerNames = new ArrayList<String>();
        for (Beer beer : favouritesList) {
            favouriteBeerNames.add(beer.getName());
        }

        if (favouriteBeerNames.contains(beerObject.getName())) {
            Toast.makeText(BeerActivity.this, "Already in Favourites!", Toast.LENGTH_SHORT).show();
        } else {

            favouritesList.add(beerObject);

            editor.putString("favourites", gson.toJson(favouritesList));
            editor.apply();

            Toast.makeText(BeerActivity.this, "Added to Favourites", Toast.LENGTH_SHORT).show();
        }
    }

    public void removeFavouriteClick (View view){
        SharedPreferences sharedPref = getSharedPreferences(FAVOURITES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        String favourites = sharedPref.getString("favourites","Removing something...");


        Gson gson = new Gson();
        TypeToken<ArrayList<Beer>> beerArrayList = new TypeToken<ArrayList<Beer>>(){};
        favouritesList = gson.fromJson(favourites,beerArrayList.getType());


        ArrayList<String> favouriteBeerNames = new ArrayList<String>();
        for (Beer beer : favouritesList){
            favouriteBeerNames.add(beer.getName());
        }

        ArrayList<Beer> newFavourites = new ArrayList<Beer>();

        if (favouriteBeerNames.contains(beerObject.getName())){
            for (Beer beer : favouritesList){
                if (!beer.getName().equals(beerObject.getName())){
                    newFavourites.add(beer);
                }
            }

            editor.putString("favourites",gson.toJson(newFavourites));
            editor.apply();

            Toast.makeText(BeerActivity.this,"Removed from Favourites",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(BeerActivity.this,"This beer isn't in Favourites",Toast.LENGTH_SHORT).show();
        }
    }


    public void deleteClick (View view) {

//        Loads main and favourites from shared prefs

        SharedPreferences sharedPref = getSharedPreferences(BEERLIST, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        SharedPreferences sharedPref2 = getSharedPreferences(FAVOURITES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPref2.edit();

//        Assigns strings

        String beers = sharedPref.getString("beerList", "Removing something...");

        String favourites = sharedPref2.getString("favourites", "Removing something...");

//        Initialises JSON

        Gson gson = new Gson();

        TypeToken<ArrayList<Beer>> beerArrayList = new TypeToken<ArrayList<Beer>>() {};
        ArrayList<Beer> beerList = gson.fromJson(beers, beerArrayList.getType());

        TypeToken<ArrayList<Beer>> favouriteBeerArrayList = new TypeToken<ArrayList<Beer>>(){};
        favouritesList = gson.fromJson(favourites,favouriteBeerArrayList.getType());


//        Deletes beer from favourites if present

        ArrayList<String> favouriteBeerNames = new ArrayList<String>();
        for (Beer beer : favouritesList){
            favouriteBeerNames.add(beer.getName());
        }

        ArrayList<Beer> newFavourites = new ArrayList<Beer>();

        if (favouriteBeerNames.contains(beerObject.getName())){
            for (Beer beer : favouritesList){
                if (!beer.getName().equals(beerObject.getName())){
                    newFavourites.add(beer);
                }
            }

            editor2.putString("favourites",gson.toJson(newFavourites));
            editor2.apply();

        }


//        Deletes beer from main

        ArrayList<String> allBeerNames = new ArrayList<String>();
        for (Beer beer : beerList) {
            allBeerNames.add(beer.getName());
        }

        ArrayList<Beer> newBeerList = new ArrayList<Beer>();

        for (Beer beer : beerList) {
            if (!beer.getName().equals(beerObject.getName())) {
                newBeerList.add(beer);
            }
        }

        editor.putString("beerList", gson.toJson(newBeerList));
        editor.apply();

        Toast.makeText(BeerActivity.this, "Beer deleted!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this,ListActivity.class);
        startActivity(intent);

    }

    public void startClick (View view){

        setContentView(R.layout.activity_calendar);

        CalendarView calendar = (CalendarView) findViewById(R.id.calendar);

        Long currentDate = calendar.getDate();

        Long fermentationTime = Long.valueOf(beerObject.steps.get("Fermentation time (days)"));
        Long conditioningTime = Long.valueOf(beerObject.steps.get("Conditioning time (weeks)"));

        Long convertedFermentationTime = TimeUnit.MILLISECONDS.convert(fermentationTime, TimeUnit.DAYS);
        Long convertedConditioningTime = TimeUnit.MILLISECONDS.convert(conditioningTime,TimeUnit.DAYS) * 7;

        Long completedFermentation = currentDate + convertedFermentationTime;
        Long completedConditioning = currentDate + convertedConditioningTime;

        String completedFermentationString = String.valueOf(completedFermentation);
        String completedConditioningString = String.valueOf(completedConditioning);

        beerObject.details.put("Fermentation completed",completedFermentationString);
        beerObject.details.put("Conditioning completed",completedConditioningString);

        Toast.makeText(BeerActivity.this, "Recipe started, end times calculated", Toast.LENGTH_SHORT).show();


        SharedPreferences sharedPref = getSharedPreferences(BEERLIST, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        String importedBeerList = sharedPref.getString("beerList","");


        Gson gson = new Gson();
        TypeToken<ArrayList<Beer>> beerArrayList = new TypeToken<ArrayList<Beer>>(){};
        ArrayList<Beer> beerList = gson.fromJson(importedBeerList,beerArrayList.getType());



        ArrayList<String> allBeerNames = new ArrayList<String>();
        for (Beer beer : beerList) {
            allBeerNames.add(beer.getName());
        }

        for (Beer beer : beerList) {
            if (beer.getName().equals(beerObject.getName())) {
                indexToReplace = beerList.indexOf(beer);
            }
        }

        beerList.set(indexToReplace,beerObject);




        editor.putString("beerList",gson.toJson(beerList));
        editor.apply();




        Intent intent = new Intent(this, BeerActivity.class);
        intent.putExtra("beerDetails",beerObject.parseDetailsHash());
        intent.putExtra("beerIngredients",beerObject.parseIngredientsHash());
        intent.putExtra("beerSteps",beerObject.parseStepsHash());
        intent.putExtra("beerObject",beerObject);

        startActivity(intent);


    }


    public void checkClick (View view){
        Intent intent = new Intent(this,CalendarCheckActivity.class);
        intent.putExtra("beerObject",beerObject);

        startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        getMenuInflater().inflate(R.menu.menu_beer, menu);
        getMenuInflater().inflate(R.menu.menu_filter, menu);
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


}
