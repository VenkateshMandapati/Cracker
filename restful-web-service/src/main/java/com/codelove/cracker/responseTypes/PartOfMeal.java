package com.codelove.cracker.responseTypes;

public class PartOfMeal {

    private String foodName;

    private Integer calories;

    public PartOfMeal() {
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }
}
