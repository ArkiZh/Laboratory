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


//    public static void main(String[] args) {
//        String result = macCalculate("Zhang", HMAC_SHA256, "love");
//        System.out.println(result);
//    }

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
            String s = byteArrayToHexString(bytes);
            return s;
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Transfer byte array to hex string.
     *
     * @param bytes
     * @return
     */
    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int n = 0; bytes != null && n < bytes.length; n++) {
            String temp = Integer.toHexString(bytes[n] & 0XFF);
            System.out.println(bytes[n] +" --> " + temp);
            if (temp.length() == 1)
                sb.append('0');
            sb.append(temp);
        }
        return sb.toString();
    }

    private static String byteArrayToHexString1(byte[] bytes) {
        char[] hexCode = "0123456789abcdef".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int temp = bytes[i] & 0xFF;
            hexChars[i * 2] = hexCode[temp >>> 4];
            hexChars[i * 2 + 1] = hexCode[temp & 0xF];
        }
        return new String(hexChars);
    }

    public static void main(String[] args) {
        byte[] bytes = {-128,-2, -1, 0, 1, 2,127};
        String s = byteArrayToHexString(bytes);
        System.out.println(s);
        String s1 = byteArrayToHexString1(bytes);
        System.out.println(s1);

    }

}
