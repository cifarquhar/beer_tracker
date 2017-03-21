package com.codeclan.beertracker;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by user on 16/03/2017.
 */

public class BeerList {

    private ArrayList<Beer> beerList;

    public BeerList(){
        beerList = new ArrayList<Beer>();
        HashMap<String,String> details1 = new HashMap<String, String>();
        details1.put("Target OG: ","1046");
        details1.put("Target FG: ","1007");
        details1.put("ABV","4.5");
        details1.put("Dry hopping required? ", "Yes");
        HashMap<String,String> details2 = new HashMap<String, String>();
        details2.put("Target OG: ","1049");
        details2.put("Target FG: ","1005");
        details2.put("ABV","5");
        details2.put("Dry hopping required? ", "No");
        HashMap<String,String> details3 = new HashMap<String, String>();
        details3.put("Target OG: ","1060");
        details3.put("Target FG: ","1009");
        details3.put("ABV","6.3");
        details3.put("Dry hopping required? ", "No");
        HashMap<String,String> ingredients1 = new HashMap<String, String>();
        ingredients1.put("pale malt","4000");
        HashMap<String,String> ingredients2 = new HashMap<String, String>();
        ingredients2.put("pilsner malt","4500");
        HashMap<String,String> ingredients3 = new HashMap<String, String>();
        ingredients3.put("carapils","250");
        HashMap<String,String> steps1 = new HashMap<String, String>();
        steps1.put("Mash temperature (C): ", "67");
        steps1.put("Mash time (mins): ", "65");
        steps1.put("Boil time (mins): ", "70");
        steps1.put("Fermentation time (days):", "6");
        steps1.put("Conditioning time (weeks): ", "6");
        HashMap<String,String> steps2 = new HashMap<String, String>();
        steps2.put("Mash temperature (C): ", "65");
        steps2.put("Mash time (mins): ", "60");
        steps2.put("Boil time (mins): ", "60");
        steps2.put("Fermentation time (days):", "8");
        steps2.put("Conditioning time (weeks): ", "4");
        HashMap<String,String> steps3 = new HashMap<String, String>();
        steps3.put("Mash temperature (C): ", "69");
        steps3.put("Mash time (mins): ", "70");
        steps3.put("Boil time (mins): ", "70");
        steps3.put("Fermentation time (days):", "7");
        steps3.put("Conditioning time (weeks): ", "10");
        beerList.add(new Beer("Beer1","IPA","Hoppy",details1,ingredients1,steps1));
        beerList.add(new Beer("Beer2","Lager","Gassy",details2,ingredients2,steps2));
        beerList.add(new Beer("Beer3","Stout","Dark",details3,ingredients3,steps3));
    }

    public ArrayList<Beer> getBeerList() {
        return new ArrayList<Beer>(beerList);
    }

//    public ArrayList<Beer> addToBeerList(Beer beer){
//
//    }
}
