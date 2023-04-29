package com.example.fitnessapp.chat_function;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.R;
import com.example.fitnessapp.UserInfo;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * This class is the UI of chat function
 */
public class chatPage extends AppCompatActivity {
    String opponentUserName = "39";
    Button sendBtn,backBtn;
    TextView chatName;
    EditText message;
    ArrayList<chatMessageModel> chatMessagesArray = new ArrayList<>();


    private WebSocketClient cc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        addMessageToArray("Hi(Received)",false);
        addMessageToArray("Hello(sent)",true);
        //pan up the activity when keyboard is shown.
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        sendBtn = findViewById(R.id.sendChatBtn);
        backBtn = findViewById(R.id.chatBackBtn);
        message = findViewById(R.id.chatingMessage);
        chatName = findViewById(R.id.chatName);
        RecyclerView recyclerView = findViewById(R.id.chatListRecyclerView);
        Chat_RecyclerViewAdapter adapter = new Chat_RecyclerViewAdapter(this,chatMessagesArray);
        recyclerView.setAdapter(adapter);


        //from bottom to top
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));

        Intent i = this.getIntent();
        //opponentUserName = i.getStringExtra("userName");
        //chatName.setText(opponentUserName);
        //websocket
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

                    String[] messageArray;
                    try {
                        messageArray = message.split(":");
                        System.out.println("sender:"+messageArray[0]);
                        if (messageArray[0].equals("[DM] "+opponentUserName)) {
                            addMessageToArray(messageArray[1], false);
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    // Update UI here
                                    adapter.setChatMessages(chatMessagesArray);
                                    recyclerView.setAdapter(adapter);
                                }
                            });

                        }else if (messageArray[0].equals("[DM] "+UserInfo.getUserID())){
                            addMessageToArray(messageArray[1], true);
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    // Update UI here
                                    adapter.setChatMessages(chatMessagesArray);
                                    recyclerView.setAdapter(adapter);
                                }
                            });
                        }

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }


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
                JSONObject messageObj = new JSONObject();
                try {
                    System.out.println(message.getText());
                    cc.send("@"+opponentUserName+" "+message.getText().toString());
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    Toast.makeText(chatPage.this, "Your message did not sent.", Toast.LENGTH_SHORT).show();
                }

                //addMessageToArray(message.getText().toString(),true);
                //adapter.setChatMessages(chatMessagesArray);
                //recyclerView.setAdapter(adapter);
            }
        });
    }

    /**
     * This method is used to add message to the message array which is used to display the message.
     * @param message text message sent or received
     * @param Isent Boolean return true if user sent the message
     */
    private void addMessageToArray(String message,Boolean Isent){
        chatMessagesArray.add(0,new chatMessageModel(message,Isent));
    }
}