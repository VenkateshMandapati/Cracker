package com.codelove.cracker.ndbapi.service;

import com.codelove.cracker.exception.NDBAPIException;
import com.codelove.cracker.ndbapi.common.responseTypes.Food;
import com.codelove.cracker.ndbapi.common.responseTypes.Nutrient;
import com.codelove.cracker.ndbapi.constants.NDBApiConstants;
import com.codelove.cracker.ndbapi.nutrientsAPI.responseTypes.FoodWithNutrientsResponse;
import com.codelove.cracker.ndbapi.nutrientsAPI.service.INutrientsAPIService;
import com.codelove.cracker.ndbapi.searchAPI.service.ISearchAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NDBApiService implements INDBApiService {

    private INutrientsAPIService nutrientAPIService;

    private ISearchAPIService searchAPIService;

    @Autowired
    public NDBApiService(INutrientsAPIService nutrientAPIService, ISearchAPIService searchAPIService) {
        this.nutrientAPIService = nutrientAPIService;
        this.searchAPIService = searchAPIService;
    }

    @Override
    public int getCaloriesForFood(String foodName, String foodGroupId) {

        if(foodName == null) {
            throw new NDBAPIException("Food Name is Null" , HttpStatus.BAD_REQUEST);
        }

        FoodWithNutrientsResponse response = getNutrientInfoForFood(foodName, foodGroupId, NDBApiConstants.caloriesNutrientId);

        return Optional.ofNullable(response)
                .map(FoodWithNutrientsResponse::getNutrientReport)
                .flatMap(report -> {
                        List<Food> foods = report.getFoods();
                        if(foods == null) {
                            return Optional.empty();
                        }
                        return foods.stream().filter(Objects::nonNull).findFirst();
                })
                .flatMap(food -> food.getNutrients().stream().findFirst())
                .map(Nutrient::getValue)
                .map(Integer::valueOf)
                .orElse(0);
    }

    @Override
    public String getNDBNumber(String foodName, String foodGroupId) {
        return searchAPIService.getNDBNumberForFoodWithFoodGroup(foodName, foodGroupId, true);
    }

    @Override
    public String getNutrientId(String nutrientName) {
        return null;
    }

    @Override
    public List<String> getAllFoodGroups() {
        return null;
    }

    @Override
    public FoodWithNutrientsResponse getNutrientInfoForFood(String foodName, String foodGroupId, String nutrientId) {

        List<String> nutrientIds = new ArrayList<>();
        nutrientIds.add(nutrientId);
        String ndbNo = getNDBNumber(foodName, foodGroupId);
        return nutrientAPIService.getNutrientsInfoForFood(ndbNo, foodGroupId, nutrientIds);
    }

    @Override
    public FoodWithNutrientsResponse getNutrientsInfoForFood(String foodName, String foodGroupId, List<String> nutrientIds) {
    	String ndbNo = getNDBNumber(foodName, foodGroupId);
        return nutrientAPIService.getNutrientsInfoForFood(ndbNo, foodGroupId, nutrientIds);
    }

}
