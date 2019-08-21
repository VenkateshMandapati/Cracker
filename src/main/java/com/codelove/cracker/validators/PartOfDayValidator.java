package com.codelove.cracker.validators;

import com.codelove.cracker.constraints.PartOfDayValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PartOfDayValidator implements ConstraintValidator<PartOfDayValidation, String>{


    @Override
    public void initialize(PartOfDayValidation constraintAnnotation) {

    }

    @Override
    public boolean isValid(String partOfDay, ConstraintValidatorContext constraintValidatorContext) {

        if(partOfDay.equals("breakfast") || partOfDay.equals("lunch") || partOfDay.equals("dinner")) {
            return true;
        }

        return false;
    }
}
