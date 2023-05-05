package com.example.fitnessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class dislikedGymsRecyclerAdapter extends RecyclerView.Adapter<dislikedGymsRecyclerAdapter.MyViewHolder>{
    Context context;
    ArrayList<dislikedGymsModel> dislikedGymsModels = new ArrayList<>();


    public dislikedGymsRecyclerAdapter(Context context, ArrayList<dislikedGymsModel> dislikedGymsModels) {

        this.context = context;
        this.dislikedGymsModels = dislikedGymsModels;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dislikedgym_recyclerview_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.email.setText(dislikedGymsModels.get(position).getUsername());

    }

    @Override
    public int getItemCount() {
        return dislikedGymsModels.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView email;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            email = itemView.findViewById(R.id.dislikedgyms_username);

        }
    }
}
