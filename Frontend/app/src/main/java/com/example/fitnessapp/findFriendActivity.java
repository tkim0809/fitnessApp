package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class findFriendActivity extends AppCompatActivity {

    private OkHttpClient httpClient;
    private WebSocket webSocket;


    @SuppressLint("MissingPermission")
    private void showFriendAddedNotification(String friendEmail) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "friend_added_channel_id")
                .setSmallIcon(R.drawable.circle) // Set the appropriate icon for your app
                .setContentTitle("Friend Added")
                .setContentText("You have added a new friend: " + friendEmail)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Friend Added";
            String description = "Notifications for friend addition";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("friend_added_channel_id", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friend);

        createNotificationChannel();

        httpClient = new OkHttpClient();
        String userId = UserInfo.getUserID();
        String webSocketUrl = "ws://coms-309-004.class.las.iastate.edu:8080/notifications/" + userId;
        okhttp3.Request request = new okhttp3.Request.Builder().url(webSocketUrl).build();
        WebSocketListener webSocketListener = new WebSocketListener() {
            // Override WebSocketListener methods based on your requirements

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                super.onMessage(webSocket, text);
                Log.i("WebSocket", "Received message: " + text);

                try {
                    JSONObject receivedJson = new JSONObject(text);
                    String messageType = receivedJson.getString("type");
                    if ("friend_added".equals(messageType)) {
                        String friendEmail = receivedJson.getString("email");
                        Toast.makeText(findFriendActivity.this, "Websockets connected", Toast.LENGTH_LONG).show();
                        showFriendAddedNotification(friendEmail);
                    }
                } catch (JSONException e) {
                    Log.e("WebSocket", "Error parsing received JSON message", e);
                }
            }


        };
        webSocket = httpClient.newWebSocket(request, webSocketListener);

        EditText emailEditText = findViewById(R.id.friendEmail);
        Button addFriendButton = findViewById(R.id.addFriendButton);
        Button backToMenuBtn = findViewById(R.id.backToMenu);

        backToMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(findFriendActivity.this, NewUserMenu.class);
                startActivity(i);
            }
        });

        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String userName = UserInfo.getUserID();

                String url = "http://coms-309-004.class.las.iastate.edu:8080/api/friends/" + userName + "/add";
                RequestQueue queue = Volley.newRequestQueue(findFriendActivity.this);
                JSONObject obj = new JSONObject();

                try {
                    obj.put("email", email);
                } catch (Exception e) {
                    System.out.println("ERROR");
                }

                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                        url, obj,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String toCompare = response.get("message").toString();

                                    if (toCompare.equals("success")) {
                                        Toast.makeText(findFriendActivity.this, "Successful added to friend list", Toast.LENGTH_LONG).show();
                                        showFriendAddedNotification(email);
                                    }

                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // ... error handling code
                    }
                }) {
                    @Override
                    public Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        return params;
                    }
                };

                queue.add(jsonObjReq);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webSocket != null) {
            webSocket.close(1000, "App closed");
        }
    }
}
