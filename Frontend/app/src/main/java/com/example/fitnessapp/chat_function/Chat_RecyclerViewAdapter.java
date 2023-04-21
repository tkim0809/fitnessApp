package com.example.fitnessapp.chat_function;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;

import java.util.ArrayList;

public class Chat_RecyclerViewAdapter extends RecyclerView.Adapter<Chat_RecyclerViewAdapter.MyViewHolder> {
    Context context;

    public void setChatMessages(ArrayList<chatModel> chatMessages) {
        this.chatMessages = chatMessages;
    }

    ArrayList<chatModel> chatMessages;
    public Chat_RecyclerViewAdapter(Context context, ArrayList<chatModel> chatMessages){
        this.context =context;
        this.chatMessages =chatMessages;
    }
    @NonNull
    @Override
    public Chat_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.chat_message_layout,parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Chat_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.message.setText(chatMessages.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView message;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.chatMessage);
        }
    }
}
