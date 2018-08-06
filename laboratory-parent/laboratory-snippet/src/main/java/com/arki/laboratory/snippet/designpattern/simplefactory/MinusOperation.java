package com.arki.laboratory.snippet.designpattern.simplefactory;

public class MinusOperation implements Operation<Double,Double> {
    @Override
    public Double calculate(Double a, Double b) {
        return a - b;
    }
}
