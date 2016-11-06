package com.sgay.giligili.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by xurancrazy on 2016/11/1.
 */
public class Utils {

    public static SimpleDateFormat createSimpleDateFormat(String pattern){
        return new SimpleDateFormat(pattern);
    }

    public static Date getToday(){
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        return cal.getTime();
    }

    public static Date getYesterday(){
        return getDayOffsetFromBasicDay(getToday(),1);
    }

    public static Date getDayOffsetFromBasicDay(Date date, int offset){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY,-(24 * offset));
        return cal.getTime();
    }

    public static String getDateFormatString(Date date){
        return createSimpleDateFormat(Constants.SF_PATTERN).format(date);
    }

}
