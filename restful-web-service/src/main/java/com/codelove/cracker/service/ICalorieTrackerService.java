package com.codelove.cracker.service;

import com.codelove.cracker.entity.Customer;
import com.codelove.cracker.entity.FoodLogEntry;
import com.codelove.cracker.requestTypes.FoodEntryRequest;
import com.codelove.cracker.responseTypes.FoodDetailsResponse;
import com.codelove.cracker.responseTypes.LoginResponse;
import com.codelove.cracker.responseTypes.NutrientsSearchResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICalorieTrackerService {

    Customer saveOrUpdateCustomer(Customer customer);

    Customer getCustomerById(Integer id);

    void deleteCustomer(Integer id);

    FoodDetailsResponse getFoodDetailsForDateRange(int customerId, String startDate, String endDate);

    FoodDetailsResponse getFoodDetailsForSingleDay(int customerId, String startDate);

    FoodDetailsResponse getCustomerLastLoggedFoodDetails(int customerId);

    FoodLogEntry saveFoodDetails(FoodEntryRequest foodEntryRequest);

    NutrientsSearchResponse getNutrientsInfoForFood(String foodName, String foodGroup, List<String> nutrientIds);

    LoginResponse isCustomerCredentialsValid(String email, String password);
}
