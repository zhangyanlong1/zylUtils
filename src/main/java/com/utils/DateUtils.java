package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
	
	
	 /**
	  * 一天有多少毫秒
	  */
	static final int millSecondsPerDay = 1000*60*60*24;
	
	
	/**
	 * 计算年龄
	 */
	public static int getAge(Date birthday) {
		Calendar calendar = Calendar.getInstance();
		
		//设置生日的年  月  日
		calendar.setTime(birthday);
		int birthYear = calendar.get(Calendar.YEAR);
		int birthMonth = calendar.get(Calendar.MONTH);
		int birthDate = calendar.get(Calendar.DATE);		
		
		//设置当前时间的年月日
		calendar.setTime(new Date());
		int cYear = calendar.get(Calendar.YEAR);
		int cMonth = calendar.get(Calendar.MONTH);
		int cDate = calendar.get(Calendar.DATE);
		
		
		//计算年龄
		int age = cYear - birthYear;
		//会存在  当年你还没有到完整的一年
		if(cMonth<birthMonth) {
			age--;
		}else if(cMonth==birthMonth && cDate<birthDate) {
			age--;
		}
		return age;
	}
	
	
	/**
	 *   判断还剩余多少天
	 */
	
	public static int getRemainDays(Date future) {
		return (int) (future.getTime() - new Date().getTime() / millSecondsPerDay);
		
	}
	
	/**
	 * 判断是否为当天
	 */
	public static  boolean isToday(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String dateStr = dateFormat.format(date);
		String todayStr = dateFormat.format(new Date());
		return dateStr.equals(todayStr);
	}
	
	/**
	 * 获取当月的月初
	 */
	
	public static Date getBeginOfMonth() {
			//获取日历实例
		Calendar calendar = Calendar.getInstance();
		
		//设置当前时间
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.AM_PM,Calendar.AM);
		calendar.set(Calendar.DATE,1);
		
		return calendar.getTime();
		
	}
	
	
	
	/**
	 * 获取当前月的月末
	 * @return
	 */
	public static Date getEndOfMonth() {
		// 获取日历的实例
		Calendar instance = Calendar.getInstance();
		// 设置成当前的时间
		instance.setTime(new Date());
		instance.add(Calendar.MONTH, 1);// 月份加1
		
		// 下列代码设置成月初
		instance.set(Calendar.SECOND, 0);// 设置0秒
		instance.set(Calendar.MINUTE, 0);// 设置0分
		instance.set(Calendar.HOUR, 0);// 设置0小时
		instance.set(Calendar.AM_PM, Calendar.AM);// 设置上午
		instance.set(Calendar.DATE, 1);// 设置1日
		
		// 减去一秒 变成当月的月末
		instance.add(Calendar.SECOND, -1);// 秒减去1
		return instance.getTime();
		
	}
	
	
	
	/**
	 * 是不是本周
	 * @param date
	 * @return
	 */
	public static boolean  isThisWeek(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

		Calendar firstDayOfWeek = Calendar.getInstance(Locale.getDefault());

		firstDayOfWeek.setFirstDayOfWeek(Calendar.MONDAY);

		int day = firstDayOfWeek.get(Calendar.DAY_OF_WEEK);

		firstDayOfWeek.add(Calendar.DATE, -day+1+1);// 后面的+1是因为从周日开始

		// 本周一的日期

		System.out.println(format.format(firstDayOfWeek.getTime()));

		Calendar lastDayOfWeek = Calendar.getInstance(Locale.getDefault());

		lastDayOfWeek.setFirstDayOfWeek(Calendar.MONDAY);

		day = lastDayOfWeek.get(Calendar.DAY_OF_WEEK);

		lastDayOfWeek.add(Calendar.DATE, 7-day+1);

		// 本周星期天的日期

		System.out.println(format.format(lastDayOfWeek.getTime()));
		
		return (date.getTime()<lastDayOfWeek.getTime().getTime() &&
				date.getTime()>firstDayOfWeek.getTime().getTime() );
	}
	
	
	/*
	* 方法1：(10分)
	* 给一个时间对象，返回该时间所在月的1日0时0分0秒。例如一个Date对象的值是2019-05-18 11:37:22
	* 则返回的结果为2019-05-01 00:00:00
	*/
	public static Date getDateByInitMonth(Date src) throws ParseException{
		Calendar c = Calendar.getInstance();
		c.setTime(src);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parse = sdf.parse(sdf.format(new Date(new Long(c.getTimeInMillis()))));
		return parse;
	}
	/*
	* 方法2：(10分)
	* 给任意一个时间对象，返回该时间所在月的最后日23时59分59秒，需要考虑月大月小和二月特殊情况。
	* 例如一个Date对象的值是2019-05-18 11:37:22，则返回的时间为2019-05-31 23:59:59
	* 例如一个Date对象的值是2019-02-05 15:42:18，则返回的时间为2019-02-28 23:59:59
	*/
	public static Date getDateByFullMonth(Date src) throws ParseException{
		Calendar c = Calendar.getInstance();
		c.setTime(src);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parse = sdf.parse(sdf.format(new Date(new Long(c.getTimeInMillis()))));
		return parse;
	}
	
	
	
	/*
	* 方法1：(10分)
	* 给一个时间对象，返回该时间所在月的1日0时0分0秒。例如一个Date对象的值是2019-05-18 11:37:22
	* 则返回的结果为2019-05-01 00:00:00
	*/
	public static Date getDateByInitMonth1(Date src){
		
	
		
		return src;
	//TODO 实现代码
	}
	/*
	* 方法2：(10分)
	* 给任意一个时间对象，返回该时间所在月的最后日23时59分59秒，需要考虑月大月小和二月特殊情况。
	* 例如一个Date对象的值是2019-05-18 11:37:22，则返回的时间为2019-05-31 23:59:59
	* 例如一个Date对象的值是2019-02-05 15:42:18，则返回的时间为2019-02-28 23:59:59
	*/
	public static Date getDateByFullMonth1(Date src){
		return src;
	//TODO 实现代码
	}
}
