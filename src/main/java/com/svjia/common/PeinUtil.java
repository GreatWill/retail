package com.svjia.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 工具类
 * @auther: chenjw
 * @date: 2018/8/22 11:16
 */
public class PeinUtil {

    public static void main(String[] args) {
        int d = getDay("2018-01-13");
        System.out.println(d);
    }

    /**
     * Description: 返回具体时间 20170101
     * @auther: chenjw
     * @date: 2018/8/28 20:21
     */
    public static int getStartDateKey(String type){
        int start = getStartYear(type);
        switch (type){
            case PeinConstants.YEAR:
                break;
            case PeinConstants.DAY:
                start = start*10000+101;
                break;
            default:
                start = start*100+1;
        }
        return start;
    }

    /**
     * Description: 返回起始年份
     * @auther: chenjw
     * @date: 2018/8/28 20:05
     */
    private static int getStartYear(String type){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDate = sdf.format(new Date());
        int year = getYear(nowDate);
        int res = 0;
        switch (type){
            case PeinConstants.YEAR:
                res = year - PeinConstants.YEAR_INTERVAL_YEAR;
                break;
            case PeinConstants.QUARTER:
                res = (year-PeinConstants.QUARTER_INTERVAL_YEAR);
                break;
            case PeinConstants.MONTH:
                res = (year-PeinConstants.MONTH_INTERVAL_YEAR);
                break;
            case PeinConstants.WEEK:
                res = (year-PeinConstants.WEEK_INTERVAL_YEAR);
                break;
            case PeinConstants.DAY:
                res = (year-PeinConstants.DAY_INTERVAL_YEAR);
                break;
        }
        return res;
    }

    /**
     * Description: 设置20180808变为201888
     * @auther: chenjw
     * @date: 2018/8/28 9:10
     */
    private static String fun2Num(int num){
        int year = num/100;
        int flag = num%100;
        return String.valueOf(year)+String.valueOf(flag);
    }

    private static String fun3Num(int num){
        int year = num/10000;
        int month = num/100%100;
        int day = num%100;
        return String.valueOf(year)+String.valueOf(month)+String.valueOf(day);

    }

    /**
     * Description: 根据不同类型返回结果
     * @auther: chenjw
     * @date: 2018/8/27 20:46
     */
    public static Map<String,String> getResMap(Map<Integer,Integer> timeMap, String type){
        Map<String,String> resMap = new HashMap<>();
        switch (type){
            case PeinConstants.YEAR:
                for(Map.Entry<Integer,Integer> entry : timeMap.entrySet()){
                    if(entry.getValue() == -1){
                        int key = entry.getKey();
                        int index = key;
                        while(timeMap.get(key) == -1){
                            key--;
                        }
                        timeMap.put(index,timeMap.get(key));
                    }
                }
                break;
            case PeinConstants.QUARTER:
                for(Map.Entry<Integer,Integer> entry : timeMap.entrySet()){
                    if(entry.getValue() == -1){
                        int key = entry.getKey();
                        int index = key;
                        while(timeMap.get(key) == -1){
                            if(key%100 == 1){
                                key = ((key/100)-1)*100+4;
                            }else{
                                key--;
                            }
                        }
                        timeMap.put(index,timeMap.get(key));
                    }
                }
                break;
            case PeinConstants.MONTH:
                for(Map.Entry<Integer,Integer> entry : timeMap.entrySet()){
                    if(entry.getValue() == -1){
                        int key = entry.getKey();
                        int index = key;
                        while(timeMap.get(key) == -1){
                            if(key%100 == 1){
                                key = ((key/100)-1)*100+12;
                            }else{
                                key--;
                            }
                        }
                        timeMap.put(index,timeMap.get(key));
                    }
                }
                break;
            case PeinConstants.WEEK:
                for(Map.Entry<Integer,Integer> entry : timeMap.entrySet()){
                    if(entry.getValue() == -1){
                        int key = entry.getKey();
                        int index = key;
                        while(timeMap.get(key) == -1){
                            if(key%100 == 1){
                                key = ((key/100)-1)*100+54;
                            }else{
                                key--;
                            }
                        }
                        timeMap.put(index,timeMap.get(key));
                    }
                }
                break;
            case PeinConstants.DAY:
                for(Map.Entry<Integer,Integer> entry : timeMap.entrySet()){
                    if(entry.getValue() == -1){
                        int key = entry.getKey();
                        int index = key;
                        while(timeMap.get(key) == -1){
                            int y = key/100/100;
                            int m = key/100%100;
                            int d = key%100;
                            if(d == 1){
                                if(m == 1){
                                    key = (y-1)*10000+12*100+31;
                                }else{
                                    key =  y*10000+(m-1)*100+31;
                                }
                            }else{
                                key--;
                            }
                        }
                        timeMap.put(index,timeMap.get(key));
                    }
                }
                break;
        }

        for(Map.Entry<Integer,Integer> entry : timeMap.entrySet()){
            int key = entry.getKey();
            String k;
            if(PeinConstants.DAY.equals(type)){
                k = fun3Num(key);
            }else{
                k = fun2Num(key);
            }
            resMap.put(k,String.valueOf(entry.getValue()));
        }
        return resMap;
    }

