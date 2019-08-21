package com.codelove.cracker.validators;

import com.codelove.cracker.constraints.FoodGroupIdValidation;
import com.codelove.cracker.controllers.BaseController;
import com.codelove.cracker.exception.InvalidRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FoodGroupIdValidator implements ConstraintValidator<FoodGroupIdValidation, String> {

    private static final Logger logger = LogManager.getLogger(BaseController.class);
    
    private static final List<String> foodGroupIdsList = 
    		new ArrayList<>(Arrays.asList("3500", "0300", "1800", "1300", "1400", "0800", "2000",
    									  "0100", "2100", "0400", "1500", "0900", "1700", "1600",
    									  "2200", "1200", "1000", "0500", "3600", "0700", "2500",
    									  "0600", "0200", "1900", "1100"));


    @Override
    public void initialize(FoodGroupIdValidation constraintAnnotation) {

    }

    @Override
    public boolean isValid(String foodGroupId, ConstraintValidatorContext constraintValidatorContext) {

    	if(foodGroupId == null || foodGroupIdsList.contains(foodGroupId)) {
    		return true;
    	}
    	
        return false;
    }
}
