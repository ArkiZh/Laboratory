package com.arki.laboratory.springboot.controller;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.arki.laboratory.springboot.service.DemoSentinelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sentinel")
public class DemoSentinelController {

    static {
        initFlowQpsRule();
    }

    private static void initFlowQpsRule() {
        List<FlowRule> rules = new ArrayList<FlowRule>();
        FlowRule rule1 = new FlowRule();
        rule1.setResource("flowControl");
        // set limit qps to 2
        rule1.setCount(2);
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rules.add(rule1);
        FlowRuleManager.loadRules(rules);
    }

    @Autowired
    private DemoSentinelService sentinelService;

    @RequestMapping("")
    public String info() {
        return "Here are sentinel tests.";
    }

    @RequestMapping("/flowControl")
    public String flowControl() {
        String result = this.sentinelService.flowControl();
        return "FlowControl result: " + result;
    }


    @RequestMapping("/printRules")
    public String printRules() {
        List<FlowRule> rules = FlowRuleManager.getRules();
        return JSON.toJSONString(rules);
    }

}
