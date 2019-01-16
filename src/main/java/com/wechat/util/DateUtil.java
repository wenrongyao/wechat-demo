package com.wechat.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Timestamp;
import java.text.*;
import java.util.Calendar;
import java.util.Date;


/**
 * Date Utility Class This is used to convert Strings to Dates and Timestamps
 */
public class DateUtil {

    private static Log log = LogFactory.getLog(DateUtil.class);

    public final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public final static SimpleDateFormat SIMPLE_DATE_FORMAT1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public final static SimpleDateFormat simpleDateFormat32 = new SimpleDateFormat("MM/dd/yyyy");

    public final static SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    public final static SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat("MMddyyyyHHmmss");
    public final static SimpleDateFormat simpleDateFormat5 = new SimpleDateFormat("yyyyMMddHHmmss");
    public final static SimpleDateFormat simpleDateFormat6 = new SimpleDateFormat("yyyy/MM/dd");

    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

    // 查询时间后缀
    public final static String START_TIME = " 00:00:00"; // 查询开始时间后缀
    public final static String END_TIME = " 23:59:59"; // 查询结束时间后缀


    private static String timePattern = "HH:mm";

    public static String datePattern = "yyyy-MM-dd HH:mm";

    public static String timestampPattern = "yyyy-MM-dd HH:mm:ss";

    public static String newtimestampPattern = "yyyyMMddHHmmss";

    private static Calendar calendar = Calendar.getInstance();

    private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

