package com.arki.laboratory.authority.jwt.controller;


import com.arki.laboratory.authority.jwt.bean.UserInfo;
import com.arki.laboratory.authority.jwt.util.JWTUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class JWTController {

    private final String secretKey = "helloworld";

    @RequestMapping("/login")
    private String loginWithPassword(@RequestParam Map<String, String> paraMap) {
        String username = paraMap.get("username");
        String password = paraMap.get("password");

        String jwt;
        if ("hello".equals(username) && "world".equals(password)) {
            jwt = generateJwt(username);
        }else{
            jwt = "验证不通过";
        }
        return jwt;
    }

    private UserInfo validateLoginInfo(String username, String password) {
        boolean result = "hello".equals(username) && "world".equals(password);
        UserInfo userInfo = null;
        if (result) {
            userInfo = new UserInfo();
            userInfo.setUsername(username);
            userInfo.setAddress("Earth");
            userInfo.setAge(200);
        }
        return userInfo;
    }
    private String generateJwt(String username) {
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("username", username);
        String token = JWTUtil.createToken(paraMap, secretKey);
        return token;
    }

}
