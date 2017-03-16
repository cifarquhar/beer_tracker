package com.codeclan.beertracker;

import java.util.HashMap;

/**
 * Created by user on 16/03/2017.
 */

public class Beer {

    String name;
    String type;
    String description;
    HashMap<String,String> details;
    HashMap<String,Integer> ingredients;
    HashMap<String,Integer> steps;

    public Beer(String name, String type, String description, HashMap<String, String> details, HashMap<String, Integer> ingredients, HashMap<String, Integer> steps) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.details = details;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<String, String> getDetails() {
        return details;
    }

    public void setDetails(HashMap<String, String> details) {
        this.details = details;
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(HashMap<String, Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public HashMap<String, Integer> getSteps() {
        return steps;
    }

    public void setSteps(HashMap<String, Integer> steps) {
        this.steps = steps;
    }
}
