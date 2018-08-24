package com.arki.laboratory.snippet;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SingletonMapTest {
    private static final Map<String, String> map = new HashMap<>();

    @Test
    public void testCapacityOfMap() {
        for (int i = 0; i < 1000000; i++) {
            map.put(String.valueOf(i), String.valueOf(i));
        }
        final Map<String, String> tempMap = new HashMap<>();
        System.out.println(tempMap);
        tempMap.put("1", "2");
        Map<String,String> resultMap = modifyFinalMap(tempMap);
        System.out.println(resultMap);
    }

    private Map<String, String> modifyFinalMap(Map<String, String> tempMap) {
        //此时修改tempMap不会改变调用源中的引用。
        tempMap = new HashMap<>();
        tempMap.put("a", "b");
        return tempMap;
    }

}
