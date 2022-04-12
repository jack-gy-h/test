package com.example.servlet1;

import com.example.proxy.AdminService;
import com.example.proxy.AdminServiceImpl;
import com.example.proxy.AdminServiceProxy;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QueueProducerServlet", value = "/QueueProducerServlet")
public class QueueProducerServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        //2.获取连接
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
        } catch (JMSException e) {
            e.printStackTrace();
        }

        //3.启动连接
        try {
            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }

        //4.获取session  (参数1：是否启动事务,参数2：消息确认模式)
        Session session = null;
        try {
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException e) {
            e.printStackTrace();
        }

        //5.创建队列对象
        Queue queue = null;
        try {
            queue = session.createQueue("test-queue");
        } catch (JMSException e) {
            e.printStackTrace();
        }

        //6.创建消息生产者
        MessageProducer producer = null;
        try {
            producer = session.createProducer(queue);
        } catch (JMSException e) {
            e.printStackTrace();
        }

        //7.创建消息
        TextMessage textMessage = null;
        try {
            textMessage = session.createTextMessage("欢迎点赞关注博主哦！！！");
        } catch (JMSException e) {
            e.printStackTrace();

        }

        //8.发送消息
        try {
            producer.send(textMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }

        //9.关闭资源
        try {
            producer.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        try {
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
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