    /**
     * Description: 根据不同类型返回时间主键
     *              设定好年份期限
     * @auther: chenjw
     * @date: 2018/8/27 20:13
     */
    public static Map<Integer,Integer> getTimeMap(String type){
        int start = getStartYear(type);
        Map<Integer,Integer> timeMap = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDate = sdf.format(new Date());
        int year = PeinUtil.getYear(nowDate);
        switch(type){
            case PeinConstants.YEAR:
                for(int i = start; i <= year; i++){
                    timeMap.put(i,-1);
                }
                break;
            case PeinConstants.QUARTER:
                int quarter = PeinUtil.getQuarter(nowDate);
                for(int i = start; i < year; i++){
                    for(int j = 1; j <= PeinConstants.QUARTER_MAX; j++){
                        timeMap.put(i*100+j,-1);
                    }
                }
                for(int j = 1; j <= quarter; j++){
                    timeMap.put(year*100+j,-1);
                }
                break;
            case PeinConstants.MONTH:
                int month = PeinUtil.getMonth(nowDate);
                for(int i = start; i < year; i++){
                    for(int j = 1; j <= PeinConstants.MONTH_MAX; j++){
                        timeMap.put(i*100+j,-1);
                    }
                }
                for(int j = 1; j <= month; j++){
                    timeMap.put(year*100+j,-1);
                }
                break;
            case PeinConstants.WEEK:
                int week = PeinUtil.getWeek(nowDate);
                for(int i = start; i < year; i++){
                    for(int j = 1; j <= PeinConstants.WEEK_MAX; j++){
                        timeMap.put(i*100+j,-1);
                    }
                }
                for(int j = 1; j <= week; j++){
                    timeMap.put(year*100+j,-1);
                }
                break;
            case PeinConstants.DAY:
                int day = PeinUtil.getDay(nowDate);
                int months = PeinUtil.getMonth(nowDate);
                for(int i = start; i < year; i++){
                    for(int j = 1; j <= PeinConstants.MONTH_MAX; j++){
                        for(int k = 1; k <= PeinConstants.DAY_MAX; k++){
                            timeMap.put(i*10000+j*100+k,-1);
                        }
                    }
                }
                for(int j = 1; j < months; j++){
                    for(int k = 1; k <= PeinConstants.DAY_MAX; k++){
                        timeMap.put(year*10000+j*100+k,-1);
                    }
                }
                for(int k = 1; k <= day; k++){
                    timeMap.put(year*10000+months*100+k,-1);
                }
                break;
        }
        return timeMap;
    }

