package com.yiban.suoai.util;

import java.util.Date;

public class AppUtil {

    public static long getTime() {
        Date date = new Date();
        return date.getTime();
    }

    public static Date getDate() {
        Date date = new Date();
        return date;
    }
}
