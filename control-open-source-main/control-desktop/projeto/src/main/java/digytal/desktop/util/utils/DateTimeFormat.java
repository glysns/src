package digytal.desktop.util.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormat extends DateTimeUtil {
    public static final LocalePattern DEFAULT_LOCALE_PATTERN = LocalePattern.pt_BR;
    public static String date(LocalDate date) {
        return date(date,DEFAULT_LOCALE_PATTERN);
    }

    public static String date(LocalDate date, LocalePattern pattern) {
        DateTimeFormatter formatter = formatterDate(pattern);
        return formatter.format(date);
    }
    public static String dateTime(LocalDateTime dateTime) {
        return dateTime(dateTime,DEFAULT_LOCALE_PATTERN);
    }

    public static String dateTime(LocalDateTime dateTime, LocalePattern pattern) {
        DateTimeFormatter formatter = formatterDateTime(pattern);
        return formatter.format(dateTime);
    }

}

