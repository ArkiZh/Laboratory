package com.arki.laboratory.springboot.service;

public interface DemoSentinelService {
    String flowControl();

    String degradeControl();

    String paramFlowControl(int param);

    String paramFlowControlAnnotation(int param);
}
