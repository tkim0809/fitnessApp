package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fitnessapp.Logic.layoutLogic;
import com.example.fitnessapp.chat_function.chatPage;
import com.example.fitnessapp.diet_function.dietPage;

public class userMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        Button logOutBtn = findViewById(R.id.logOutBtn);
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logOut = new Intent(userMenu.this, LoginPage.class);
                startActivity(logOut);
            }
        });
        Button recordBtn = findViewById(R.id.recordBtn);
        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toRecord = new Intent(userMenu.this, statsPage.class);
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
                Intent toMilestone = new Intent(userMenu.this, milestone.class);
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
        ImageButton profileBtn = findViewById(R.id.profileButton);
        profileBtn.setImageResource(R.drawable.icon);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toProfile = new Intent(userMenu.this, profilePage.class);
                startActivity(toProfile);
            }
        });
        Button dietBtn = findViewById(R.id.dietBtn);
        dietBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toDiet = new Intent(userMenu.this, dietPage.class);
                startActivity(toDiet);
            }
        });

        Button leaderboardBtn = findViewById(R.id.leaderboardBtn);

        leaderboardBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent i = new Intent(userMenu.this, leaderboard.class);
                startActivity(i);
            }
        });


        LinearLayout layout = findViewById(R.id.linearLayout2);
        layoutLogic.defBtnColor(layout);
        TextView userName = findViewById(R.id.textUserName);
        userName.setText("Hi, "+UserInfo.getUserID()+"!");
        userName.setTextColor(Color.WHITE);
    }
}