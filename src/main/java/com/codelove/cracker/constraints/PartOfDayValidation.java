package com.codelove.cracker.constraints;

import com.codelove.cracker.validators.PartOfDayValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {PartOfDayValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PartOfDayValidation {
    String message() default "{com.codelove.cracker.InvalidPartOfDay.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
