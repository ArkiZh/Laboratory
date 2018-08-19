package com.arki.laboratory.serviceprovider.jwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.*;

public class JWTUtil {

    /**
     * 校验JWT token。 校验失败返回字符串，成功返回DecodedJWT对象。
     * @param token
     * @param secretKey
     * @return
     */
    public static Object verifyToken(String token,String secretKey) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
        try {
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
        } catch (Exception e) {
            return e.getClass().getSimpleName()+": " + e.getLocalizedMessage();
        }
    }

    public static String createToken(Map<String, Object> paramMap, String secretKey) {

        if (paramMap == null) {
            throw new IllegalArgumentException("Cannot be null!");
        }
        Map<String, Object> headClaims = new HashMap<>();
        headClaims.put("alg", "HS256");
        headClaims.put("typ", "JWT");

        Calendar expireDate = Calendar.getInstance();
        expireDate.add(Calendar.SECOND, 60);

        JWTCreator.Builder builder = JWT.create()
                .withHeader(headClaims)
                .withExpiresAt(expireDate.getTime())
                .withIssuedAt(new Date())
                .withIssuer("JWT-Authority-APP");

        Set<String> keySet = paramMap.keySet();
        for (String key : keySet) {
            builder.withClaim(key, String.valueOf(paramMap.get(key)));
        }

        String token = builder.sign(Algorithm.HMAC256(secretKey));
        return token;
    }
}
