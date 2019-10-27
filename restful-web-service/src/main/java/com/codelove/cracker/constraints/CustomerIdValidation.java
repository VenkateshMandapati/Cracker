package com.codelove.cracker.constraints;

import com.codelove.cracker.validators.CustomerValidator;
import com.codelove.cracker.validators.NutrientIdsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {CustomerValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomerIdValidation {
    String message() default "{com.codelove.cracker.InvalidCustomer.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
