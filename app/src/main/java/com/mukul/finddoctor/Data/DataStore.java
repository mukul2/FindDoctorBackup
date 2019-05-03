package com.mukul.finddoctor.Data;

import com.mukul.finddoctor.R;
import com.mukul.finddoctor.model.SpacialistModel;
import com.mukul.finddoctor.model.TestModel;
import com.mukul.finddoctor.model.testSelectedModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mukul on 3/10/2019.
 */

public class DataStore {
    public static String convertToWeekDay(String day) {
        Map<String, String> days = new HashMap<>();
        days.put("6", "Sat");
        days.put("0", "Sun");
        days.put("1", "Mon");
        days.put("2", "Tues");
        days.put("3", "Wednes");
        days.put("4", "Thurs");
        days.put("5", "Fri");

        return days.get(day);
    }

    public static String convertDayToNmber(String day) {
        Map<String, String> days = new HashMap<>();
        days.put("Sat", "6");
        days.put("Sun", "0");
        days.put("Mon", "1");
        days.put("Tues", "2");
        days.put("Wednes", "3");
        days.put("Thurs", "4");
        days.put("Fri", "5");

        return days.get(day);
    }
    public static String changeDateformate(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sourceDate = null;
        try {
            sourceDate = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat targetFormat = new SimpleDateFormat("MMM dd");
        return targetFormat.format(sourceDate);
    }

    public static List<String> sevenDays() {
        List<String> days = new ArrayList<>();
        days.add("Sat");
        days.add("Sun");
        days.add("Mon");
        days.add("Tues");
        days.add("Wednes");
        days.add("Thurs");
        days.add("Fri");

        return days;
    }

    public static String convertToMonth(int day) {
        Map<Integer, String> days = new HashMap<>();
        days.put(0, "Jan");
        days.put(1, "Feb");
        days.put(2, "March");
        days.put(3, "Appril");
        days.put(4, "May");
        days.put(5, "June");
        days.put(6, "Jully");
        days.put(7, "August");
        days.put(8, "Sept");
        days.put(9, "Oct");
        days.put(10, "Nov");
        days.put(11, "Dec");

        return days.get(day);
    }

    public static List<testSelectedModel> testModelList = new ArrayList<>();
    public static List<String> selectedTestIds = new ArrayList<>();


}