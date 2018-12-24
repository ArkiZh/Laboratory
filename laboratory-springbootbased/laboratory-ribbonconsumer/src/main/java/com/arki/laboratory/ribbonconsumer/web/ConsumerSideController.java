package com.arki.laboratory.ribbonconsumer.web;

import com.arki.laboratory.ribbonconsumer.sampler.MyAlwaysSampler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class ConsumerSideController {

    @Autowired
    private RestTemplate restTemplate;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public MyAlwaysSampler defaultSampler() {
        return new MyAlwaysSampler(true);
    }

    @RequestMapping("/consume/{serviceName}/{methodName}")
    public String consumeTargetService(@PathVariable String serviceName,@PathVariable String methodName) {
        logger.info("Method invoked: [{}]", "consumeTargetService");
        ResponseEntity<String> entity = restTemplate.getForEntity("http://" + serviceName + "/" + methodName, String.class);
        String body = entity.getBody();
        return body;
    }

}
