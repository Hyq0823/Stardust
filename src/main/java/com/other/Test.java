package com.other;

import com.date.DateHelper;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws ParseException {
        String time = "2018-11-07 17:02:00";
        int interval = 5;


        //根据当前时间 和计算时间间隔,获取时间段
        //比如当前2018-11-07 17:46:00 时间步长 为5分钟
        //则时间段为 2018-11-07 17:40:00 --> 2018-11-07 17:45:00
        //得出
        Date date = DateHelper.parseTime(time,DateHelper.PATTERN_TIMEMIN);
        int minute = DateHelper.carlendarValue(date,Calendar.MINUTE);
        int gap = minute % interval;
        Date endDate = DateHelper.getTargetDate(date, Calendar.MINUTE, -gap);
        Date beginDate  = DateHelper.getTargetDate(endDate, Calendar.MINUTE, -interval);
        System.out.println(DateHelper.formatTime(beginDate));
        System.out.println(DateHelper.formatTime(endDate));

//        List<String> list = new ArrayList<String>();
//        list.add("h");
//        list.add("y");
//        list.add("q");
//
//        for(String s : list){
//            System.out.println("外部:"+list.size());
//            if("q".equals(s)){
//                list.remove(s);
//            }
//        }
    }
}
