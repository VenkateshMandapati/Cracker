package com.codelove.cracker.controllers;

import com.codelove.cracker.entity.Customer;
import com.codelove.cracker.errors.RequestError;
import com.codelove.cracker.exception.InvalidRequestException;
import com.codelove.cracker.requestTypes.FoodEntryRequest;
import com.codelove.cracker.requestTypes.FoodWithNutrientsRequest;
import com.codelove.cracker.responseTypes.FoodDetailsResponse;
import com.codelove.cracker.responseTypes.NutrientsSearchResponse;
import com.codelove.cracker.service.ICalorieTrackerService;

import com.codelove.cracker.validators.RequestValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BaseController {

    @Autowired
    ICalorieTrackerService calorieTrackerService;

    //for slf4j implementation
    //private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    private static final Logger logger = LogManager.getLogger(BaseController.class);

    @GetMapping("log")
    public String testToLog(){

        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

        return "Logging completed";
    }

    @GetMapping("customers")
    public Customer getCustomer( @RequestParam("id") Integer customerId){
        RequestValidator.isCustomerValid(customerId, calorieTrackerService);
        return calorieTrackerService.getCustomerById(customerId);
    }

    @PostMapping("customers")
    public Customer saveCustomer(@Valid @RequestBody Customer customer, BindingResult bindingResult){
        customer.setCustomerId(0);
        checkForBindingErrors(bindingResult);
        return calorieTrackerService.saveOrUpdateCustomer(customer);
    }

    @PutMapping("customers")
    public Customer updateCustomer(@Valid @RequestBody Customer customer, BindingResult bindingResult){
        RequestValidator.isCustomerValid(customer.getCustomerId(), calorieTrackerService);
        checkForBindingErrors(bindingResult);
        return calorieTrackerService.saveOrUpdateCustomer(customer);
    }

    @DeleteMapping("customers/{id}")
    public String deleteCustomer(@PathVariable("id") Integer customerId, BindingResult bindingResult){
        RequestValidator.isCustomerValid(customerId, calorieTrackerService);
        calorieTrackerService.deleteCustomer(customerId);
        return "Customer with Id: " + customerId + " deleted succesfully";
    }

    @GetMapping("/fooddetails")
    public FoodDetailsResponse getFoodDetailsForDateRange(@RequestParam("id") int customerId,
                                                          @RequestParam("startdate") String startDate,
                                                          @RequestParam(value="enddate", required=false) String endDate){
        RequestValidator.isCustomerValid(customerId, calorieTrackerService);
        return endDate != null ? calorieTrackerService.getFoodDetailsForDateRange(customerId, startDate, endDate):
                                 calorieTrackerService.getFoodDetailsForSingleDay(customerId, startDate);
    }

    @PostMapping("/fooddetails")
    public String saveFoodDetails(@Valid @RequestBody FoodEntryRequest foodEntryRequest,
                                  BindingResult bindingResult) {

        checkForBindingErrors(bindingResult);
        calorieTrackerService.saveFoodDetails(foodEntryRequest);
        //need to implement post redirect get pattern
        return "Food Details uploaded Succesfully";
    }

    @PostMapping("/nutrients")
    public NutrientsSearchResponse getNutrientsInfoForFood(@Valid @RequestBody FoodWithNutrientsRequest foodWithNutrientsRequest, BindingResult bindingResult) {
        checkForBindingErrors(bindingResult);
        return calorieTrackerService.getNutrientsInfoForFood(foodWithNutrientsRequest.getFoodName(),foodWithNutrientsRequest.getFoodGroupId(),
                foodWithNutrientsRequest.getNutrientIds());

    }

    private void checkForBindingErrors(BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new InvalidRequestException(bindingResult.getFieldErrors()
                    .stream()
                    .map(fieldError -> {
                        RequestError bindError = new RequestError();
                        bindError.setFieldName(fieldError.getField());
                        bindError.setErrorMessage(fieldError.getDefaultMessage());
                        return bindError;
                    })
                    .collect(Collectors.toList()), HttpStatus.BAD_REQUEST);
        }

    }

}
