package com.digytal.control.repository.modulo.fincanceiro;

import com.digytal.control.model.modulo.financeiro.parcelamento.parcela.liquidacao.ParcelaPagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelaPagamentoRepository extends JpaRepository<ParcelaPagamentoEntity, Integer> {

}
