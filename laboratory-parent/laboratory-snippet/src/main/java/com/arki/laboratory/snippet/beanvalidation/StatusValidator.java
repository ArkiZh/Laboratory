package com.arki.laboratory.snippet.beanvalidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StatusValidator implements ConstraintValidator<Status,Order> {

    @Override
    public void initialize(Status constraintAnnotation) {

    }

    @Override
    public boolean isValid(Order order, ConstraintValidatorContext constraintValidatorContext) {
        String status = order.getStatus();

        return status != null &&(
                "created".equals(status) ||
                        "paid".equals(status) ||
                        "shipped".equals(status) ||
                        "closed".equals(status));
    }
}
