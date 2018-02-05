package com.arki.portal.service;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {

    public String hello(String name){
        return "Hello " + name;
    }

}
