package com.digytal.control.model.comum;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Embeddable
@Getter
public class RegistroData {
    @Column(name = "cpt_data")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dataHora;
    @Column(name = "cpt_dia")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dia;
    @Column(name = "cpt_mes")
    private Integer mes;
    @Column(name = "cpt_ano")
    private Integer ano;
    @Column(name = "cpt_periodo")
    private Integer periodo;
    @Column(name = "cpt_competencia")
    private Integer competencia;

    @JsonFormat(pattern = "HH:mm:ss")
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    public LocalTime getHora(){
        return dataHora.toLocalTime();
    }
    public static RegistroData of(){
        return of(LocalDateTime.now());
    }
    public static RegistroData of(LocalDate data){
        return of(data,null);
    }
    public static RegistroData of(LocalDate data, LocalTime hora){
        LocalDateTime dataHora = LocalDateTime.of(data==null?LocalDate.now():data, hora==null?LocalTime.now() : hora);
        return of(dataHora);
    }


    public static RegistroData of(LocalDateTime dataHora){
        RegistroData instance = new RegistroData();
        instance.dataHora = dataHora;
        LocalDate data = dataHora.toLocalDate();
        instance.dia = data;
        instance.mes=data.getMonthValue();
        instance.ano = data.getYear();
        instance.periodo = periodo(data);
        instance.competencia = instance.periodo;
        return instance;
    }
    public static Integer periodo (LocalDate data){
        return Integer.valueOf(data.getYear()+String.format("%02d", data.getMonthValue()));
    }
}
