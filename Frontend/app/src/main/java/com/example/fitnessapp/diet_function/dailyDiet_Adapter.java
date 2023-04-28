package com.example.fitnessapp.diet_function;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.chat_function.Chat_RecyclerViewAdapter;

import java.util.ArrayList;

public class dailyDiet_Adapter extends RecyclerView.Adapter<dailyDiet_Adapter.MyViewHolder> {
    ArrayList<dietFoodItemModel> items;
    Context context;

    public dailyDiet_Adapter(ArrayList<dietFoodItemModel> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public dailyDiet_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.daily_diet_item,parent,false);
        return new dailyDiet_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull dailyDiet_Adapter.MyViewHolder holder, int position) {
        holder.meal.setText(items.get(position).getMeal());
        holder.Cal.setText(items.get(position).getCal());
        holder.foodName.setText(items.get(position).getFoodName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public  static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView foodName,Cal,meal;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodItemName);
            Cal = itemView.findViewById(R.id.foodItemCal);
            meal = itemView.findViewById(R.id.foodItemMeal);
        }
    }
}
