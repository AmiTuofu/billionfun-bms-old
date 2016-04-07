package com.billionfun.bms.product.mall.common.utils;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
	public final static String YEAR = "year";

	public final static String MONTH = "month";

	public final static String DAY = "day";

	public final static String WEEK = "week";

	public final static String HOUR = "hour";

	public final static String MINUTE = "minute";

	public final static String SECOND = "second";
	public static final String YYMMDD = "yyyy-MM-dd HH:mm:ss";
	public static final String YYMMDDhhmmss = "yyyyMMddHHmmss";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";

	public static TimeZone UTC = TimeZone.getTimeZone("UTC");

	/* :TODO: let Locale/TimeZone come from init args for rounding only */

	/** TimeZone for DateMath (UTC) */
	protected static final TimeZone MATH_TZ = UTC;
	/** Locale for DateMath (Locale.US) */
	protected static final Locale MATH_LOCALE = Locale.US;

	/**
	 * Fixed TimeZone (UTC) needed for parsing/formating Dates in the canonical
	 * representation.
	 */
	protected static final TimeZone CANONICAL_TZ = UTC;
	/**
	 * Fixed Locale needed for parsing/formating Milliseconds in the canonical
	 * representation.
	 */
	protected static final Locale CANONICAL_LOCALE = Locale.US;

	public static String getDateByAddedMin(int interval) {

		// quartz format(ss mm hh dd MM ? yyyy)
		SimpleDateFormat df = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
		Calendar time = Calendar.getInstance();
		time.add(Calendar.MINUTE, interval);
		Date date = time.getTime();
		return df.format(date);
	}

	public static String getDateByAddedSecond(int interval) {

		// quartz format(ss mm hh dd MM ? yyyy)
		SimpleDateFormat df = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
		Calendar time = Calendar.getInstance();
		time.add(Calendar.SECOND, interval);
		Date date = time.getTime();
		return df.format(date);
	}

	public static String formatYYMMDDHHmmss(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	public static String formatByPattern(Date date, String pattern) {
		if (null == date)
			return null;
		if (null == pattern)
			pattern = YYMMDD;
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}

	/**
	 * string to Date
	 * 
	 * @param dateStr
	 * @return Date
	 */
	public static Date toDate(String dateStr) {

		Date date = null;
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern("yyyy-MM-dd");
		try {
			date = formater.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date toDate(Date date) {

		DateFormat df = new SimpleDateFormat(YYYY_MM_DD);
		try {
			date = df.parse(df.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date toDate(Date date, String formaterString) {

		DateFormat df = new SimpleDateFormat(formaterString);
		try {
			date = df.parse(df.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	

	/**
	 * string to Date as format
	 * 
	 * @param dateStr
	 * @param formaterString
	 * @return
	 */
	public static Date toDate(String dateStr, String formaterString) {

		Date date = null;
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern(formaterString);
		try {
			date = formater.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * Date to string
	 * 
	 * @param date
	 * @return
	 */
	public static String toString(Date date) {
		if (date == null)
			return null;
		String time;
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern("yyyy-MM-dd");
		time = formater.format(date);
		return time;
	}

	/**
	 * date to string as format
	 * 
	 * @param date
	 * @param formaterString
	 * @return
	 */
	public static String toString(Date date, String formaterString) {

		String time;
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern(formaterString);
		time = formater.format(date);
		return time;
	}

	/**
	 * Get the specified date with the timeoff seconds
	 * 
	 * @param date
	 * @param timeOff
	 * @return Date
	 */
	public static Date getDateWithTimeOff(Date date, long timeOff) {

		long time = date.getTime() + timeOff * 1000;
		return new Date(time);
	}

	/**
	 * method
	 * 
	 * @param dateString
	 * 
	 * @return dataTime timestamp
	 */
	public final static java.sql.Timestamp string2Time(String dateString)
			throws java.text.ParseException {

		DateFormat dateFormat;

		dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");
		dateFormat.setLenient(false);
		java.util.Date timeDate = dateFormat.parse(dateString);
		java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());
		return dateTime;
	}

	/**
	 * method
	 * 
	 * @param dateString
	 * 
	 * @param formaterString
	 *            dateString
	 * @return
	 * @throws java.text.ParseException
	 */
	public final static java.sql.Timestamp string2Time(String dateString,
			String formaterString) throws java.text.ParseException {

		DateFormat dateFormat;
		dateFormat = new SimpleDateFormat(formaterString);
		// dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		dateFormat.setLenient(false);
		java.util.Date timeDate = dateFormat.parse(dateString);
		java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());
		return dateTime;
	}

	public static String toExternal(Date d) {
		return fmtThreadLocal.get().format(d) + 'Z';
	}

	public static String toExternal(Timestamp d) {
		return fmtThreadLocal.get().format(d) + 'Z';
	}

	public final static Timestamp nowTimestamp() {

		return new Timestamp(System.currentTimeMillis());
	}

	public final static Date nowDate() {

		return new Date(System.currentTimeMillis());
	}

	private final static ThreadLocalDateFormat fmtThreadLocal = new ThreadLocalDateFormat(
			new ISO8601CanonicalDateFormat());

	private static class ISO8601CanonicalDateFormat extends SimpleDateFormat {

		protected NumberFormat millisParser = NumberFormat
				.getIntegerInstance(CANONICAL_LOCALE);

		protected NumberFormat millisFormat = new DecimalFormat(".###",
				new DecimalFormatSymbols(CANONICAL_LOCALE));

		public ISO8601CanonicalDateFormat() {
			super("yyyy-MM-dd'T'HH:mm:ss", CANONICAL_LOCALE);
			this.setTimeZone(CANONICAL_TZ);
		}

		@Override
		public Date parse(String i, ParsePosition p) {
			/* delegate to SimpleDateFormat for easy stuff */
			Date d = super.parse(i, p);
			int milliIndex = p.getIndex();
			/* worry aboutthe milliseconds ourselves */
			if (null != d && -1 == p.getErrorIndex()
					&& milliIndex + 1 < i.length()
					&& '.' == i.charAt(milliIndex)) {
				p.setIndex(++milliIndex); // NOTE: ++ to chomp '.'
				Number millis = millisParser.parse(i, p);
				if (-1 == p.getErrorIndex()) {
					int endIndex = p.getIndex();
					d = new Date(d.getTime()
							+ (long) (millis.doubleValue() * Math.pow(10,
									(3 - endIndex + milliIndex))));
				}
			}
			return d;
		}

		@Override
		public StringBuffer format(Date d, StringBuffer toAppendTo,
				FieldPosition pos) {
			/* delegate to SimpleDateFormat for easy stuff */
			super.format(d, toAppendTo, pos);
			/* worry aboutthe milliseconds ourselves */
			long millis = d.getTime() % 1000l;
			if (0l == millis) {
				return toAppendTo;
			}
			int posBegin = toAppendTo.length();
			toAppendTo.append(millisFormat.format(millis / 1000d));
			if (DateFormat.MILLISECOND_FIELD == pos.getField()) {
				pos.setBeginIndex(posBegin);
				pos.setEndIndex(toAppendTo.length());
			}
			return toAppendTo;
		}

		@Override
		public Object clone() {
			ISO8601CanonicalDateFormat c = (ISO8601CanonicalDateFormat) super
					.clone();
			c.millisParser = NumberFormat.getIntegerInstance(CANONICAL_LOCALE);
			c.millisFormat = new DecimalFormat(".###",
					new DecimalFormatSymbols(CANONICAL_LOCALE));
			return c;
		}
	}

	private static class ThreadLocalDateFormat extends ThreadLocal<DateFormat> {
		DateFormat proto;

		public ThreadLocalDateFormat(DateFormat d) {
			super();
			proto = d;
		}

		@Override
		protected DateFormat initialValue() {
			return (DateFormat) proto.clone();
		}
	}

	public static String dateToNoDivString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(YYMMDDhhmmss);
		return format.format(date);
	}

	public static Date noDivStringToDate(String source) {
		SimpleDateFormat format = new SimpleDateFormat(YYMMDDhhmmss);
		Date retDate;
		try {
			retDate = format.parse(source);
		} catch (ParseException e) {
			System.out.println("Failed to parse the input date string "
					+ source + ", replace with current date");
			retDate = new Date();
		}

		return retDate;
	}

	public static Date addDate(Date date, long timescope) {
		long dateTime = date.getTime();
		return new Date(dateTime + timescope);
	}

	public static Date minusDate(Date date, long timescope) {
		long dateTime = date.getTime();
		return new Date(dateTime - timescope);
	}

	public static Date minusDate(Date date, String pattern, String type,
			int count) {
		if (date == null || checkParam(pattern) || checkParam(type)) {
			return null;
		}
		if (0 == count) {
			return date;
		}
		DateFormat df = new SimpleDateFormat(pattern);
		try {
			date = df.parse(df.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Locale locale = Locale.getDefault();
		Calendar calendar = new GregorianCalendar(locale);
		calendar.setTime(date);

		if (DateUtil.YEAR.equals(type)) {
			calendar.add(Calendar.YEAR, -count);
		} else if (DateUtil.MONTH.equals(type)) {
			calendar.add(Calendar.MONTH, -count);
		} else if (DateUtil.DAY.equals(type)) {
			calendar.add(Calendar.DAY_OF_MONTH, -count);
		} else if (DateUtil.WEEK.equals(type)) {
			calendar.add(Calendar.WEEK_OF_MONTH, -count);
		} else if (DateUtil.HOUR.equals(type)) {
			calendar.add(Calendar.HOUR, -count);
		} else if (DateUtil.MINUTE.equals(type)) {
			calendar.add(Calendar.MINUTE, -count);
		} else if (DateUtil.SECOND.equals(type)) {
			calendar.add(Calendar.SECOND, -count);
		} else {
			return null;
		}
		return calendar.getTime();
	}

	public static Date minusDate(Date date, BigInteger timescope) {
		long dateTime = date.getTime();
		BigInteger biFirst = new BigInteger(String.valueOf(dateTime));
		BigInteger biMinus = biFirst.subtract(timescope);
		return new Date(biMinus.longValue());
	}

	public static String addDate(String dateStr, String pattern, String type,
			int count) {
		if (checkParam(dateStr) || checkParam(pattern) || checkParam(type)) {
			return "";
		}
		if (0 == count) {
			return dateStr;
		}
		Date date = toDate(dateStr, pattern);
		Locale locale = Locale.getDefault();
		Calendar calendar = new GregorianCalendar(locale);
		calendar.setTime(date);

		if (DateUtil.YEAR.equals(type)) {
			calendar.add(Calendar.YEAR, count);
		} else if (DateUtil.MONTH.equals(type)) {
			calendar.add(Calendar.MONTH, count);
		} else if (DateUtil.DAY.equals(type)) {
			calendar.add(Calendar.DAY_OF_MONTH, count);
		} else if (DateUtil.WEEK.equals(type)) {
			calendar.add(Calendar.WEEK_OF_MONTH, count);
		} else if (DateUtil.HOUR.equals(type)) {
			calendar.add(Calendar.HOUR, count);
		} else if (DateUtil.MINUTE.equals(type)) {
			calendar.add(Calendar.MINUTE, count);
		} else if (DateUtil.SECOND.equals(type)) {
			calendar.add(Calendar.SECOND, count);
		} else {
			return "";
		}

		return toString(calendar.getTime(), pattern);
	}

	/**
	 * 鍒ゆ柇鍙傛暟鏄惁绛変簬null鎴栬�绌�
	 * 
	 * @param param
	 * @return
	 */
	public static boolean checkParam(Object param) {
		if (null == param || "".equals(param)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @Title: 得到两个日期之间的所有日期
	 * @Description: TODO
	 * @param @param p_start
	 * @param @param p_end
	 * @param @return
	 * @return List<Date>
	 * @throws
	 */
	public static List<Date> getDates(Date start, Date end, String type,int count) {
		Calendar c_start = date2Cal(start);
		Calendar c_end = date2Cal(end);
		List<Date> result = new ArrayList<Date>();
		Calendar temp = c_start;
		while (temp.before(c_end)||temp.equals(c_end)) {
			result.add(temp.getTime());
			if (DateUtil.YEAR.equals(type)) {
				temp.add(Calendar.YEAR, count);
			} else if (DateUtil.MONTH.equals(type)) {
				temp.add(Calendar.MONTH, count);
			} else if (DateUtil.DAY.equals(type)) {
				temp.add(Calendar.DAY_OF_MONTH, count);
			} else if (DateUtil.WEEK.equals(type)) {
				temp.add(Calendar.WEEK_OF_MONTH, count);
			} else if (DateUtil.HOUR.equals(type)) {
				temp.add(Calendar.HOUR, count);
			} else if (DateUtil.MINUTE.equals(type)) {
				temp.add(Calendar.MINUTE, count);
			} else if (DateUtil.SECOND.equals(type)) {
				temp.add(Calendar.SECOND, count);
			}else{
				return null;
			}
		}
		return result;
	}
	
	public static int getDateSpace(String date1, String date2)
            throws ParseException {

        int result = 0;

        Calendar calst = Calendar.getInstance();;
        Calendar caled = Calendar.getInstance();

        calst.setTime(toDate(date1,"yyyy-MM-dd"));
        caled.setTime(toDate(date2,"yyyy-MM-dd"));
 
         //设置时间为0时   
         calst.set(Calendar.HOUR_OF_DAY, 0);   
         calst.set(Calendar.MINUTE, 0);   
         calst.set(Calendar.SECOND, 0);   
         caled.set(Calendar.HOUR_OF_DAY, 0);   
         caled.set(Calendar.MINUTE, 0);   
         caled.set(Calendar.SECOND, 0);   
        //得到两个日期相差的天数   
         int days = ((int)(caled.getTime().getTime()/1000)-(int)(calst.getTime().getTime()/1000))/3600/24;   
         
        return days;   
    }
	
	public static int getDateSpace(Date date1, Date date2)
            throws ParseException {

        int result = 0;

        Calendar calst = Calendar.getInstance();;
        Calendar caled = Calendar.getInstance();

        calst.setTime(toDate(date1,"yyyy-MM-dd"));
        caled.setTime(toDate(date2,"yyyy-MM-dd"));
 
         //设置时间为0时   
         calst.set(Calendar.HOUR_OF_DAY, 0);   
         calst.set(Calendar.MINUTE, 0);   
         calst.set(Calendar.SECOND, 0);   
         caled.set(Calendar.HOUR_OF_DAY, 0);   
         caled.set(Calendar.MINUTE, 0);   
         caled.set(Calendar.SECOND, 0);   
        //得到两个日期相差的天数   
         int days = ((int)(caled.getTime().getTime()/1000)-(int)(calst.getTime().getTime()/1000))/3600/24;   
         
        return days;   
    }
	
	 public static int getMonthSpace(String date1, String date2)
	            throws ParseException {

	        int result = 0;


	        Calendar c1 = Calendar.getInstance();
	        Calendar c2 = Calendar.getInstance();

	        c1.setTime(toDate(date1, "yyyy-MM-dd"));
	        c2.setTime(toDate(date2, "yyyy-MM-dd"));

	        result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

	        return result == 0 ? 1 : Math.abs(result);

	    }

	public static Calendar date2Cal(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public static void main(String[] args) throws ParseException {
		Date start = toDate("2016-03-02");
		Date end = toDate("2016-04-02");
		List list = getDates(start, end, "year",1);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = df.parse("2004-03-26 13:31:40");
	    Date d2 = df.parse("2004-03-28 11:30:24");
	    Calendar start1 = date2Cal(d1);
	    System.out.println(getDateSpace("2004-03-26 13:31:40", "2004-03-27 11:30:24"));
		// String t = DateUtil.toString(new Date());
		// System.out.println(t);
		// Date date = DateUtil.toDate("2010-06-17");
		// System.out.println(date);
		//
		// // String sToTimestamp = "2005-8-18 14:21:12.123";//
		//
		// String sToTimestamp = "2005-8-18 14:21";
		// Timestamp Timestamp = string2Time(sToTimestamp);
		// System.out.println(Timestamp);
		//
		// String time = DateUtil.toExternal(nowTimestamp());
		// System.out.println(time);
		//
		// System.out.println("..............................");
		// Date startTime = DateUtil.toDate("20120414000000",
		// DateUtil.YYMMDDhhmmss);
		// System.out.println("startTime=" + startTime);
		// System.out.println("startTime long=" + startTime.getTime());
		// Date endTime = DateUtil.toDate("20120415000000",
		// DateUtil.YYMMDDhhmmss);
		// System.out.println("endTime long=" + endTime.getTime());
		//
		// long scope = endTime.getTime() - startTime.getTime();
		// System.out.println("scope long=" + scope);
		//
		// Date minusTime = DateUtil.minusDate(endTime, 86400000);
		// System.out.println("minusTime=" + minusTime);
	}

	@SuppressWarnings("static-access")
	public static String getLastDate(Date date, String pattern) {
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(c.DAY_OF_MONTH, -1);

		return sf.format(c.getTime());
	}
}
