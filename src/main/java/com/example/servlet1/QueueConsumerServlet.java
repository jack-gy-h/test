package com.example.servlet1;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QueueConsumerServlet", value = "/QueueConsumerServlet")
public class QueueConsumerServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.创建连接工厂
        //1.创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.49.1:61616");
        //2.获取连接
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
        } catch (JMSException e) {
            e.printStackTrace();
            System.out.println("111111111111111111111");
        }
        //3.启动连接
        try {
            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
            System.out.println("22222222222222222");
        }
        //4.获取session  (参数1：是否启动事务,参数2：消息确认模式)
        Session session = null;
        try {
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            e.printStackTrace();
            System.out.println("333333333333333");
        }
        //5.创建队列对象
        Queue queue = null;
        try {
            queue = session.createQueue("test-queue");
        } catch (JMSException e) {
            e.printStackTrace();
            System.out.println("444444444444444");
        }
        //6.创建消息消费
        MessageConsumer consumer = null;
        try {
            consumer = session.createConsumer(queue);
        } catch (JMSException e) {
            e.printStackTrace();
            System.out.println("555555555555555");
        }

        //7.监听消息
        try {
            consumer.setMessageListener(message -> {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("接收到消息：" + textMessage.getText());
                } catch (JMSException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    System.out.println("666666666666666");
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
            System.out.println("777777777777777777777777777");
        }
        //8.等待键盘输入
        System.out.println("888888888888888888888888");
        //9.关闭资源
        try {
            consumer.close();
        } catch (JMSException e) {
            e.printStackTrace();
            System.out.println("999999999999999999");
        }
        try {
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
            System.out.println("00000000000000000000000000000000");
        }
        try {
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
