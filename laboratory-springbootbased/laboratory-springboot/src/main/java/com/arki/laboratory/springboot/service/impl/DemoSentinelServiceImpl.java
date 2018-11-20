package com.arki.laboratory.springboot.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.arki.laboratory.springboot.service.DemoSentinelService;
import org.springframework.stereotype.Service;

@Service
public class DemoSentinelServiceImpl implements DemoSentinelService {

    @Override
    @SentinelResource(value = "flowControl",blockHandler = "blockHandler")
    public String flowControl() {
        System.out.println("Normal...");
        return "success!";
    }

    public String blockHandler(BlockException e) {
        System.out.println(e.getClass());
        return "Blocked!";
    }
}
