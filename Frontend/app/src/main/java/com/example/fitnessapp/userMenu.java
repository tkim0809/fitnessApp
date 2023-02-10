package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class userMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        Button logOutBtn = findViewById(R.id.logOutBtn);
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logOut = new Intent(userMenu.this, MainActivity.class);
                startActivity(logOut);
            }
        });
        Button recordBtn = findViewById(R.id.recordBtn);
        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toRecord = new Intent(userMenu.this, recordPage.class);
                startActivity(toRecord);
            }
        });
        Button friendsBtn = findViewById(R.id.friendsBtn);
        friendsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toFriends = new Intent(userMenu.this, friendsPage.class);
                startActivity(toFriends);
            }
        });
        Button milestoneBtn = findViewById(R.id.milestoneBtn);
        milestoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMilestone = new Intent(userMenu.this, milestonePage.class);
                startActivity(toMilestone);
            }
        });
        Button chatBtn = findViewById(R.id.chatBtn);
        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toChat = new Intent(userMenu.this, chatPage.class);
                startActivity(toChat);
            }
        });
        Button profileBtn = findViewById(R.id.profileButton);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toProfile = new Intent(userMenu.this, profilePage.class);
                startActivity(toProfile);
            }
        });
    }
}