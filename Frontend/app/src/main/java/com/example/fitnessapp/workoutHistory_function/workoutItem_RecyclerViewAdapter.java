package com.example.fitnessapp.workoutHistory_function;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.chat_function.ChatItem_RecyclerViewAdapter;

import java.util.ArrayList;

public class workoutItem_RecyclerViewAdapter extends RecyclerView.Adapter<workoutItem_RecyclerViewAdapter.MyViewHolder> {


    ArrayList<workoutHistoryItemModel> Items;
    Context context;

    public workoutItem_RecyclerViewAdapter(ArrayList<workoutHistoryItemModel> items, Context context) {
        Items = items;
        this.context = context;
    }
    public void setItems(ArrayList<workoutHistoryItemModel> items) {
        Items = items;
    }

    @NonNull
    @Override
    public workoutItem_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.workout_history_item,parent,false);
        return new workoutItem_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull workoutItem_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.reps.setText(Items.get(position).getReps());
        holder.sets.setText(Items.get(position).getSets());
        holder.weight.setText(Items.get(position).getWeight());
        holder.workoutName.setText(Items.get(position).getWorkoutName());
    }

    @Override
    public int getItemCount() {
        return Items.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView workoutName,sets,weight,reps;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            workoutName = itemView.findViewById(R.id.workoutItemName);
            sets = itemView.findViewById(R.id.workoutItemSets);
            weight = itemView.findViewById(R.id.workoutItemWeight);
            reps = itemView.findViewById(R.id.workoutItemReps);
        }
    }
}
