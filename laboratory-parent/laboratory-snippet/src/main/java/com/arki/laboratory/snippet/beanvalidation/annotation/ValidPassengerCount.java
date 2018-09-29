package com.arki.laboratory.snippet.beanvalidation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = ValidPassengerCountValidator.class)
public @interface ValidPassengerCount {

    String message() default "乘客数不能超过座位数${validatedValue.seatCount}";

    Class<?>[] groups() default {};

    Class<? extends Payload> [] payload() default {};
}
