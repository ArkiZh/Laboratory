package com.arki.laboratory.snippet;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;

public class LoadPropertiesTest {

    @Test
    public void addMethodTest() {
        Class<?> classForStatic = new Object() {
            public Class<?> getClassForStatic() {
                return this.getClass();
            }
        }.getClassForStatic();
        System.out.println(classForStatic);
    }

    @Test
    public void readProperties() {
        ResourceBundle resourceBundle = getResourceBundle();
        Set<String> keySet = resourceBundle.keySet();
        for (String s : keySet) {
            System.out.println(s + " -> " + resourceBundle.getString(s));
        }
    }


    private ResourceBundle getResourceBundle() {
        return Resource.bundle;
    }
    private static class Resource {
        private static ResourceBundle bundle;

        static {
            try {
                String path = getClassForStatic().getProtectionDomain().getCodeSource().getLocation().toString();
                if (path.startsWith("zip") || path.startsWith("vfs")) { // 当class文件在war中时，此时返回zip:D:/...这样的路径
                    path = path.substring(4);
                } else if (path.startsWith("file")) { // 当class文件在class文件中时，此时返回file:/D:/...这样的路径
                    path = path.substring(5);
                } else if (path.startsWith("jar")) { // 当class文件在jar文件里面时，此时返回jar:file:/D:/...这样的路径
                    path = path.substring(9);
                }
                if ((path.charAt(0) == '/') && (path.indexOf(":") >= 0)) {
                    path = path.substring(1);
                }
                path = URLDecoder.decode(path, "utf-8");
                //int index = path.indexOf("WEB-INF/lib");
                //InputStream is = new FileInputStream(new File(path.substring(0, index) + "WEB-INF/configuration/virtualPlatform.properties"));
                FileInputStream is = new FileInputStream(new File(path + "LoadProperties.properties"));
                bundle = new PropertyResourceBundle(is);
            } catch (IOException e) {
                bundle = null;
                e.printStackTrace();
            }
        }

        private static Class<?> getClassForStatic() {
            return new Object() {
                public Class<?> getClassForStatic() {
                    return this.getClass();
                }
            }.getClassForStatic();
        }
    }
}
