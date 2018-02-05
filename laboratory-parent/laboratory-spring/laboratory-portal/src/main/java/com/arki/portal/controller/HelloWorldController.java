package com.arki.portal.controller;


import com.arki.portal.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/helloworld")
public class HelloWorldController {

    @Autowired
    HelloWorldService helloWorldService;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(String name) {
        String result = this.helloWorldService.hello(name);
        return result;
    }

}
