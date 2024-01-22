package com.digytal.control.repository.modulo.param;

import com.digytal.control.model.modulo.param.cep.CepEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CepRepository extends JpaRepository<CepEntity, String> {
}
