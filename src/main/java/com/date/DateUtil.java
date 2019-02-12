

package com.date;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class DateUtil {
    public static final String DATE_FORMAT_YYYY = "yyyy";
    public static final String DATE_FORMAT_YYYYMM = "yyyyMM";
    public static final String DATE_FORMAT_YYYY_MM = "yyyy-MM";
    public static final String DATE_FORMAT_YYMMDD = "yyMMdd";
    public static final String DATE_FORMAT_YY_MM_DD = "yy-MM-dd";
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_POINTYYYYMMDD = "yyyy.MM.dd";
    public static final String DATE_TIME_FORMAT_YYYY年MM月DD日 = "yyyy年MM月dd日";
    public static final String DATE_FORMAT_YYYYMMDDHHmm = "yyyyMMddHHmm";
    public static final String DATE_TIME_FORMAT_YYYYMMDD_HH_MI = "yyyyMMdd HH:mm";
    public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI = "yyyy-MM-dd HH:mm";
    public static final String DATE_TIME_FORMAT_YYYYMMDDHHMISS = "yyyyMMddHHmmss";
    public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_FORMAT_YYYYMMDDHHMISSSSS = "yyyyMMddHHmmssSSS";
    public static final String DATE_FORMAT_MMDDHHMI = "MM-dd HH:mm";
    public static final String DATE_FORMAT_HHMI = ":HH:mm";

    public DateUtil() {
    }

    public static Integer getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(1);
    }

    public static Integer getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(2) + 1;
    }

    public static Integer getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(5);
        return day;
    }

    public static String parseDateToStr(Date time, String timeFromat) {
        DateFormat dateFormat = new SimpleDateFormat(timeFromat);
        return dateFormat.format(time);
    }

    public static String parseTimestampToStr(Timestamp timestamp, String timeFromat) {
        SimpleDateFormat df = new SimpleDateFormat(timeFromat);
        return df.format(timestamp);
    }

    public static String parseDateToStr(Date time, String timeFromat, Date defaultValue) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(timeFromat);
            return dateFormat.format(time);
        } catch (Exception var4) {
            return defaultValue != null ? parseDateToStr(defaultValue, timeFromat) : parseDateToStr(new Date(), timeFromat);
        }
    }

    public static String parseDateToStr(Date time, String timeFromat, String defaultValue) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(timeFromat);
            return dateFormat.format(time);
        } catch (Exception var4) {
            return defaultValue;
        }
    }

    public static Date parseStrToDate(String time, String timeFromat) {
        if (time != null && !time.equals("")) {
            Date date = null;

            try {
                DateFormat dateFormat = new SimpleDateFormat(timeFromat);
                date = dateFormat.parse(time);
            } catch (Exception var4) {
                ;
            }

            return date;
        } else {
            return null;
        }
    }

    public static Date parseStrToDate(String strTime, String timeFromat, Date defaultValue) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(timeFromat);
            return dateFormat.parse(strTime);
        } catch (Exception var4) {
            return defaultValue;
        }
    }

    public static Date strToDate(String strTime) {
        if (strTime != null && strTime.trim().length() > 0) {
            Date date = null;
            List<String> list = new ArrayList(0);
            list.add("yyyy-MM-dd HH:mm:ss");
            list.add("yyyyMMddHHmmssSSS");
            list.add("yyyy-MM-dd HH:mm");
            list.add("yyyyMMdd HH:mm");
            list.add("yyyyMMddHHmmss");
            list.add("yyyy-MM-dd");
            list.add("yyyyMMdd");
            list.add("yyyy-MM");
            list.add("yyyyMM");
            list.add("yyyy");
            Iterator iter = list.iterator();

            while(iter.hasNext()) {
                String format = (String)iter.next();
                if ((strTime.indexOf("-") <= 0 || format.indexOf("-") >= 0) && (strTime.indexOf("-") >= 0 || format.indexOf("-") <= 0) && strTime.length() <= format.length()) {
                    date = parseStrToDate(strTime, format);
                    if (date != null) {
                        break;
                    }
                }
            }

            return date;
        } else {
            return null;
        }
    }

    public static List<String> getMonthListOfDate(String beginDateStr, String endDateStr) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
        String sRet = "";
        Date beginDate = null;
        Date endDate = null;
        GregorianCalendar beginGC = null;
        GregorianCalendar endGC = null;
        ArrayList list = new ArrayList();

        try {
            beginDate = f.parse(beginDateStr);
            endDate = f.parse(endDateStr);
            beginGC = new GregorianCalendar();
            beginGC.setTime(beginDate);
            endGC = new GregorianCalendar();
            endGC.setTime(endDate);

            while(beginGC.getTime().compareTo(endGC.getTime()) <= 0) {
                sRet = beginGC.get(1) + "-" + (beginGC.get(2) + 1);
                list.add(sRet);
                beginGC.add(2, 1);
            }

            return list;
        } catch (Exception var10) {
            var10.printStackTrace();
            return null;
        }
    }

    public static List<String> getDayListOfDate(String beginDateStr, String endDateStr) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;
        Calendar beginGC = null;
        Calendar endGC = null;
        ArrayList list = new ArrayList();

        try {
            beginDate = f.parse(beginDateStr);
            endDate = f.parse(endDateStr);
            beginGC = Calendar.getInstance();
            beginGC.setTime(beginDate);
            endGC = Calendar.getInstance();
            endGC.setTime(endDate);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            while(beginGC.getTime().compareTo(endGC.getTime()) <= 0) {
                list.add(sdf.format(beginGC.getTime()));
                beginGC.add(5, 1);
            }

            return list;
        } catch (Exception var9) {
            var9.printStackTrace();
            return null;
        }
    }

    public static List<Integer> getYearListOfYears(int before, int behind) {
        if (before >= 0 && behind >= 0) {
            List<Integer> list = new ArrayList();
            Calendar c = null;
            c = Calendar.getInstance();
            c.setTime(new Date());
            int currYear = Calendar.getInstance().get(1);
            int startYear = currYear - before;
            int endYear = currYear + behind;

            for(int i = startYear; i < endYear; ++i) {
                list.add(i);
            }

            return list;
        } else {
            return null;
        }
    }

    public static Integer getWeekthOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(2);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);
        return c.get(3);
    }

    public static HashMap<Integer, String> getWeekTimeOfYear(int year) {
        HashMap<Integer, String> map = new LinkedHashMap();
        Calendar c = new GregorianCalendar();
        c.set(year, 11, 31, 23, 59, 59);
        int count = getWeekthOfYear(c.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dayOfWeekStart = "";
        String dayOfWeekEnd = "";

        for(int i = 1; i <= count; ++i) {
            dayOfWeekStart = sdf.format(getFirstDayOfWeek(year, i));
            dayOfWeekEnd = sdf.format(getLastDayOfWeek(year, i));
            map.put(i, "第" + i + "周(从" + dayOfWeekStart + "至" + dayOfWeekEnd + ")");
        }

        return map;
    }

    public static Integer getWeekCountOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, 11, 31, 23, 59, 59);
        int count = getWeekthOfYear(c.getTime());
        return count;
    }

    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(2);
        c.setTime(date);
        c.set(7, c.getFirstDayOfWeek());
        return c.getTime();
    }

    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(2);
        c.setTime(date);
        c.set(7, c.getFirstDayOfWeek() + 6);
        return c.getTime();
    }

    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(1, year);
        c.set(2, 0);
        c.set(5, 1);
        Calendar cal = (GregorianCalendar)c.clone();
        cal.add(5, week * 7);
        return getFirstDayOfWeek(cal.getTime());
    }

    public static Date getLastDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(1, year);
        c.set(2, 0);
        c.set(5, 1);
        Calendar cal = (GregorianCalendar)c.clone();
        cal.add(5, week * 7);
        return getLastDayOfWeek(cal.getTime());
    }

    public static Date getFirstDayOfMonth(int year, int month) {
        --month;
        Calendar c = Calendar.getInstance();
        c.set(1, year);
        c.set(2, month);
        int day = c.getActualMinimum(5);
        c.set(5, day);
        c.set(11, 0);
        c.set(12, 0);
        c.set(13, 0);
        c.set(14, 0);
        return c.getTime();
    }

    public static Date getLastDayOfMonth(int year, int month) {
        --month;
        Calendar c = Calendar.getInstance();
        c.set(1, year);
        c.set(2, month);
        int day = c.getActualMaximum(5);
        c.set(5, day);
        c.set(11, 23);
        c.set(12, 59);
        c.set(13, 59);
        c.set(14, 999);
        return c.getTime();
    }

    public static String getDayWeekOfDate1(Date date) {
        String[] weekDays = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(7) - 1;
        if (w < 0) {
            w = 0;
        }

        return weekDays[w];
    }

    public static Integer getDayWeekOfDate2(Date date) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(date);
        int weekDay = aCalendar.get(7);
        return weekDay;
    }

    public static boolean validateIsDate(String strTime) {
        if (strTime != null && strTime.trim().length() > 0) {
            Date date = null;
            List<String> list = new ArrayList(0);
            list.add("yyyy-MM-dd HH:mm:ss");
            list.add("yyyyMMddHHmmssSSS");
            list.add("yyyy-MM-dd HH:mm");
            list.add("yyyyMMdd HH:mm");
            list.add("yyyyMMddHHmmss");
            list.add("yyyy-MM-dd");
            list.add("yyyyMMdd");
            Iterator iter = list.iterator();

            while(iter.hasNext()) {
                String format = (String)iter.next();
                if ((strTime.indexOf("-") <= 0 || format.indexOf("-") >= 0) && (strTime.indexOf("-") >= 0 || format.indexOf("-") <= 0) && strTime.length() <= format.length()) {
                    date = parseStrToDate(strTime.trim(), format);
                    if (date != null) {
                        break;
                    }
                }
            }

            return date != null;
        } else {
            return false;
        }
    }

    public static Date formatHhMmSsOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    public static Date addDate(Date date, int year, int month, int day, int hour, int minute, int second, int millisecond) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(1, year);
        c.add(2, month);
        c.add(5, day);
        c.add(10, hour);
        c.add(12, minute);
        c.add(13, second);
        c.add(14, millisecond);
        return c.getTime();
    }

    public static Long getDistanceTimestamp(Date startDate, Date endDate) {
        long daysBetween = (endDate.getTime() - startDate.getTime() + 1000000L) / 86400000L;
        return daysBetween;
    }

    public static Boolean compareIsSameMonth(Date date1, Date date2) {
        boolean flag = false;
        int year1 = getYear(date1);
        int year2 = getYear(date2);
        if (year1 == year2) {
            int month1 = getMonth(date1);
            int month2 = getMonth(date2);
            if (month1 == month2) {
                flag = true;
            }
        }

        return flag;
    }

    public static long[] getDistanceTime(Date one, Date two) {
        long day = 0L;
        long hour = 0L;
        long min = 0L;
        long sec = 0L;

        try {
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }

            day = diff / 86400000L;
            hour = diff / 3600000L - day * 24L;
            min = diff / 60000L - day * 24L * 60L - hour * 60L;
            sec = diff / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L - min * 60L;
        } catch (Exception var16) {
            var16.printStackTrace();
        }

        long[] times = new long[]{day, hour, min, sec};
        return times;
    }

    public static long[] getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long day = 0L;
        long hour = 0L;
        long min = 0L;
        long sec = 0L;

        try {
            Date one = df.parse(str1);
            Date two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }

            day = diff / 86400000L;
            hour = diff / 3600000L - day * 24L;
            min = diff / 60000L - day * 24L * 60L - hour * 60L;
            sec = diff / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L - min * 60L;
        } catch (ParseException var19) {
            var19.printStackTrace();
        }

        long[] times = new long[]{day, hour, min, sec};
        return times;
    }

    public static Long getDistanceDays(String str1, String str2) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long days = 0L;

        try {
            Date one = df.parse(str1);
            Date two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }

            days = diff / 86400000L;
        } catch (ParseException var13) {
            var13.printStackTrace();
        }

        return days;
    }

    public static Date getDayBeginTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(11, 0);
        c.set(12, 0);
        c.set(13, 0);
        c.set(14, 0);
        return c.getTime();
    }

    public static Date getBeginDayOfLastWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int dayofweek = cal.get(7);
            if (dayofweek == 1) {
                dayofweek += 7;
            }

            cal.add(5, 2 - dayofweek - 7);
            return getDayStartTime(cal.getTime());
        }
    }

    public static Date getEndDayOfLastWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfLastWeek());
        cal.add(7, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }

    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }

        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 0, 0, 0);
        calendar.set(14, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }

        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 23, 59, 59);
        calendar.set(14, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    public static Date getBeginDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 2, 1);
        return getDayStartTime(calendar.getTime());
    }

    public static Date getEndDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 2, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 2, day);
        return getDayEndTime(calendar.getTime());
    }

    public static Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
        gc.setTime(date);
        return gc.get(1);
    }

    public static int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar)Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }
}
