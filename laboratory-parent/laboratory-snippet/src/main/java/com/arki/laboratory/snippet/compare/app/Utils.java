package com.arki.laboratory.snippet.compare.app;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Utils {
    public static String calculateMD5(File file) {
        String md5 = null;
        if (file.exists() && file.isFile()) {
            try {
                long a = System.currentTimeMillis();
                FileInputStream fis =  new FileInputStream(file);
                md5 = DigestUtils.md2Hex(fis);
                long b = System.currentTimeMillis() - a;
                if (b > 1000) {
                    System.out.println("MD5 cost ms: " + b + " Size(MB):" + file.length() / (1024 * 1024.0) + " Path: " + file.getCanonicalPath());
                }
                fis.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return md5;
    }
}
