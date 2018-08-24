package com.arki.laboratory.snippet;

import org.junit.Test;

import java.util.HashSet;

public class IteratorTest {
    @Test
    public void iteratorTest() {
        HashSet<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        for (String s : set) { //java.util.ConcurrentModificationException
            set.remove(s);
        }
        System.out.println(set);
    }
}
