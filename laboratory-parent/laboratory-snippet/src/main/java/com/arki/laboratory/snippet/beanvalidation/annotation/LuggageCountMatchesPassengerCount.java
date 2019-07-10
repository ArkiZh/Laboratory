package com.arki.laboratory.snippet.beanvalidation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Constraint(validatedBy = LuggageCountMatchesPassengerCountValidator.class)
public @interface LuggageCountMatchesPassengerCount {
    String message() default "There are too many luggages!";
    int piecesOfLuggagesPerPassenger() default 1;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
