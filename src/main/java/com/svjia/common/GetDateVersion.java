package com.svjia.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/* 
 * GetDateVersion
 * 时间模块：获取基于当前时间的相关时间区间
 * 获取方式：使用成员方法 .getXXXXX() ，返回结果是包含两个元素的时间字符串数组
 * 			第 0 个元素是该时间区间的起始时间点，第 1 个元素是该时间区间的终结时间点。
 * 			数组元素（时间字符串）的格式为：2018-01-01 00:00:00
 * 
 * author：liy123
 */
public class GetDateVersion implements InterfaceOfGetDateVersion {
	//今年始
	private String currentYearBegin = null;
	//今年终
	private String currentYearEnd = null;
	/************/
	//去年始
	private String lastYearBegin = null;
	//去年终
	private String lastYearEnd = null;
	/************/
	//前年始
	private String lastLastYearBegin = null;
	//前年终
	private String lastLastYearEnd = null;
	/************/
	//本季度始
	private String currentQuarterBegin = null;
	//本季度终
	private String currentQuarterEnd = null;
	/************/
	//上季度始
	private String lastQuarterBegin = null;
	//上季度终
	private String lastQuarterEnd = null;
	/************/
	//前季度始
	private String lastLastQuarterBegin = null;
	//前季度终
	private String lastLastQuarterEnd = null;
	/************/
	//去年上季度始
	private String lastYearLastQuarterBegin = null;
	//去年上季度终
	private String lastYearLastQuarterEnd = null;
	/************/
	//本月始
	private String currentMonthBegin = null;
	//本月终
	private String currentMonthEnd = null;
	/************/
	//上月始
	private String lastMonthBegin = null;
	//上月终
	private String lastMonthEnd = null;
	/************/
	//前月始
	private String lastLastMonthBegin = null;
	//前月终
	private String lastLastMonthEnd = null;
	/************/
	//去年上月始
	private String lastYearLastMonthBegin = null;
	//去年上月终
	private String lastYearLastMonthEnd = null;
	/************/	
	//本周始
	private String currentWeekBegin = null;
	//本周终
	private String currentWeekEnd = null;
	/************/
	//上周始
	private String lastWeekBegin = null;
	//上周终
	private String lastWeekEnd = null;
	/************/
	//前周始
	private String lastLastWeekBegin = null;
	//前周终
	private String lastLastWeekEnd = null;
	/************/
	//去年上周始
	private String lastYearLastWeekBegin = null;
	//去年上周终
	private String lastYearLastWeekEnd = null;
	/************/
	//今天始
	private String todayBegin = null;
	//今天终
	private String todayEnd = null;
	/************/
	//昨天始
	private String yesterdayBegin = null;
	//昨天终
	private String yesterdayEnd = null;
	/************/
	//前天始
	private String lastYesterdayBegin = null;
	//前天终
	private String lastYesterdayEnd = null;
	/************/
	//去年昨天始
	private String lastYearYesterdayBegin = null;
	//去年昨天终
	private String lastYearYesterdayEnd = null;

	
	public GetDateVersion(){
		Calendar calendar = Calendar.getInstance();
		// 获取年
		int year = calendar.get(Calendar.YEAR);
		
		// 设置今年始终时间点
		this.currentYearBegin = String.valueOf(year)+"-01-01 00:00:00";
		this.currentYearEnd = String.valueOf(year)+"-12-31 23:59:59";
		
		// 设置去年始终时间点
		this.lastYearBegin = String.valueOf(year-1)+"-01-01 00:00:00";
		this.lastYearEnd = String.valueOf(year-1)+"-12-31 23:59:59";
		
		// 设置前年始终时间点
		this.lastLastYearBegin = String.valueOf(year-2)+"-01-01 00:00:00";
		this.lastLastYearEnd = String.valueOf(year-2)+"-12-31 23:59:59";
		
		// 获取月，这里需要需要月份的范围为0~11，因此获取月份的时候需要+1才是当前月份值
        int month = calendar.get(Calendar.MONTH) + 1;
        
        /*
         * 获本月始终时间点
         */   
        // 解决本月是一位数还是两位数的问题
        if(month > 9){
        	this.currentMonthBegin = String.valueOf(year)+"-"+String.valueOf(month);
        }else{
        	this.currentMonthBegin = String.valueOf(year)+"-0"+String.valueOf(month);
        }
        // 获取本月终结时间点
        // 先求出该月份后一个月份的第一天
        calendar.set(year, month, 1);	// 为什么这里不加 1，因为前面已经加 1 成为逻辑上正确的月份；由于calendar里面月份是 0 开始，这里直接传进去就等于当前月的下一个月了。
        // 后退一天
        calendar.add(Calendar.DATE, -1);
        // 获取该月的最后一天
        int monthDay = calendar.get(Calendar.DAY_OF_MONTH);
        this.currentMonthEnd = this.currentMonthBegin+"-"+String.valueOf(monthDay)+" 23:59:59";
        // 补全本月起始时间点
        this.currentMonthBegin += "-01 00:00:00";
        
        /*
         * 获取（上、去年上）月始终时间点
         */
        if(month==1){
        	this.lastMonthBegin = String.valueOf(year-1)+"-12-01 00:00:00";
        	this.lastMonthEnd = String.valueOf(year-1)+"-12-31 23:59:59";
        	
        	this.lastYearLastMonthBegin = String.valueOf(year-2)+"-12-01 00:00:00";
        	this.lastYearLastMonthEnd = String.valueOf(year-2)+"-12-31 23:59:59";
        }else{
        	if(month > 10){
            	this.lastMonthBegin = String.valueOf(year)+"-"+String.valueOf(month-1);
            	
            	this.lastYearLastMonthBegin = String.valueOf(year-1)+"-"+String.valueOf(month-1);
            }else{
            	this.lastMonthBegin = String.valueOf(year)+"-0"+String.valueOf(month-1);
            	
            	this.lastYearLastMonthBegin = String.valueOf(year-1)+"-0"+String.valueOf(month-1);
            }
        	// 获取本月的第一天
        	calendar.set(year, month-1, 1);
        	// 后退一天得上个月的最后一天
        	calendar.add(Calendar.DATE, -1);
        	int lastMonthEndDay = calendar.get(Calendar.DAY_OF_MONTH);
        	this.lastMonthEnd = this.lastMonthBegin+"-"+String.valueOf(lastMonthEndDay)+" 23:59:59";
        	this.lastMonthBegin += "-01 00:00:00";
        	
        	// 重置时间
            calendar.clear();
            calendar = Calendar.getInstance();
            
            // 获取去年本月的第一天
        	calendar.set(year-1, month-1, 1);
        	// 后退一天得去年上个月的最后一天
        	calendar.add(Calendar.DATE, -1);
        	lastMonthEndDay = calendar.get(Calendar.DAY_OF_MONTH);
        	this.lastYearLastMonthEnd = this.lastYearLastMonthBegin+"-"+String.valueOf(lastMonthEndDay)+" 23:59:59";
        	this.lastYearLastMonthBegin += "-01 00:00:00";
        }
        
        /*
         * 获取前月始终时间点
         */
        if(month==2){
        	this.lastLastMonthBegin = String.valueOf(year-1)+"-12-01 00:00:00";
        	this.lastLastMonthEnd = String.valueOf(year-1)+"-12-31 23:59:59";
        }else if(month==1) {
        	this.lastLastMonthBegin = String.valueOf(year-1)+"-11-01 00:00:00";
        	this.lastLastMonthEnd = String.valueOf(year-1)+"-11-30 23:59:59";
        }else{
        	if(month == 12){
            	this.lastLastMonthBegin = String.valueOf(year)+"-"+String.valueOf(month-2);
            }else{
            	this.lastLastMonthBegin = String.valueOf(year)+"-0"+String.valueOf(month-2);
            }
        	// 获取上月的第一天
        	calendar.set(year, month-2, 1);
        	// 后退一天得前个月的最后一天
        	calendar.add(Calendar.DATE, -1);
        	int lastLastMonthEndDay = calendar.get(Calendar.DAY_OF_MONTH);
        	this.lastLastMonthEnd = this.lastLastMonthBegin+"-"+String.valueOf(lastLastMonthEndDay)+" 23:59:59";
        	this.lastLastMonthBegin += "-01 00:00:00";
        }
        
        // 重置时间
        calendar.clear();
        calendar = Calendar.getInstance();
        
        /*
         * 获取（本、上、去年本）季度始终时间点
         */
        int lastYear = year - 1;
        // int lastLastYear = year - 2;
        if(month>=1&&month<=3){
        	// 本季度始终时间点
        	this.currentQuarterBegin = String.valueOf(year)+"-01-01 00:00:00";
        	this.currentQuarterEnd = String.valueOf(year)+"-03-31 23:59:59";
        	// 上季度始终时间点
        	this.lastQuarterBegin = String.valueOf(lastYear)+"-10-01 00:00:00";
        	this.lastQuarterEnd = String.valueOf(lastYear)+"-12-31 23:59:59";
        	// 前季度始终时间点
        	this.lastLastQuarterBegin = String.valueOf(lastYear)+"-07-01 00:00:00";
        	this.lastLastQuarterEnd = String.valueOf(lastYear)+"-09-30 23:59:59";
        	// 去年上季度始终时间点
        	this.lastYearLastQuarterBegin = String.valueOf(lastYear-1)+"-10-01 00:00:00";
        	this.lastYearLastQuarterEnd = String.valueOf(lastYear-1)+"-12-31 23:59:59";
        }else if(month>=4&&month<=6){
        	//
        	this.currentQuarterBegin = String.valueOf(year)+"-04-01 00:00:00";
        	this.currentQuarterEnd = String.valueOf(year)+"-06-30 23:59:59";
        	//
        	this.lastQuarterBegin = String.valueOf(year)+"-01-01 00:00:00";
        	this.lastQuarterEnd = String.valueOf(year)+"-03-31 23:59:59";
        	//
        	this.lastLastQuarterBegin = String.valueOf(lastYear)+"-10-01 00:00:00";
        	this.lastLastQuarterEnd = String.valueOf(lastYear)+"-12-31 23:59:59";
        	//
        	this.lastYearLastQuarterBegin = String.valueOf(lastYear)+"-01-01 00:00:00";
        	this.lastYearLastQuarterEnd = String.valueOf(lastYear)+"-03-31 23:59:59";
        }else if(month>=7&&month<=9){
        	//
        	this.currentQuarterBegin = String.valueOf(year)+"-07-01 00:00:00";
        	this.currentQuarterEnd = String.valueOf(year)+"-09-30 23:59:59";
        	//
        	this.lastQuarterBegin = String.valueOf(year)+"-04-01 00:00:00";
        	this.lastQuarterEnd = String.valueOf(year)+"-06-30 23:59:59";
        	//
        	this.lastLastQuarterBegin = String.valueOf(year)+"-01-01 00:00:00";
        	this.lastLastQuarterEnd = String.valueOf(year)+"-03-31 23:59:59";
        	//
        	this.lastYearLastQuarterBegin = String.valueOf(lastYear)+"-04-01 00:00:00";
        	this.lastYearLastQuarterEnd = String.valueOf(lastYear)+"-06-30 23:59:59";
        }else{
        	//
        	this.currentQuarterBegin = String.valueOf(year)+"-10-01 00:00:00";
        	this.currentQuarterEnd = String.valueOf(year)+"-12-31 23:59:59";
        	//
        	this.lastQuarterBegin = String.valueOf(year)+"-07-01 00:00:00";
        	this.lastQuarterEnd = String.valueOf(year)+"-09-30 23:59:59";
        	//
        	this.lastLastQuarterBegin = String.valueOf(year)+"-04-01 00:00:00";
        	this.lastLastQuarterEnd = String.valueOf(year)+"-06-30 23:59:59";
        	//
        	this.lastYearLastQuarterBegin = String.valueOf(lastYear)+"-07-01 00:00:00";
        	this.lastYearLastQuarterEnd = String.valueOf(lastYear)+"-09-30 23:59:59";
        }
        
		// 获取日
        // int day = calendar.get(Calendar.DAY_OF_MONTH);
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        // 设置今天始终时间点
    	this.todayBegin = formatter.format(calendar.getTime()).substring(0, 10)+" 00:00:00";
    	this.todayEnd = formatter.format(calendar.getTime()).substring(0, 10)+" 23:59:59";
        
        /*
         * 获取昨天始终时间点
         */
    	// 重置时间
        calendar.clear();
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
        this.yesterdayBegin = formatter.format(calendar.getTime()).substring(0, 10)+" 00:00:00";
        this.yesterdayEnd = formatter.format(calendar.getTime()).substring(0, 10)+" 23:59:59";
        /*
         * 获取前天始终时间点
         */
        // 重置时间
        calendar.clear();
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 2);
        this.lastYesterdayBegin = formatter.format(calendar.getTime()).substring(0, 10)+" 00:00:00";
        this.lastYesterdayEnd = formatter.format(calendar.getTime()).substring(0, 10)+" 23:59:59";
        /*
         * 获取去年昨天始终时间点
         */
        this.lastYearYesterdayBegin = String.valueOf(year-1)+this.yesterdayBegin.substring(4);
        this.lastYearYesterdayEnd = String.valueOf(year-1)+this.yesterdayEnd.substring(4);
        
