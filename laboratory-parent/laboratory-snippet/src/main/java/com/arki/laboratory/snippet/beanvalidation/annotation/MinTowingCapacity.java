package com.arki.laboratory.snippet.beanvalidation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
@Constraint(validatedBy = MinTowingCapacityValidator.class)
public @interface MinTowingCapacity {
    int value();
    String message() default "{com.arki.laboratory.snippet.beanvalidation.annotation.MinTowingCapacity.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
