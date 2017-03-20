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
        details1.put("ABV","4.5");
        HashMap<String,String> details2 = new HashMap<String, String>();
        details2.put("ABV","5");
        HashMap<String,String> details3 = new HashMap<String, String>();
        details3.put("ABV","6.3");
        HashMap<String,String> ingredients1 = new HashMap<String, String>();
        ingredients1.put("pale malt","4000");
        HashMap<String,String> ingredients2 = new HashMap<String, String>();
        ingredients2.put("pilsner malt","4500");
        HashMap<String,String> ingredients3 = new HashMap<String, String>();
        ingredients3.put("carapils","250");
        HashMap<String,String> steps1 = new HashMap<String, String>();
        steps1.put("Mash temp","67");
        HashMap<String,String> steps2 = new HashMap<String, String>();
        steps2.put("Mash time","70");
        HashMap<String,String> steps3 = new HashMap<String, String>();
        steps3.put("fermentation","6");
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
