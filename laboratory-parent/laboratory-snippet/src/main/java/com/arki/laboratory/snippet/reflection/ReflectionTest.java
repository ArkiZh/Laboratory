package com.arki.laboratory.snippet.reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ReflectionTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException {

        Bean bean = new Bean<AtomicBoolean>();

        typeTest(bean);

        transferObjectAttributeToUpperCase(bean);

        System.out.println(bean.b);

    }

    /**
     * 将bean属性中的字符串转为大写（转换范围：声明为字符串、字符串List、字符串数组的成员变量）
     * @param t
     * @throws IllegalAccessException
     */
    private static void transferObjectAttributeToUpperCase(Object t) throws IllegalAccessException {
        if(t==null) throw new IllegalArgumentException();
        Field[] declaredFields = t.getClass().getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
            Class<?> type = declaredField.getType();
            declaredField.setAccessible(true);
            Object o = declaredField.get(t);
            if (o != null) {
                if (type == String.class) {
                    declaredField.set(t, o.toString().toUpperCase());
                }else if (List.class.isAssignableFrom(type)) {
                    List list = (List) o;
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(j) instanceof String) {
                            list.set(j, ((String) list.get(j)).toUpperCase());
                        }
                    }
                } else if (type.isArray()) {
                    //数组类型不太好转 强转只能转为声明的类型 如int[]只能强转为int[] 不能是Integer[]、Object[]
                    int length = Array.getLength(o);
                    for (int j = 0; j < length; j++) {
                        Object e = Array.get(o, j);
                        if (e instanceof String) {
                            Array.set(o, j, ((String) e).toUpperCase());
                        }
                    }
                }
            }

        }
    }

    /**
     * 测试getGenericType、getType获取的类型区别
     * @param o
     * @return
     * @throws ClassNotFoundException
     */
    public static Object typeTest(Object o) throws ClassNotFoundException {
        Field[] declaredFields = o.getClass().getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
            Type genericType = declaredField.getGenericType();
            Class<?> type = declaredField.getType();
            System.out.println("属性名：" + declaredField.getName());
            System.out.println("带泛型声明：" + genericType);//带泛型 java.util.ArrayList<com.arki.laboratory.snippet.reflection.ReflectTest$Ball> 及 T
            System.out.println("Class类：" + type);//Class类 class java.util.ArrayList 及 class java.lang.Object
            System.out.println("是否是数组：" + type.isArray()); //判断是否是数组
            System.out.println("是否是int数组：" + (type == Class.forName("[I")));//判断是否是int数组
            System.out.println("是否是某个List的子类：" + List.class.isAssignableFrom(type)); //判断是否是某个类的子类
            System.out.println("是否是某个Iterable的子类：" + Iterable.class.isAssignableFrom(type));//判断是否可遍历
            System.out.println();
        }
        return o;
    }

    public static class Bean<T>{
        private int a;
        private String b;
        private Object c;
        private ArrayList<Ball> d;
        private T e;
        private int[] f;
        private Object[] g = new Object[]{"abc", 1};
    }

    public static class Ball{

    }

}
