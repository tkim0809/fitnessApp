package com.example.fitnessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Recycler adapter class that is used to view lists of all the leaderboard progress.
 */
public class leaderboard_recycler_adapter extends RecyclerView.Adapter<leaderboard_recycler_adapter.MyViewHolder>{

    ArrayList<leaderboardModel> leaderboardModels;
    Context context;

    public leaderboard_recycler_adapter(Context context, ArrayList<leaderboardModel> leaderboardModels) {

        this.context = context;
        this.leaderboardModels = leaderboardModels;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.leaderboard_recyclerview_row, parent, false);

        return new leaderboard_recycler_adapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.leaderboardRank.setText((position + 1) + ". ");
        holder.leaderboardUsername.setText(leaderboardModels.get(position).getUserName());
        holder.leaderboardPushups.setText(leaderboardModels.get(position).getPushUps());

    }

    @Override
    public int getItemCount() {
        return leaderboardModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView leaderboardUsername, leaderboardPushups, leaderboardRank;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            leaderboardUsername = itemView.findViewById(R.id.leaderboardUsername);
            leaderboardPushups = itemView.findViewById(R.id.leaderboardPushups);
            leaderboardRank = itemView.findViewById(R.id.leaderboardRank);

        }
    }


}
