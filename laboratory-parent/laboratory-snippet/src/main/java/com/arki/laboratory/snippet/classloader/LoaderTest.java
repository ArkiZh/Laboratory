package com.arki.laboratory.snippet.classloader;

import java.io.IOException;
import java.io.InputStream;

public class LoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = LoaderTest.class.getClassLoader(); //sun.misc.Launcher$AppClassLoader
        System.out.println("Default class loader: " + classLoader.getClass());

        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf('.') + 1) + ".class";
                InputStream is = getClass().getResourceAsStream(fileName);
                if (is == null) {
                    return super.loadClass(name);
                }
                try {
                    byte[] bytes = new byte[is.available()];
                    is.read(bytes);
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        System.out.println("Custom class loader : " + myClassLoader.getClass());

        Class<?> clazz = myClassLoader.loadClass("com.arki.laboratory.snippet.classloader.LoaderTest");
        System.out.println("Class name loaded by custom class loader : " + clazz);
        System.out.println("Class name loaded by default class loader: " + LoaderTest.class);
        Object o = clazz.newInstance();
        System.out.println("Is custom loaded object instance of default LoaderTest? " + (o instanceof LoaderTest));

    }

}
