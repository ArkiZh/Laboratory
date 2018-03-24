package com.arki.laboratory.snippet;

import com.arki.laboratory.common.Logger;
import org.junit.Test;

import java.util.HashMap;

public class ThreadLocalTest {

    @Test
    public void mapTest() {
        HashMap<Object, Object> map = new HashMap<>();
        Person person = new Person();
        person.setName("zh");
        Person person1 = new Person();
        person1.setName("ang");

        map.put(person, person);
        map.put(person1, person1);
        Logger.info(map.toString());
    }

    @Test
    public void threadLocalTest() {
        ThreadLocal<Person> threadLocal = new ThreadLocal<>();
        Person boy = new Person();
        boy.setName("Li Lei");
        Person girl = new Person();
        girl.setName("Han Mei");
        threadLocal.set(boy);
        Logger.info(threadLocal.get().toString());
        threadLocal.set(girl);
        Logger.info(threadLocal.get().toString());
    }


}


class Person {
    private String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}