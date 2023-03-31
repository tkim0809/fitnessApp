package com.example.fitnessapp;

import java.util.Calendar;

public class DateLogic {
    Calendar calendar;
    String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    String currentDay;
    int day;
    public DateLogic() {
        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_WEEK);
        currentDay = days[day - 1];
    }
    public String getCurrentDate(){
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String out = year+"/"+month+"/"+dayOfMonth;
        return out;
    }
    public String DateSunday(){
        Calendar c = Calendar.getInstance();
        switch (day-1){
            case 1:
                c.add(Calendar.DAY_OF_YEAR,-1);
            case 2:
                c.add(Calendar.DAY_OF_YEAR,-2);
            case 3:
                c.add(Calendar.DAY_OF_YEAR,-3);
            case 4:
                c.add(Calendar.DAY_OF_YEAR,-4);
            case 5:
                c.add(Calendar.DAY_OF_YEAR,-5);
            case 6:
                c.add(Calendar.DAY_OF_YEAR,-6);
        }
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        String out = year+"/"+month+"/"+dayOfMonth;
        return out;
    }
    public String DateMonday(){
        Calendar c = Calendar.getInstance();
        switch (day-1){
            case 0:
                c.add(Calendar.DAY_OF_YEAR,1);
            case 2:
                c.add(Calendar.DAY_OF_YEAR,-1);
            case 3:
                c.add(Calendar.DAY_OF_YEAR,-2);
            case 4:
                c.add(Calendar.DAY_OF_YEAR,-3);
            case 5:
                c.add(Calendar.DAY_OF_YEAR,-4);
            case 6:
                c.add(Calendar.DAY_OF_YEAR,-5);
        }
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        String out = year+"/"+month+"/"+dayOfMonth;
        return out;
    }
    public String DateTuesday(){
        Calendar c = Calendar.getInstance();
        switch (day-1){
            case 0:
                c.add(Calendar.DAY_OF_YEAR,2);
            case 1:
                c.add(Calendar.DAY_OF_YEAR,1);
            case 3:
                c.add(Calendar.DAY_OF_YEAR,-1);
            case 4:
                c.add(Calendar.DAY_OF_YEAR,-2);
            case 5:
                c.add(Calendar.DAY_OF_YEAR,-3);
            case 6:
                c.add(Calendar.DAY_OF_YEAR,-4);
        }
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        String out = year+"/"+month+"/"+dayOfMonth;
        return out;
    }
    public String DateWednesday(){
        Calendar c = Calendar.getInstance();
        switch (day-1){
            case 0:
                c.add(Calendar.DAY_OF_YEAR,3);
            case 1:
                c.add(Calendar.DAY_OF_YEAR,2);
            case 2:
                c.add(Calendar.DAY_OF_YEAR,1);
            case 4:
                c.add(Calendar.DAY_OF_YEAR,-1);
            case 5:
                c.add(Calendar.DAY_OF_YEAR,-2);
            case 6:
                c.add(Calendar.DAY_OF_YEAR,-3);
        }
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        String out = year+"/"+month+"/"+dayOfMonth;
        return out;
    }
    public String DateThursday(){
        Calendar c = Calendar.getInstance();
        switch (day-1){
            case 0:
                c.add(Calendar.DAY_OF_YEAR,4);
            case 1:
                c.add(Calendar.DAY_OF_YEAR,3);
            case 2:
                c.add(Calendar.DAY_OF_YEAR,2);
            case 3:
                c.add(Calendar.DAY_OF_YEAR,1);
            case 5:
                c.add(Calendar.DAY_OF_YEAR,-1);
            case 6:
                c.add(Calendar.DAY_OF_YEAR,-2);
        }
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        String out = year+"/"+month+"/"+dayOfMonth;
        return out;
    }
    public String DateFriday(){
        Calendar c = Calendar.getInstance();
        switch (day-1){
            case 0:
                c.add(Calendar.DAY_OF_YEAR,5);
            case 1:
                c.add(Calendar.DAY_OF_YEAR,4);
            case 2:
                c.add(Calendar.DAY_OF_YEAR,3);
            case 3:
                c.add(Calendar.DAY_OF_YEAR,2);
            case 4:
                c.add(Calendar.DAY_OF_YEAR,1);
            case 6:
                c.add(Calendar.DAY_OF_YEAR,-1);
        }
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        String out = year+"/"+month+"/"+dayOfMonth;
        return out;
    }
    public String DateSaturday(){
        Calendar c = Calendar.getInstance();
        switch (day-1){
            case 0:
                c.add(Calendar.DAY_OF_YEAR,6);
            case 1:
                c.add(Calendar.DAY_OF_YEAR,5);
            case 2:
                c.add(Calendar.DAY_OF_YEAR,4);
            case 3:
                c.add(Calendar.DAY_OF_YEAR,3);
            case 4:
                c.add(Calendar.DAY_OF_YEAR,2);
            case 5:
                c.add(Calendar.DAY_OF_YEAR,1);
        }
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        String out = year+"/"+month+"/"+dayOfMonth;
        return out;
    }
}
