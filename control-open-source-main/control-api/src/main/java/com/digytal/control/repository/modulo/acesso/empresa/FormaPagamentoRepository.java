package com.digytal.control.repository.modulo.acesso.empresa;

import com.digytal.control.model.modulo.acesso.empresa.pagamento.FormaPagamentoEntity;
import com.digytal.control.model.comum.MeioPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamentoEntity, Integer> {
    List<FormaPagamentoEntity> findByConta(Integer conta);
    boolean existsByEmpresaAndMeioPagamentoAndNumeroParcelas(Integer empresa, MeioPagamento meioPagamento, Integer numeroParcelas);
    List<FormaPagamentoEntity> findByEmpresaAndMeioPagamento(Integer empresa, MeioPagamento meioPagamento);
    FormaPagamentoEntity findByEmpresaAndMeioPagamentoAndNumeroParcelas(Integer empresa, MeioPagamento meioPagamento, Integer numeroParcelas);
}
