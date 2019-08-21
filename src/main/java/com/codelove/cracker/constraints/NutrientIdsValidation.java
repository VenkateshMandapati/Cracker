package com.codelove.cracker.constraints;

import com.codelove.cracker.validators.NutrientIdsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {NutrientIdsValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NutrientIdsValidation {
    String message() default "{com.codelove.cracker.InvalidNutrientIds.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
