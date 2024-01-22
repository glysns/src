package com.digytal.control.service.modulo.financeiro.consulta;

import com.digytal.control.infra.utils.Calculos;
import com.digytal.control.model.consulta.lancamento.PagamentoFiltro;
import com.digytal.control.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import com.digytal.control.model.modulo.financeiro.pagamento.response.PagamentoResponse;
import com.digytal.control.model.modulo.financeiro.pagamento.response.PagamentoResumo;
import com.digytal.control.service.comum.AbstractConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digytal.control.repository.modulo.fincanceiro.PagamentoRepository;
import com.digytal.control.service.modulo.acesso.ContaService;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagamentoConsultaService extends AbstractConsultaService {
    @Autowired
    private PagamentoRepository repository;
    @Autowired
    private ContaService contaService;
    public List<PagamentoResponse> pesquisar(PagamentoFiltro filtro){
        validarPeriodoData(filtro);
        return repository.pesquisar(requestInfo.getEmpresa(), filtro);
    }
    public List<PagamentoResponse> pesquisarCompleto(PagamentoFiltro filtro){
        validarPeriodoData(filtro);
        return repository.pesquisarCompleto(requestInfo.getEmpresa(), filtro);
    }
    public PagamentoResumo resumir(PagamentoFiltro filtro){
        validarPeriodoData(filtro);
        List<PagamentoResponse> pagamentos = pesquisarCompleto(filtro);
        List<PagamentoResponse> receitas = pagamentos.stream().filter(p-> AplicacaoTipo.RECEITA == p.getTipo()).collect(Collectors.toList());
        List<PagamentoResponse> despesas = pagamentos.stream().filter(p-> AplicacaoTipo.DESPESA == p.getTipo()).collect(Collectors.toList());

        PagamentoResumo resumo = new PagamentoResumo();
        resumo.setPagamentos(pagamentos);
        resumo.setReceitas(receitas);
        resumo.setDespesas(despesas);
        resumo.setTotalReceitas(receitas.stream().mapToDouble(p->p.getValor().getValorInformado()).sum());
        resumo.setTotalDespesas(despesas.stream().mapToDouble(p->p.getValor().getValorInformado()).sum());
        resumo.setSaldo(Calculos.subtrair(resumo.getTotalReceitas(), resumo.getTotalDespesas()));
        return resumo;
    }
}
