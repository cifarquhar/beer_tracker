package com.codeclan.beertracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

import static com.codeclan.beertracker.ListActivity.BEERLIST;

public class AddActivity extends AppCompatActivity {

    EditText newBeerName;
    EditText newBeerStyle;
    EditText newBeerDescription;
    EditText newBeerIngredientName;
    EditText newBeerIngredientWeight;
    EditText newBeerOG;
    EditText newBeerFG;
    EditText newBeerMashTemp;
    EditText newBeerMashTime;
    EditText newBeerBoilTime;
    EditText newBeerFermentation;
    EditText newBeerDryHops;
    EditText newBeerConditioning;
    HashMap<String,String> beerDetails;
    HashMap<String,String> beerIngredients;
    HashMap<String,String> beerSteps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        newBeerName = (EditText) findViewById(R.id.name_input);
        newBeerStyle = (EditText) findViewById(R.id.style_input);
        newBeerDescription = (EditText) findViewById(R.id.description_input);
//        newBeerIngredientName = (EditText) findViewById(R.id.ingredient_name_input);
//        newBeerIngredientWeight = (EditText) findViewById(R.id.ingredient_weight_input);
        newBeerOG = (EditText) findViewById(R.id.target_og_input);
        newBeerFG = (EditText) findViewById(R.id.target_fg_input);
        newBeerMashTemp = (EditText) findViewById(R.id.mash_temp_input);
        newBeerMashTime = (EditText) findViewById(R.id.mash_time_input);
        newBeerBoilTime = (EditText) findViewById(R.id.boil_time_input);
        newBeerFermentation = (EditText) findViewById(R.id.fermentation_time_input);
        newBeerDryHops = (EditText) findViewById(R.id.dry_hop_input);
        newBeerConditioning = (EditText) findViewById(R.id.conditioning_time_input);

        beerDetails = new HashMap<String,String>();
        beerIngredients = new HashMap<String,String>();
        beerSteps = new HashMap<String,String>();

    }

    public void newIngredientClick (View view){

        newBeerIngredientName = (EditText) findViewById(R.id.ingredient_name_input);
        newBeerIngredientWeight = (EditText) findViewById(R.id.ingredient_weight_input);

        String beerIngredientName = newBeerIngredientName.getText().toString();
        String beerIngredientWeight = newBeerIngredientWeight.getText().toString();

        beerIngredients.put(beerIngredientName,beerIngredientWeight);

        Toast.makeText(AddActivity.this,"Ingredient added!",Toast.LENGTH_LONG).show();

    }





    public String dryHopNeeded(String days){
        if (days.equals("0")){
            return "No";
        }
        else{
            return "Yes";
        }
    }

    public void addClick(View view){

        String beerName = newBeerName.getText().toString();
        Log.d("OutputTest",beerName);
        String beerStyle = newBeerStyle.getText().toString();
        Log.d("OutputTest",beerStyle);
        String beerDescription = newBeerDescription.getText().toString();
        Log.d("OutputTest",beerDescription);
        String beerOG = newBeerOG.getText().toString();
        Log.d("OutputTest",beerOG);
        String beerFG = newBeerFG.getText().toString();
        Log.d("OutputTest",beerFG);
        String beerMashTemp = newBeerMashTemp.getText().toString();
        Log.d("OutputTest",beerMashTemp);
        String beerMashTime = newBeerMashTime.getText().toString();
        Log.d("OutputTest",beerMashTime);
        String beerBoilTime = newBeerBoilTime.getText().toString();
        Log.d("OutputTest",beerBoilTime);
        String beerFermentation = newBeerFermentation.getText().toString();
        Log.d("OutputTest",beerFermentation);
        String beerDryHops = newBeerDryHops.getText().toString();
        Log.d("OutputTest",beerDryHops);
        String beerConditioning = newBeerConditioning.getText().toString();
        Log.d("OutputTest",beerConditioning);

        beerDetails.put("Target OG", beerOG);
        beerDetails.put("Target FG", beerFG);

        Double doubleOG = Double.parseDouble(beerOG);
        Double doubleFG = Double.parseDouble(beerFG);
        Double beerABV = ((doubleOG - doubleFG) * 131.25) / 1000;

        beerDetails.put("Expected ABV", beerABV.toString());

        beerDetails.put("Dry hopping required?", dryHopNeeded(beerDryHops));


        beerSteps.put("Mash temperature (C)", beerMashTemp);
        beerSteps.put("Mash time (mins)", beerMashTime);
        beerSteps.put("Boil time (mins)", beerBoilTime);
        beerSteps.put("Fermentation time (days)", beerFermentation);
        beerSteps.put("Conditioning time (weeks)", beerConditioning);

        Beer newBeer = new Beer(beerName,beerStyle,beerDescription,beerDetails,beerIngredients,beerSteps);


        SharedPreferences sharedPref = getSharedPreferences(BEERLIST, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        String importedBeerList = sharedPref.getString("beerList","");

        Log.d("AfterGetString",importedBeerList);


        Gson gson = new Gson();
        TypeToken<ArrayList<Beer>> beerArrayList = new TypeToken<ArrayList<Beer>>(){};
        ArrayList<Beer> beerList = gson.fromJson(importedBeerList,beerArrayList.getType());

//        Log.d("BeforeAdd",favouritesList.toString());

        beerList.add(newBeer);

//        Log.d("AfterAdd",favouritesList.toString());



        editor.putString("beerList",gson.toJson(beerList));
        editor.apply();





        Toast.makeText(AddActivity.this,"New recipe added!",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this,ListActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        getMenuInflater().inflate(R.menu.menu_beer, menu);
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
