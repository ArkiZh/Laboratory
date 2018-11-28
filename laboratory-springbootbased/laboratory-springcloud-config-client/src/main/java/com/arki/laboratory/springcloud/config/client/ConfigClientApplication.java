package com.arki.laboratory.springcloud.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * $ curl localhost:8080/actuator/refresh -d {} -H "Content-Type: application/json"
 * 发送POST请求到localhost:8080/actuator/refresh来重新拉取config server的配置（需要设置请求头Content-Type: application/json）
 */
@SpringBootApplication
@RestController
public class ConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    @RequestMapping("/")
    public String home() {
        return "Hello world!";
    }
}

@RestController
@RefreshScope
class MessageRestController{
    @Value("${message: hello default}")
    private String message;

    @RequestMapping("/message")
    public Object getMessage() {
        return this.message;
    }
}
