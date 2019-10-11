package com.example.watchudrive;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeHandler {

    public String ConvertDateToReadableDate(String DateTime) {
        if (DateTime != null) {
            if (!DateTime.equals("")) {
                // the input should be in this format 2019-03-08 15:14:29
                //if not you have to change the pattern in SimpleDateFormat("yyyy-MM-dd hh:mm:ss")

                Date newDate;
                Date currentDate = new java.util.Date();
                int hour = 0, min = 0, sec = 0;
                String dayName = "", dayNum = "", monthName = "", year = "";
                long numOfMilliSecondPassed = 0;
                float milliSecond = 86400000.0f; // 1 day is 86400000 milliseconds
                float numOfDayPass;

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                try {
                    newDate = dateFormat.parse(DateTime); // convert String to date
                    numOfMilliSecondPassed = currentDate.getTime() - newDate.getTime(); //get the difference in date in millisecond

                    hour = Integer.parseInt((String) android.text.format.DateFormat.format("hh", newDate));
                    min = Integer.parseInt((String) android.text.format.DateFormat.format("mm", newDate));
                    sec = Integer.parseInt((String) android.text.format.DateFormat.format("ss", newDate));
                    dayName = (String) android.text.format.DateFormat.format("EEEE", newDate);
                    dayNum = (String) android.text.format.DateFormat.format("dd", newDate);
                    monthName = (String) android.text.format.DateFormat.format("MMM", newDate);
                    year = (String) android.text.format.DateFormat.format("yyyy", newDate);


                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //Convert the milliseconds to days
                numOfDayPass = (numOfMilliSecondPassed / milliSecond);


                if (numOfDayPass < 1) {
                    return hour + ":" + min + ":" + sec;
                } else if ((numOfDayPass >= 1) && (numOfDayPass < 7)) {
                    return dayName + " "+ hour + ":" + min + ":" + sec;
                } else if ((numOfDayPass >= 7) && (numOfDayPass < 30)) {
                    int weeks = (int) numOfDayPass / 7;

                    if(weeks > 1) {
                        return weeks + " weeks ago";
                    }else{
                        return weeks + " week ago";
                    }
                }else if((numOfDayPass >= 30) && (numOfDayPass < 90) ){
                    int months = (int) numOfDayPass/30;

                    if(months > 1) {
                        return months + " months ago";
                    }else{
                        return months + " month ago";
                    }
                }else if((numOfDayPass >= 360) && (numOfDayPass < 1080) ){
                    int years = (int) numOfDayPass/360;

                    if(years > 1) {
                        return years + " years ago";
                    }else{
                        return years + " year ago";
                    }
                }else{
                    return dayName + " " + dayNum + " " + monthName + " " + year + " "+
                            hour + ":" + min + ":" + sec;
                }

            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
