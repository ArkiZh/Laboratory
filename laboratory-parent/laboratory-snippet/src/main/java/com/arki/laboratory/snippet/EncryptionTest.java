package com.arki.laboratory.snippet;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * HMAC: Hash-based Message Authentication Code
 * HMAC TYPE               LENGTH of BIT
 * HmacMD5                 128
 * HmacSHA1                160
 * HmacSHA256              256
 * HmacSHA384              384
 * HmacSHA512              512
 */
public class EncryptionTest {

    public static final String HMAC_MD5 = "HmacMD5";
    public static final String HMAC_SHA1 = "HmacSHA1";
    public static final String HMAC_SHA256 = "HmacSHA256";
    public static final String HMAC_SHA384 = "HmacSHA384";
    public static final String HMAC_SHA512 = "HmacSHA512";


    public static void main(String[] args) {
        System.out.println("=============== Test calculating mac(message authentication code):");
        String macType = HMAC_SHA256;
        String key = "HelloKey!";
        String message = "HelloWorld!";
        String macResult = macCalculate(message, macType, key);
        System.out.println("Mac algorithm: "+macType);
        System.out.println("Secret key: " + key);
        System.out.println("Message: " + message);
        System.out.println("Mac: " + macResult);

        System.out.println("=============== Test generating secret key:");
        byte[] bytes = generateSecretKey(macType);
        System.out.println("Mac algorithm: " + macType);
        System.out.println("Generate secret key: " + convertByteArrayToString(bytes));
        String hexStr = byteArrayToHexStringByMappingChar(bytes);
        System.out.println("Convert bytes to hex string: " + hexStr);
        System.out.println("Convert hex string back to bytes: " + convertByteArrayToString(hexStringToByteArray(hexStr)));
    }

    /**
     * Generate secret key for the specified mac type.
     * @param macType
     * @return
     */
    public static byte[] generateSecretKey(String macType) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(macType);
            SecretKey key = keyGenerator.generateKey();
            byte[] keyBytes = key.getEncoded();
            return keyBytes;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Calculate mac according to the params.
     * @param message The message to be encrypted.
     * @param macType The mac type supported by <code>javax.crypto.Mac</code>
     * @param secretKey The secret key for hmac.
     * @return
     */
    public static String macCalculate(String message, String macType, String secretKey) {
        SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), macType);
        try {
            Mac mac = Mac.getInstance(macType);
            mac.init(key);
            byte[] bytes = mac.doFinal(message.getBytes());
            String s = byteArrayToHexStringByJdk(bytes);
            return s;
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Convert byte array to hex string.
     *
     * @param bytes
     * @return
     */
    private static String byteArrayToHexStringByJdk(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int n = 0; bytes != null && n < bytes.length; n++) {
            // '&0xFF' to ensure the lower 8 bits not be changed when negative byte is converted to integer.
            String temp = Integer.toHexString(bytes[n] & 0XFF);
            // If the length of hex string is 1, append an extra "0" before {temp},
            // to ensure each byte be converted to a string length of 2.
            if (temp.length() == 1) sb.append('0');
            sb.append(temp);
        }
        return sb.toString();
    }

    /**
     * Convert byte array to hex string.
     * @param bytes
     * @return
     */
    private static String byteArrayToHexStringByMappingChar(byte[] bytes) {
        char[] hexCode = "0123456789abcdef".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int temp = bytes[i] & 0xFF;
            hexChars[i * 2] = hexCode[temp >>> 4];
            hexChars[i * 2 + 1] = hexCode[temp & 0xF];
        }
        return new String(hexChars);
    }

    /**
     * Contert hex string to byte array.
     * @param hexString
     * @return
     */
    private static byte[] hexStringToByteArray(String hexString) {
        if (hexString == null || hexString.length() % 2 != 0) {
            return null;
        }
        byte[] bytes = new byte[hexString.length() / 2];
        String lowerCase = hexString.toLowerCase();
        String hexBaseStr = "0123456789abcdef";
        for (int i = 0; i < lowerCase.length(); i += 2) {
            // Convert the higher 4 bits to integer.
            char c1 = lowerCase.charAt(i);
            int int1 = hexBaseStr.indexOf(c1);
            // Convert the lower 4 bits to integer.
            char c2 = lowerCase.charAt(i + 1);
            int int2 = hexBaseStr.indexOf(c2);
            // Combine the higher 4 bits and lower 4 bits to a byte.
            bytes[i / 2] = (byte) (((int1 << 4) | (int2 & 0xF)) & 0xFF);
        }
        return bytes;
    }

    private static String convertByteArrayToString(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < bytes.length; i++) {
            sb.append(bytes[i]).append(',');
        }
        String s = sb.substring(0, sb.length() - 1);
        return s + "]";
    }
}
