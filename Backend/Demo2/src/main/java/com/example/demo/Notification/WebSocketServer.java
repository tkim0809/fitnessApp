package com.example.demo.Notification;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@ServerEndpoint("/websocket/{userId}")
@Component
public class WebSocketServer {

    // Store all socket sessions and their corresponding userIds.
    private static Map<Session, String> sessionUserIdMap = new Hashtable<>();
    private static Map<String, Session> userIdSessionMap = new Hashtable<>();

    private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) throws IOException {
        logger.info("Entered into Open");

        sessionUserIdMap.put(session, userId);
        userIdSessionMap.put(userId, session);

        String notification = "User with ID:" + userId + " has joined the notification service.";
        broadcast(notification);
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        // Handle new messages
        logger.info("Entered into Message: Got Message:" + message);
        String userId = sessionUserIdMap.get(session);

        // Message to all users
        broadcast("Notification from User " + userId + ": " + message);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        logger.info("Entered into Close");

        String userId = sessionUserIdMap.get(session);
        sessionUserIdMap.remove(session);
        userIdSessionMap.remove(userId);

        String notification = "User with ID:" + userId + " has left the notification service.";
        broadcast(notification);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
        logger.info("Entered into Error");
    }

    private void broadcast(String notification) {
        sessionUserIdMap.forEach((session, userId) -> {
            try {
                session.getBasicRemote().sendText(notification);
            } catch (IOException e) {
                logger.info("Exception: " + e.getMessage().toString());
                e.printStackTrace();
            }
        });
    }

    public void sendMessageToPArticularUser(String userId, String message) {
        Session session = userIdSessionMap.get(userId);
        if (session != null) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                logger.info("Exception: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}

