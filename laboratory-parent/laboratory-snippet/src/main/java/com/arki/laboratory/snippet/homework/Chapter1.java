package com.arki.laboratory.snippet.homework;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class Chapter1 {
    public static void main(String[] args) {

    }

    @Test
    public void test1_1() {
        Map<String, Long> timeMap = new LinkedHashMap<>();
        timeMap.put("1 second", (long) 1000);
        timeMap.put("1 minute", (long)1000 * 60);
        timeMap.put("1 hour"  , (long)1000 * 60 * 60);
        timeMap.put("1 day"   , (long)1000 * 60 * 60 * 24);
        timeMap.put("1 month", (long) 1000 * 60 * 60 * 24 * 30);
        timeMap.put("1 year"  , (long)1000 * 60 * 60 * 24 * 30 * 365);
        timeMap.put("1 century", (long) 1000 * 60 * 60 * 24 * 30 * 365 * 100);
        Long[] timeArray = new Long[timeMap.size()];
        int index = 0;
        for (Map.Entry<String, Long> entry : timeMap.entrySet()) {
            timeArray[index++] = entry.getValue();
            System.out.println(String.format("%0$-10s = %2$,25d ms", entry.getKey(), entry.getValue()));
        }
        System.out.println("lg(n) <=time");
        for (Map.Entry<String, Long> entry : timeMap.entrySet()) {
            System.out.println(Math.pow(2,entry.getValue()));
        }
        System.out.println("根号n <=time");
        for (Map.Entry<String, Long> entry : timeMap.entrySet()) {
            System.out.println(Math.pow(entry.getValue(),2));
        }
        System.out.println("nlg(n) <=time");
        System.out.println((Math.log(Long.MAX_VALUE)/Math.log(2)));
        long a = 1;
        for (int i = 0; i < 62; i++) {
            a = 2 * a;
        }
        System.out.println(String.format("%,d", Long.MAX_VALUE));
        System.out.println(String.format("%,d", a));
        /*index = 0;
        long n = 1;
        while (true) {
            double v = n * (Math.log(n) / Math.log(2));
            Long aLong = timeArray[index];
            if (v >= aLong) {
                System.out.println(String.format("当n=%25d时，nlg(n)=%f >= %d", n, v, aLong));
                index++;
            }
            n++;
            if(index>timeArray.length-1) break;
        }*/
        System.out.println("n平方<=time");
        for (Map.Entry<String, Long> entry : timeMap.entrySet()) {
            System.out.println(Math.sqrt(entry.getValue()));
        }
        System.out.println("n立方<=time");
        for (Map.Entry<String, Long> entry : timeMap.entrySet()) {
            System.out.println(Math.pow(entry.getValue(), 1.0 / 3));
        }
        System.out.println("2的n次方<=time");
        for (Map.Entry<String, Long> entry : timeMap.entrySet()) {
            System.out.println(Math.log(entry.getValue())/Math.log(2));
        }
        System.out.println("n!<=time");
        int n1 = 1;
        long total1 = 1;
        index = 0;
        while (true) {
            total1 = total1 * n1;
            Long bLong = timeArray[index];
            if (total1 > bLong) {
                System.out.println(String.format("当n=%2d时，nlg(n)=%,d > %,d", n1, total1, bLong));
                index++;
            }
            n1++;
            if (index > timeArray.length - 1) {
                break;
            }
        }
    }
}
