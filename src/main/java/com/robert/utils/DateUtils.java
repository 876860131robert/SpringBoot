/**
 * Copyright(C) Asiainfo All rights Reserved
 * @Description: TODO(用一句话描述该文件做什么)
 * @author:      名字
 * @version      V1.0 
 * @date:        2016年4月27日 上午11:17:18
 *
 * Modification  History:
 * Date          Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年4月27日        名字                                   1.0            1.0
 * <修改原因描述>: 
 */
package com.robert.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Description 处理时间的工具类
 * @author zhangxin
 * @date 2016年4月27日 上午11:00:01
 * 
 */
public class DateUtils {

	/**
	 * 获取形如yyyyMMddHHmmss的当前日期字串
	 * 
	 * @return String
	 */
	public static String getDateString() {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(new Date());
	}
	
	/**
	 * 获取当前时间
	 * yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getCurrentTime(){     
	    String temp_str="";     
	    Date dt = new Date();     
	    //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制     
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
	    temp_str=sdf.format(dt);     
	    return temp_str;     
	} 

	/**
	 * 获取指定格式的当前日期字串
	 * 
	 * @return String
	 */
	public static String getDateString(String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		return df.format(new Date());
	}

	/**
	 * 获取形如yyyyMMddHHmmss的日期字串
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String getDateString(Date date) {
		if (date == null) {
			return "";
		}
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(date);
	}

	/**
	 * 获取指定格式的日期字串
	 * 
	 * @param date
	 * @param pattirn
	 * @return
	 */
	public static String getDateString(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return date != null ? sdf.format(date) : "";
	}

