package cn.cwnu.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理
 *
 */
public class DateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /** 时间格式(yyyyMMdd) */
    public final static String YEAR_MONTH_DAY = "yyyyMMdd";

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String formatDate(Date date) {
        return format(date, DATE_TIME_PATTERN);
    }

    public static String yearMonthDay(Date date) {
        return format(date, YEAR_MONTH_DAY);
    }
    /**
     * 自定义转换规则
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

}
