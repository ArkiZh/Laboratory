package com.arki.laboratory.serviceprovider.web;

import com.arki.laboratory.serviceprovider.util.SpringUtil;
import com.arki.laboratory.serviceprovider.bo.ApplicationInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello world! From: " + getAppInfo();
    }

    @RequestMapping("/appinfo")
    public ApplicationInfo getAppInfo() {
        return SpringUtil.getBean(ApplicationInfo.class);
    }
}
