package com.example.demo.Messages;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class WebSocketServer {

    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        System.out.println("Session " + session.getId() + " has opened a connection");
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println("Message received: " + message);
        for (Session s : sessions) {
            if (s.isOpen()) {
                try {
                    s.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        System.out.println("Session " + session.getId() + " has closed a connection");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("Error on session " + session.getId());
        throwable.printStackTrace();
    }

}
