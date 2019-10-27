package com.codelove.cracker.service;

import com.codelove.cracker.dao.ICustomerDaoRepository;
import com.codelove.cracker.dao.IFoodLogEntryRepository;
import com.codelove.cracker.entity.Customer;
import com.codelove.cracker.entity.FoodLogEntry;
import com.codelove.cracker.exception.CrackerException;
import com.codelove.cracker.exception.NDBAPIException;
import com.codelove.cracker.ndbapi.common.responseTypes.Food;
import com.codelove.cracker.ndbapi.nutrientsAPI.responseTypes.FoodWithNutrientsResponse;
import com.codelove.cracker.ndbapi.nutrientsAPI.responseTypes.NutrientReport;
import com.codelove.cracker.ndbapi.service.INDBApiService;
import com.codelove.cracker.requestTypes.FoodEntryRequest;
import com.codelove.cracker.responseTypes.FoodDetailsPerDay;
import com.codelove.cracker.responseTypes.FoodDetailsResponse;
import com.codelove.cracker.responseTypes.Meal;

import com.codelove.cracker.responseTypes.NutrientInfo;
import com.codelove.cracker.responseTypes.NutrientsSearchResponse;
import com.codelove.cracker.responseTypes.PartOfMeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CalorieTrackerService implements ICalorieTrackerService{

    private ICustomerDaoRepository customerDaoRepository;

    private IFoodLogEntryRepository foodLogEntryRepository;

    private INDBApiService ndbApiService;

    @Autowired
    public CalorieTrackerService(ICustomerDaoRepository customerDaoRepository, IFoodLogEntryRepository foodLogEntryRepository,
                                    INDBApiService ndbApiService) {
        this.customerDaoRepository = customerDaoRepository;
        this.foodLogEntryRepository = foodLogEntryRepository;
        this.ndbApiService = ndbApiService;
    }

    public CalorieTrackerService() {
    }

    @Override
    public Customer saveOrUpdateCustomer(Customer customer) {
    	Integer customerId = customer.getCustomerId();
    	if(customerId > 0) {
    		Customer oldCustomer = customerDaoRepository.findById(customerId).orElse(null);
    		customer = updateOldCustomer(oldCustomer, customer);
    	}
        Customer saveOrUpdatedCustomer = customerDaoRepository.save(customer);

        if(customer.getCustomerId() == 0){
            customer.setCustomerId(saveOrUpdatedCustomer.getCustomerId());
        }

        return customer;
    }

	@Override
    public Customer getCustomerById(Integer id) {
        return customerDaoRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCustomer(Integer customerId) {
        customerDaoRepository.deleteById(customerId);
    }

    @Override
    public FoodDetailsResponse getFoodDetailsForDateRange(int customerId, String startDate, String endDate) {

        List<FoodLogEntry> loggedFoodDetails = foodLogEntryRepository.getFoodDetailsForDateRange(customerId, startDate, endDate);
        Map<LocalDate, Map<String, Meal>> foodDetailsMap = new LinkedHashMap<>();

        loggedFoodDetails.stream()
                .forEach(foodLogEntry -> {
                        if(foodDetailsMap.get(foodLogEntry.getDay()) == null){
                            foodDetailsMap.put(foodLogEntry.getDay(), initAndGetMealMap());
                        }

                        Map<String, Meal> mealMap = foodDetailsMap.get(foodLogEntry.getDay());
                        this.populateMealMapForSingleFoodLogEntry(foodLogEntry, mealMap);
                });


        List<FoodDetailsPerDay> foodDetailsPerDayList = new ArrayList<>();
        for(Map.Entry<LocalDate, Map<String, Meal>> foodDetailsEntry: foodDetailsMap.entrySet()){
            List<Meal> oneDayMealsList = new ArrayList<>();
            for(Map.Entry<String, Meal> mealMapEntry:foodDetailsEntry.getValue().entrySet()){
                if(!mealMapEntry.getValue().getPartOfMeals().isEmpty()){
                    oneDayMealsList.add(mealMapEntry.getValue());
                }
            }
            FoodDetailsPerDay foodDetailsPerDay = mapFoodDetailsPerDay(foodDetailsEntry.getKey(), oneDayMealsList);
            foodDetailsPerDayList.add(foodDetailsPerDay);
        }

        return mapFoodDetailsResponse(customerId, foodDetailsPerDayList);
    }

    @Override
    public FoodDetailsResponse getFoodDetailsForSingleDay(int customerId, String startDate) {
    	LocalDate date = LocalDate.parse(startDate);
    	List<FoodLogEntry> loggedFoodDetails = foodLogEntryRepository.getFoodDetailsForSingleDay(customerId, startDate);
    	Map<String, Meal> mealMap = initAndGetMealMap();

        loggedFoodDetails.stream().forEach(foodLogEntry -> this.populateMealMapForSingleFoodLogEntry(foodLogEntry, mealMap));

        List<Meal> oneDayMealsList = new ArrayList<>();

        for(Map.Entry<String, Meal> entry:mealMap.entrySet()){
            if(!entry.getValue().getPartOfMeals().isEmpty()){
                oneDayMealsList.add(entry.getValue());
            }
        }

        List<FoodDetailsPerDay> foodDetailsPerDayList = new ArrayList<>();
        foodDetailsPerDayList.add(mapFoodDetailsPerDay(date, oneDayMealsList));
        return mapFoodDetailsResponse(customerId, foodDetailsPerDayList);
    }

    @Override
    public FoodLogEntry saveFoodDetails(FoodEntryRequest foodEntryRequest) {
        if(foodEntryRequest.getCalories() == 0){
            int calories;
            try{
                calories = ndbApiService.getCaloriesForFood(foodEntryRequest.getFoodName(), foodEntryRequest.getFoodGroupId());
                calories = calories*foodEntryRequest.getQuantity();
                if(calories==0){
                    throw new CrackerException("We could not find calories for the food " + foodEntryRequest.getFoodName()
                            + ", Please input calories manually", HttpStatus.BAD_REQUEST);
                }
            }catch(NDBAPIException ndbAPIException){
                String errorMessage = "Invalid Food Entry " + foodEntryRequest.getFoodName();
                if(foodEntryRequest.getFoodGroupId() != null){
                    errorMessage = errorMessage + ", Try without FoodGroupID";
                }
                else{
                    errorMessage = errorMessage + ", Please input calories manually";
                }
                throw new CrackerException(errorMessage, ndbAPIException, ndbAPIException.getHttpStatus());
            }
            foodEntryRequest.setCalories(calories);
        }
        return foodLogEntryRepository.save(mapFoodEntryRequest(foodEntryRequest));
    }

    @Override
    public NutrientsSearchResponse getNutrientsInfoForFood(String foodName, String foodGroupId, List<String> nutrientIds) {
        FoodWithNutrientsResponse foodWithNutrientsResponse = null;
        foodWithNutrientsResponse = ndbApiService.getNutrientsInfoForFood(foodName, foodGroupId, nutrientIds);
        NutrientsSearchResponse nutrientSearchResponse = mapNutrientResponse(foodWithNutrientsResponse);
        return nutrientSearchResponse;
    }

    private FoodLogEntry mapFoodEntryRequest(FoodEntryRequest foodEntryRequest){
        FoodLogEntry foodLogEntry = new FoodLogEntry();
        foodLogEntry.setCustomerId(foodEntryRequest.getCustomerId());
        foodLogEntry.setDay(foodEntryRequest.getDay());
        foodLogEntry.setPartOfDay(foodEntryRequest.getPartOfDay());
        foodLogEntry.setFoodName(foodEntryRequest.getFoodName());
        foodLogEntry.setCalories(foodEntryRequest.getCalories());
        return foodLogEntry;
    }

    private void populateMealMapForSingleFoodLogEntry(FoodLogEntry foodLogEntry, Map<String, Meal> mealMap){
        PartOfMeal partOfMeal = new PartOfMeal();
        partOfMeal.setFoodName(foodLogEntry.getFoodName());
        partOfMeal.setCalories(foodLogEntry.getCalories());

        Meal meal = mealMap.get(foodLogEntry.getPartOfDay());
        if(meal == null){
            throw new CrackerException("Invalid FoodLog Entry", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        int calories = meal.getMealCalories();
        calories = calories + foodLogEntry.getCalories();
        meal.setMealCalories(calories);
        meal.getPartOfMeals().add(partOfMeal);
    }

    private Map<String, Meal> initAndGetMealMap(){
        Map<String, Meal> mealMap = new HashMap<>();
        mealMap.put("breakfast", new Meal("breakfast"));
        mealMap.put("lunch", new Meal("lunch"));
        mealMap.put("dinner", new Meal("dinner"));
        return mealMap;
    }

    private FoodDetailsPerDay mapFoodDetailsPerDay(LocalDate day, List<Meal> meals){
        FoodDetailsPerDay foodDetailsPerDay = new FoodDetailsPerDay();
        foodDetailsPerDay.setDay(day.toString());
        foodDetailsPerDay.setTotalCalories(calculateTotalCalories(meals));
        if(!meals.isEmpty()){
            foodDetailsPerDay.setMeals(meals);
        }
        return foodDetailsPerDay;
    }

    private FoodDetailsResponse mapFoodDetailsResponse(Integer customerId, List<FoodDetailsPerDay> foodDetailsPerDayList){
        FoodDetailsResponse foodDetailsResponse = new FoodDetailsResponse();
        foodDetailsResponse.setCustomerId(customerId);
        foodDetailsResponse.setFoodDetails(foodDetailsPerDayList);
        return foodDetailsResponse;
    }

    private int calculateTotalCalories(List<Meal> meals) {

    	return meals.stream()
    				.filter(Objects::nonNull)
    				.map(Meal::getMealCalories)
    				.collect(Collectors.summingInt(Integer::intValue));

    }

    private Customer updateOldCustomer(Customer oldCustomer, Customer customer) {
    	if(customer.getFirstName() != null) {
    		oldCustomer.setFirstName(customer.getFirstName());
    	}

    	if(customer.getLastName() != null) {
    		oldCustomer.setLastName(customer.getLastName());
    	}

    	if(customer.getEmail() != null) {
    		oldCustomer.setEmail(customer.getEmail());
    	}

    	return oldCustomer;
	}

	private NutrientsSearchResponse mapNutrientResponse(FoodWithNutrientsResponse foodWithNutrientsResponse){

        Optional<FoodWithNutrientsResponse> apiResponseOptional = Optional.of(foodWithNutrientsResponse);

        return apiResponseOptional
                .map(FoodWithNutrientsResponse::getNutrientReport)
                .map(this::mapToNutrientSearchResponse)
                .orElse(null);
    }

    private NutrientsSearchResponse mapToNutrientSearchResponse(NutrientReport nutrientReport){
        //Group group = null;
        Food food = nutrientReport.getFoods().get(0);
//
//        if(nutrientReport.getGroups() != null){
//            group = nutrientReport.getGroups().get(0);
//        }

        NutrientsSearchResponse nutrientsSearchResponse = new NutrientsSearchResponse();
        nutrientsSearchResponse.setFoodName(food.getName());
        //nutrientsSearchResponse.setFoodGroup(group.getDescription());
        nutrientsSearchResponse.setFoodMeasure(food.getMeasure());
        nutrientsSearchResponse.setFoodWeightForTheMeasure(food.getWeight());
        List<NutrientInfo> nutrientInfoList = new ArrayList<>();
        food.getNutrients().stream()
                .forEach(nutrient -> {
                    NutrientInfo nutrientInfo = new NutrientInfo();
                    nutrientInfo.setNutrientId(nutrient.getNutrientId());
                    nutrientInfo.setNutrientName(nutrient.getNutrient());
                    nutrientInfo.setUnit(nutrient.getMeasurementUnit());
                    nutrientInfo.setValue(nutrient.getValue());
                    nutrientInfo.setHundredGramEquivalentValue(String.valueOf(nutrient.getHundredGramsEquivalentValue()));
                    nutrientInfoList.add(nutrientInfo);
                });

        nutrientsSearchResponse.setNutrients(nutrientInfoList);
        return nutrientsSearchResponse;
    }

    //searchAPI food details API (5 results)

    //searchAPI for NDB number

}
