package com.example.servlet1;

import com.google.common.eventbus.Subscribe;

import javax.jms.QueueReceiver;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Proxy;

@WebServlet(name = "DestroyedServlet2", value = "/DestroyedServlet2")
public class kkServlet2 extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.getSession().removeAttribute("kickout");







    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
