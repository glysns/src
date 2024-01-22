package digytal.desktop.util.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeConvert extends DateTimeUtil {
    public static final LocalePattern DEFAULT_LOCALE_PATTERN = LocalePattern.en_US;

    public static LocalDate localDate(String date) {
        return localDate(date, DEFAULT_LOCALE_PATTERN);
    }

    public static LocalDateTime localDateTimeMin(String date) {
        return localDateTimeMin(date, DEFAULT_LOCALE_PATTERN);
    }

    public static LocalDateTime localDateTimeMin(String date, LocalePattern pattern) {
        DateTimeFormatter formatter = formatterDate(pattern);
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate.atStartOfDay();
    }
    public static LocalDateTime localDateTimeMax(String date) {
        return localDateTimeMax(date, DEFAULT_LOCALE_PATTERN);
    }

    public static LocalDateTime localDateTimeMax(String date, LocalePattern pattern) {
        DateTimeFormatter formatter = formatterDate(pattern);
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate.atTime(23,59,59,999999999);
    }
    public static LocalDate localDate(String date, LocalePattern pattern) {
        DateTimeFormatter formatter = formatterDate(pattern);
        return LocalDate.parse(date, formatter);
    }

    public static LocalDateTime localDateTime(String date) {
        return localDateTime(date, DEFAULT_LOCALE_PATTERN);
    }

    public static LocalDateTime localDateTime(String date, LocalePattern pattern) {
        DateTimeFormatter formatter = formatterDateTime(pattern);
        return LocalDateTime.parse(date, formatter);
    }

}
