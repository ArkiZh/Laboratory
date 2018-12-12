package com.arki.laboratory.springboot.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MQTests {

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Autowired
    private RabbitMQReceiver rabbitMQReceiver;

    /**
     * 先启动应用，里面有方法监听队列，方法：
     * com.arki.laboratory.springboot.mq.RabbitMQReceiver#process(java.lang.String)
     * 再执行这个单元测试，向队列里发消息，监听方法会从队列里取消息打印到控制台
     */
    @Test
    public void rabbitMqTest() {
        rabbitMQSender.send("Hello from spring.");
    }
}
