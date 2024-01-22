package com.digytal.control.repository.modulo.fincanceiro;

import com.digytal.control.model.consulta.lancamento.PagamentoFiltro;
import com.digytal.control.model.modulo.financeiro.pagamento.PagamentoEntity;
import com.digytal.control.model.modulo.financeiro.pagamento.response.PagamentoResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Integer> {
    List<PagamentoResponse> pesquisar(Integer empresa, PagamentoFiltro filtro);
    List<PagamentoResponse> pesquisarCompleto(Integer empresa, PagamentoFiltro filtro);
}
