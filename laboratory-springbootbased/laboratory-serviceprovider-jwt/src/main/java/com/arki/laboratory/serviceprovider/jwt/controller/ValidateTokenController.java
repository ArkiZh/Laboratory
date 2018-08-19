package com.arki.laboratory.serviceprovider.jwt.controller;

import com.arki.laboratory.serviceprovider.jwt.util.Constant;
import com.arki.laboratory.serviceprovider.jwt.util.JWTUtil;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

@RestController
public class ValidateTokenController {

    @RequestMapping("/validate")
    public String validateToken(@RequestParam Map<String, String> paramMap, HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin", "*");

        String jwt = paramMap.get("jwtTokenToValidate");
        if (jwt == null) {
            return "Haven't received param named by \"jwtTokenToValidate\"";
        }
        Object result = JWTUtil.verifyToken(jwt, Constant.secretKey);
        if (result instanceof DecodedJWT) {
            DecodedJWT decodedJWT = (DecodedJWT) result;
            Map<String, Claim> claims = decodedJWT.getClaims();
            Set<String> keySet = claims.keySet();
            StringBuilder sb = new StringBuilder();
            for (String key : keySet) {
                sb.append("<br>").append(key + " --> " + claims.get(key).asString());
                System.out.println(key + " --> " + claims.get(key).asString());
            }
            return sb.toString();
        } else {
            return result.toString();
        }
    }

}