    /**
     * This method generates a string representation of a date/time in the
     * format you specify on input
     *
     * @param aMask   the date pattern the string is in
     * @param strDate a string representation of a date
     * @return a converted Date object
     * @throws ParseException
     * @see SimpleDateFormat
     */
    public static final Date convertStringToDate(String aMask, String strDate)
            throws ParseException {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);
        if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '"
                    + aMask + "'");
        }

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            // log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }

    /**
     * This method returns the current date time in the format: MM/dd/yyyy HH:MM
     * a
     *
     * @param theTime the current time
     * @return the current date/time
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(timePattern, theTime);
    }

    public static String getDateStr(Date theTime) {
        return getDateTime(datePattern, theTime);
    }

    /**
     * This method generates a string representation of a date's date/time in
     * the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param aDate a date object
     * @return a formatted string representation of the date
     * @see SimpleDateFormat
     */
    public static final String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            log.error("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }


    /**
     * 根据自定格式获取当前日期:pattern:YYYYMMDD
     *
     * @param pattern 时间的格式：YYYYMMDD或yyyyMMddkkmmssSSS等
     * @param
     * @return
     * @author
     */
    public static String getDateTime(String pattern) {
        if (pattern == null || "".equals(pattern)) {
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar rightNow = Calendar.getInstance();
        rightNow.add(Calendar.DAY_OF_MONTH, 0);
        String dt = sdf.format(rightNow.getTime());
        return dt;
    }

    /**
     * 根据自定格式获取日期:pattern:YYYYMMDD
     *
     * @param pattern 时间的格式：YYYYMMDD或yyyyMMddkkmmssSSS等
     * @param dateNum 在当前时间上向前推或向后延的时间天数
     * @return
     * @author
     */
    public static String getDateTime(String pattern, int dateNum) {
        if (pattern == null || "".equals(pattern)) {
            pattern = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar rightNow = Calendar.getInstance();
        rightNow.add(Calendar.DAY_OF_MONTH, dateNum);
        String dt = sdf.format(rightNow.getTime());
        return dt;
    }

    /**
     * add   pw 2011.7.29根据给定日期算出向前向后的天数
     * 根据自定格式获取日期:pattern:YYYYMMDD
     *
     * @param pattern 时间的格式：YYYYMMDD或yyyyMMddkkmmssSSS等
     * @param dateNum 在当前时间上向前推或向后延的时间天数
     * @return
     * @author
     */
    public static String getDateTime(String pattern, String beginTime, int dateNum) {
        if (pattern == null || "".equals(pattern)) {
            pattern = "yyyy-MM-dd";
        }
        Date startTime = DateStringToDate(beginTime);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(startTime);
        rightNow.add(Calendar.DAY_OF_MONTH, dateNum);
        String dt = sdf.format(rightNow.getTime());
        return dt;
    }

    public static Date DateStringToDate(String s) {
        Date date = new Date();
        try {
            SimpleDateFormat simpledateformat = new SimpleDateFormat(
                    "yyyy-MM-dd");
            ParsePosition parseposition = new ParsePosition(0);
            date = simpledateformat.parse(s, parseposition);
        } catch (Exception ex) {
        }
        return date;
    }

    public static final String getYesterday(String pattern) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return new SimpleDateFormat(pattern).format(calendar.getTime());
    }

    public static boolean isToday(java.sql.Date aDate) {
        final Date now = new Date();
        String nowtime = df.format(now).toString();
        String createtime = df.format(aDate).toString();
        return nowtime.equals(createtime);
    }

    public static Date addDate(Date date, int type, int intervel) {
        if (date == null) {
            date = new Date();
        }
        calendar.setTime(date);
        calendar.add(type, intervel);
        return calendar.getTime();
    }

    /**
     * �õ�����ʱ�������ٷ���
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int getMinuteMargin(Date beginDate, Date endDate) {
        int minute = 0;
        final int mOfMinute = 1000 * 60;
        final long divtime = (endDate.getTime() - beginDate.getTime());
        final long lminute = divtime % mOfMinute > 0 ? divtime / mOfMinute + 1
                : divtime / mOfMinute;
        minute = Long.valueOf(lminute).intValue();
        return minute;
    }

    public static float dateDiff(Timestamp t1, Timestamp t2, int type) {
        float i = t1.getTime() - t2.getTime();
        float f = 0.0f;// i / (1000 * 60);
        switch (type) {
            case 1:// hour
                f = i / (1000 * 60 * 60);
                break;
            case 2:// min
                f = i / (1000 * 60);
                break;
            case 3:// sec
                f = i / (1000);
                break;
            case 0: // defaut is day
                f = i / (1000 * 60 * 60 * 24);
        }
        String temp = "#,##0.0";
        try {
            return Float.valueOf(new DecimalFormat(temp).format(f));
        } catch (Exception e) {
            ;//System.out.println(e);
        }
        return f;
    }

    public static Timestamp dateAdd(Timestamp t1, Integer i, int type) {
        long interval = i * 1000 * 60 * 60 * 24l;
        Timestamp t = null;
        switch (type) {
            case 1:// hour
                interval = 1000 * 60 * 60 * i;
                t = new Timestamp(t1.getTime() + interval);
                break;
            case 2:// min
                interval = 1000 * 60 * i;
                t = new Timestamp(t1.getTime() + interval);
                break;
            case 3:// sec
                interval = 1000 * i;
                t = new Timestamp(t1.getTime() + interval);
                break;
            case 0: // defaut is day
                t = new Timestamp(t1.getTime() + interval);
                break;
            default:
                t = t1;
        }
        return t;
    }

    /**
     * 获取月份的开始日期与结束日期 （结束日期为下月的一号）
     *
     * @param dateTime 日期
     * @return Date[]
     */
    public static Date[] getMonthOfYear(Date dateTime) {
        Date[] date = new Date[2];
        try {
            int monthfield, yearReport;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateTime);
            yearReport = calendar.get(Calendar.YEAR);
            monthfield = calendar.get(Calendar.MONTH) + 1;
            Calendar lastDate = Calendar.getInstance();
            lastDate.set(Calendar.YEAR, yearReport);
            lastDate.set(Calendar.MONTH, monthfield - 1);
            lastDate.set(Calendar.DATE, 1);
            int year = lastDate.get(Calendar.YEAR);
            int month = lastDate.get(Calendar.MONTH) + 1;
            int day = lastDate.get(Calendar.DATE);
            String startDate = year + "-" + month + "-" + day;
            date[0] = SIMPLE_DATE_FORMAT.parse(startDate);
            lastDate.add(Calendar.MONTH, 1);
            lastDate.add(Calendar.DATE, -1);
            year = lastDate.get(Calendar.YEAR);
            month = lastDate.get(Calendar.MONTH) + 1;
            day = lastDate.get(Calendar.DATE);
            startDate = year + "-" + month + "-" + day + " 24" + ":" + "00" + ":" + "00";
            date[1] = SIMPLE_DATE_FORMAT1.parse(startDate);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return date;
    }

    /**
     * 获取当前月份的15日
     *
     * @param nowDateTime 日期
     * @return Date
     */
    public static Date getMiddleDateOfMonth(Date nowDateTime) {
        Date returnDate = new Date();
        try {
            int monthfield, yearReport;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(nowDateTime);
            yearReport = calendar.get(Calendar.YEAR);
            monthfield = calendar.get(Calendar.MONTH) + 1;
            Calendar lastDate = Calendar.getInstance();
            lastDate.set(Calendar.YEAR, yearReport);
            lastDate.set(Calendar.MONTH, monthfield - 1);
            lastDate.set(Calendar.DATE, 1);
            int year = lastDate.get(Calendar.YEAR);
            int month = lastDate.get(Calendar.MONTH) + 1;
            //int day = lastDate.get(Calendar.DATE);
            String middleDate = year + "-" + month + "-" + 15 + " 00" + ":" + "00" + ":" + "00";
            returnDate = SIMPLE_DATE_FORMAT.parse(middleDate);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return returnDate;
    }

    /**
     * 根据当前时间，获取前后n个月的 第一天的日期
     *
     * @param direction
     * @return
     */
    public static Date getSpecialDateOfMonth(int direction) {
        Date returnDate = new Date();
        Date nowDateTime = new Date();
        try {
            int monthfield, yearReport;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(nowDateTime);
            yearReport = calendar.get(Calendar.YEAR);
            monthfield = calendar.get(Calendar.MONTH) + 1 + direction;
            Calendar lastDate = Calendar.getInstance();
            lastDate.set(Calendar.YEAR, yearReport);
            lastDate.set(Calendar.MONTH, monthfield - 1 - direction);
            lastDate.set(Calendar.DATE, 1);
            int year = lastDate.get(Calendar.YEAR);
            int month = lastDate.get(Calendar.MONTH) + 1 + direction;
            //int day = lastDate.get(Calendar.DATE);
            String middleDate = year + "-" + month + "-01" + " 00" + ":" + "00" + ":" + "00";
            returnDate = SIMPLE_DATE_FORMAT.parse(middleDate);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return returnDate;
    }

    /**
     * 获取日期 年 月 日
     *
     * @param date
     * @return
     */
    public static int[] getYearAndMonthAndDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new int[]{year, month, day};
    }

    /**
     * 获取两个日期的时间差
     *
     * @param beginDate
     * @param endDate
     * @return 相隔天数
     */
    public static int getTimeDifference(Date beginDate, Date endDate) {
        long DAY = 24L * 60L * 60L * 1000L;
        long day = endDate.getTime() - beginDate.getTime();
        return Long.valueOf(day / DAY).intValue();
    }

    /*
     * 比较参数时间和当前时间：
     * 时间格式 2005-4-21 16:16:34
     * 如果date1<date2 返回>=0 否则<0
     */
    public static int compareDate(String date1, String date2) {
        try {
            DateFormat df = DateFormat.getDateTimeInstance();
            return df.parse(date2).compareTo(df.parse(date1));
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * Formats a Date into a date/time string which format like:'yyyy-MM-dd HH:mm:ss'
     *
     * @param date   java.util.Date object, that need to be format.
     * @param format the format of the data/time string, such as: "yyyy-MM-dd
     *               HH:mm:ss.SSS"
     */
    public static String format(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * 计算两个时间相差的天数(以24小时间隔为一天)
     *
     * @param smdate 较小的时间
     * @param bdate   较大的时间
     * @return
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }


    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    public static final String getSpecialDayBeginTimeStr(int direction) {
        String dayPattern = "yyyy-MM-dd";
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, direction);
        String specialDayStr = new SimpleDateFormat(dayPattern).format(calendar.getTime());
        return specialDayStr + " 00:00:00";
    }

    public static final Date getSpecialDayBeginTime(int direction) {
        Date date = null;
        String specialDayStr = getSpecialDayBeginTimeStr(direction);
        SimpleDateFormat sf = new SimpleDateFormat(timestampPattern);
        try {
            date = sf.parse(specialDayStr);
        } catch (ParseException pe) {
            log.error("ParseException: " + pe);
        }
        return (date);
    }

    public static final String getSpecialDayEndTimeStr(int direction) {
        String dayPattern = "yyyy-MM-dd";
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, direction);
        String specialDayStr = new SimpleDateFormat(dayPattern).format(calendar.getTime());
        return specialDayStr + " 23:59:59";
    }

    public static final Date getSpecialDayEndTime(int direction) {
        Date date = null;
        String specialDayStr = getSpecialDayEndTimeStr(direction);
        SimpleDateFormat sf = new SimpleDateFormat(timestampPattern);
        try {
            date = sf.parse(specialDayStr);
        } catch (ParseException pe) {
            log.error("ParseException: " + pe);
        }
        return (date);
    }

    /**
     * 如果当前日期是15号之前，则返还上个月的字符串，
     * 如果当前日期是15号之后，则返还当前月字符串
     * 如：2017-04-10  则返回 "201703"
     * 如 2017-04-20  则返回 "201704"
     *
     * @return 年月字符串
     */
    public static String getSumMonthStr() {
        Date nowDateTime = new Date();
        Date midMonthDateTime = getMiddleDateOfMonth(nowDateTime);
        long subTime = nowDateTime.getTime() - midMonthDateTime.getTime();
        //subTime =  midMonthDateTime.getTime() - nowDateTime.getTime()  ;
        if (subTime > 0) {
            return getDateTime("yyyyMM", nowDateTime);
        } else {
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(nowDateTime);
            rightNow.add(Calendar.DAY_OF_MONTH, -16);
            return getDateTime("yyyyMM", rightNow.getTime());
        }

    }


    /**
     * 获取两个时间之间的间隔，不同的type返回不同的间隔单位，
     * d-表示相隔多少天，h-表示相隔多少个小时，"m"-表示相隔多少分钟,s-表示相隔多少秒
     *
     * @param beginTime
     * @param endTime
     * @param type
     * @return
     */
    public static long getTimeBetween(Date beginTime, Date endTime, String type) {
        long begin = beginTime.getTime();//开始时间毫秒数
        long end = endTime.getTime();//结束时间毫秒数
        long dis = end - begin;
        long ret = 0;
        //根据不同的传入类型计算间隔
        if ("d".equalsIgnoreCase(type) || type == null) {//日
            ret = Math.round(dis / (24 * 60 * 60 * 1000));
        } else if ("h".equalsIgnoreCase(type)) {
            ret = Math.round(dis / (60 * 60 * 1000));
        } else if ("m".equalsIgnoreCase(type)) {
            ret = Math.round(dis / (60 * 1000));
        } else if ("s".equalsIgnoreCase(type)) {
            ret = Math.round(dis / 1000);
        }
        return ret;
    }

    public static void main(String[] args) {
//		 int [] time = DateUtil.getYearAndMonthAndDay(addDate(null, Calendar.DATE, -17));
//		 System.out.println(time[0]);
//		 System.out.println(time[1]);
//		 System.out.println(time[2]);

//		Date[] dates = DateUtil.getMonthOfYear(new Date());
//		for(Date datetmp:dates){
//			System.out.println("datetmp=="+datetmp);
//		}
//		 
//		 System.out.println(DateUtil.getDateTime("yyyy-MM-dd"));
//		 System.out.println(DateUtil.getDateTime("yyyy-MM-dd",-7));
//		 
        //System.out.println("getTomorrowBeginTime = "+getSpecialDayBeginTimeStr( 31 ));

        //System.out.println("DateUtil.main() getSumMonthStr="+getSumMonthStr( ));

        System.out.println("DateUtil.main() getSpecialDateOfMonth = " + getDateTime("yyyyMM", getSpecialDateOfMonth(-3)));

    }
}
