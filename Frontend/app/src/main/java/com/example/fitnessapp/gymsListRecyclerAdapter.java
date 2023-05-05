package com.example.fitnessapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class gymsListRecyclerAdapter extends RecyclerView.Adapter<gymsListRecyclerAdapter.MyViewHolder> {

    Context context;
    ArrayList<gymsModel> gymsModel;

    public gymsListRecyclerAdapter(Context context, ArrayList<gymsModel> gymsModel) {

        this.context = context;
        this.gymsModel = gymsModel;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.gymslist_recyclerview_row, parent, false);

        return new gymsListRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.gymName.setText(gymsModel.get(position).getGymName());
        holder.gymDescription.setText(gymsModel.get(position).getGymDescription());
        holder.gymLocation.setText(gymsModel.get(position).getGymLocation());
        holder.gymPhoneNumber.setText(gymsModel.get(position).getGymPhoneNumber());
        holder.gymHours.setText(gymsModel.get(position).getGymHours());


        holder.liked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, likedGymsPage.class);
                //intent.putExtra("gym_id", gymsModel.get(position).getGymId());
                context.startActivity(intent);
            }
        });

        holder.disliked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, dislikedGymsPage.class);
                //intent.putExtra("gym_id", gymsModel.get(position).getGymId());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return gymsModel.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView gymName, gymDescription, gymLocation, gymPhoneNumber, gymHours;
        Button liked, disliked;
        private ImageView image, image2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            gymName = itemView.findViewById(R.id.gymName);
            gymDescription = itemView.findViewById(R.id.gymDescription);
            gymLocation = itemView.findViewById(R.id.gymLocation);
            gymPhoneNumber = itemView.findViewById(R.id.gymPhoneNumber);
            gymHours = itemView.findViewById(R.id.gymHours);
            liked = itemView.findViewById(R.id.liked);
            disliked = itemView.findViewById(R.id.disliked);



        }
    }



}


