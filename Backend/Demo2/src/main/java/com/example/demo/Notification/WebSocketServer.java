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
import org.json.JSONException;
import org.json.JSONObject;


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
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        // Handle new messages
        logger.info("Entered into Message: Got Message:" + message);
        String userId = sessionUserIdMap.get(session);

        try {
            JSONObject jsonMessage = new JSONObject(message);
            String targetUserId = jsonMessage.getString("targetUserId");
            String notification = jsonMessage.getString("notification");
            sendMessageToParticularUser(targetUserId, notification);
        } catch (JSONException e) {
            logger.error("Error parsing received JSON message", e);
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        logger.info("Entered into Close");

        String userId = sessionUserIdMap.get(session);
        sessionUserIdMap.remove(session);
        userIdSessionMap.remove(userId);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
        logger.info("Entered into Error");
    }

    public void sendMessageToParticularUser(String userId, String message) {
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


