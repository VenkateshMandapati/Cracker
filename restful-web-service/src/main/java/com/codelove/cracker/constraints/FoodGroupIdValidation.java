package com.codelove.cracker.constraints;


import com.codelove.cracker.validators.FoodGroupIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {FoodGroupIdValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FoodGroupIdValidation {
    String message() default "{com.codelove.cracker.InvalidFoodGroupId.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
