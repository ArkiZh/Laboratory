package com.arki.laboratory.snippet.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.*;

public class JsonWebTokenTest {


    public static void main(String[] args) {
        String token = createToken();
        System.out.println(token);
        //token = "abc";//com.auth0.jwt.exceptions.JWTDecodeException: The token was expected to have 3 parts, but got 1.
        //token = "a.b.c";//com.auth0.jwt.exceptions.JWTDecodeException: The string '' doesn't have a valid JSON format.
        /*token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJuYW1lIjoiYXJraSIsIndlYXRoZXIiOiJyYWlueSIsImlzcyI6ImFkbWluIiwiZmVlbGluZyI6Imdvb2QiLCJleHAiOjE1MzQ2NzU4MDAsImlhdCI6MTUzNDY3NTc5OH0" +
                ".LeXh4M5oAwH--woR4ErypD_tAc4fx2xnNZ9FrW_02MO";*/
        //com.auth0.jwt.exceptions.SignatureVerificationException: The Token's Signature resulted invalid when verified using the Algorithm: HmacSHA256
        DecodedJWT jwt = verifyToken(token);
        if (jwt == null) {
            System.out.println("Token has expired.");
        } else {
            System.out.println("Verified successfully.");
            Map<String, Claim> claims = jwt.getClaims();
            Set<String> keySet = claims.keySet();
            for (String key : keySet) {
                System.out.println(key + " --> " + claims.get(key).asString());
            }
        }
    }


    public static DecodedJWT verifyToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("key***")).build();
        try {
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
        } catch (TokenExpiredException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static String createToken() {
        Map<String, Object> headClaims = new HashMap<>();
        headClaims.put("alg","HS256");
        headClaims.put("typ","JWT");

        Calendar expireDate = Calendar.getInstance();
        expireDate.add(Calendar.SECOND,2);

        String token = JWT.create()
                .withHeader(headClaims)
                .withClaim("name", "arki")
                .withClaim("weather", "rainy")
                .withClaim("feeling", "good")
                .withExpiresAt(expireDate.getTime())
                .withIssuedAt(new Date())
                .withIssuer("admin")
                .sign(Algorithm.HMAC256("key***"));
        return token;
    }
}
