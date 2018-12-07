package com.arki.laboratory.springboot.web;


import com.arki.laboratory.springboot.mq.RabbitMQReceiver;
import com.arki.laboratory.springboot.mq.RabbitMQSender;
import org.hamcrest.Matchers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Autowired
    private RabbitMQReceiver rabbitMQReceiver;

    @Test
    public void hello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("Hello world!")));
    }

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
