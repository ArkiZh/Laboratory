package com.arki.laboratory.snippet.designpattern.simplefactory;

public interface Operation <T,M>{
    String Operator = "Operator";
    String Operand0 = "Operand0";
    String Operand1 = "Operand1";
    String Plus = "+";
    String Minus = "-";
    String Multiply = "*";
    String Divide = "/";

    T calculate(M a, M b);

}
