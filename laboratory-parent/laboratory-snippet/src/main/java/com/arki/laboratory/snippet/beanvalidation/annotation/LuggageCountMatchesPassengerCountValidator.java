package com.arki.laboratory.snippet.beanvalidation.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class LuggageCountMatchesPassengerCountValidator implements ConstraintValidator<LuggageCountMatchesPassengerCount, Object[]> {
    private int piecesOfLuggagesPerPassenger;

    @Override
    public void initialize(LuggageCountMatchesPassengerCount constraintAnnotation) {
        this.piecesOfLuggagesPerPassenger = constraintAnnotation.piecesOfLuggagesPerPassenger();
    }

    @Override
    public boolean isValid(Object[] value, ConstraintValidatorContext context) {
        int passengerCount = (int) value[0];
        int luggageCount = (int) value[1];
        return luggageCount <= passengerCount * piecesOfLuggagesPerPassenger;
    }

}
