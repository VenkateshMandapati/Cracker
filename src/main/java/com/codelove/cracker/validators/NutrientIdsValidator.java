package com.codelove.cracker.validators;

import com.codelove.cracker.constraints.NutrientIdsValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class NutrientIdsValidator implements ConstraintValidator<NutrientIdsValidation, List<String>> {

    @Override
    public void initialize(NutrientIdsValidation constraintAnnotation) {

    }

    @Override
    public boolean isValid(List<String> nutrientIds, ConstraintValidatorContext constraintValidatorContext) {

        for(String nutrientId:nutrientIds){
            Integer nutrientIdStr = Integer.parseInt(nutrientId);
            if(nutrientIdStr < 0 || nutrientIdStr > 1000){
                return false;
            }
        }

        return true;
    }
}
