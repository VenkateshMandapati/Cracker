package com.codelove.cracker.ndbapi.nutrientsAPI.service;

import com.codelove.cracker.ndbapi.nutrientsAPI.responseTypes.FoodWithNutrientsResponse;

import java.util.List;

public interface INutrientsAPIService {

    FoodWithNutrientsResponse getNutrientsInfoForFood(String ndbNo, String foodGroupId, List<String> nutrientIds);

}
