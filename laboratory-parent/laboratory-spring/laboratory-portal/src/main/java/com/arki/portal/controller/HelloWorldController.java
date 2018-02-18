package com.arki.portal.controller;


import com.arki.laboratory.common.Logger;
import com.arki.portal.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/helloworld")
public class HelloWorldController {

    @Autowired
    HelloWorldService helloWorldService;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @ResponseBody
    public String hello(String name) {
        Logger.info("Parameter: name=[{}]", name);
        String result = this.helloWorldService.hello(name);
        return result;
    }

}
