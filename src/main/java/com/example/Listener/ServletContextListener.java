package com.example.Listener;

import javax.servlet.ServletContextEvent;

public class ServletContextListener implements javax.servlet.ServletContextListener {


    //    tomcat启动时
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("contextInitialized");
    }

    //    tomcat销毁时
    public void contextDestroyed(ServletContextEvent sce) {

        System.out.println("contextDestroyed");
    }
}
