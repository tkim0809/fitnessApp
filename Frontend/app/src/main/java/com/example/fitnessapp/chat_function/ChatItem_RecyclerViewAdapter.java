package com.example.fitnessapp.chat_function;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;

import java.util.ArrayList;

/**
 * Adapter class for chatList
 */
public class ChatItem_RecyclerViewAdapter extends RecyclerView.Adapter<ChatItem_RecyclerViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    ArrayList<chatItemModel> chatList;
    Context context;

    public ChatItem_RecyclerViewAdapter(ArrayList<chatItemModel> chatList, Context context,RecyclerViewInterface recyclerViewInterface) {
        this.chatList = chatList;
        this.context = context;
        this.recyclerViewInterface =recyclerViewInterface;
    }

    @NonNull
    @Override
    public ChatItem_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.chat_list_layout,parent,false);
        return new ChatItem_RecyclerViewAdapter.MyViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatItem_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.userName.setText(chatList.get(position).getUserName());
        //holder.icon.setImageResource();
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView userName;
        ImageView icon;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            icon = itemView.findViewById(R.id.userIcon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface!=null){
                        int pos = getAdapterPosition();
                        if (pos!=RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
