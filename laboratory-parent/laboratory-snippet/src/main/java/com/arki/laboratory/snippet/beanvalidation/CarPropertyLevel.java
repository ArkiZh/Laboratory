package com.arki.laboratory.snippet.beanvalidation;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

public class CarPropertyLevel {
    private String manufacturer; //制造商
    private boolean isRegistered;
    public CarPropertyLevel(String manufacturer, boolean isRegistered) {
        this.manufacturer = manufacturer;
        this.isRegistered = isRegistered;
    }
    @NotNull
    public String getManufacturer() {
        return manufacturer;
    }
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    @AssertTrue
    public boolean isRegistered() {
        return isRegistered;
    }
    public void setRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }
}
