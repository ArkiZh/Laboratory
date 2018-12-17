package com.arki.laboratory.snippet;

import java.lang.ref.WeakReference;

public class WeakReferenceTest {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Car car = new Car();
        car.setColor("Blue");
        WeakReference<Car> carWeakReference = new WeakReference<>(car);
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long time = System.currentTimeMillis() - startTime;
            if (carWeakReference.get() != null) {
                System.out.println("Weak reference has lived for " + time + " ms.");
            } else {
                System.out.println("Weak reference is removed at " + time + " ms.");
                break;
            }
        }
    }

    static class Car {
        String color;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "color='" + color + '\'' +
                    '}';
        }
    }
}