	/**
	 * 将形如yyyyMMddHHmmss的字串转换成日期
	 * 
	 * @param strDate
	 *            String
	 * @return Date
	 * @throws ParseException
	 */
	public static Date getDate(String strDate) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return df.parse(strDate);
	}

	/**
	 * 将指定格式的字串转换成日期
	 * 
	 * @param strDate
	 *            String
	 * @return Date
	 * @throws ParseException
	 */
	public static Date getDate(String strDate, String pattern)
			throws ParseException {
		DateFormat df = new SimpleDateFormat(pattern);
		return df.parse(strDate);
	}

	/**
	 * 将指定格式的字串(20061111163558)转换成指定格式的字串(2006-11-11 16:35:58)
	 * 
	 * @param strDate
	 *            String
	 * @return Date
	 * @throws ParseException
	 */
	public static String getStringDate(String stringdate) {
		if (stringdate == null)
			return null;

		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat formatter2 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String dateString = "";
		try {
			Date date = formatter1.parse(stringdate);
			dateString = formatter2.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateString;
	}

	/**
	 * 根据字符串格式转换成指定格式的字符串
	 * 
	 * @param stringdate
	 * @param fpattern
	 * @param tpattern
	 * @return
	 */
	public static String getStringDate(String stringdate, String fpattern,
			String tpattern) {
		if (stringdate == null)
			return null;

		SimpleDateFormat formatter1 = new SimpleDateFormat(fpattern);
		SimpleDateFormat formatter2 = new SimpleDateFormat(tpattern);
		String dateString = "";
		try {
			Date date = formatter1.parse(stringdate.trim());
			dateString = formatter2.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateString;
	}

	/**
	 * 日期运算
	 * 
	 * @param days
	 *            和当前日期的差值（单位:天）
	 * @param pattern
	 * @return
	 */
	public static String getDateString(int days, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		long days2 = (long) days;
		return df.format(new java.util.Date((new Date()).getTime() + 1000 * 60
				* 60 * 24 * days2));
	}

	/**
	 * 获取两个日期的差值（单位：月）
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getTwoMonthNum(String startDate, String endDate) {
		int year1 = Integer.parseInt(startDate.substring(0, 4));
		int year2 = Integer.parseInt(endDate.substring(0, 4));
		int month1 = Integer.parseInt(startDate.substring(4));
		int month2 = Integer.parseInt(endDate.substring(4));
		return Math.abs(year1 - year2) * 12 - (month1 - month2) + 1;
	}

	/**
	 * 得到当前月的后month_num个月的帐期 例如当前为2005-09，返回上个月的帐期，则设置month_num为-1,返回为200508
	 * 例如当前为2005-09，返回下个月的帐期，则设置month_num为1,返回为200510
	 * 例如当前为2006-1，返回上个月的帐期，则设置month_num为1,返回为200512
	 */
	public static String getChdate(int month_num) {
		Calendar c1 = Calendar.getInstance();
		String result = "";
		c1.add(2, month_num);
		result = String.valueOf(c1.get(1));
		if ((c1.get(2) + 1) >= 10) {
			result = result + String.valueOf(c1.get(2) + 1);
		} else {
			result = result + "0" + String.valueOf(c1.get(2) + 1);
		}
		return result;
	}

	/**
	 * 获取当前日期中的年份部分。
	 * 
	 * @return
	 */
	public static int getSysYear() {
		Calendar calendar = new GregorianCalendar();
		int iyear = calendar.get(Calendar.YEAR);
		return iyear;
	}

	/**
	 * 获取当前日期的月份部分。
	 * 
	 * @return
	 */
	public static int getSysMonth() {
		Calendar calendar = new GregorianCalendar();
		int imonth = calendar.get(Calendar.MONTH) + 1;
		return imonth;
	}

	/**
	 * 获取当前日期为该月中的第几天。
	 * 
	 * @return
	 */
	public static int getSysDay() {
		Calendar calendar = new GregorianCalendar();
		int idate = calendar.get(Calendar.DAY_OF_MONTH);
		return idate;
	}

	/**
	 * 2017    8    7   
	 * 
	 * @return
	 */
	public static String getDateString2() {
		String tmp = "";
		tmp = getSysYear() + "    " + getSysMonth() + "    " + getSysDay()
				+ "    ";
		return tmp;
	}

	public static String getNextMonth(String startDate, int i) {
		int start = Integer.parseInt(startDate);
		int next = start + i;
		int year = Integer.parseInt(String.valueOf(next).substring(0, 4));
		int month = Integer.parseInt(String.valueOf(next).substring(4));
		if (month > 12) {
			year = year + 1;
			month = month - 12;
		}
		if (month < 10) {
			return year + "0" + month;
		} else {
			return year + "" + month;
		}
	}

	// 取得 某年某月的天数
	// 月的数值为 0-11
	public static int getDays(String yearMonth) {
		int[] days = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int year = Integer.parseInt(yearMonth.substring(0, 4));
		int month = Integer.parseInt(yearMonth.substring(4)) - 1;
		if (month == 1) {
			if (year % 4 == 0) {
				if (year % 100 == 0) {
					return 28;
				} else {
					return 29;
				}
			} else {
				return 28;
			}
		} else {
			return days[month];
		}
	}

	/**
	 * 判断当前日期是否包含在两个日期中
	 * 
	 * @param startDay
	 * @param startDayHour
	 * @param endDay
	 * @param endDayHour
	 * @return
	 */
	public static int isBetweenDays(String startDay, String startDayHour,
			String endDay, String endDayHour) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHH");
		Date date = new Date();
		String today = formatter.format(date);
		startDay = today.substring(0, 6) + startDay + startDayHour;
		endDay = today.substring(0, 6) + endDay + endDayHour;
		if (today.compareTo(startDay) >= 0 && today.compareTo(endDay) <= 0) {
			return 0;
		} else {
			return 1;
		}
	}

	public static boolean isDate(String dateStr, String dateFomrat) {
		// "yyyy-mm-dd","yyyyMMddHHmmss"
		boolean tmp = false;
		try {
			Date d = getDate(dateStr, dateFomrat);
			DateFormat dfmt = new SimpleDateFormat(dateFomrat);
			dfmt.format(d);
			tmp = true;
		} catch (ParseException e) {
			tmp = false;
		}
		return tmp;
	}

	/**
	 * 判断结束日期是是大于开始时间
	 * 
	 * @param startDay
	 * @param startDayHour
	 * @param endDay
	 * @param endDayHour
	 * @return
	 */
	public static boolean isBetweenDays(String startDay, String endDay,
			String dateFomrat) {
		boolean tmp = false;

		if (isDate(startDay, dateFomrat) && isDate(endDay, dateFomrat)) {
			try {
				if (getDate(startDay, dateFomrat).getTime() > (getDate(endDay,
						dateFomrat)).getTime()) {
					tmp = false;
				} else
					tmp = true;
			} catch (ParseException e) {
				;
			}
		}

		return tmp;
	}

	public static String getYyyyMm(String theDayYyyy_mm_dd) {
		String dayYYYYMMDD = "";
		dayYYYYMMDD = StringUtil.replace(theDayYyyy_mm_dd, "-", "");
		return dayYYYYMMDD.substring(0, 6);
	}

	public static boolean isDateStr(String strDate, String pattern) {
		boolean tmp = true;

		try {
			getDate(strDate, pattern);
		} catch (ParseException e) {
			tmp = false;
		}

		return tmp;
	}

	/**
	 * 获得指定日期之后若干天的日期
	 */
	public static Date getAfterNDaysDate(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, days);

		return cal.getTime();
	}

	/**
	 * @author hashan 计算两个日期相隔的分钟数
	 */
	public static long DaysBetweenTwoDate(String firstString,
			String secondString) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date firstDate = null;
		Date secondDate = null;
		try {
			firstDate = df.parse(firstString);
			secondDate = df.parse(secondString);
		} catch (Exception e) {
			// 日期型字符串格式错误
		}

		long nDay = ((secondDate.getTime() - firstDate.getTime()) / (60 * 1000));
		return nDay;
	}
	
	/**
	 * 增加分
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date addMinute(Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(cal.MINUTE, num);
		return cal.getTime();
	}
}
