package com.codelove.cracker.validators;

import com.codelove.cracker.constraints.CustomerIdValidation;
import com.codelove.cracker.service.ICalorieTrackerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomerValidator implements ConstraintValidator<CustomerIdValidation, Integer>{

    @Autowired
    ICalorieTrackerService calorieTrackerService;

    @Override
    public void initialize(CustomerIdValidation constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer customerId, ConstraintValidatorContext constraintValidatorContext) {
        if(customerId < 0 || calorieTrackerService.getCustomerById(customerId) == null){
            return false;
        }

        return true;
    }
}
