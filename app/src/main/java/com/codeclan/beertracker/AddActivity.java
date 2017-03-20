package com.codeclan.beertracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class AddActivity extends AppCompatActivity {

    EditText newBeerName;
    EditText newBeerStyle;
    EditText newBeerDescription;
//    ArrayList<EditText> newBeerIngredients;
    NumberPicker newBeerOG;
    NumberPicker newBeerFG;
    NumberPicker newBeerMashTemp;
    NumberPicker newBeerMashTime;
    NumberPicker newBeerBoilTime;
    NumberPicker newBeerFermentation;
    NumberPicker newBeerDryHops;
    NumberPicker newBeerConditioning;
    HashMap<String,String> beerDetails;
    HashMap<String,Integer> beerIngredients;
    HashMap<String,Integer> beerSteps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        newBeerName = (EditText) findViewById(R.id.name_input);
        newBeerStyle = (EditText) findViewById(R.id.style_input);
        newBeerDescription = (EditText) findViewById(R.id.description_input);
//        newBeerIngredients = ???
        newBeerOG = (NumberPicker) findViewById(R.id.target_og_input);
        newBeerFG = (NumberPicker) findViewById(R.id.target_fg_input);
        newBeerMashTemp = (NumberPicker) findViewById(R.id.mash_temp_input);
        newBeerMashTime = (NumberPicker) findViewById(R.id.mash_time_input);
        newBeerBoilTime = (NumberPicker) findViewById(R.id.boil_time_input);
        newBeerFermentation = (NumberPicker) findViewById(R.id.fermentation_time_input);
        newBeerDryHops = (NumberPicker) findViewById(R.id.dry_hop_input);
        newBeerConditioning = (NumberPicker) findViewById(R.id.conditioning_time_input);

        beerDetails = new HashMap<String,String>();
        beerIngredients = new HashMap<String,Integer>();
        beerSteps = new HashMap<String,Integer>();

    }

    public String dryHopNeeded(int days){
        if (days == 0){
            return "No";
        }
        else{
            return "Yes";
        }
    }

    public void addClick(){

        String beerName = newBeerName.getText().toString();
        String beerStyle = newBeerStyle.getText().toString();
        String beerDescription = newBeerDescription.getText().toString();
        Integer beerOG = newBeerOG.getValue();
        Integer beerFG = newBeerFG.getValue();
        Integer beerMashTemp = newBeerMashTemp.getValue();
        Integer beerMashTime = newBeerMashTime.getValue();
        Integer beerBoilTime = newBeerBoilTime.getValue();
        Integer beerFermentation = newBeerFermentation.getValue();
        Integer beerDryHops = newBeerDryHops.getValue();
        Integer beerConditioning = newBeerConditioning.getValue();

        beerDetails.put("Target OG: ", beerOG.toString());
        beerDetails.put("Target FG: ", beerFG.toString());

        Double doubleOG = beerOG.doubleValue();
        Double doubleFG = beerFG.doubleValue();
        Double beerABV = (doubleOG - doubleFG) * 131.25;

        beerDetails.put("Expected ABV: ", beerABV.toString());

        beerDetails.put("Dry hopping required? ", dryHopNeeded(beerDryHops));


        beerSteps.put("Mash temperature (C): ", beerMashTemp);
        beerSteps.put("Mash time (mins): ", beerMashTime);
        beerSteps.put("Boil time (mins): ", beerBoilTime);
        beerSteps.put("Fermentation time (days):", beerFermentation);
        beerSteps.put("Conditioning time (days): ", beerConditioning);

        Beer newBeer = new Beer(beerName,beerStyle,beerDescription,beerDetails,beerIngredients,beerSteps);







        Toast.makeText(AddActivity.this,"New recipe added!",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this,ListActivity.class);
        startActivity(intent);
    }
}
