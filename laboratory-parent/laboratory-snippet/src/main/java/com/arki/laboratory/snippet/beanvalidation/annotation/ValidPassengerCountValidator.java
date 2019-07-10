package com.arki.laboratory.snippet.beanvalidation.annotation;

import com.arki.laboratory.snippet.beanvalidation.CarClassLevel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidPassengerCountValidator implements ConstraintValidator<ValidPassengerCount, CarClassLevel> {
    @Override
    public void initialize(ValidPassengerCount constraintAnnotation) {

    }

    @Override
    public boolean isValid(CarClassLevel value, ConstraintValidatorContext context) {
        if(value.getPassengers()==null) return true;
        return  value.getPassengers().size()<=value.getSeatCount();
    }
}
