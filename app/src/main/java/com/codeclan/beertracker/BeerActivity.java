package com.codeclan.beertracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class BeerActivity extends AppCompatActivity {

    String detailsString;
    String ingredientsString;
    String stepsString;
    Beer beerObject;

    ArrayList<Beer> favouritesList;



    public static final String DETAILS_FRAGMENT_KEY = "Details Fragment";
    public static final String INGREDIENTS_FRAGMENT_KEY = "Ingredients Fragment";
    public static final String STEPS_FRAGMENT_KEY = "Steps Fragment";
    public static final String FAVOURITES = "FavouriteBeers";

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



    public void favouriteClick (View view){
        SharedPreferences sharedPref = getSharedPreferences(FAVOURITES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        String favourites = sharedPref.getString("favourites","Adding something...");



        Gson gson = new Gson();
        TypeToken<ArrayList<Beer>> beerArrayList = new TypeToken<ArrayList<Beer>>(){};
        favouritesList = gson.fromJson(favourites,beerArrayList.getType());

        Log.d("BeforeAdd",favouritesList.toString());

        favouritesList.add(beerObject);

        Log.d("AfterAdd",favouritesList.toString());



        editor.putString("favourites",gson.toJson(favouritesList));
        editor.apply();

        Toast.makeText(BeerActivity.this,"Added to Favourites",Toast.LENGTH_LONG).show();
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_beer, menu);
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

        return super.onOptionsItemSelected(item);
    }




}
