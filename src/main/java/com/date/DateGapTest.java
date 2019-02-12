package com.date;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/12/12 22:17.
 */
public class DateGapTest {
    public static void main(String[] args) {
        String s = bulidQueryIndexByStep(EsIndex.SCENE_CONTROL.value(), "2018-12-09 09:00:00", "2018-12-09 09:00:00", EsQueryType.SEARCH);
        System.out.println(s);
    }

    /**
     * 构建查询索引
     * @param prefix 前缀 如 index_scene_tache_finish
     * @return /index_scene_tache_finish_20180918,index_scene_tache_finish_20180919/_search
     *
     * index_scene_control_20181209,index_scene_control_20181210,index_scene_control_20181211,index_scene_control_20181212,index_scene_control_20181213,index_scene_control_20181214,index_scene_control_20181215,index_scene_control_20181216,index_scene_control_20181217,index_scene_control_20181218,index_scene_control_20181219/_search
     */
    public static String bulidQueryIndexByStep(String prefix,String startTime,String endTime,EsQueryType queryType){
        String searchOrCount = queryType.value();
        String defaultIndex = "/"+ prefix + "_*/" + searchOrCount;

        if(StringUtils.isEmpty(startTime) || StringUtils.isEmpty(endTime)){
            return defaultIndex;
        }

        //容错
        if(prefix.endsWith("_")){
            prefix = prefix.substring(0,prefix.lastIndexOf("_"));
        }

        if(prefix.startsWith("/")){
            prefix = prefix.substring(prefix.indexOf("/") + 1);
        }
        try{
            //获取两个时间间隔的天
            List<String> dayListOfDate = DateUtil.getDayListOfDate(startTime, endTime);
            List<String> dayList = new ArrayList<>();
            for(String day : dayListOfDate){
                Date date = DateHelper.parseTime(day, DateHelper.PATTERN_ABOVEDAY);
                String formatTime = DateHelper.formatTime(date, DateHelper.PATTERN_YYYYMMDD);

                String join = String.join("_", prefix, formatTime);
                dayList.add(join);
            }
            String allItem = String.join(",", dayList);
            return "/" + allItem + "/" + searchOrCount;
        }catch (Exception e){
            e.printStackTrace();
            return "/"+ prefix + "_*/" + searchOrCount;
        }
    }
}
