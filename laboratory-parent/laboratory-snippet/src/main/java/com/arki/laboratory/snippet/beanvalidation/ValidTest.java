package com.arki.laboratory.snippet.beanvalidation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.Set;

public class ValidTest {
    public static void main(String[] args) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Order order = new Order();
        order.setAddress("a");

        Set<ConstraintViolation<Order>> violations = validator.validate(order);
        Iterator<ConstraintViolation<Order>> iterator = violations.iterator();
        while (iterator.hasNext()) {
            ConstraintViolation<Order> violation = iterator.next();
            String message = violation.getMessage();
            System.out.println(message);
        }
    }
}
