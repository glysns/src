package com.digytal.control.service.modulo.financeiro.consulta;

import com.digytal.control.model.consulta.lancamento.TransacaoFiltro;
import com.digytal.control.model.modulo.financeiro.transacao.TransacaoResponse;
import com.digytal.control.repository.modulo.fincanceiro.TransacaoRepository;
import com.digytal.control.service.comum.AbstractConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoConsultaService extends AbstractConsultaService {
    @Autowired
    private TransacaoRepository repository;
    public List<TransacaoResponse> pesquisar(TransacaoFiltro filtro){
        validarPeriodoData(filtro);
        return repository.pesquisar(requestInfo.getEmpresa(), filtro);
    }
    public List<TransacaoResponse> pesquisarCompleto(TransacaoFiltro filtro){
        validarPeriodoData(filtro);
        return repository.pesquisarCompleto(requestInfo.getEmpresa(), filtro);
    }
}
