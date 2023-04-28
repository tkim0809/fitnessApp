package com.example.fitnessapp;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class gymsListRecyclerAdapter extends RecyclerView.Adapter<friendListRecyclerAdapter.MyViewHolder>{

    Context context;
    ArrayList<gymsModel> gymsModel;

    public gymsListRecyclerAdapter(Context context, ArrayList<gymsModel> gymsModel) {

        this.context = context;
        this.gymsModel = gymsModel;

    }

    @NonNull
    @Override
    public friendListRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull friendListRecyclerAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
