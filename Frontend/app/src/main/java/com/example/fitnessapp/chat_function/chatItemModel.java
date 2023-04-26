package com.example.fitnessapp.chat_function;

public class chatItemModel {
    String userName;
    int icon;

    public chatItemModel(String userName, int icon) {
        this.userName = userName;
        this.icon = icon;
    }

    public String getUserName() {
        return userName;
    }

    public int getIcon() {
        return icon;
    }
}
