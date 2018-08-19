package com.arki.laboratory.serviceprovider.jwt.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constant {


    public static String secretKey = null;

    @Value("${app.config.secretkey}")
    public void setSecretKey(String secretKey) {
        Constant.secretKey = secretKey;
    }
}
