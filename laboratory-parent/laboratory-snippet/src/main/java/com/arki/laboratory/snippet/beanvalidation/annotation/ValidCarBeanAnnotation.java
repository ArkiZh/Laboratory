package com.arki.laboratory.snippet.beanvalidation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = ValidCarBeanReal.class)
public @interface ValidCarBeanAnnotation {
    String message() default "Vo校验失败";

    Class<?>[] groups() default {};

    Class<? extends Payload> [] payload() default {};
}

