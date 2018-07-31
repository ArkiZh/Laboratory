package com.arki.laboratory.ribbonconsumer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class ConsumerSideController {


    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/consume/{serviceName}/{methodName}")
    public String consumeTargetService(@PathVariable String serviceName,@PathVariable String methodName) {
        ResponseEntity<String> entity = restTemplate.getForEntity("http://" + serviceName + "/" + methodName, String.class);
        String body = entity.getBody();
        return body;
    }

}
