package com.arki.laboratory.snippet.designpattern.simplefactory;

public class OperateFactory {

    public static Operation getOperation(String operator) {
        switch (operator) {
            case (Operation.Plus):
                return new PlusOperation();
            case (Operation.Minus):
                return new MinusOperation();
            case (Operation.Multiply):
                    return new MultiplyOperation();
            case (Operation.Divide):
                return new DivideOperation();
            default:
                throw new IllegalArgumentException("不支持这种运算！");

        }

    }

}
