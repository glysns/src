package digytal.desktop.util.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import digytal.desktop.util.enums.DiaIntervalo;
import digytal.desktop.util.enums.DiaSemana;

public abstract class DateTimeUtil {
    protected static DateTimeFormatter formatterDate(LocalePattern pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.getDate());
        return formatter;
    }
    protected static DateTimeFormatter formatterDateTime(LocalePattern pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.getDateTime());
        return formatter;
    }

    public static LocalDate newDate(LocalDate date, DiaSemana dayOfWeek, DiaIntervalo intervalDays){
        LocalDate newDate = date.plusDays(intervalDays.getDias());
        if(DiaIntervalo.DIARIO != intervalDays){
           newDate = newDate.plusDays(dayOfWeek.getDia()-newDate.getDayOfWeek().getValue());
        }
        return newDate;
    }

    public static void main(String[] args) {
        //LocalDate newDate = LocalDate.now();
        LocalDate newDate = LocalDate.of(2022,8,1);

        DiaSemana dia = DiaSemana.SEG;
        //for(int x=0; x<4;x++){
            newDate = newDate(newDate,dia,DiaIntervalo.MENSAL);
            System.out.println(newDate);
        //}
    }
}
