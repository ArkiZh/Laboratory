package com.arki.laboratory.snippet.beanvalidation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE})
@Constraint(validatedBy = {CheckCaseValidator.class})
@Repeatable(CheckCase.List.class)
public @interface CheckCase {
    CaseMode value() default CaseMode.UPPER;
    String message() default "{com.arki.laboratory.snippet.beanvalidation.annotation.CheckCase.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
    @interface List{
        CheckCase[] value();
    }
}
