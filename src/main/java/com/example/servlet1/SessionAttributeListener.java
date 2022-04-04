package com.example.servlet1;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.websocket.Session;
import java.util.ArrayList;

public class SessionAttributeListener implements HttpSessionAttributeListener {




    // 在session中添加对象时触发此操作
    public void attributeAdded(HttpSessionBindingEvent sbe) {

    }

    // 删除session中添加对象时触发此操作
    public void attributeRemoved(HttpSessionBindingEvent arg0) {

        System.out.println("Removed");
    }

    //    修改对象的值时
    public void attributeReplaced(HttpSessionBindingEvent arg0) {

        if (arg0.getSession().getAttribute("kickout").equals("true")) {

            System.out.println("sessionid:" + arg0.getSession().getId() + "aa");

            MyWebSocket myWebSocket = new MyWebSocket();


            myWebSocket.onMessage("true", MyWebSocket.sessionMap.get(arg0.getSession().getId()));


        } else {

            System.out.println("sessionid:" + arg0.getSession().getId() + "bb");
        }

        System.out.println("sbe.getValue():" + arg0.getValue());

    }

}

