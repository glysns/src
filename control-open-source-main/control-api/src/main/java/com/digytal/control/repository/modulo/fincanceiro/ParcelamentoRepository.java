package com.digytal.control.repository.modulo.fincanceiro;

import com.digytal.control.model.consulta.lancamento.LancamentoFiltro;
import com.digytal.control.model.modulo.financeiro.parcelamento.ParcelamentoEntity;
import com.digytal.control.model.modulo.financeiro.parcelamento.parcela.ParcelaResponse;
import com.digytal.control.model.modulo.financeiro.parcelamento.response.ParcelamentoResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParcelamentoRepository extends JpaRepository<ParcelamentoEntity, Integer> {
    public List<ParcelamentoResponse> pesquisar(Integer empresa, LancamentoFiltro filtro);
    List<ParcelaResponse> listarParcelas(Integer parcelamento);
}
