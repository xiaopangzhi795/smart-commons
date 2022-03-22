/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.lang.date;

import com.geek45.lang.StringUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**
 * @ClassName: DateUtils
 * @Decription:
 * @Author: rubik
 *  rubik create DateUtils.java of 2022/3/22 6:00 下午
 */
public class DateUtils {
    public static final String DEFAULT_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMATTER_TO_MILLIS = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String FORMATTER_TO_MINUTES = "yyyy-MM-dd HH:mm";
    public static final String FORMATTER_TO_HOURS = "yyyy-MM-dd HH";
    public static final String FORMATTER_TO_DAYS = "yyyy-MM-dd";
    public static final String FORMATTER_TO_MONTH = "yyyy-MM";
    public static final String FORMATTER_TO_YEARS = "yyyy";
    public static final String FORMATTER_DATE = "MM-dd";
    public static final String FORMATTER_DATE_TIME = "MM-dd HH:mm:ss";
    public static final String FORMATTER_TIME = "HH:mm:ss";

    private DateTimeFormatter dateTimeFormatter;
    private static DateUtils DEFAULT_INSTANCE = new DateUtils(DEFAULT_FORMATTER);
    private static DateUtils INSTANCE;
    private static String INSTANCE_FORMATTER;

    /**
     * 日期增加增加年值
     * @param date
     * @param years
     * @return
     */
    public static final Date addYears(final Date date, final Integer years) {
        if (null == date || null == years) {
            return date;
        }
        return localDateTimeToDate(add(dateToLocalDateTime(date), Long.valueOf(years), ChronoUnit.YEARS));
    }
    /**
     * 日期增加增加月值
     * @param date
     * @param months
     * @return
     */
    public static final Date addMonths(final Date date, final Integer months) {
        if (null == date || null == months) {
            return date;
        }
        return localDateTimeToDate(add(dateToLocalDateTime(date), Long.valueOf(months), ChronoUnit.MONTHS));
    }
    /**
     * 日期增加增加周值
     * @param date
     * @param weeks
     * @return
     */
    public static final Date addWeeks(final Date date, final Integer weeks) {
        if (null == date || null == weeks) {
            return date;
        }
        return localDateTimeToDate(add(dateToLocalDateTime(date), Long.valueOf(weeks), ChronoUnit.WEEKS));
    }
    /**
     * 日期增加增加天值
     * @param date
     * @param day
     * @return
     */
    public static final Date addDays(final Date date, final Integer day) {
        if (null == date || null == day) {
            return date;
        }
        return localDateTimeToDate(add(dateToLocalDateTime(date), Long.valueOf(day), ChronoUnit.DAYS));
    }
    /**
     * 日期增加增加小时值
     * @param date
     * @param hours
     * @return
     */
    public static final Date addHours(final Date date, final Integer hours) {
        if (null == date || null == hours) {
            return date;
        }
        return localDateTimeToDate(add(dateToLocalDateTime(date), Long.valueOf(hours), ChronoUnit.HOURS));
    }

    /**
     * 日期增加增加分钟值
     * @param date
     * @param minutes
     * @return
     */
    public static final Date addMinutes(final Date date, final Integer minutes) {
        if (null == date || null == minutes) {
            return date;
        }
        return localDateTimeToDate(add(dateToLocalDateTime(date), Long.valueOf(minutes), ChronoUnit.MINUTES));
    }

    /**
     * 日期增加增加秒值
     * @param date
     * @param seconds
     * @return
     */
    public static final Date addSeconds(final Date date, final Integer seconds) {
        if (null == date || null == seconds) {
            return date;
        }
        return localDateTimeToDate(add(dateToLocalDateTime(date), Long.valueOf(seconds), ChronoUnit.SECONDS));
    }

    /**
     * 日期增加毫秒值
     *
     * @param date
     * @param milliseconds
     * @return
     */
    public static final Date addMilliseconds(final Date date, final Long milliseconds) {
        if (null == date || null == milliseconds) {
            return date;
        }
        return localDateTimeToDate(add(dateToLocalDateTime(date), milliseconds, ChronoUnit.MILLIS));
    }

    /**
     * 时间操作
     *
     * @param dateTime     当前时间
     * @param sum          要操作的值
     * @param temporalUnit 要操作的单位
     * @return
     */
    public static final LocalDateTime add(final LocalDateTime dateTime, final Long sum, final TemporalUnit temporalUnit) {
        LocalDateTime nowTime = dateTime.plus(sum, temporalUnit);
        return nowTime;
    }

    /**
     * 字符串转日期格式
     * @param dateTimeStr
     * @return
     */
    public final Date parseDate(String dateTimeStr) {
        if (StringUtils.isBlank(dateTimeStr)) {
            return null;
        }
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
        return localDateTimeToDate(dateTime);
    }

    /**
     * 字符串输出日期
     * @param date
     * @return
     */
    public final String dateToStr(final Date date) {
        if (null == date) {
            return null;
        }
        LocalDateTime dateTime = dateToLocalDateTime(date);
        return dateTimeFormatter.format(dateTime);
    }

    /**
     * 日期转时间戳
     * @param date
     * @return
     */
    public static final Long dateToMillisTime(final Date date) {
        if (null == date) {
            return null;
        }
        return dateToLocalDateTime(date).atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    /**
     * 时间戳转时间
     * @param millisTime
     * @return
     */
    public static final Date millisTimeToDate(final Long millisTime) {
        if (null == millisTime) {
            return null;
        }
        return localDateTimeToDate(Instant.ofEpochMilli(millisTime).atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

    /**
     * 当前时间戳
     * @return
     */
    public static final Long now() {
        return System.currentTimeMillis();
    }

    /**
     * 当前纳秒时间戳
     * @return
     */
    public static final Long nowNanoTime() {
        return System.nanoTime();
    }

    /**
     * 当前时间
     * @return
     */
    public static final LocalDateTime nowLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 当前时间
     * @return
     */
    public static final Date nowDate() {
        return localDateTimeToDate(nowLocalDateTime());
    }


    /**
     * date 转LocalDateTime
     * @param date
     * @return
     */
    public static final LocalDateTime dateToLocalDateTime(final Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * date 转 LocalDate
     * @param date
     * @return
     */
    public static final LocalDate dateToLocalDate(final Date date) {
        if (null == date) {
            return null;
        }
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * localDate 转 Date
     * @param localDate
     * @return
     */
    public static final Date localDateToDate(final LocalDate localDate) {
        if (null == localDate) {
            return null;
        }
        Date date = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        return date;
    }

    /**
     * localDateTime 转date
     * @param localDateTime
     * @return
     */
    public static final Date localDateTimeToDate(final LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return null;
        }
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return date;
    }

    private DateUtils(String formatter) {
        dateTimeFormatter = DateTimeFormatter.ofPattern(formatter);
    }

    /**
     * 默认实现
     * yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static DateUtils defaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /**
     *
     * @param formatter 日期格式化
     * @return
     */
    public static DateUtils getInstance(final String formatter) {
        if (StringUtils.isBlank(formatter)) {
            return defaultInstance();
        }
        if (null == INSTANCE) {
            INSTANCE_FORMATTER = formatter;
            INSTANCE = new DateUtils(formatter);
            return INSTANCE;
        }
        if (StringUtils.equals(INSTANCE_FORMATTER, formatter)) {
            return INSTANCE;
        }
        INSTANCE_FORMATTER = formatter;
        INSTANCE = new DateUtils(formatter);
        return INSTANCE;
    }

}