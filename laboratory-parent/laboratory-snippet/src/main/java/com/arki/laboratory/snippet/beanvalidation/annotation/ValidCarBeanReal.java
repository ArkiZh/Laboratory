package com.arki.laboratory.snippet.beanvalidation.annotation;

import com.arki.laboratory.snippet.beanvalidation.CarClassLevelAbstract;

import javax.validation.ConstraintValidatorContext;

public class ValidCarBeanReal extends ValidCarBean {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        CarClassLevelAbstract car = (CarClassLevelAbstract) value;
        if(car.getDriverName()=="A" && car.getSeatCount()==1){
            return true;
        }
        return false;
    }
}
