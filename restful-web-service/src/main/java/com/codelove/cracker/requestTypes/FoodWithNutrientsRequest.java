package com.codelove.cracker.requestTypes;

import com.codelove.cracker.constraints.FoodGroupIdValidation;
import com.codelove.cracker.constraints.NutrientIdsValidation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FoodWithNutrientsRequest {

    @NotBlank
    String foodName;

    @FoodGroupIdValidation
    String foodGroupId;

    @NotEmpty(message="nutrient list cant be empty")
    @NutrientIdsValidation
    private List<String> nutrientIds;

    public FoodWithNutrientsRequest() {
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodGroupId() {
        return foodGroupId;
    }

    public void setFoodGroupId(String foodGroup) {
        this.foodGroupId = foodGroup;
    }

    public List<String> getNutrientIds() {
        return nutrientIds;
    }

    public void setNutrientIds(List<String> nutrientIds) {
        this.nutrientIds = nutrientIds;
    }
}
