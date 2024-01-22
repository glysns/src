package com.digytal.control.repository.modulo.acesso.empresa;

import com.digytal.control.infra.model.usuario.EmpresaSimplificadaResponse;
import com.digytal.control.model.modulo.acesso.empresa.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Integer> {
    List<EmpresaSimplificadaResponse> listarEmpresas(Integer usuario);
    boolean existsByCpfCnpj(String cpfCnpj);
    boolean existsByEmail(String email);
    /*

    @Query("SELECT e.organizacao FROM EmpresaEntity e WHERE e.id = :id")
    Integer buscarOrganizacao(@Param("id") Integer empresa);

    List<EmpresaConsultaResponse> listarEmpresas(Integer usuario);
    */

}
