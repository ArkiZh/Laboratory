package com.arki.laboratory.snippet;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecureRandomTest {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String s = generateRandomLetterString(60);
        System.out.println(s);
    }

    /**
     * Generate a length-specified letter string by SecureRandom.
     * @param length
     * @return
     */
    public static String generateRandomLetterString(int length) {
        String letterStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int originSize = letterStr.length();
        SecureRandom random;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(letterStr.charAt(random.nextInt(originSize)));
        }
        return sb.toString();
    }
}
