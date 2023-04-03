package com.example.fitnessapp.Logic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    public String getCurrentDay() {
        return currentDay;
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
        switch (currentDay){
            case "Monday":
                c.add(Calendar.DAY_OF_YEAR,-1);
                break;
            case "Tuesday":
                c.add(Calendar.DAY_OF_YEAR,-2);
                break;
            case "Wednesday":
                c.add(Calendar.DAY_OF_YEAR,-3);
                break;
            case "Thursday":
                c.add(Calendar.DAY_OF_YEAR,-4);
                break;
            case "Friday":
                c.add(Calendar.DAY_OF_YEAR,-5);
                break;
            case "Saturday":
                c.add(Calendar.DAY_OF_YEAR,-6);
                break;
        }
        Date date = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String formattedDate = sdf.format(date);
        return formattedDate;
    }
    public String DateMonday(){
        Calendar c = Calendar.getInstance();
        switch (day-1){
            case 0:
                c.add(Calendar.DAY_OF_YEAR,1);
                break;
            case 2:
                c.add(Calendar.DAY_OF_YEAR,-1);
                break;
            case 3:
                c.add(Calendar.DAY_OF_YEAR,-2);
                break;
            case 4:
                c.add(Calendar.DAY_OF_YEAR,-3);
                break;
            case 5:
                c.add(Calendar.DAY_OF_YEAR,-4);
                break;
            case 6:
                c.add(Calendar.DAY_OF_YEAR,-5);
                break;
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
                break;
            case 1:
                c.add(Calendar.DAY_OF_YEAR,1);
                break;
            case 3:
                c.add(Calendar.DAY_OF_YEAR,-1);
                break;
            case 4:
                c.add(Calendar.DAY_OF_YEAR,-2);
                break;
            case 5:
                c.add(Calendar.DAY_OF_YEAR,-3);
                break;
            case 6:
                c.add(Calendar.DAY_OF_YEAR,-4);
                break;
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
                break;
            case 1:
                c.add(Calendar.DAY_OF_YEAR,2);
                break;
            case 2:
                c.add(Calendar.DAY_OF_YEAR,1);
                break;
            case 4:
                c.add(Calendar.DAY_OF_YEAR,-1);
                break;
            case 5:
                c.add(Calendar.DAY_OF_YEAR,-2);
                break;
            case 6:
                c.add(Calendar.DAY_OF_YEAR,-3);
                break;
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
                break;
            case 1:
                c.add(Calendar.DAY_OF_YEAR,3);
                break;
            case 2:
                c.add(Calendar.DAY_OF_YEAR,2);
                break;
            case 3:
                c.add(Calendar.DAY_OF_YEAR,1);
                break;
            case 5:
                c.add(Calendar.DAY_OF_YEAR,-1);
                break;
            case 6:
                c.add(Calendar.DAY_OF_YEAR,-2);
                break;
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
                break;
            case 1:
                c.add(Calendar.DAY_OF_YEAR,4);
                break;
            case 2:
                c.add(Calendar.DAY_OF_YEAR,3);
                break;
            case 3:
                c.add(Calendar.DAY_OF_YEAR,2);
                break;
            case 4:
                c.add(Calendar.DAY_OF_YEAR,1);
                break;
            case 6:
                c.add(Calendar.DAY_OF_YEAR,-1);
                break;
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
                break;
            case 1:
                c.add(Calendar.DAY_OF_YEAR,5);
                break;
            case 2:
                c.add(Calendar.DAY_OF_YEAR,4);
                break;
            case 3:
                c.add(Calendar.DAY_OF_YEAR,3);
                break;
            case 4:
                c.add(Calendar.DAY_OF_YEAR,2);
                break;
            case 5:
                c.add(Calendar.DAY_OF_YEAR,1);
                break;
        }
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        String out = year+"/"+month+"/"+dayOfMonth;
        return out;
    }
}
