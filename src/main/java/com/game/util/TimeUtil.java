package com.game.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

	public final static long ONE_DAY = 86400000L;
	public final static long ONE_WEEK = 604800000L;
	public final static long HALF_HOUR = 1800000;
	public final static long ONE_HOUR = 3600000;
	public final static long ONE_MINUTE = 60000;
	public final static long ONE_month = 2592000000L;
	/**
	 * 判断是否同一天
	 * 
	 * @param time
	 * @return
	 */
	public static boolean isTheSameDay(long time) {
		long now = System.currentTimeMillis();
		Calendar c1 = Calendar.getInstance();
		c1.setTimeInMillis(time);

		Calendar c2 = Calendar.getInstance();
		c2.setTimeInMillis(now);

		return (c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH)
				&& c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) && c1
					.get(Calendar.YEAR) == c2.get(Calendar.YEAR));
	}

	public static long now() {
		return System.currentTimeMillis();
	}

	public static String toMillisString() {
		return "" + System.currentTimeMillis();
	}

	public static long toMillis(String strMillis) {
		return Long.valueOf(strMillis);
	}

	/**
	 * same as now()
	 * 
	 * @param strMillis
	 * @return
	 */
	public static long toMillis() {
		return System.currentTimeMillis();
	}

	public static long nextDay(long millis) {
		return millis + ONE_DAY;
	}

	public static long nextWeek(long millis) {
		return millis + ONE_WEEK;
	}

	public static long nextHour(long millis) {
		return millis + ONE_HOUR;
	}

	public static long nextHalfHour(long millis) {
		return millis + HALF_HOUR;
	}

	public static long lastWeek(long millis) {
		return millis - ONE_WEEK;
	}

	public static long lastDay(long millis) {
		return millis - ONE_DAY;
	}

	public static long lastHour(long millis) {
		return millis - ONE_HOUR;
	}

	public static long lastMinute(long millis) {
		return millis - ONE_MINUTE;
	}

	/**
	 * 
	 * @return 当天的0点0分0秒
	 */
	public static long CLOCK_0() { 
		Calendar c = Calendar.getInstance();
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		c.set(Calendar.MILLISECOND,0);		
		return c.getTimeInMillis();
	} 
	/**
	 * 计划在几点
	 * 
	 * @param HOUR
	 * @return
	 */
	public static long scheduledAt(int HOUR) {
		return scheduledAt(HOUR, 0);
	}

	/**
	 * 计划在几点几分
	 * 
	 * @param HOUR
	 * @param minute
	 * @return
	 */
	public static long scheduledAt(int HOUR, int minute) {
		long scheduledAt = CLOCK_0() + ONE_HOUR * HOUR + ONE_MINUTE * minute;
		if (scheduledAt <= now()) {
			scheduledAt += ONE_DAY;
		}

		return scheduledAt;
	}
	
	public static long fixedAt(int HOUR, int minute){
		return CLOCK_0() + ONE_HOUR * HOUR + ONE_MINUTE * minute;
	}

	/**
	 * 将时间格式和时间字符串值传入，获得时间戳<br>
	 * 举例时间格式 yyyy-MM-dd hh:mm:ss
	 * 
	 * @param timeStr
	 * @param format
	 * @return
	 */
	public static long getTime(String timeStr, String format) {
		try {
			// 时间格式 yyyy-MM-dd hh:mm:ss
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date d = sdf.parse(timeStr);
			return d.getTime();
		} catch (Exception ex) {
		}
		return 0L;
	}

	/**
	 * 
	 * @l ：秒
	 * @return
	 */
	public static String formatSec(long l) {
		String time = new String();

		long t = l;

		long sec = t % 60;
		t -= sec;
		t /= 60;

		long minute = t % 60;
		t -= minute;
		t /= 60;

		long hour = t;

		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		df.applyPattern("0");
		time += (df.format(hour));

		df.applyPattern("00");
		time += (":" + df.format(minute));
		time += (":" + df.format(sec));

		return time;
	}

	/**
	 * 
	 * @l 单位：秒
	 * @return
	 */
	public static String formatMSec(long l) {
		String time = new String();

		long t = l;

		long msec = t % 1000;
		t -= msec;
		t /= 1000;

		long sec = t % 60;
		t -= sec;
		t /= 60;

		long minute = t % 60;
		t -= minute;
		t /= 60;

		long hour = t;

		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		df.applyPattern("0");
		time += (df.format(hour));

		df.applyPattern("00");
		time += (":" + df.format(minute));
		time += (":" + df.format(sec));

		df.applyPattern("000");
		time += (":" + df.format(msec));

		return time;
	}

	/**
	 * 将时间转换成 day:hour:minute:second
	 * 
	 * @param 单位
	 *            ：秒
	 * @return
	 */
	public static String formatDay(long l) {
		String time = new String();

		long t = l;

		long sec = t % 60;
		t -= sec;
		t /= 60;

		long minute = t % 60;
		t -= minute;
		t /= 60;

		long hour = t % 24;
		t -= hour;
		t /= 24;

		long day = t;

		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		df.applyPattern("0");
		time += (df.format(day));

		df.applyPattern("00");
		time += (":" + df.format(hour));
		time += (":" + df.format(minute));
		time += (":" + df.format(sec));

		return time;
	}

	/**
	 * 将时间转换成 day:hour:minute:second
	 * 
	 * @param 单位
	 *            ：秒
	 * @return
	 */
	public static String formatDayCN(long l) {
		String time = new String();

		long t = l;

		long sec = t % 60;
		t -= sec;
		t /= 60;

		long minute = t % 60;
		t -= minute;
		t /= 60;

		long hour = t % 24;
		t -= hour;
		t /= 24;

		long day = t;

		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		df.applyPattern("0");
		time += (df.format(day));
		time += ("天" + df.format(hour));
		time += ("小时" + df.format(minute));
		time += ("分" + df.format(sec));
		time += "秒";

		return time;
	}

	/**
	 * 
	 * @param 单位
	 *            ：毫秒
	 * @return
	 */

	public static String convert(long l) {
		String time = new String();

		long t = l;
		long msec = t % 1000;
		t -= msec;
		t /= 1000;

		long sec = t % 60;
		t -= sec;
		t /= 60;

		long minute = t % 60;
		t -= minute;
		t /= 60;

		long hour = t % 24;
		t -= hour;
		t /= 24;

		long day = t % 24;

		if (day > 0) {
			time += ("" + day);
			time += "day";
		}
		if (day > 0 || hour > 0) {
			time += (" " + hour);
			time += "hour";
		}
		if (day > 0 || hour > 0 || minute > 0) {
			time += (" " + minute);
			time += "min";
		}
		if (day > 0 || hour > 0 || minute > 0 || sec > 0) {
			time += (" " + sec);
			time += "sec";
		}
		if (day > 0 || hour > 0 || minute > 0 || sec > 0 || msec >= 0) {
			time += (" " + msec);
			time += "msec";
		}

		return time;
	}

	/**
	 * 支持把 hh:mm:ss 格式的字符串，转换成秒为单位的long。 mm:ss 或者 ss 格式的字符串也可以
	 * 
	 * @param ss
	 * @return 单位:秒
	 */
	public static long format(String ss) {
		String[] tt = ss.split(":");
		int[] unit = new int[4];
		unit[0] = 60;
		unit[1] = 60;
		unit[2] = 60;
		unit[3] = 24;

		long total = 0;
		int j = 0;
		int x = 1;
		for (int i = tt.length - 1; i >= 0; i--, j++) {
			String t = tt[i];

			int time = Integer.parseInt(t);
			time *= x;
			total += time;
			x *= unit[j];
		}

		return total;
	}

	/**
	 * 判断是否是今天
	 */
	public static boolean isSameDay(Date d1, long l2) {
		Date d2 = new Date();
		d2.setTime(l2);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str1 = sdf.format(d1);
		String str2 = sdf.format(d2);
		if (str1.equals(str2)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否是今天
	 */
	public static boolean isToday(Date d1) {
		return isSameDay(d1, new Date());
	}

	/**
	 * 判断是否是今天
	 */
	public static boolean isToday(long time) {
		Date dt = new Date();
		dt.setTime(time);
		return isSameDay(dt, new Date());
	}

	public static boolean isSameDay(Date d1, Date d2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date1Str = sdf.format(d1);
		String date2Str = sdf.format(d2);
		return date1Str.equals(date2Str);
	}

	public static String format(Date d) {
		if (d == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yy年MM月dd日");
		return sdf.format(d);
	}

	public static String format(Date d, String formatter) {
		if (d == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(formatter); // "MM-dd HH:mm"
		return sdf.format(d);
	}

	public static String formatDate(Date d) {
		Date today = new Date();
		Date yesterday = new Date();
		yesterday.setTime(today.getTime() - 24 * 60 * 60 * 1000);
		Date beforeYesterday = new Date();
		beforeYesterday.setTime(today.getTime() - 48 * 60 * 60 * 1000);

		if (isSameDay(d, today)) {
			return "今天";
		} else if (isSameDay(d, yesterday)) {
			return "昨天";
		} else if (isSameDay(d, beforeYesterday)) {
			return "前天";
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
			return sdf.format(d);
		}
	}

	public static String formatTime(long time) {
		Date d = new Date();
		d.setTime(time);
		return formatDate(d);
	}

	/**
	 * 动态定时的 小时计算<br>
	 * 仅仅支持小时
	 * 
	 * @param nowClock
	 * @param delay
	 * @param clockBeforeDelay
	 * @return
	 */
	public static int scheduledAtHour(int nowClock, int delay,
			int clockBeforeDelay) {
		if (nowClock < clockBeforeDelay)
			return clockBeforeDelay;
		if ((nowClock - clockBeforeDelay) % delay == 0)
			return nowClock + delay;
		return nowClock - nowClock % delay + clockBeforeDelay % delay;
	}

	/**
	 * 现在几点
	 * 
	 * @return
	 */
	public static int getHour() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.HOUR_OF_DAY);
	}
	
	
	/**
	 * 
	 * @param day  :0标识当天,-1标识前一天
	 * @return
	 */
	public static long getDayBeginTime(int day){
		Calendar c = Calendar.getInstance();
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH)+day, 0, 0, 0);
		c.set(Calendar.MILLISECOND,0);		
		return c.getTimeInMillis();
	}
	
	/**
	 * 
	 * @param month  :0标识当天,-1标识前一天
	 * @return
	 */
	public static long getMonthBeginTime(int month){
		Calendar c = Calendar.getInstance();
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+month,
				1, 0, 0, 0);
		c.set(Calendar.MILLISECOND,0);		
		return c.getTimeInMillis();
	}
	
	/**
	 * 获得几分钟之前的时间
	 * @param minute
	 * @return
	 */
	public static long getBeforeTime(int minute){
		return System.currentTimeMillis()-minute*ONE_MINUTE;
	}

	/**
	 * 获得当前的系统时间
	 * @return
	 */
	public static long getNowTime() { 
		return System.currentTimeMillis();
	}	
	
}
