package com.date;


import com.alibaba.druid.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 时间工具
 * @Author: huangyunquan
 * @Description:Created on 2018/4/11 14:18.
 */
public class DateHelper {
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_TIMEMIN = "yyyyMMddHHmm";
    public static final String PATTERN_DATE = "yyyy-MM-dd";
    public static final String PATTERN_DATE_MIN = "yyyy-MM-dd HH:mm";


    public static int carlendarValue(Date date,int carlendarField){
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int fieldValue = instance.get(carlendarField);
        return fieldValue;
    }

    public static Date parseTime(String dateStr,String pattern) throws ParseException {
        if(null == pattern || "".equals(pattern)){
            pattern = DEFAULT_PATTERN;
        }
        return new SimpleDateFormat(pattern).parse(dateStr);
    }

    public static Date parseTime(String dateStr) throws ParseException {
        return parseTime(dateStr,DEFAULT_PATTERN);
    }

    public static String formatTime(Date date,String pattern){
        if(null == pattern || "".equals(pattern)){
            pattern = DEFAULT_PATTERN;
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String formatTime(Date date){
        return formatTime(date,DEFAULT_PATTERN);
    }


    /**
     * 获取目标时间
     * @param dateStr 作用时间
     * @param field  Calendar field
     * @param interval  时间间隔
     * @return
     * @throws ParseException
     */
    public static Date getTargetDate(String dateStr, int field,int interval) throws ParseException {
        if(null == dateStr || "".equals(dateStr)){
            return null;
        }
        Date date = parseTime(dateStr);
        return getTargetDate(date,field,interval);
    }

    public static Date getTargetDate(Date date, int field,int interval) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field,interval);

        Date targetDate = calendar.getTime();
        return targetDate;
    }

    public static String getTargetDateStr(String dateStr, int field,int interval) throws ParseException {
        Date targetDate = getTargetDate(dateStr, field, interval);
        return formatTime(targetDate,DEFAULT_PATTERN);
    }

    /**
     * 根据时间间隔获取时间坐标组
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param intervalMilliSecond 时间间隔 单位毫秒
     * @return
     */
    public static List<String> bulidIntervalTime(String startTime, String endTime, long intervalMilliSecond) throws ParseException {
        long start = formatMinuteWithGap(startTime, 5);
        long end = formatMinuteWithGap(endTime, 5);
        List<String> list = new LinkedList<>();
        while (start <= end){
            list.add(DateHelper.formatTime(new Date(start),"HH:mm:ss"));
            start += intervalMilliSecond;
        }
        return list;
    }

    /**
     * 按间隔大小 格式化分钟数
     * @param targetDate 目标时间
     * @param gap 间隔
     * @return 毫秒数
     * @throws ParseException
     */
    public static long  formatMinuteWithGap( String targetDate,int gap) throws ParseException {
        Date date = formatMinuteWithGapDate(targetDate, gap);
        long timeLong = date.getTime();
        return timeLong;
    }

    public static Date  formatMinuteWithGapDate( String targetDate,int gap) throws ParseException {
        if(StringUtils.isEmpty(targetDate)){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date tempDate = sdf.parse(targetDate);
        return formatMinuteWithGapDate(tempDate,gap);
    }

    public static String  formatMinuteWithGapDateStr( String targetDate,int gap) throws ParseException {
        Date date = formatMinuteWithGapDate(targetDate, gap);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static Date  formatMinuteWithGapDate( Date targetDate,int gap) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(targetDate);
        int fromTimeMinute = calendar.get(Calendar.MINUTE);
        calendar.set(Calendar.MINUTE,((fromTimeMinute / gap) * gap));
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        Date time = calendar.getTime();
        return time;
    }

    //获取时间间隔分钟数
    public static long getTimeGapMin(Date start,Date end){
        return (end.getTime() - start.getTime()) / (1000 * 60);
    }

    public static Date firstDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     *
     * 获取当前时间的开始，保留小时数
     * 比如当前时间：2018-06-27 14点28分03秒则返回2018-06-27 14点00分00秒
     * @param date
     * @return
     */
    public static Date hourStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     *
     * 获取当前时间的结束，保留小时
     * 比如当前时间：2018-06-27 14点28分03秒则返回2018-06-27 14点59分59秒
     * @param date
     * @return
     */
    public static  Date hourEnd(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }



    public static Date lastDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }


}
