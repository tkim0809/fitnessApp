package com.example.fitnessapp;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class friendListRecyclerAdapter extends RecyclerView.Adapter<friendListRecyclerAdapter.MyViewHolder> {

    private static OnItemClickListener listener;
    Context context;
    ArrayList<friendModel> friendModels;

    public interface OnItemClickListener {

        void onItemClick(int position);

    }

    public static void setOnItemClickListener(OnItemClickListener clickListener) {

        listener = clickListener;

    }



    public friendListRecyclerAdapter(Context context, ArrayList<friendModel> friendModels) {

        this.context = context;
        this.friendModels = friendModels;

    }

    @NonNull
    @Override
    public friendListRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.friendlist_recyclerview_row, parent, false);

        return new friendListRecyclerAdapter.MyViewHolder(view, (OnItemClickListener) listener);
    }

    @Override
    public void onBindViewHolder(@NonNull friendListRecyclerAdapter.MyViewHolder holder, int position) {

        holder.email.setText(friendModels.get(position).getEmail());
        holder.userName.setText(friendModels.get(position).getUserName());

    }

    @Override
    public int getItemCount() {
        return friendModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

    TextView email, userName;
    private ImageView image2;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {

            super(itemView);

            image2 = itemView.findViewById(R.id.delete_id_friendList);
            email = itemView.findViewById(R.id.friendListEmail);
            userName = itemView.findViewById(R.id.friendListUsername);

            image2.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    listener.onItemClick(getAdapterPosition());
                }
            });



        }
    }

}
