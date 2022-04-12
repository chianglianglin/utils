package com.example.demo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class DateConvertingUtils {
	public static final String strDateFormat = "yyyy-MM-dd HH:mm";
	public static final int milisecondsPerMinute = 60 * 1000;

	public static String convertDateToString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		return dateFormat.format(date);
	}

	public static Boolean dateComparator(Date date1, Date date2) {
		return date1.compareTo(date2) > 0;
	}

	public static String formatDate(Date date, Integer num, Integer interval) {
		Date roundOffDate = roundOffInterval(date, interval);
		Date newDate = dateAddMinute(roundOffDate, interval * num);
		return convertDateToString(newDate);
	}

	public static Integer dateDiffInMinutes(Date begin, Date end) {
		long diff = end.getTime() - begin.getTime();
		int min = (int) diff / milisecondsPerMinute;
		return min;
	}

	public static Date setSecondsToZero(Date date) {
		Calendar calendar = converDateToCalendar(date);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	// 取得0時0分0秒
	public static Date getStartOfDate(Date date) {
		Calendar calendar = converDateToCalendar(date);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Calendar converDateToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public static Date dateAddMinute(Date date, Integer min) {
		Calendar calendar = converDateToCalendar(date);
		calendar.add(Calendar.MINUTE, min);
		return calendar.getTime();
	}

	/**
	 * 把時間中的分鐘歸零(在某區間)
	 * @param date
	 * @param interval 歸零區間
	 * @return
	 */
	public static Date roundOffIntervalForMinute(Date date, Integer interval) {
		Calendar calendar = converDateToCalendar(date);
		int currentHour = calendar.get(Calendar.HOUR);
		int currentMin = calendar.get(Calendar.MINUTE);
		int residual = (currentHour * 60  + currentMin) % interval;
		Date newDate = dateAddMinute(date, residual * -1);
		return newDate;
	}

	// 將分鐘數向下歸到0或5, 秒數歸0
	public static Date roundOffInterval(Date date, Integer interval) {
		Calendar calendar = converDateToCalendar(date);
		int currentHour = calendar.get(Calendar.HOUR);
		int currentMin = calendar.get(Calendar.MINUTE);
		int residual = (currentHour * 60  + currentMin) % interval;
		Date newDate = dateAddMinute(date, residual * -1);
		return setSecondsToZero(newDate);
	}

	// 將分鐘數向下歸到0或5, 秒數歸0
	public static Date roundUpInterval(Date date, Integer interval) {
		Date newDate = roundOffInterval(date, interval);
		newDate = dateAddMinute(newDate, interval);
		return setSecondsToZero(newDate);
	}

	// 建立groups, 依起迄時間 & interval分組
	public static Map<String, Double> createDateGroups(Date begin, Date end, Integer interval) {
		Map<String, Double> groups = new TreeMap<String, Double>();
		Date beginTime = roundOffInterval(begin, interval);
		Date endTime = roundOffInterval(end, interval);
		long diffInMinute = (endTime.getTime() - beginTime.getTime()) / milisecondsPerMinute;
		for (int step = 0; step < diffInMinute; step += interval) {
			String str = convertDateToString(dateAddMinute(beginTime, step));
			groups.put(str, new Double(0));
		}
		return groups;
	}
}