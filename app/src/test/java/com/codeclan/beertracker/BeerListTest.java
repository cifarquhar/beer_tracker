package com.codeclan.beertracker;

import org.junit.Test;

import static junit.framework.Assert.*;
/**
 * Created by user on 16/03/2017.
 */

public class BeerListTest {

    @Test
    public void canGetBeerList(){
        BeerList beerList = new BeerList();
        assertEquals(3,beerList.getBeerList().size());
    }

}
