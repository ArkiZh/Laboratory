package com.arki.laboratory.snippet.beanvalidation;

import javax.validation.*;
import java.util.*;

public class ValidTest {
    public static void main(String[] args) {


        // Field level constraint.
        CarFieldLevel carFieldLevel = new CarFieldLevel( null, "DD-AB-123", 4 );
        Set<ConstraintViolation<CarFieldLevel>> constraintViolations = validateBean(carFieldLevel);
        printViolationSet(constraintViolations);

        // Property level constraint.
        CarPropertyLevel carPropertyLevel = new CarPropertyLevel("Fox", false);
        printViolationSet(validateBean(carPropertyLevel));

        // Container element constraint.
        CarContainerElement carContainerElement = new CarContainerElement();
        Map<FuelType, Integer> fuelConsumption = carContainerElement.getFuelConsumption();
        fuelConsumption.put(FuelType.GAS, 30);
        fuelConsumption.put(FuelType.PETROL, 9);
        List<String> partList = carContainerElement.getPartList();
        partList.add("a");
        Set<String> partSet = carContainerElement.getPartSet();
        partSet.add("A");
        carContainerElement.setTowingCapacity(Optional.of(9));
        printViolationSet(validateBean(carContainerElement));

        // Class level constraint.

    }

    /**
     * Initialize validatorFactory.
     */
    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    /**
     * Validate bean.
     * @param t
     * @param <T>
     * @return
     */
    private static <T> Set<ConstraintViolation<T>> validateBean(T t) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> violationSet = validator.validate(t);
        return violationSet;
    }

    /**
     * Print violation set.
     * @param violationSet
     * @param <T>
     */
    private static<T> void printViolationSet(Set<ConstraintViolation<T>> violationSet) {
        Iterator<ConstraintViolation<T>> iterator = violationSet.iterator();
        while (iterator.hasNext()) {
            ConstraintViolation<T> violation = iterator.next();
            T rootBean = violation.getRootBean();
            Path propertyPath = violation.getPropertyPath();
            Object invalidValue = violation.getInvalidValue();
            String message = violation.getMessage();
            System.out.println("=============== "
                    + "RootBean: " + String.valueOf(rootBean.getClass().getSimpleName())
                    + "    " + "PropertyPath: " + String.valueOf(propertyPath)
                    + "    " + "Value: " + String.valueOf(invalidValue)
                    + "    " + "Message: " + message);

        }
    }
}
