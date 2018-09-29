package com.arki.laboratory.snippet.beanvalidation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
@Constraint(validatedBy = MaxAllowedFuelConsumptionValidator.class)
public @interface MaxAllowedFuelConsumption {
    int value();
    String message() default "{com.arki.laboratory.snippet.beanvalidation.annotation.MaxAllowedFuelConsumption.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
