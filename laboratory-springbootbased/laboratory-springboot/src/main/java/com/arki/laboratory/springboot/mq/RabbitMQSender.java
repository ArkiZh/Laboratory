package com.arki.laboratory.springboot.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(Object content) {
        this.amqpTemplate.convertAndSend("rabbitTestQueue", content);
    }
}
