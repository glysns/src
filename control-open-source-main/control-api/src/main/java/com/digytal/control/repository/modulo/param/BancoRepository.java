package com.digytal.control.repository.modulo.param;

import com.digytal.control.model.modulo.param.banco.BancoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BancoRepository extends JpaRepository<BancoEntity, Integer> {
    List<BancoEntity> findByNomeContainingOrderByNome(String nome);
}
