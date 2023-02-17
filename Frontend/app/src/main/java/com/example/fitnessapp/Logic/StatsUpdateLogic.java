package com.example.fitnessapp.Logic;

//todo

import android.widget.Toast;

import com.example.fitnessapp.IView;
import com.example.fitnessapp.Network.IServerRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class StatsUpdateLogic implements IVolleyListener{
    IView i;
    IServerRequest serverRequest;
    public StatsUpdateLogic(IView i, IServerRequest serverRequest){
        this.i = i;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }



    public void addExercise(String name, String rep, String max) throws JSONException {
        final String url = "TBD";
        JSONObject newExercise = new JSONObject();
        newExercise.put("name",name);
        newExercise.put("rep",rep);
        newExercise.put("max",max);
        serverRequest.sendToServer(url,newExercise,"Post");


    }

    @Override
    public void onSuccess() {
            i.showText("Saved");
    }

    @Override
    public void onError() {
        i.showText("Error");
    }
}
