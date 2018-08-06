package com.arki.laboratory.snippet.designpattern.simplefactory;

public class DivideOperation implements Operation<Double,Double> {
    @Override
    public Double calculate(Double a, Double b) {
        if(b==0) throw new IllegalArgumentException("除数不能为0");
        return a / b;
    }
}
