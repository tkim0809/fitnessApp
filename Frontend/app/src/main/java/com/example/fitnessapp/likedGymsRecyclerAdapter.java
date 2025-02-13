package com.example.fitnessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class likedGymsRecyclerAdapter extends RecyclerView.Adapter<likedGymsRecyclerAdapter.MyViewHolder>{
    Context context;
    ArrayList<likedGymsModel> likedGymsModels = new ArrayList<>();


    public likedGymsRecyclerAdapter(Context context, ArrayList<likedGymsModel> likedGymsModels) {

        this.context = context;
        this.likedGymsModels = likedGymsModels;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.likedgyms_recyclerview_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.email.setText(likedGymsModels.get(position).getUserName());

    }

    @Override
    public int getItemCount() {
        return likedGymsModels.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView email;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            email = itemView.findViewById(R.id.likedgyms_username);

        }
    }
}
