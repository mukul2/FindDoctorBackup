package com.mukul.finddoctor.model;

/**
 * Created by mukul on 4/22/2019.
 */

public class DaysTimeModel {
    String dayName;
    String startTime;
    String endTime;

    public DaysTimeModel() {
    }

    public DaysTimeModel(String dayName, String startTime, String endTime) {
        this.dayName = dayName;
        this.startTime = startTime;
        this.endTime = endTime;
    }



    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
