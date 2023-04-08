package com.example.fitnessapp.Logic;

import android.graphics.Color;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class layoutLogic {
    public  static void changeAllBGCForBtns(ViewGroup rootView, int BGColor, int TxtColor){
        for (int i = 0; i < rootView.getChildCount(); i++) {
            View child = rootView.getChildAt(i);
            if (child instanceof Button) {
                Button button = (Button) child;
                button.setBackgroundColor(BGColor);
                button.setTextColor(TxtColor);
            }
        }

    }
    public static void setAllTxtColor(ViewGroup rootView,int color){
        for (int i = 0; i < rootView.getChildCount(); i++) {
            View view = rootView.getChildAt(i);
            if (view instanceof TextView) {
                // Set the text color of the TextView to white
                ((TextView) view).setTextColor(color);
            }
            if (view instanceof EditText){
                ((EditText) view).setTextColor(color);
            }
        }
    }
    public static void defBtnColor(ViewGroup rootView){
        changeAllBGCForBtns(rootView,Color.WHITE,Color.BLACK);
    }
    public  static void changeAllBGCForBtns(ScrollView rootView, int BGColor, int TxtColor){
        for (int i = 0; i < rootView.getChildCount(); i++) {
            View child = rootView.getChildAt(i);
            if (child instanceof Button) {
                Button button = (Button) child;
                button.setBackgroundColor(BGColor);
                button.setTextColor(TxtColor);
            }
        }

    }
    public static void setAllTxtColor(ScrollView rootView,int color){
        for (int i = 0; i < rootView.getChildCount(); i++) {
            View view = rootView.getChildAt(i);
            if (view instanceof TextView) {
                // Set the text color of the TextView to white
                ((TextView) view).setTextColor(color);
            }
            if (view instanceof EditText){
                ((EditText) view).setTextColor(color);
            }
        }
    }
    public static void defBtnColor(ScrollView rootView){
        changeAllBGCForBtns(rootView,Color.WHITE,Color.BLACK);
    }
    public  static void changeAllBGCForBtns(LinearLayout rootView, int BGColor, int TxtColor){
        for (int i = 0; i < rootView.getChildCount(); i++) {
            View child = rootView.getChildAt(i);
            if (child instanceof Button) {
                Button button = (Button) child;
                button.setBackgroundColor(BGColor);
                button.setTextColor(TxtColor);
            }
        }

    }
    public static void setAllTxtColor(LinearLayout rootView,int color){
        for (int i = 0; i < rootView.getChildCount(); i++) {
            View view = rootView.getChildAt(i);
            if (view instanceof TextView) {
                // Set the text color of the TextView to white
                ((TextView) view).setTextColor(color);
            }
            if (view instanceof EditText){
                ((EditText) view).setTextColor(color);
            }
        }
    }
    public static void defBtnColor(LinearLayout rootView){
        changeAllBGCForBtns(rootView,Color.WHITE,Color.BLACK);
    }
}