package com.example.fitnessapp.Logic;

import com.example.fitnessapp.IView;
import com.example.fitnessapp.Network.IServerRequest;

import org.json.JSONObject;

public class getStatsLogic implements IVolleyListener{
    IView i;
    IServerRequest serverRequest;
    public getStatsLogic(IView i, IServerRequest serverRequest){
        this.i =i;
        this.serverRequest =serverRequest;
        serverRequest.addVolleyListener(this);
    }

    /**
     * JsonObject request
     * Get 1 Exercise as JsonObject
     */
    public JSONObject getExercise(){
        final String url = "TBD";
        serverRequest.sendToServer(url,null,"Post");
        JSONObject exercise = serverRequest.getObject();
        return exercise;
    }


    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }
}
