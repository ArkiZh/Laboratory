package com.arki.laboratory.snippet.beanvalidation.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MaxAllowedFuelConsumptionValidator implements ConstraintValidator<MaxAllowedFuelConsumption, Integer> {

    private Integer maxConsumption;

    @Override
    public void initialize(MaxAllowedFuelConsumption constraintAnnotation) {
        this.maxConsumption = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if(value==null) return true;
        return value >= maxConsumption;
    }
}