        // 获取本周始终时间点
        this.currentWeekBegin = getCurrentLastMonSun(1)+" 00:00:00";
        this.currentWeekEnd = getCurrentLastMonSun(7)+" 23:59:59";
        
        // 获取上周始终时间点
        this.lastWeekBegin = getCurrentLastMonSun(-6)+" 00:00:00";
        this.lastWeekEnd = getCurrentLastMonSun(0)+" 23:59:59";
        
        // 获取前周始终时间点
        this.lastLastWeekBegin = getCurrentLastMonSun(-13)+" 00:00:00";
        this.lastLastWeekEnd = getCurrentLastMonSun(-7)+" 23:59:59";
        
        // 获取去年上周始终时间点
        calendar.clear();
		calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR)-1); // 时间设置成上一年
		calendar.set(Calendar.WEEK_OF_YEAR, calendar.get(Calendar.WEEK_OF_YEAR)-1); // 设置为与本年相同的周
		calendar.set(Calendar.DAY_OF_WEEK, 2); // 1表示周日，2表示周一，7表示周六
		this.lastYearLastWeekBegin = formatter.format(calendar.getTime()).substring(0, 10)+" 00:00:00";;
		
		calendar.clear();
		calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR)-1); // 时间设置成上一年
		calendar.set(Calendar.WEEK_OF_YEAR, calendar.get(Calendar.WEEK_OF_YEAR)); // 设置为与本年相同的周
		calendar.set(Calendar.DAY_OF_WEEK, 1); // 1表示周日，2表示周一，7表示周六
		this.lastYearLastWeekEnd = formatter.format(calendar.getTime()).substring(0, 10)+" 23:59:59";
        
	}
	
	public String getCurrentLastMonSun(int i) {
		// i = 1：获取今天所在周的周一
		// i = 7：获取今天所在周的周日
		// i = -6：获取上周的周一
		// i = 0：获取上周的周日
		// i = -13：获取前周的周一
		// i = -7：获取前周的周日
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
		day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + i);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(c.getTime()).substring(0, 10);
	}
	
	/*
	 * 返回‘今年’时间区间
	 * 返回一个长度为 2 的字符串数组
	 * 数组第 0 个为今年的起始时间，数组第 1 个 为今年的结束时间
	 * 数组元素（时间字符串）的格式为：2018-01-01 00:00:00
	 */
	public String[] getCurrentYear(){
		String[] currentYear = new String[2];
		currentYear[0] = this.currentYearBegin;
		currentYear[1] = this.currentYearEnd;
		return currentYear;
	}
	
	/*
	 * 返回‘去年’时间区间
	 * 返回一个长度为 2 的字符串数组
	 * 数组第 0 个为去年的起始时间，数组第 1 个 为去年的结束时间
	 * 数组元素（时间字符串）的格式为：2018-01-01 00:00:00
	 */
	public String[] getLastYear(){
		String[] lastYear = new String[2];
		lastYear[0] = this.lastYearBegin;
		lastYear[1] = this.lastYearEnd;
		return lastYear;
	}
	
	/*
	 * 返回‘前年’时间区间
	 * 返回一个长度为 2 的字符串数组
	 * 数组第 0 个为前年的起始时间，数组第 1 个 为前年的结束时间
	 * 数组元素（时间字符串）的格式为：2018-01-01 00:00:00
	 */
	public String[] getLastLastYear(){
		String[] lastLastYear = new String[2];
		lastLastYear[0] = this.lastLastYearBegin;
		lastLastYear[1] = this.lastLastYearEnd;
		return lastLastYear;
	}
	
	/*
	 * 返回‘本月’时间区间
	 * 返回一个长度为 2 的字符串数组
	 * 数组第 0 个为本月的起始时间，数组第 1 个 为本月的结束时间
	 * 数组元素（时间字符串）的格式为：2018-01-01 00:00:00
	 */
	public String[] getCurrentMonth(){
		String[] currentMonth = new String[2];
		currentMonth[0] = this.currentMonthBegin;
		currentMonth[1] = this.currentMonthEnd;
		return currentMonth;
	}
	
	/*
	 * 返回‘上月’时间区间
	 * 返回一个长度为 2 的字符串数组
	 * 数组第 0 个为上月的起始时间，数组第 1 个 为上月的结束时间
	 * 数组元素（时间字符串）的格式为：2018-01-01 00:00:00
	 */
	public String[] getLastMonth(){
		String[] lastMonth = new String[2];
		lastMonth[0] = this.lastMonthBegin;
		lastMonth[1] = this.lastMonthEnd;
		return lastMonth;
	}
	
	/*
	 * 返回‘前月’时间区间
	 * 返回一个长度为 2 的字符串数组
	 * 数组第 0 个为前月的起始时间，数组第 1 个 为前月的结束时间
	 * 数组元素（时间字符串）的格式为：2018-01-01 00:00:00
	 */
	public String[] getLastLastMonth(){
		String[] lastLastMonth = new String[2];
		lastLastMonth[0] = this.lastLastMonthBegin;
		lastLastMonth[1] = this.lastLastMonthEnd;
		return lastLastMonth;
	}
	
	/*
	 * 返回‘去年上月’时间区间
	 * 返回一个长度为 2 的字符串数组
	 * 数组第 0 个为去年上月的起始时间，数组第 1 个 为去年上月的结束时间
	 * 数组元素（时间字符串）的格式为：2018-01-01 00:00:00
	 */
	public String[] getLastYearLastMonth(){
		String[] lastYearLastMonth = new String[2];
		lastYearLastMonth[0] = this.lastYearLastMonthBegin;
		lastYearLastMonth[1] = this.lastYearLastMonthEnd;
		return lastYearLastMonth;
	}
	
	/*
	 * 返回‘本季度’时间区间
	 * 返回一个长度为 2 的字符串数组
	 * 数组第 0 个为本季度的起始时间，数组第 1 个 为本季度的结束时间
	 * 数组元素（时间字符串）的格式为：2018-01-01 00:00:00
	 */
	public String[] getCurrentQuarter(){
		String[] currentQuarter = new String[2];
		currentQuarter[0] = this.currentQuarterBegin;
		currentQuarter[1] = this.currentQuarterEnd;
		return currentQuarter;
	}
	
	/*
	 * 返回‘上季度’时间区间
	 * 返回一个长度为 2 的字符串数组
	 * 数组第 0 个为上季度的起始时间，数组第 1 个 为上季度的结束时间
	 * 数组元素（时间字符串）的格式为：2018-01-01 00:00:00
	 */
	public String[] getLastQuarter(){
		String[] lastQuarter = new String[2];
		lastQuarter[0] = this.lastQuarterBegin;
		lastQuarter[1] = this.lastQuarterEnd;
		return lastQuarter;
	}
	
	/*
	 * 返回‘前季度’时间区间
	 * 返回一个长度为 2 的字符串数组
	 * 数组第 0 个为前季度的起始时间，数组第 1 个 为前季度的结束时间
	 * 数组元素（时间字符串）的格式为：2018-01-01 00:00:00
	 */
	public String[] getLastLastQuarter(){
		String[] lastLastQuarter = new String[2];
		lastLastQuarter[0] = this.lastLastQuarterBegin;
		lastLastQuarter[1] = this.lastLastQuarterEnd;
		return lastLastQuarter;
	}
	
	/*
	 * 返回‘去年上季度’时间区间
	 * 返回一个长度为 2 的字符串数组
	 * 数组第 0 个为去年上季度的起始时间，数组第 1 个 去年上季度的结束时间
	 * 数组元素（时间字符串）的格式为：2018-01-01 00:00:00
	 */
	public String[] getLastYearLastQuarter(){
		String[] lastYearCurrentQuarter = new String[2];
		lastYearCurrentQuarter[0] = this.lastYearLastQuarterBegin;
		lastYearCurrentQuarter[1] = this.lastYearLastQuarterEnd;
		return lastYearCurrentQuarter;
	}
	
	/*
	 * 返回‘今天’时间区间
	 * 返回一个长度为 2 的字符串数组
	 * 数组第 0 个为今天的起始时间，数组第 1 个 为今天的结束时间
	 * 数组元素（时间字符串）的格式为：2018-01-01 00:00:00
	 */
	public String[] getToday(){
		String[] today = new String[2];
		today[0] = this.todayBegin;
		today[1] = this.todayEnd;
		return today;
	}
	
	/*
	 * 返回‘昨天’时间区间
	 * 返回一个长度为 2 的字符串数组
	 * 数组第 0 个为昨天的起始时间，数组第 1 个 为昨天的结束时间
	 * 数组元素（时间字符串）的格式为：2018-01-01 00:00:00
	 */
	public String[] getYesterday(){
		String[] yesterday = new String[2];
		yesterday[0] = this.yesterdayBegin;
		yesterday[1] = this.yesterdayEnd;
		return yesterday;
	}
	
	/*
	 * 返回‘前天’时间区间
	 * 返回一个长度为 2 的字符串数组
	 * 数组第 0 个为前天的起始时间，数组第 1 个 为前天的结束时间
	 * 数组元素（时间字符串）的格式为：2018-01-01 00:00:00
	 */
	public String[] getLastYesterday(){
		String[] lastYesterday = new String[2];
		lastYesterday[0] = this.lastYesterdayBegin;
		lastYesterday[1] = this.lastYesterdayEnd;
		return lastYesterday;
	}
	
	/*
	 * 返回‘去年昨天’时间区间
	 * 返回一个长度为 2 的字符串数组
	 * 数组第 0 个为去年昨天的起始时间，数组第 1 个 为去年昨天的结束时间
	 * 数组元素（时间字符串）的格式为：2018-01-01 00:00:00
	 */
	public String[] getLastYearYesterday(){
		String[] lastYearYesterday = new String[2];
		lastYearYesterday[0] = this.lastYearYesterdayBegin;
		lastYearYesterday[1] = this.lastYearYesterdayEnd;
		return lastYearYesterday;
	}
	
	/*
	 * 返回‘本周’时间区间
	 * 返回一个长度为 2 的字符串数组
	 * 数组第 0 个为本周的起始时间，数组第 1 个 为本周的结束时间
	 * 数组元素（时间字符串）的格式为：2018-01-01 00:00:00
	 */
	public String[] getCurrentWeek(){
		String[] currentWeek = new String[2];
		currentWeek[0] = this.currentWeekBegin;
		currentWeek[1] = this.currentWeekEnd;
		return currentWeek;
	}
	
	/*
	 * 返回‘上周’时间区间
	 * 返回一个长度为 2 的字符串数组
	 * 数组第 0 个为上周的起始时间，数组第 1 个 为上周的结束时间
	 * 数组元素（时间字符串）的格式为：2018-01-01 00:00:00
	 */
	public String[] getLastWeek(){
		String[] lastWeek = new String[2];
		lastWeek[0] = this.lastWeekBegin;
		lastWeek[1] = this.lastWeekEnd;
		return lastWeek;
	}
	
	/*
	 * 返回‘前周’时间区间
	 * 返回一个长度为 2 的字符串数组
	 * 数组第 0 个为前周的起始时间，数组第 1 个 为前周的结束时间
	 * 数组元素（时间字符串）的格式为：2018-01-01 00:00:00
	 */
	public String[] getLastLastWeek(){
		String[] lastLastWeek = new String[2];
		lastLastWeek[0] = this.lastLastWeekBegin;
		lastLastWeek[1] = this.lastLastWeekEnd;
		return lastLastWeek;
	}
	
	/*
	 * 返回‘去年上周’时间区间
	 * 返回一个长度为 2 的字符串数组
	 * 数组第 0 个为去年上周的起始时间，数组第 1 个 为去年上周的结束时间
	 * 数组元素（时间字符串）的格式为：2018-01-01 00:00:00
	 */
	public String[] getLastYearLastWeek(){
		String[] lastYearLastWeek = new String[2];
		lastYearLastWeek[0] = this.lastYearLastWeekBegin;
		lastYearLastWeek[1] = this.lastYearLastWeekEnd;
		return lastYearLastWeek;
	}
	
	/************************************************************/
	
	/*
	 * 获取年时间段
	 * 
	 * choice = 1，返回“今年”时间段
	 * choice = 2，返回“去年”时间段
	 * choice = 3，返回“前年”时间段
	 */
	@Override
	public String[] getYear(int choice){
		switch (choice) {
		case 1:
			return getCurrentYear();
		case 2:
			return getLastYear();
		case 3:
			return getLastLastYear();
		default:
			return new String[2];
		}
	}
	
	/*
	 * 获取季度时间段
	 * 
	 * choice = 1，返回“本季度”时间段
	 * choice = 2，返回“上季度”时间段
	 * choice = 3，返回“前季度”时间段
	 * choice = 4，返回“去年上季度”时间段
	 */
	@Override
	public String[] getQuarter(int choice){
		switch (choice) {
		case 1:
			return getCurrentQuarter();
		case 2:
			return getLastQuarter();
		case 3:
			return getLastLastQuarter();
		case 4:
			return getLastYearLastQuarter();
		default:
			return new String[2];
		}
	}
	
	/*
	 * 获取月时间段
	 * 
	 * choice = 1，返回“本月”时间段
	 * choice = 2，返回“上月”时间段
	 * choice = 3，返回“前月”时间段
	 * choice = 4，返回“去年上月”时间段
	 */
	@Override
	public String[] getMonth(int choice){
		switch (choice) {
		case 1:
			return getCurrentMonth();
		case 2:
			return getLastMonth();
		case 3:
			return getLastLastMonth();
		case 4:
			return getLastYearLastMonth();
		default:
			return new String[2];
		}
	}
	
	/*
	 * 获取周时间段
	 * 
	 * choice = 1，返回“本周”时间段
	 * choice = 2，返回“上周”时间段
	 * choice = 3，返回“前周”时间段
	 * choice = 4，返回“去年上周”时间段
	 */
	@Override
	public String[] getWeek(int choice){
		switch (choice) {
		case 1:
			return getCurrentWeek();
		case 2:
			return getLastWeek();
		case 3:
			return getLastLastWeek();
		case 4:
			return getLastYearLastWeek();
		default:
			return new String[2];
		}
	}
	
	/*
	 * 获取日时间段
	 * 
	 * choice = 1，返回“今天”时间段
	 * choice = 2，返回“昨天”时间段
	 * choice = 3，返回“前天”时间段
	 * choice = 4，返回“去年昨天”时间段
	 */
	@Override
	public String[] getDay(int choice){
		switch (choice) {
		case 1:
			return getToday();
		case 2:
			return getYesterday();
		case 3:
			return getLastYesterday();
		case 4:
			return getLastYearYesterday();
		default:
			return new String[2];
		}
	}
}
