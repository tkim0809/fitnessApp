package com.example.fitnessapp.Logic;



import com.example.fitnessapp.IView;
import com.example.fitnessapp.Network.IServerRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class StatsUpdateLogic implements IVolleyListener {
    IView i;
    IServerRequest serverRequest;

    public StatsUpdateLogic(IView i, IServerRequest serverRequest) {
        this.i = i;
        this.serverRequest = serverRequest;
        serverRequest.addVolleyListener(this);
    }


//JSONOBJECT{
    //"name":"input"
    //"rep":"input"
    //"max":"input"
//}

    public void addExercise(String name,String sets, String reps, String weight) throws JSONException {
        final String url = "https://52f9ae65-dabb-4c69-b849-73127aa5c466.mock.pstmn.io/exe";
        JSONObject newExercise = new JSONObject();
        newExercise.put("workoutName",name);
        newExercise.put("workoutSets",sets);
        newExercise.put("workoutReps",reps);
        newExercise.put("workoutWeight",weight);

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
