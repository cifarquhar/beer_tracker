package com.codeclan.beertracker;

import org.junit.*;
import org.junit.Assert.*;

import java.util.HashMap;

/**
 * Created by user on 16/03/2017.
 */

public class BeerTest {

    Beer testBeer;

    @Before
    public void Before(){
        String name = new String("newBeer");
        String type = new String("IPA");
        String description = new String("newBeer description");
        HashMap<String,String> details = new HashMap<String,String>();
        details.put("ABV","4.5");
        HashMap<String,Integer> ingredients = new HashMap<String,Integer>();
        ingredients.put("pale malt",4000);
        HashMap<String,Integer> steps = new HashMap<String, Integer>();
        steps.put("Mash temp",67);
        testBeer = new Beer(name,type,description,details,ingredients,steps);
    }

}
