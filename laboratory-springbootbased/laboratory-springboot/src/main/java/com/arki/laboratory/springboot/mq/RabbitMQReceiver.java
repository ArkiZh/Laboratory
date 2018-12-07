package com.arki.laboratory.springboot.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "rabbitTestQueue")
public class RabbitMQReceiver {

    @RabbitHandler
    public void process(String content) {
        System.out.println("Received from rabbitTestQueue: " + String.valueOf(content));
    }
}
