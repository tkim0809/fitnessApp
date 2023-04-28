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
    private static final int SENT_MESSAGE = 0;
    private static final int RECEIVED_MESSAGE = 1;

    public void setChatMessages(ArrayList<chatMessageModel> chatMessages) {
        this.chatMessages = chatMessages;
    }

    ArrayList<chatMessageModel> chatMessages;
    public Chat_RecyclerViewAdapter(Context context, ArrayList<chatMessageModel> chatMessages){
        this.context =context;
        this.chatMessages =chatMessages;
    }
    @NonNull
    @Override
    public Chat_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view;
        if (viewType == SENT_MESSAGE){
            view = layoutInflater.inflate(R.layout.chat_message_send_layout,parent,false);
        }else {
            view = layoutInflater.inflate(R.layout.chat_message_receive_layout,parent,false);
        }
        return new Chat_RecyclerViewAdapter.MyViewHolder(view);
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
            message = itemView.findViewById(R.id.message_text);
        }
    }
    @Override
    public int getItemViewType(int position){
        chatMessageModel m = chatMessages.get(position);
        if (m.getIsent()){
            return SENT_MESSAGE;
        }
        else {
            return RECEIVED_MESSAGE;
        }
    }
}
