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
