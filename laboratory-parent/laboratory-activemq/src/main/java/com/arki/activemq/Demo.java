package com.arki.activemq;

import com.arki.laboratory.common.Logger;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * 192.168.222.140_ACTIVEMQ
 * ActiveMQ端口号：61616。
 * ActiveMQ路径：
 * /usr/local/bin/mq/apache-activemq-5.11.3。
 * ActiveMQ启动方式：
 * /usr/local/bin/mq/apache-activemq-5.11.3/bin/activemq start。
 * ActiveMQ访问网址：http://192.168.222.140:8161/admin
 * 默认用户名/密码：admin/admin。
 */
public class Demo {

    private static String brokerUrl = "tcp://192.168.222.140:61616";
    private static String username = "admin";
    private static String password = "admin";
    private static ActiveMQConnectionFactory connectionFactory;
    private static String queueName = "testQueue";
    private static int publicCounts = 1200;
    private static int receiveCounts = 6000;
    static {
        connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setUserName(username);
        connectionFactory.setPassword(password);
    }


    public static MessageConsumer createConsumer() throws JMSException {
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(queueName);
        MessageConsumer consumer = session.createConsumer(queue);
        return consumer;
    }


    @Test
    public void publisher() throws JMSException, InterruptedException {
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Queue queue = session.createQueue(queueName);
        MessageProducer producer = session.createProducer(queue);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        for (int i = 0; i < publicCounts; i++) {
            Thread.sleep(1000);
            String text = "Tick-tick-" + i;
            Logger.info("Sending message...Content:[{}]", text);
            producer.send(session.createTextMessage(text));
        }
        producer.close();
        Logger.info("Producer closed.");
    }

    @Test
    public void consumerWithListener() throws JMSException, InterruptedException {
        MessageConsumer consumer = createConsumer();
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    String result = message == null ? "NULL" : ((TextMessage) message).getText();
                    Logger.info("Receiving by listener...Content:[{}]", result);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        int i = 0;
        while (true) {
            Logger.info("Sleep for 10 seconds. Count: "+i++);
            Thread.sleep(1000 * 10);
        }
    }

    @Test
    public void consumerWithLoop() throws JMSException, InterruptedException {
        MessageConsumer consumer = createConsumer();
        int i = 0;
        while (true) {
            Message message = consumer.receive();
            String result = message == null ? "NULL" : ((TextMessage) message).getText();
            Logger.info("Receiving by loop..." + i++ + "...Content:[{}]", result);
        }
    }

}
