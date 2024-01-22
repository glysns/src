package com.digytal.control.repository.modulo.fincanceiro;


import com.digytal.control.model.modulo.financeiro.parcelamento.parcela.ParcelaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface ParcelaRepository extends JpaRepository<ParcelaEntity, Integer> {
    @Query("SELECT e FROM ParcelaEntity e WHERE e.boleto.solicitado=true AND e.boleto.status='E' and e.detalhe.dataVencimento BETWEEN :dataInicial AND :dataFinal ")
    List<ParcelaEntity> listarParcelasBoleto(@Param("dataInicial") LocalDate dataInicial,@Param("dataFinal") LocalDate dataFinal);

    ParcelaEntity findByBoletoNumeroAutorizacao(String numeroAutorizacao);
}
