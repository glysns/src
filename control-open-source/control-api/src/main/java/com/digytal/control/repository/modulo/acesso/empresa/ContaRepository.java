package com.digytal.control.repository.modulo.acesso.empresa;

import com.digytal.control.model.modulo.acesso.empresa.conta.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContaRepository extends JpaRepository<ContaEntity, Integer> {
    List<ContaEntity> findByEmpresa(Integer empresa);
    boolean existsByAgenciaAndNumeroAndContaCredito(String agencia,String numero,  boolean contaCredito);

     /*

    boolean existsByEmpresaAndContaCredito(Integer empresa, boolean contaCredito);


    @Modifying(flushAutomatically = true)
    @Query("UPDATE EmpresaContaEntity e set e.contaPadrao = false WHERE e.empresa = :empresa AND e.contaCredito = :contaCredito and e.id <> :id ")
    void atualizarContasNaoPadrao(@Param("empresa") Integer empresa, @Param("contaCredito") boolean contaCredito, @Param("id") Integer id);

    @Query(value = "SELECT e.saldo FROM EmpresaContaEntity e WHERE e.banco = 9999 AND e.empresa = :empresa ")
    Double buscarContaBalcaoSaldo(@Param("empresa") Integer empresa);

    @Query(value = "SELECT e FROM EmpresaContaEntity e WHERE e.banco = 9999 AND e.empresa = :empresa ")
    ContaEntity buscarContaBalcao(@Param("empresa") Integer empresa);


     */
}
