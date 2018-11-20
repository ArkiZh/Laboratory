package com.arki.laboratory.snippet.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试阿里推出的熔断限流组件Sentinel。
 * 注解方式的使用需要AOP支持，在这里不能测试，转到Laboratory/laboratory-springbootbased/laboratory-springboot中进行测试。
 */
public class SentinelTest {
    public static void main(String[] args) {
    }


    @Test
    public void helloTrueFalse() {
        initFlowRule();
        //定义资源 返回布尔值
        int count = 0;
        while (true) {
            boolean canExecute = SphO.entry("HelloWorld");
            if (canExecute) {
                try{
                    System.out.println("Hello world! " + (++count));
                }finally {
                    SphO.exit();
                }
            }else{
                count = 0;
                System.out.print(".");
            }
        }
    }

    @Test
    public void helloBlockException() {
        initFlowRule();
        //定义资源 抛异常
        int count = 0;
        while (true) {
            Entry entry = null;
            try {
                entry = SphU.entry("HelloWorld");
                System.out.println("Hello world! "+(++count));
            } catch (BlockException e) {
                count=0;
                System.out.print(".");
            }finally {
                if (entry != null) {
                    entry.exit();
                }
            }
        }
    }

    public static void initFlowRule() {
        //https://github.com/alibaba/Sentinel/wiki/%E4%BB%8B%E7%BB%8D
        //定义规则
        List<FlowRule> rules = new ArrayList<>();
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("HelloWorld");
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setCount(20);
        rules.add(flowRule);
        FlowRuleManager.loadRules(rules);
    }
}
