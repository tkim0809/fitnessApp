//package com.example.demo.Messages;
//
//import java.io.IOException;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.websocket.OnClose;
//import javax.websocket.OnError;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.ServerEndpoint;
//
//@ServerEndpoint("/chat")
//public class WebSocketServer {
//
//    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());
//
//    @OnOpen
//    public void onOpen(Session session) {
//        sessions.add(session);
//        System.out.println("Session " + session.getId() + " has opened a connection");
//    }
//
//    @OnMessage
//    public void onMessage(Session session, String message) {
//        System.out.println("Message received: " + message);
//        for (Session s : sessions) {
//            if (s.isOpen()) {
//                try {
//                    s.getBasicRemote().sendText(message);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    @OnClose
//    public void onClose(Session session) {
//        sessions.remove(session);
//        System.out.println("Session " + session.getId() + " has closed a connection");
//    }
//
//    @OnError
//    public void onError(Session session, Throwable throwable) {
//        System.out.println("Error on session " + session.getId());
//        throwable.printStackTrace();
//    }
//
//}





package com.example.demo.Messages;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class WebSocketServer {

    private static Map<String, Session> sessions = Collections.synchronizedMap(new HashMap<>());

    @OnOpen
    public void onOpen(Session session) {
        String username = session.getRequestParameterMap().get("username").get(0);
        sessions.put(username, session);
        System.out.println("Session " + session.getId() + " has opened a connection for user " + username);
    }

    @OnMessage
    public void onMessage(Session senderSession, String message) {
        String senderUsername = getUsernameFromSession(senderSession);
        int colonIndex = message.indexOf(':');
        if (colonIndex != -1) {
            String recipientUsername = message.substring(0, colonIndex).trim();
            Session recipientSession = sessions.get(recipientUsername);
            if (recipientSession != null && recipientSession.isOpen()) {
                String privateMessage = message.substring(colonIndex + 1).trim();
                try {
                    recipientSession.getBasicRemote().sendText(senderUsername + ": " + privateMessage);
                    senderSession.getBasicRemote().sendText(senderUsername + ": " + privateMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    senderSession.getBasicRemote().sendText("User " + recipientUsername + " is not online");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                senderSession.getBasicRemote().sendText("Invalid message format. Please use 'recipient: message'");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        String username = getUsernameFromSession(session);
        sessions.remove(username);
        System.out.println("Session " + session.getId() + " has closed a connection for user " + username);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("Error on session " + session.getId());
        throwable.printStackTrace();
    }

    private String getUsernameFromSession(Session session) {
        for (Map.Entry<String, Session> entry : sessions.entrySet()) {
            if (entry.getValue().equals(session)) {
                return entry.getKey();
            }
        }
        return null;
    }

}
