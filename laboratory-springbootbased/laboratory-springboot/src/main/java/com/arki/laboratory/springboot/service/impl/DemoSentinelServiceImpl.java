package com.arki.laboratory.springboot.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.arki.laboratory.springboot.service.DemoSentinelService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DemoSentinelServiceImpl implements DemoSentinelService {

    @Override
    @SentinelResource(value = "flowControl",blockHandler = "blockHandler")
    // blockHandler / blockHandlerClass: blockHandler 对应处理 BlockException 的函数名称，可选项。若未配置，则将 BlockException 直接抛出。
    // blockHandler 函数访问范围需要是 public，返回类型需要与原方法相匹配，参数类型需要和原方法相匹配并且最后加一个额外的参数，类型为 BlockException。
    // blockHandler 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 blockHandlerClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
    public String flowControl() {
        System.out.println("Normal...");
        return "success!";
    }

    @Override
    @SentinelResource(value = "degradeControl", blockHandler = "blockHandler", fallback = "fallback")
    // fallback: fallback 函数名称，可选项，仅针对降级功能生效（DegradeException）。
    // fallback 函数的访问范围需要是 public，参数类型和返回类型都需要与原方法相匹配，并且需要和原方法在同一个类中。
    public String degradeControl() {
        Random random = new Random();
        int sleepTime = random.nextInt(21) + 1;
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Have slept for " + sleepTime + " ms.";
    }

    public String blockHandler(BlockException e) {
        System.out.println(e.getClass());
        return "Blocked!";
    }

    public String fallback(BlockException e) {
        return "Fallback!";
    }
}
