package com.arki.laboratory.common;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ArrayUtil {
    public static <T> String transferArrayToString(T[] a){
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i].toString()).append(',');
        }
        String s = sb.substring(0, sb.length() - 1);
        return s + "]";
    }
    public static <T> T[] copyArray(T[] a){

        int length = a.length;
        T[] t = (T[])Array.newInstance(a[0].getClass(), length);
        for (int i = 0; i < length; i++) {
            t[i] = a[i];
        }
        return t;
    }

    public static <T> List<T[]> cutLongArrayToShortArrays(T[] srcArray, int maxLengthOfShortArray) {
        if(srcArray==null) return null;
        List<T[]> t = new ArrayList<>();
        int counts = srcArray.length / maxLengthOfShortArray;
        int remainder = srcArray.length % maxLengthOfShortArray;
        for (int i = 0; i < counts; i++) {
            T[] temp = (T[]) Array.newInstance(srcArray[0].getClass(), maxLengthOfShortArray);
            for (int j = 0; j < maxLengthOfShortArray; j++) {
                temp[j] = srcArray[i * maxLengthOfShortArray + j];
            }
            t.add(temp);
        }
        if (remainder > 0) {
            T[] temp = (T[]) Array.newInstance(srcArray[0].getClass(), remainder);
            for (int j = 0; j < remainder; j++) {
                temp[j] = srcArray[counts * maxLengthOfShortArray + j];
            }
            t.add(temp);
        }
        return t;
    }

    public static void main(String[] args) {
        int N = 61;
        //Integer[] integers = new Integer[N];
        //for (int i = 0; i < N; i++) {
        //    integers[i] = i;
        //}
        //Integer[] a = copyArray(integers);
        String[] s = new String[N];
        for (int i = 0; i < N; i++) {
            s[i] = i+"s";
        }
        List<String[]> list = cutLongArrayToShortArrays(s, 30);
        for (int i = 0; i < list.size(); i++) {
            String[] array = list.get(i);
            Logger.info(transferArrayToString(array));
        }
    }

}
