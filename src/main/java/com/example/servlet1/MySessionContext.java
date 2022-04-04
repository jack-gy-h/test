package com.example.servlet1;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Set;

public class MySessionContext {
    private static MySessionContext instance;
    private HashMap<String, HttpSession> sessionMap;

    private int sort = 0;

    private MySessionContext() {
        sessionMap = new HashMap<String, HttpSession>();
    }

    public static MySessionContext getInstance() {
        if (instance == null) {
            instance = new MySessionContext();
        }
        return instance;
    }

    public synchronized void addSession(HttpSession session) {
        if (session != null) {

            sort++;

            session.setAttribute("sort",sort);
            sessionMap.put(session.getId(), session);
        }
    }

    public synchronized void delSession(HttpSession session) {
        if (session != null) {
            sessionMap.remove(session.getId());
        }
    }

    public synchronized HttpSession getSession(String sessionID) {
        if (sessionID == null) {
            return null;
        }
        return sessionMap.get(sessionID);
    }

    public synchronized Set<String> getAllSession() {






        return sessionMap.keySet();
    }

}
