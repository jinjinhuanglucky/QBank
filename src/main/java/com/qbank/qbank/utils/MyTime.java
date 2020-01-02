package com.qbank.qbank.utils;

import java.util.Calendar;

/**
 * @author wangyujie
 */
public class MyTime {

    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        return year + "." + month + "." + day + " " + hour + ":" + minute + ":" + second;
    }
}
