package com.arki.laboratory.snippet.designpattern.simplefactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Calculator {



    public static void main(String[] args) {
        System.out.println("欢迎使用计算器，目前支持两个数字的加减乘除运算。");
        while (true) {
            System.out.println("请输入表达式：");
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            Map<String,Object> paramMap = resolveInput(s);
            if (paramMap != null) {
                Object result = calculate(paramMap);
                System.out.println("= " + String.valueOf(result));
            }
            scanner.nextLine();
        }


    }

    private static Object calculate(Map<String, Object> paramMap) {
        try {
            Operation operation = OperateFactory.getOperation((String) paramMap.get(Operation.Operator));
            Object result = operation.calculate(paramMap.get(Operation.Operand0), paramMap.get(Operation.Operand1));
            return result;
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }
    }

    private static Map<String, Object> resolveInput(String s) {
        if(s==null){
            System.out.println("输入不能为空！");
            return null;
        }
        
        String[] split = s.split("[+\\-*/]");
        if (split.length != 2){
            System.out.println("格式有误！");
            return null;
        }

        String operand0 = split[0].trim();
        String operand1 = split[1].trim();
        String operator = s.substring(split[0].length(), split[0].length() + 1);
        try {
            Double a = Double.valueOf(operand0);
            Double b = Double.valueOf(operand1);
            Map<String, Object> map = new HashMap<>();
            map.put(Operation.Operand0,a);
            map.put(Operation.Operand1,b);
            map.put(Operation.Operator, operator);
            return map;
        } catch (Exception e) {
            System.out.println("格式有误！" + e.getLocalizedMessage());
            return null;
        }

    }

}
