package com.codelove.cracker.ndbapi.searchAPI.service;

public interface ISearchAPIService {

    String getNDBNumberForFood(String foodName);

    String getNDBNumberForFoodWithFoodGroup(String foodName, String foodGroupId, boolean retry);

}
