package com.codeclan.beertracker;

import android.graphics.drawable.shapes.PathShape;

import org.junit.*;
import static org.junit.Assert.*;

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

    @Test
    public void canGetName(){
        assertEquals("newBeer",testBeer.getName());
    }

    @Test
    public void canSetName(){
        testBeer.setName("changedBeer");
        assertEquals("changedBeer",testBeer.getName());
    }

    @Test
    public void canGetType(){
        assertEquals("IPA",testBeer.getType());
    }

    @Test
    public void canSetType(){
        testBeer.setType("Stout");
        assertEquals("Stout",testBeer.getType());
    }

    @Test
    public void canGetDescription(){
        assertEquals("newBeer description",testBeer.getDescription());
    }

    @Test
    public void canSetDescription(){
        testBeer.setDescription("changed");
        assertEquals("changed",testBeer.getDescription());
    }

    @Test
    public void canGetDetails(){
        HashMap<String,String> detailHash = testBeer.getDetails();
        assertEquals("4.5",detailHash.get("ABV"));
    }

//    @Test
//    public void canSetDetails(){
//
//    }

    @Test
    public void canGetIngredients(){
        HashMap<String,Integer> ingredientsHash = testBeer.getIngredients();
        Integer expectedValue = 4000;
        Integer returnedValue = ingredientsHash.get("pale malt");
        assertEquals(expectedValue,returnedValue);
    }

//    @Test
//    public void canSetIngredients(){
//
//    }

    @Test
    public void canGetSteps(){
        HashMap<String,Integer> stepsHash = testBeer.getSteps();
        assertSame(67,stepsHash.get("Mash temp"));
    }

//    @Test
//    public void canSetSteps(){
//
//    }
}
