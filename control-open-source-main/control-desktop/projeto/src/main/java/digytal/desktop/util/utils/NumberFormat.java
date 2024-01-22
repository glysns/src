package digytal.desktop.util.utils;

import java.util.Locale;

public class NumberFormat {
    public static final Locale DEFAULT_LOCALE = new Locale("pt", "BR");
    public static final int MAX_FRACTION_DIGITS =2;
    public static String integer(Number number) {
        return integer(number, DEFAULT_LOCALE);
    }

    public static String integer(Number number, Locale locale) {
        java.text.NumberFormat formatador = java.text.NumberFormat.getIntegerInstance(locale);
        return formatador.format(number);
    }

    public static String decimal(Number number) {
        return decimal(number, MAX_FRACTION_DIGITS);
    }

    public static String decimal(Number number, Integer maxFractionDigits) {
        return decimal(number, maxFractionDigits, DEFAULT_LOCALE);
    }
    public static String decimal(Number number, Locale locale) {
        return decimal(number, MAX_FRACTION_DIGITS, locale);
    }
    public static String decimal(Number number, Integer maxFractionDigits, Locale locale) {
        java.text.NumberFormat formatador = java.text.NumberFormat.getInstance (locale);
        formatador.setMaximumFractionDigits(maxFractionDigits);
        return formatador.format(number);
    }
    public static String currency(Number number) {
        return currency(number, MAX_FRACTION_DIGITS);
    }
    public static String currency(Number number,Integer maxFractionDigits) {
        return currency(number, maxFractionDigits,DEFAULT_LOCALE);
    }
    public static String currency(Number numero,Locale locale) {
        return currency(numero, MAX_FRACTION_DIGITS,locale);
    }
    public static String currency(Number number, Integer maxFractionDigits, Locale locale) {
        java.text.NumberFormat formatador = java.text.NumberFormat.getCurrencyInstance(locale);
        formatador.setMaximumFractionDigits(maxFractionDigits);
        return formatador.format(number);
    }

}


