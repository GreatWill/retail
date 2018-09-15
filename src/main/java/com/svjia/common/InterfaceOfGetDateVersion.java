package com.svjia.common;

/*
 * 该接口对 GetDateVersion 的获取函数按时间类别进行封装
 * 
 * version : 1.0
 * auth : liy123
 */
public interface InterfaceOfGetDateVersion {
	/*
	 * 获取年时间段
	 * 
	 * choice = 1，返回“今年”时间段
	 * choice = 2，返回“去年”时间段
	 * choice = 3，返回“前年”时间段
	 */
	public String[] getYear(int choice);
	
	/*
	 * 获取季度时间段
	 * 
	 * choice = 1，返回“本季度”时间段
	 * choice = 2，返回“上季度”时间段
	 * choice = 3，返回“前季度”时间段
	 * choice = 4，返回“去年上季度”时间段
	 */
	public String[] getQuarter(int choice);
	
	/*
	 * 获取月时间段
	 * 
	 * choice = 1，返回“本月”时间段
	 * choice = 2，返回“上月”时间段
	 * choice = 3，返回“前月”时间段
	 * choice = 4，返回“去年上月”时间段
	 */
	public String[] getMonth(int choice);
	
	/*
	 * 获取周时间段
	 * 
	 * choice = 1，返回“本周”时间段
	 * choice = 2，返回“上周”时间段
	 * choice = 3，返回“前周”时间段
	 * choice = 4，返回“去年上周”时间段
	 */
	public String[] getWeek(int choice);
	
	/*
	 * 获取日时间段
	 * 
	 * choice = 1，返回“今天”时间段
	 * choice = 2，返回“昨天”时间段
	 * choice = 3，返回“前天”时间段
	 * choice = 4，返回“去年昨天”时间段
	 */
	public String[] getDay(int choice);
}
