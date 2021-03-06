package com.arki.laboratory.snippet.beanvalidation;

import javax.validation.*;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.math.BigDecimal;
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
        CarClassLevel carClassLevel = new CarClassLevel();
        carClassLevel.setSeatCount(1);
        List<CarClassLevel.Person> passengers = carClassLevel.getPassengers();
        passengers.add(new CarClassLevel.Person("Wang"));
        passengers.add(new CarClassLevel.Person(" "));
        passengers.add(null);
        printViolationSet(validateBean(carClassLevel));

        // Abstract-annotation class level constraint.
        // The ValidCarBeanReal extends ValidCarBean.
        // Used by ValidCarBeanAnnotation: @Constraint(validatedBy = ValidCarBeanReal.class)
        CarClassLevelAbstract carClassLevelAbstract = new CarClassLevelAbstract();
        carClassLevelAbstract.setDriverName("A");
        carClassLevelAbstract.setSeatCount(2);
        printViolationSet(validateBean(carClassLevelAbstract));

        // Message interpolation.
        CarMessageInterpolation carMessageInterpolation = new CarMessageInterpolation("A", "1", 1, 360, BigDecimal.valueOf(300000));
        printViolationSet(validateBean(carMessageInterpolation));


        // Validator methods test.
        Validator validator = validatorFactory.getValidator();
        printViolationSet(validator.validate(carFieldLevel));
        printViolationSet(validator.validateProperty(carFieldLevel,"manufacturer"));
        printViolationSet(validator.validateValue(CarFieldLevel.class, "seatCount", 1));

        // Validate parameter constraints.
        ExecutableValidator executableValidator = validatorFactory.getValidator().forExecutables();
        RentalStation rentalStation = new RentalStation("Arki");
        Method rentCar = null;
        try {
            rentCar = RentalStation.class.getMethod("rentCar", RentalStation.Customer.class,Date.class,int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Set<ConstraintViolation<RentalStation>> constraintViolationSet = executableValidator.validateParameters(
                rentalStation,
                rentCar,
                new Object[]{new RentalStation.Customer(), new Date(), 0});
        printViolationSet(constraintViolationSet);

        // Validate cross parameter constraints.
        RentalStation.Car car = new RentalStation.Car("Fox");
        car.load(1,3);
        Method load = null;
        try {
            load = RentalStation.Car.class.getMethod("load", int.class, int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Set<ConstraintViolation<RentalStation.Car>> constraintViolationSet2 = executableValidator.validateParameters(car,
                load,
                new Object[]{1, 3});
        printViolationSet(constraintViolationSet2);

        // Valid return value.
        Set<ConstraintViolation<RentalStation>> constraintViolationSet1 = executableValidator.validateReturnValue(rentalStation,
                rentCar,
                rentalStation.rentCar(new RentalStation.Customer(), new Date(System.currentTimeMillis()), 0));
        printViolationSet(constraintViolationSet1);



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

    private static int printCount = 0;
    /**
     * Print violation set.
     * @param violationSet
     * @param <T>
     */
    private static<T> void printViolationSet(Set<ConstraintViolation<T>> violationSet) {
        System.out.println("=============== Print count: " + (++printCount));
        Iterator<ConstraintViolation<T>> iterator = violationSet.iterator();
        while (iterator.hasNext()) {
            ConstraintViolation<T> violation = iterator.next();
            T rootBean = violation.getRootBean();
            Path propertyPath = violation.getPropertyPath();
            Object invalidValue = violation.getInvalidValue();
            String message = violation.getMessage();
            System.out.println("=============== "
                    + "RootBean: " + String.valueOf(rootBean == null ? null : rootBean.getClass().getSimpleName())
                    + "    " + "PropertyPath: " + String.valueOf(propertyPath)
                    + "    " + "Value: " + String.valueOf(invalidValue)
                    + "    " + "Message: " + message);

        }
    }
}
