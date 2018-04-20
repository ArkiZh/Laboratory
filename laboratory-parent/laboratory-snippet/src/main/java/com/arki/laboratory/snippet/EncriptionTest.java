package com.arki.laboratory.snippet;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class EncriptionTest {
    public static void main(String[] args) {
        String result = sha256_HMAC("Zhang", "123");
        System.out.println(result);
    }

    //Hash Message Authentication Code
    public static String sha256_HMAC(String message, String secretKey){
        String hmacName = "HmacSHA256";
        Mac hmacSHA256 = null;
        try {
            hmacSHA256 = Mac.getInstance(hmacName);
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), hmacName);
            hmacSHA256.init(keySpec);
            byte[] bytes = hmacSHA256.doFinal(message.getBytes());
            String hexString = byteArrayToHexString(bytes);
            return hexString;
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将加密后的字节数组转换成字符串
     * @param b
     * @return
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b!=null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toLowerCase();
    }

}
