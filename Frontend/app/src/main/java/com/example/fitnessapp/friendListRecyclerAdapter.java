package com.example.fitnessapp;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Recycler adapter class that is used to view lists of friends
 */
public class friendListRecyclerAdapter extends RecyclerView.Adapter<friendListRecyclerAdapter.MyViewHolder> {

    Context context;
    ArrayList<friendModel> friendModels;

    /**
     * recycler adapter constructor for friend list.
     * @param context
     * @param friendModels
     */
    public friendListRecyclerAdapter(Context context, ArrayList<friendModel> friendModels) {

        this.context = context;
        this.friendModels = friendModels;

    }

    @NonNull
    @Override
    public friendListRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.friendlist_recyclerview_row, parent, false);

        return new friendListRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull friendListRecyclerAdapter.MyViewHolder holder, int position) {

        holder.email.setText(friendModels.get(position).getEmail());
        holder.userName.setText(friendModels.get(position).getUserName());

    }

    /**
     * @return the size of friendModels(arrayList)
     */
    @Override
    public int getItemCount() {
        return friendModels.size();
    }

    /**
     * view holder class
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {

    TextView email, userName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            email = itemView.findViewById(R.id.friendListEmail);
            userName = itemView.findViewById(R.id.friendListUsername);

        }
    }

}
