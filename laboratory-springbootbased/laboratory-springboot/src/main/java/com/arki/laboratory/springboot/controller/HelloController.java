package com.arki.laboratory.springboot.controller;

import com.arki.laboratory.springboot.bo.ApplicationInfo;
import com.arki.laboratory.springboot.bo.ApplicationProfile;
import com.arki.laboratory.springboot.util.SpringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello world!";
    }

    @RequestMapping("/appinfo")
    public ApplicationInfo getAppInfo() {
        return SpringUtil.getBean(ApplicationInfo.class);
    }

    @RequestMapping("/profile")
    public ApplicationProfile getAppProfile() {
        return SpringUtil.getBean(ApplicationProfile.class);
    }
}
