package com.digytal.control.infra.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Formatos {
    public static String data(LocalDate data){
        return data.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.getDefault()));
    }
    public static String hora(LocalTime hora){
        return hora.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.getDefault()));
    }
    public static String horario(LocalTime hora){
        return hora.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).withLocale(Locale.getDefault()));
    }
    public static String dataHora(LocalDateTime dataHora){
        return dataHora.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(Locale.getDefault()));
    }
    private static final int ESCALA_PADRAO=2;

    public static String numero(Double numero){
        return numero(numero, ESCALA_PADRAO,false);
    }
    public static String moeda(Double numero){
        return numero(numero, ESCALA_PADRAO,false);
    }
    public static String numero(Double numero, int escala,boolean exibirMoeda){
        return numero(new BigDecimal(numero),escala, exibirMoeda);
    }
    public static String numero(BigDecimal numero){
        return numero(numero,ESCALA_PADRAO, false);
    }
    public static String moeda(BigDecimal numero){
        return numero(numero,ESCALA_PADRAO, true);
    }
    public static String numero(BigDecimal numero,boolean exibirMoeda){
        return numero(numero,ESCALA_PADRAO, exibirMoeda);
    }
    public static String numero(BigDecimal numero, int escala, boolean exibirMoeda){
        BigDecimal valor = numero.setScale(escala, RoundingMode.HALF_EVEN);
        return (exibirMoeda ? NumberFormat.getCurrencyInstance():NumberFormat.getNumberInstance()).format(valor);
    }
    public static void main(String[] args) {
        Locale[] locales = {Locale.US, Locale.UK, Locale.CHINA, Locale.CANADA, new Locale("pt", "BR")};;
        //LocaleContextHolder.setDefaultLocale();
        for(Locale locale : locales){
            System.out.println("********************************************");
            System.out.println("Definindo Locale " + locale.toString());
            System.out.println("********************************************");
            Locale.setDefault(locale);
            System.out.println(moeda(new BigDecimal("10233.4612")));
            System.out.println(numero(new BigDecimal("10233.4612")));
            System.out.println(data(LocalDate.now()));
            System.out.println(hora(LocalTime.now()));
            System.out.println(horario(LocalTime.now()));
            System.out.println(dataHora(LocalDateTime.now()));
        }

    }

}