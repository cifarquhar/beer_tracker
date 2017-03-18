package com.codeclan.beertracker;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.HashMap;

public class BeerActivity extends AppCompatActivity {

    public static final String DETAILS_FRAGMENT_KEY = "Details Fragment";
    public static final String INGREDIENTS_FRAGMENT_KEY = "Ingredients Fragment";
    public static final String STEPS_FRAGMENT_KEY = "Steps Fragment";

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



    }


//    private Fragment createCustomFragment(){
//        Bundle bundle = new Bundle();
//        bundle.putString(DETAILS_FRAGMENT_KEY, "This is a test");
//
//    }



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });








//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_beer, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {




        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        String details = extras.getString("beerDetails");
//        String ingredients = extras.getString("beerIngredients");
//        String steps = extras.getString("beerSteps");


        @Override
        public Fragment getItem(int position) {






            switch (position) {
            case 0:
//                Intent detailIntent = getIntent();
//                Bundle detailExtras = detailIntent.getExtras();
//                String details = detailExtras.getString("beerDetails");
                Bundle detailsBundle = new Bundle();
                detailsBundle.putString(DETAILS_FRAGMENT_KEY, "details");
                DetailsActivity beerDetails = new DetailsActivity();
                beerDetails.setArguments(detailsBundle);
                return beerDetails;
            case 1:
//                Intent ingredientIntent = getIntent();
//                Bundle ingredientExtras = ingredientIntent.getExtras();
//                String ingredients = ingredientExtras.getString("beerIngredients");
                Bundle ingredientsBundle = new Bundle();
                ingredientsBundle.putString(INGREDIENTS_FRAGMENT_KEY, "ingredients");
                IngredientsActivity beerIngredients = new IngredientsActivity();
                return beerIngredients;
            case 2:
//                Intent stepIntent = getIntent();
//                Bundle stepExtras = stepIntent.getExtras();
//                String steps = stepExtras.getString("beerSteps");
                Bundle stepsBundle = new Bundle();
                stepsBundle.putString(STEPS_FRAGMENT_KEY, "steps");
                StepsActivity beerSteps = new StepsActivity();
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
}