    /**
     * Description:获取 前年/去年上季度/去年上月/去年上周/去年昨日
     * @auther: chenjw
     * @date: 2018/8/10 8:57
     */
    public static String[] createLastPreDate(String type){
        GetDateVersion rd = new GetDateVersion();
        switch(type){
            case PeinConstants.YEAR:
                return rd.getLastLastYear();
            case PeinConstants.QUARTER:
                return rd.getLastYearLastQuarter();
            case PeinConstants.MONTH:
                return rd.getLastYearLastMonth();
            case PeinConstants.WEEK:
                return rd.getLastYearLastWeek();
            case PeinConstants.DAY:
                return rd.getLastYearYesterday();
            default:return null;
        }
    }

    /**
     * Description:获取 前年/上上个季度/上上个月/上上周/前日,
     * @auther: chenjw
     * @date: 2018/8/10 8:57
     */
    public static String[] createPrePreDate(String type){
        GetDateVersion rd = new GetDateVersion();
        switch(type){
            case PeinConstants.YEAR:
                return rd.getLastLastYear();
            case PeinConstants.QUARTER:
                return rd.getLastLastQuarter();
            case PeinConstants.MONTH:
                return rd.getLastLastMonth();
            case PeinConstants.WEEK:
                return rd.getLastLastWeek();
            case PeinConstants.DAY:
                return rd.getLastYesterday();
            default:return null;
        }
    }

    /**
     * Description:获取起始时间 去年/上个季度/上个月/上周/昨日,
     * @auther: chenjw
     * @date: 2018/8/10 8:57
     */
    public static String[] createPreDate(String type){
        GetDateVersion rd = new GetDateVersion();
        switch(type){
            case PeinConstants.YEAR:
                return rd.getLastYear();
            case PeinConstants.QUARTER:
                return rd.getLastQuarter();
            case PeinConstants.MONTH:
                return rd.getLastMonth();
            case PeinConstants.WEEK:
                return rd.getLastWeek();
            case PeinConstants.DAY:
                return rd.getYesterday();
            default:return null;
        }
    }

    /**
     * Description:获取 今年/本季度/本月/本周/今日
     * @auther: chenjw
     * @date: 2018/8/10 8:57
     */
    public static String createCurDate(String type){
        GetDateVersion rd = new GetDateVersion();
        switch(type){
            case PeinConstants.YEAR:
                return rd.getCurrentYear()[0];
            case PeinConstants.QUARTER:
                return rd.getCurrentQuarter()[0];
            case PeinConstants.MONTH:
                return rd.getCurrentMonth()[0];
            case PeinConstants.WEEK:
                return rd.getCurrentWeek()[0];
            case PeinConstants.DAY:
                return rd.getToday()[0];
            default:return null;
        }
    }

    /**
     * Description:得到输入日期的年份
     * @auther: chenjw
     * @date: 2018/8/29 10:38
     */
    public static int getYear(String date) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cal.setTime(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    /**
     * Description:得到一天是一年的第几个季度
     * @auther: chenjw
     * @date: 2018/8/29 10:39
     */
    public static int getQuarter(String date) {
        int month = getMonth(date);
        int quarter;
        switch (month) {
            case 1:
            case 2:
            case 3:
                quarter = 1;
                break;
            case 4:
            case 5:
            case 6:
                quarter = 2;
                break;
            case 7:
            case 8:
            case 9:
                quarter = 3;
                break;
            case 10:
            case 11:
            case 12:
                quarter = 4;
                break;
            default:
                quarter = 0;
                break;
        }
        return quarter;
    }

    /**
     * Description:得到一天是一年的第几个月
     * @auther: chenjw
     * @date: 2018/8/29 10:38
     */
    public static int getMonth(String date) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cal.setTime(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int month = cal.get(Calendar.MONTH) + 1;
        return month;
    }

    /**
     * Description:得到一天是一年中的第几周
     * @auther: chenjw
     * @date: 2018/8/29 10:38
     */
    public static int getWeek(String date) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cal.setTime(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        return week;
    }

    /**
     * Description:得到一天日期
     * @auther: chenjw
     * @date: 2018/8/29 10:38
     */
    public static int getDay(String date) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cal.setTime(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return day;
    }

}