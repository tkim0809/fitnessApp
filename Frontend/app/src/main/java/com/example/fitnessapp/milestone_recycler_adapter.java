package com.example.fitnessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Recycler adapter class that is used to create milestone recycler view
 */
public class milestone_recycler_adapter extends RecyclerView.Adapter<milestone_recycler_adapter.MyViewHolder>{

    Context context;
    ArrayList<milestoneModel> milestoneModels;
    private static OnItemClickListener listener;


    public interface OnItemClickListener {

        void onItemClick(int position);

    }

    public static void setOnItemClickListener(OnItemClickListener clickListener) {

        listener = clickListener;

    }

    public milestone_recycler_adapter(Context context, ArrayList<milestoneModel> milestoneModels) {

        this.context = context;
        this.milestoneModels = milestoneModels;

    }

    @NonNull
    @Override
    public milestone_recycler_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_row, parent, false);

        return new milestone_recycler_adapter.MyViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull milestone_recycler_adapter.MyViewHolder holder, int position) {

        holder.workoutName.setText(milestoneModels.get(position).getWorkoutName());
        holder.workoutWeights.setText(milestoneModels.get(position).getWorkoutWeights());
        holder.workoutSets.setText(milestoneModels.get(position).getWorkoutSets());
        holder.workoutReps.setText(milestoneModels.get(position).getWorkoutReps());

    }

    @Override
    public int getItemCount() {

        return milestoneModels.size();

    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {



        TextView workoutName, workoutWeights, workoutReps, workoutSets;
        private ImageView image;


        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {

                super(itemView);

                image = itemView.findViewById(R.id.delete_id);
                workoutName = itemView.findViewById(R.id.workout1);
                workoutWeights = itemView.findViewById(R.id.workout2);
                workoutReps = itemView.findViewById(R.id.workout3);
                workoutSets = itemView.findViewById(R.id.workout4);

                image.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(getAdapterPosition());
                    }
                });

        }

    }

}
