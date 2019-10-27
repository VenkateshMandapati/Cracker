package com.codelove.cracker.responseTypes;

import java.util.List;

public class FoodDetailsPerDay {

    private String day;
    
    private int totalCalories;

    private List<Meal> meals;

    public FoodDetailsPerDay() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
    
    

    public int getTotalCalories() {
		return totalCalories;
	}

	public void setTotalCalories(int totalCalories) {
		this.totalCalories = totalCalories;
	}

	@Override
    public String toString() {
        return "FoodDetailPerDay{" +
                "day='" + day + '\'' +
                "totalCalories='" + totalCalories + '\'' +
                ", meals=" + meals +
                '}';
    }
}
