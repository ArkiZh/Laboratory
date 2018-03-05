package com.arki.activemq;

import com.arki.laboratory.common.Logger;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer {

    private static String brokerUrl = "tcp://192.168.222.140:61616";
    private static String username = "admin";
    private static String password = "admin";
    private static ActiveMQConnectionFactory connectionFactory;
    private static String queueName = "testQueue";
    private static int publishCounts = 1200;
    private static int receiveCounts = 6000;
    static {
        connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setUserName(username);
        connectionFactory.setPassword(password);
    }

    public static void main(String[] args) throws JMSException, InterruptedException {
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(queueName);
        MessageConsumer consumer = session.createConsumer(queue);
        for (int i = 0; i < receiveCounts; i++) {
            Message message = consumer.receive();
            String result = message == null ? "NULL" : ((TextMessage) message).getText();
            Logger.info("Receiving..." + i + "..." + result);
        }
        consumer.close();
        Logger.info("Consumer closed.");
        session.close();
        connection.close();
    }
}
