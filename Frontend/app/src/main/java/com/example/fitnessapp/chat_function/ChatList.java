package com.example.fitnessapp.chat_function;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.fitnessapp.R;

import java.util.ArrayList;

public class ChatList extends AppCompatActivity implements RecyclerViewInterface{
    ArrayList<chatItemModel> chatItemModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        RecyclerView recyclerView = findViewById(R.id.chatListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatItemModelArrayList.add(new chatItemModel("eddy",1));
        ChatItem_RecyclerViewAdapter adapter = new ChatItem_RecyclerViewAdapter(chatItemModelArrayList,this,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent toChat = new Intent(ChatList.this,chatPage.class);
        toChat.putExtra("userName",chatItemModelArrayList.get(position).getUserName());
        //missing methods to get friend's ID
        toChat.putExtra("userId","37");
        startActivity(toChat);
    }
}