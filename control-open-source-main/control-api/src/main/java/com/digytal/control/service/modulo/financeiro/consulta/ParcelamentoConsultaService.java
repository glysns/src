package com.digytal.control.service.modulo.financeiro.consulta;

import com.digytal.control.model.consulta.lancamento.LancamentoFiltro;
import com.digytal.control.model.modulo.financeiro.parcelamento.parcela.ParcelaResponse;
import com.digytal.control.model.modulo.financeiro.parcelamento.response.ParcelamentoResponse;
import com.digytal.control.repository.modulo.fincanceiro.ParcelamentoRepository;
import com.digytal.control.service.comum.AbstractConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParcelamentoConsultaService extends AbstractConsultaService {
    @Autowired
    private ParcelamentoRepository repository;
    public List<ParcelamentoResponse> pesquisar(LancamentoFiltro filtro){
        validarPeriodoData(filtro);
        return repository.pesquisar(requestInfo.getEmpresa(), filtro);
    }

    public List<ParcelaResponse> listarParcelas(Integer parcelamento) {
        return repository.listarParcelas(parcelamento);
    }

}
