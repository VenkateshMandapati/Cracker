package com.codelove.cracker.responseTypes;

import java.util.List;

public class NutrientsSearchResponse {

    private String foodName;

    private String foodGroup;

    private String foodMeasure;

    private Double foodWeightForTheMeasure;

    List<NutrientInfo> nutrients;

    public NutrientsSearchResponse() {
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodGroup() {
        return foodGroup;
    }

    public void setFoodGroup(String foodGroup) {
        this.foodGroup = foodGroup;
    }

    public List<NutrientInfo> getNutrients() {
        return nutrients;
    }

    public void setNutrients(List<NutrientInfo> nutrients) {
        this.nutrients = nutrients;
    }

    public String getFoodMeasure() {
        return foodMeasure;
    }

    public void setFoodMeasure(String foodMeasure) {
        this.foodMeasure = foodMeasure;
    }

    public Double getFoodWeightForTheMeasure() {
        return foodWeightForTheMeasure;
    }

    public void setFoodWeightForTheMeasure(Double foodWeightForTheMeasure) {
        this.foodWeightForTheMeasure = foodWeightForTheMeasure;
    }
}
