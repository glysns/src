package com.digytal.control.service.comum;

import com.digytal.control.infra.business.ParametroInvalidoException;
import com.digytal.control.model.comum.filtro.FiltroData;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AbstractConsultaService extends AbstractService{
    private final int DIAS_INTERVALO = 31;
    protected void validarPeriodoData(FiltroData filtro){
        filtro.setDataInicial(filtro.getDataInicial()==null ? LocalDate.now() : filtro.getDataInicial());
        filtro.setDataFinal(filtro.getDataFinal()==null ? LocalDate.now().withDayOfMonth(filtro.getDataInicial().getMonth().length(filtro.getDataInicial().isLeapYear())) : filtro.getDataFinal()); ;
        if(ChronoUnit.DAYS.between(filtro.getDataInicial(), filtro.getDataFinal()) > DIAS_INTERVALO)
            throw new ParametroInvalidoException("O intervalo de datas ultrapassou os 31 dias");
    }
}
