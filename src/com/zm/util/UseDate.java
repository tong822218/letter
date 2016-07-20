package com.zm.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class UseDate {

	/*
	 * java中对日期的加减操作 
	 * gc.add(1,-1)表示年份减一.
	 * gc.add(2,-1)表示月份减一.
	 * gc.add(3.-1)表示周减一.
	 * gc.add(5,-1)表示天减一. 以此类推应该可以精确的毫秒吧.没有再试.大家可以试试. GregorianCalendar类的add(int
	 * field,int amount)方法表示年月日加减. field参数表示年,月.日等. amount参数表示要加减的数量.
	 *
	 * UseDate.java 测试如下:
	 */

	Date d = new Date();
	GregorianCalendar gc = new GregorianCalendar();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

	public String getYears() {
		gc.setTime(d);
		gc.add(1, +1);
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
		return sf.format(gc.getTime());
	}

	public String getHalfYear() {
		gc.setTime(d);
		gc.add(2, +6);
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
		return sf.format(gc.getTime());
	}
	//减十个月
	public String get_10month() {
		gc.setTime(d);
		gc.add(2, -10);
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
		return sf.format(gc.getTime());
	}

	public String getQuarters() {
		gc.setTime(d);
		gc.add(2, +3);
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
		return sf.format(gc.getTime());
	}

	public String getLocalDate() {
		return sf.format(d);
	}

	public static void main(String[] args) {
		UseDate ud = new UseDate();
		System.out.println(ud.getLocalDate());
		System.out.println(ud.getYears());
		System.out.println(ud.getHalfYear());
		System.out.println(ud.getQuarters());
	}
}
