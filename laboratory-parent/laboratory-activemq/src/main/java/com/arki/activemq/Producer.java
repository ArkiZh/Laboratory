package com.arki.activemq;

import com.arki.laboratory.common.Logger;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {

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
        MessageProducer producer = session.createProducer(queue);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        for (int i = 0; i < publishCounts; i++) {
            Thread.sleep(1000);
            Logger.info("Sending message..." + i);
            producer.send(session.createTextMessage("Tick-tick..."+i));
        }
        producer.close();
        Logger.info("Producer closed.");
        session.close();
        connection.close();
    }
}
