package com.example.servlet1;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {





//　　　　相应 给浏览器一个 session id
        response.setContentType("text/html;charset=utf-8");

        MySessionContext myc = MySessionContext.getInstance();
        Set<String> sess = myc.getAllSession();

        PrintWriter out = response.getWriter();
        Iterator it = sess.iterator();
        out.println("<html><body>");

        out.println("<h1>" + "当前登录人数为：" + sess.size()+ "</h1>");

//      获取sessionid
        while (it.hasNext()){

            String sessionid = (String) it.next();

            HttpSession session = myc.getSession(sessionid);

//            session.setAttribute("user","吴家剑");



//            Enumeration sessionAttribute = session.getAttributeNames();

            long time = session.getCreationTime();
            long time1 = session.getLastAccessedTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date date = new Date(time);
            String str = sdf.format(date);

            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date date1 = new Date(time1);
            String str1 = sdf1.format(date1);

            out.println("<h1>" + "当前登录的人的sessionid为：" + sessionid + "</h1>");

            out.println("<h1>" + "当前登录的人的sessionid的创建时间为：" + str + "</h1>");

            out.println("<h1>" + "当前登录的人的sessionid的上一次活跃时间为：" + str1 + "</h1>");

            String str2 = null;

            long timeUnActivity = (new Date()).getTime() - time1;

            if (timeUnActivity > 0 && timeUnActivity <60000 ){

                str2 = String.valueOf(timeUnActivity/1000)+"秒";


            }else if(timeUnActivity> 60000 && timeUnActivity < 1800000){

                str2 = String.valueOf(timeUnActivity / 60000) + "分" +String.valueOf((timeUnActivity%60000)/1000)+"秒";
            }

            out.println("<h1>" + "当前登录的人的sessionid的不活跃时间为：" + str2+ "</h1>");




//            while (sessionAttribute.hasMoreElements()){
//                out.println("<h1>" + "当前登录的人的session的变量有：" + sessionAttribute.nextElement() + "</h1>");
//            }


        }

        out.println("</body></html>");
    }

    public void destroy() {
    }
}
