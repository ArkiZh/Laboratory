package com.arki.laboratory.snippet.beanvalidation;

import com.arki.laboratory.snippet.beanvalidation.annotation.LuggageCountMatchesPassengerCount;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentalStation {
    private String name;
    public RentalStation(@NotNull String name) {
        this.name = name;
    }

    public @AssertTrue boolean rentCar(@NotNull Customer customer, @NotNull @FutureOrPresent Date startDate, @Min(1) int durationInDays) {
        System.out.println("You can rent car.");
        return false;
    }
    public static class Customer{

    }

    public static class Car{
        private String manufacturer;
        private String team;
        private int speedInMph;
        private List<Passenger> passengers = new ArrayList<>();
        public Car(@NotNull String manufacturer) {
        }

        @LuggageCountMatchesPassengerCount(piecesOfLuggagesPerPassenger = 2)
        public void load(int passengerCount, int luggageCount){

        }

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        public String getTeam() {
            return team;
        }

        public void setTeam(String team) {
            this.team = team;
        }

        public int getSpeedInMph() {
            return speedInMph;
        }

        public void setSpeedInMph(int speedInMph) {
            this.speedInMph = speedInMph;
        }

        public List<Passenger> getPassengers() {
            return passengers;
        }

        public void setPassengers(List<Passenger> passengers) {
            this.passengers = passengers;
        }
    }

    public static class Passenger{

    }
}
