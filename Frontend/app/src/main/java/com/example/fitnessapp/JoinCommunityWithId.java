package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fitnessapp.chat_function.ChatList;
import com.example.fitnessapp.chat_function.connectingID;

public class JoinCommunityWithId extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_community_with_id);
        EditText id = findViewById(R.id.CConnectIdEdit);
        Button next = findViewById(R.id.CnextBtn);
        id.setText(UserInfo.getUserID());
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(JoinCommunityWithId.this, CommunityChat.class);
                UserInfo.setUserID(id.getText().toString());
                System.out.println(id.getText().toString());
                startActivity(i);
            }
        });
    }
    }