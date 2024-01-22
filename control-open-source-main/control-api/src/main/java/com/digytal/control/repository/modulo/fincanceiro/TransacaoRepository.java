package com.digytal.control.repository.modulo.fincanceiro;

import com.digytal.control.model.consulta.lancamento.TransacaoFiltro;
import com.digytal.control.model.modulo.financeiro.transacao.TransacaoEntity;
import com.digytal.control.model.modulo.financeiro.transacao.TransacaoResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<TransacaoEntity, Integer> {
    List<TransacaoResponse> pesquisar(Integer empresa, TransacaoFiltro filtro);
    List<TransacaoResponse> pesquisarCompleto(Integer empresa, TransacaoFiltro filtro);
}
