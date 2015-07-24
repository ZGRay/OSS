//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.orm.item;

import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class DateTimeUtils {
    public static final int DATETIME_FIELD_REFERSH = 10;
    public static final long ONE_SECOND = 1000L;
    public static final long ONE_MINUTE = 60000L;
    public static final long ONE_HOUR = 3600000L;
    public static final long ONE_DAY = 86400000L;
    public static final String MM_Yue_dd_Ri = "MM月dd日";
    public static final String M_Yue_d_Ri = "M月d日";
    public static final String d_Ri = "d日";
    public static final String yyyyMMdd = "yyyyMMdd";
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    public static final String yyyy_MM = "yyyy-MM";
    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String HH_mm = "HH:mm";
    public static final String yyyy_Nian_MM_Yue_dd_Ri = "yyyy年MM月dd日";
    public static final String yyyy_Nian_MM_Yue = "yyyy年MM月";
    public static final String MM_yy = "MM/yy";
    public static final String dd_MM = "dd/MM";
    public static final String MM_dd = "MM-dd";
    public static final String HH_mm_ss = "HH:mm:ss";
    private static final String[] PATTERNS = new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd", "yyyyMMdd"};
    public static final String KEY_TSLGAPM = "chaos.liu.tslgapm";
    public static boolean hasServerTime;
    public static long tslgapm;
    public static String tss;
    private static String[] weekdays = new String[]{"", "周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    private static String[] weekdays1 = new String[]{"", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    public DateTimeUtils() {
    }


    public static <T> Calendar getCalendar(T src, Calendar fallback) {
        if(src != null) {
            try {
                return getCalendar(src);
            } catch (Exception var3) {
                ;
            }
        }

        return (Calendar)fallback.clone();
    }

    public static <T> Calendar getCalendar(T src) {
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        if(src == null) {
            return null;
        } else {
            if(src instanceof Calendar) {
                calendar.setTimeInMillis(((Calendar)src).getTimeInMillis());
            } else if(src instanceof Date) {
                calendar.setTime((Date)src);
            } else if(src instanceof Long) {
                calendar.setTimeInMillis(((Long)src).longValue());
            } else {
                if(!(src instanceof String)) {
                    throw new IllegalArgumentException();
                }

                String nSrc = (String)src;
                if(TextUtils.isEmpty(nSrc)) {
                    return null;
                }

                try {
                    if(Pattern.compile("\\d{4}年\\d{1,2}月\\d{1,2}日").matcher(nSrc).find()) {
                        nSrc = fixDateString(nSrc);
                        return getCalendarByPattern(nSrc, "yyyy-MM-dd");
                    }

                    return getCalendarByPatterns(nSrc, PATTERNS);
                } catch (Exception var6) {
                    try {
                        calendar.setTimeInMillis(Long.valueOf(nSrc).longValue());
                    } catch (NumberFormatException var5) {
                        throw new IllegalArgumentException(var5);
                    }
                }
            }

            return calendar;
        }
    }

    private static String fixDateString(String date) {
        if(TextUtils.isEmpty(date)) {
            return date;
        } else {
            String[] dateArray = date.split("[年月日]");
            if(dateArray.length == 1) {
                dateArray = date.split("-");
            }

            for(int i = 0; i < 3; ++i) {
                if(dateArray[i].length() == 1) {
                    dateArray[i] = "0" + dateArray[i];
                }
            }

            return dateArray[0] + "-" + dateArray[1] + "-" + dateArray[2];
        }
    }

    public static Calendar getCalendarByPattern(String dateTimeStr, String patternStr) {
        try {
            SimpleDateFormat e = new SimpleDateFormat(patternStr, Locale.US);
            e.setLenient(false);
            Date d = e.parse(dateTimeStr);
            Calendar c = Calendar.getInstance();
            c.setLenient(false);
            c.setTimeInMillis(d.getTime());
            return c;
        } catch (Exception var5) {
            throw new IllegalArgumentException(var5);
        }
    }

    public static Calendar getCalendarByPatterns(String dateTimeStr, String[] patternStr) {
        String[] arr$ = patternStr;
        int len$ = patternStr.length;
        int i$ = 0;

        while(i$ < len$) {
            String string = arr$[i$];

            try {
                return getCalendarByPattern(dateTimeStr, string);
            } catch (Exception var7) {
                ++i$;
            }
        }

        throw new IllegalArgumentException();
    }

    public static Calendar getCurrentDateTime() {
        Calendar now = Calendar.getInstance();
        now.setLenient(false);
        if(hasServerTime) {
            now.setTimeInMillis(now.getTimeInMillis() + tslgapm);
        }

        return now;
    }

    public static Calendar getLoginServerDate() {
        return getCalendar(tss);
    }


    public static long getIntervalTimes(Calendar from, Calendar to, long unit) {
        return from != null && to != null?Math.abs(from.getTimeInMillis() - to.getTimeInMillis()) / unit:0L;
    }





    public static boolean isRefersh(long beforeTime) {
        return isRefersh(600000L, beforeTime);
    }

    public static boolean isRefersh(long gap, long beforeTime) {
        return (new Date()).getTime() - beforeTime >= gap;
    }

    public static String printCalendarByPattern(Calendar c, String patternStr) {
        if(null != c && null != patternStr) {
            SimpleDateFormat sdf = new SimpleDateFormat(patternStr, Locale.US);
            sdf.setLenient(false);
            return sdf.format(c.getTime());
        } else {
            return null;
        }
    }

}
