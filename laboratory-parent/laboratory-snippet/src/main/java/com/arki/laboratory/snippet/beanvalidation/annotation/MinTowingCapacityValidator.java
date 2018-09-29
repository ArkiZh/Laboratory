package com.arki.laboratory.snippet.beanvalidation.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinTowingCapacityValidator implements ConstraintValidator<MinTowingCapacity, Integer> {

    private int minTowingCapacity;

    @Override
    public void initialize(MinTowingCapacity constraintAnnotation) {
        this.minTowingCapacity = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value >= minTowingCapacity;
    }
}
