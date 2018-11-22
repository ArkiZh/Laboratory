package com.arki.laboratory.springboot.controller;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
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
        initDegradeRule();
    }

    private static void initFlowQpsRule() {
        List<FlowRule> rules = new ArrayList<FlowRule>();
        FlowRule rule = new FlowRule();
        rule.setResource("flowControl");
        // set limit qps to 2
        rule.setCount(2);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    private static void initDegradeRule() {
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule();
        rule.setResource("degradeControl");
        // set threshold rt(response time), 10 ms
        rule.setCount(5);
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        // set time window in seconds
        rule.setTimeWindow(10);
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }

    @Autowired
    private DemoSentinelService sentinelService;

    @RequestMapping("")
    public String info() {
        return "Here are sentinel tests.";
    }

    @RequestMapping("/printRules")
    public String printRules() {
        List<FlowRule> flowRules = FlowRuleManager.getRules();
        List<DegradeRule> degradeRules = DegradeRuleManager.getRules();
        return JSON.toJSONString(flowRules) + JSON.toJSONString(degradeRules);
    }

    @RequestMapping("/flowControl")
    public String flowControl() {
        String result = this.sentinelService.flowControl();
        return "FlowControl result: " + result;
    }

    @RequestMapping("/degradeControl")
    public String degradeControl() {
        String result = this.sentinelService.degradeControl();
        return "DegradeControl result: " + result;
    }

}
