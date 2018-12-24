package com.arki.laboratory.serviceprovider.web;

import com.arki.laboratory.serviceprovider.sampler.MyAlwaysSampler;
import com.arki.laboratory.serviceprovider.util.SpringUtil;
import com.arki.laboratory.serviceprovider.bo.ApplicationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public MyAlwaysSampler defaultSampler() {
        return new MyAlwaysSampler(true);
    }

    @RequestMapping("/hello")
    public String hello(){
        logger.info("Method invoked: [{}]", "hello");
        return "Hello world! From: " + getAppInfo();
    }

    @RequestMapping("/appinfo")
    public ApplicationInfo getAppInfo() {
        logger.info("Method invoked: [{}]", "getAppInfo");
        return SpringUtil.getBean(ApplicationInfo.class);
    }
}
