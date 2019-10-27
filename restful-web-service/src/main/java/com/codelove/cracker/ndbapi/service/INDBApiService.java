package com.codelove.cracker.ndbapi.service;

import com.codelove.cracker.ndbapi.nutrientsAPI.responseTypes.FoodWithNutrientsResponse;

import java.util.List;

public interface INDBApiService {

    int getCaloriesForFood(String foodName, String foodGroupId);

    String getNDBNumber(String foodName, String foodGroup);

    String getNutrientId(String nutrientName);

    List<String> getAllFoodGroups();

    FoodWithNutrientsResponse getNutrientInfoForFood(String foodName, String foodGroupId, String nutrientId);

    FoodWithNutrientsResponse getNutrientsInfoForFood(String foodName, String foodGroupId, List<String> nutrientIds);
}
