/*
 * Copyright (c) 2014, 2016, XIANDIAN and/or its affiliates. All rights reserved.
 * XIANDIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.xiandian.cloud.storage.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {

	public static String longToString(String dateFormat,Long millSec) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	    Date date= new Date(millSec);
	    return sdf.format(date);
	}
	public static String DateToString(String dateFormat,Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}
	
	public static String timeTostrHMS(Date date) {
		String strDate = "";
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			strDate = format.format(date);
		}
		return strDate;
	}
	
	public static String gmtToHMS(String gmt){
		Date date = new Date();
		// Tue, 17 Jan 2017 01:18:27 GMT
		SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
		try {
			date = format.parse(gmt);
			date = new Date(date.getTime() + 8*60*60*1000); // 时区差
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timeTostrHMS(date);
	}
}
