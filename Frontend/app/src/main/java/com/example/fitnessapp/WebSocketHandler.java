package com.example.fitnessapp;

import android.content.Context;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import org.json.JSONException;
import org.json.JSONObject;

public class WebSocketHandler {
    private OkHttpClient httpClient;
    private WebSocket webSocket;

    public WebSocketHandler(Context context, String userId) {
        httpClient = new OkHttpClient();
        String webSocketUrl = "ws://coms-309-004.class.las.iastate.edu:8080/notifications/" + userId;
        okhttp3.Request request = new okhttp3.Request.Builder().url(webSocketUrl).build();
        WebSocketListener webSocketListener = new WebSocketListener() {

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                super.onMessage(webSocket, text);
                Log.i("WebSocket", "Received message: " + text);

                try {
                    JSONObject receivedJson = new JSONObject(text);
                    String messageType = receivedJson.getString("type");
                    if ("friend_added".equals(messageType)) {
                        String friendEmail = receivedJson.getString("email");
                        boolean isSender = receivedJson.getBoolean("isSender");
                        String userNotificationText = isSender ?
                                "You have added a new friend: " + friendEmail :
                                "You have been added as a friend by: " + friendEmail;

                        NotificationUtils.showNotification(context, "Friend Added", userNotificationText);
                    }
                } catch (JSONException e) {
                    Log.e("WebSocket", "Error parsing received JSON message", e);
                }
            }
        };

        webSocket = httpClient.newWebSocket(request, webSocketListener);
    }

    public void close() {
        if (webSocket != null) {
            webSocket.close(1000, "App closed");
        }
    }
}