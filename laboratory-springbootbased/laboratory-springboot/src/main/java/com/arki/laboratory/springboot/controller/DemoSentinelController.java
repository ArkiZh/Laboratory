package com.arki.laboratory.springboot.controller;

import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowItem;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.arki.laboratory.springboot.service.DemoSentinelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/sentinel")
public class DemoSentinelController {

    static {
        initFlowQpsRule();
        initDegradeRule();
        initParamFlowRule();
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

    private static void initParamFlowRule() {
        final int PARAM_A = 5;
        ParamFlowRule paramFlowRule = new ParamFlowRule("paramFlowControl")
                .setParamIdx(0)
                .setCount(2);
        // 针对 int 类型的参数 PARAM_A，单独设置限流 QPS 阈值为 10，而不是全局的阈值 5.
        ParamFlowItem paramFlowItem = new ParamFlowItem()
                .setObject(String.valueOf(PARAM_A))
                .setClassType(int.class.getName())
                .setCount(1);
        paramFlowRule.setParamFlowItemList(Collections.singletonList(paramFlowItem));
        ParamFlowRuleManager.loadRules(Collections.singletonList(paramFlowRule));
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
        List<ParamFlowRule> paramFlowRules = ParamFlowRuleManager.getRules();
        return JSON.toJSONString(flowRules)
                + JSON.toJSONString(degradeRules)
                + JSON.toJSONString(paramFlowRules);
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

    /**
     * 经验证 ParamFlowRule不支持注解形式，需要自己实现<br>
     * ParamFlowRule不在sentinel-core里
     * @param param
     * @return
     */
    @RequestMapping("paramFlowControlAnnotation")
    public String paramFlowControlAnnotation(int param) {
        String result = this.sentinelService.paramFlowControlAnnotation(param);
        return "ParameterFlowControl reuslt: " + result;
    }

    @RequestMapping("paramFlowControl")
    public String paramFlowControl(int param) {
        String result = this.sentinelService.paramFlowControl(param);
        return "ParameterFlowControl reuslt: " + result;
    }

}
