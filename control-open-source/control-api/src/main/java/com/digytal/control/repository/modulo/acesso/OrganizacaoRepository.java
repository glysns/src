package com.digytal.control.repository.modulo.acesso;

import com.digytal.control.model.modulo.acesso.organizacao.OrganizacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizacaoRepository extends JpaRepository<OrganizacaoEntity, Integer> {
    boolean existsByCpfCnpjOrEmail(String cpfCnpj,String email);
}
