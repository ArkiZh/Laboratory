package com.arki.laboratory.snippet.beanvalidation;

import com.arki.laboratory.snippet.beanvalidation.annotation.CaseMode;
import com.arki.laboratory.snippet.beanvalidation.annotation.MaxAllowedFuelConsumption;
import com.arki.laboratory.snippet.beanvalidation.annotation.MinTowingCapacity;
import com.arki.laboratory.snippet.beanvalidation.annotation.CheckCase;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;

public class CarContainerElement {

    @NotEmpty
    private Set<@CheckCase(CaseMode.UPPER) String> partSet = new HashSet<>();
    @NotEmpty
    private List<@CheckCase(CaseMode.UPPER) String> partList = new ArrayList<>();
    @NotEmpty
    private Map<@NotNull FuelType, @MaxAllowedFuelConsumption(value = 10) Integer> fuelConsumption = new HashMap<>();
    private Optional<@MinTowingCapacity(10) Integer> towingCapacity = Optional.empty();

    public Set<String> getPartSet() {
        return partSet;
    }

    public void setPartSet(Set<String> partSet) {
        this.partSet = partSet;
    }

    public List<String> getPartList() {
        return partList;
    }

    public void setPartList(List<String> partList) {
        this.partList = partList;
    }

    public Map<FuelType, Integer> getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(Map<FuelType, Integer> fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public Optional<Integer> getTowingCapacity() {
        return towingCapacity;
    }

    public void setTowingCapacity(Optional<Integer> towingCapacity) {
        this.towingCapacity = towingCapacity;
    }
}
