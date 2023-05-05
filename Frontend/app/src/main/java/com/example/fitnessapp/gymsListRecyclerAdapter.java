package com.example.fitnessapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

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

                int position = holder.getAdapterPosition() + 1;

                Intent intent = new Intent(context, likedGymsPage.class);
                intent.putExtra("likedAdapterPosition", position);
                context.startActivity(intent);
            }
        });
        holder.disliked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = holder.getAdapterPosition() + 1;

                Intent intent = new Intent(context, dislikedGymsPage.class);
                intent.putExtra("dislikedAdapterPosition", position);

                //intent.putExtra("gym_id", gymsModel.get(position).getGymId());
                context.startActivity(intent);
            }
        });
        holder.thumbUp.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                sendThumbUpRequest(holder.getAdapterPosition());

            }
        });

        holder.thumbDown.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                sendThumbDownRequest(holder.getAdapterPosition());
            }
        });




    }

    private void sendThumbUpRequest(int position) {

        int newPosition = position + 1;

        String url = "http://coms-309-004.class.las.iastate.edu:8080" +"/gyms/" + newPosition + "/like" + "/" + UserInfo.getUserIdFromLogin();
        RequestQueue queue = Volley.newRequestQueue(context);

        JSONObject requestBody = new JSONObject();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the response
                        Log.d("Response", response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle the error
                Log.e("Error", error.toString());
            }
        });

        queue.add(request);
    }

    private void sendThumbDownRequest(int position) {

        int newPosition = position + 1;

        String url = "http://coms-309-004.class.las.iastate.edu:8080" +"/gyms/" + newPosition + "/dislike" + "/" + UserInfo.getUserIdFromLogin();
        RequestQueue queue = Volley.newRequestQueue(context);

        JSONObject requestBody = new JSONObject();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the response
                        Log.d("Response", response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle the error
                Log.e("Error", error.toString());
            }
        });

        queue.add(request);
    }




    @Override
    public int getItemCount() {
        return gymsModel.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView gymName, gymDescription, gymLocation, gymPhoneNumber, gymHours;
        Button liked, disliked;
        private ImageView image, image2, thumbUp, thumbDown;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            gymName = itemView.findViewById(R.id.gymName);
            gymDescription = itemView.findViewById(R.id.gymDescription);
            gymLocation = itemView.findViewById(R.id.gymLocation);
            gymPhoneNumber = itemView.findViewById(R.id.gymPhoneNumber);
            gymHours = itemView.findViewById(R.id.gymHours);
            liked = itemView.findViewById(R.id.liked);
            disliked = itemView.findViewById(R.id.disliked);
            thumbUp = itemView.findViewById(R.id.thumbUp);
            thumbDown = itemView.findViewById(R.id.thumbDown);



        }
    }



}


