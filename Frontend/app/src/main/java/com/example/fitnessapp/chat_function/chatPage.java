package com.example.fitnessapp.chat_function;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fitnessapp.R;
import com.example.fitnessapp.UserInfo;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class chatPage extends AppCompatActivity {
    String opponentId;
    Button sendBtn,backBtn;
    EditText message;
    ArrayList<chatModel> chatMessagesArray = new ArrayList<>();

    private WebSocketClient cc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        sendBtn = findViewById(R.id.sendChatBtn);
        backBtn = findViewById(R.id.chatBackBtn);
        message = findViewById(R.id.chatingMessage);
        RecyclerView recyclerView = findViewById(R.id.chatRecyclerView);
        Chat_RecyclerViewAdapter adapter = new Chat_RecyclerViewAdapter(this,chatMessagesArray);
        recyclerView.setAdapter(adapter);
        //from bottom to top
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        /**
         * websocket
         *
         */
        Draft[] drafts = {
                new Draft_6455()
        };
        String w = "ws://10.0.2.2:8080/websocket/" + UserInfo.getUserID();

        try {
            Log.d("Socket:", "connecting");
            cc = new WebSocketClient(new URI(w), (Draft) drafts[0]) {
                @Override
                public void onMessage(String message) {
                    Log.d("", "run() returned: " + message);
                    //String s = t1.getText().toString();
                    //t1.setText(s + "\nServer:" + message);
                    addMessageToArray(message,false);
                    adapter.setChatMessages(chatMessagesArray);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onOpen(ServerHandshake handshake) {
                    Log.d("OPEN", "run() returned: " + "is connecting");
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d("CLOSE", "onClose() returned: " + reason);
                }

                @Override
                public void onError(Exception e) {
                    Log.d("Exception:", e.toString());
                }
            };
        } catch (URISyntaxException e) {
            Log.d("Exception:", e.getMessage().toString());
            e.printStackTrace();
        }
        cc.connect();
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cc.send("@"+opponentId + message.getText().toString());
                addMessageToArray(message.getText().toString(),true);
                adapter.setChatMessages(chatMessagesArray);
                recyclerView.setAdapter(adapter);
            }
        });
    }
    private void addMessageToArray(String message,Boolean Isent){
        chatMessagesArray.add(new chatModel(message,Isent));
    }
}