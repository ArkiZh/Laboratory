package com.arki.laboratory.common;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
    public static <T> List<List<T>> cutLongListToShortLists(List<T> srcList, int maxLengthOfShortList) {
        if(srcList==null) return null;
        List<List<T>> retval = new ArrayList<>();
        int counts = srcList.size() / maxLengthOfShortList;
        int remainder = srcList.size() % maxLengthOfShortList;
        for (int i = 0; i < counts; i++) {
            List<T> temp = new ArrayList<>();
            for (int j = 0; j < maxLengthOfShortList; j++) {
                temp.add(srcList.get(i * maxLengthOfShortList + j));
            }
            retval.add(temp);
        }
        if (remainder > 0) {
            List<T> temp = new ArrayList<>();
            for (int j = 0; j < remainder; j++) {
                temp.add(srcList.get(counts * maxLengthOfShortList + j));
            }
            retval.add(temp);
        }
        return retval;
    }

    public static void main(String[] args) {
        int N = 100;
        List<String> src = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            src.add("S" + i);
        }

        List<List<String>> lists = cutLongListToShortLists(src, 30);
        for (int i = 0; i < lists.size(); i++) {
            List<String> strings = lists.get(i);
            Logger.info(strings.toString());
        }
    }
}
