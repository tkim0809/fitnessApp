package com.example.fitnessapp.Logic;

import com.example.fitnessapp.IView;
import com.example.fitnessapp.Network.IServerRequest;
import com.example.fitnessapp.Network.getStatsRequests;

public class getStatsLogic implements IVolleyListener{
    IView i;
    String name;
    String rep;
    String max;

    public String getName() {
        return name;
    }

    public String getRep() {
        return rep;
    }

    public String getMax() {
        return max;
    }
    IServerRequest serverRequest;
    public getStatsLogic(IView i, IServerRequest serverRequest){
        this.i =i;
        this.serverRequest =serverRequest;
        serverRequest.addVolleyListener(this);
    }

    /**
     * JsonObject request
     *
     */
    public void getExercise(){
        final String url = "https://52f9ae65-dabb-4c69-b849-73127aa5c466.mock.pstmn.io/stats";
        serverRequest.sendToServer(url,null,"Get");
        name = ((getStatsRequests)serverRequest).getName();
        rep = ((getStatsRequests)serverRequest).getRep();
        max = ((getStatsRequests)serverRequest).getMax();
    }


    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }
}
