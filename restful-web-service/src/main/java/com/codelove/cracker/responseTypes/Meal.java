package com.codelove.cracker.responseTypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Meal {

    private String partOfDay;

    private List<PartOfMeal> partOfMeals = new ArrayList<>();

    private int mealCalories;

    public Meal() {
    }

    public Meal(String partOfDay) {
        this.setPartOfDay(partOfDay);
    }

    public String getPartOfDay() {
        return partOfDay;
    }

    public void setPartOfDay(String partOfDay) {
        this.partOfDay = partOfDay;
    }

    public List<PartOfMeal> getPartOfMeals() {
        return partOfMeals;
    }

    public void setPartOfMeals(List<PartOfMeal> partOfMeals) {
        this.partOfMeals = partOfMeals;
    }

    public int getMealCalories() {
        return mealCalories;
    }

    public void setMealCalories(int mealCalories) {
        this.mealCalories = mealCalories;
    }
}
