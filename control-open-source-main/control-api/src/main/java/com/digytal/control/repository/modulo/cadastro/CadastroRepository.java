package com.digytal.control.repository.modulo.cadastro;

import com.digytal.control.model.modulo.cadastro.CadastroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CadastroRepository extends JpaRepository<CadastroEntity, Integer> {
    boolean existsByCpfCnpjAndOrganizacao(String cpfCnpj, Integer organizacao);
    boolean existsByEmailAndOrganizacao(String email,Integer organizacao);
    Optional<CadastroEntity> findByOrganizacaoAndCpfCnpj(Integer organizacao, String cpfCnpj);
    @Query("SELECT e FROM CadastroEntity e WHERE (e.perfil.cliente=:cliente OR e.perfil.fornecedor=:fornecedor) AND (e.organizacao=:organizacao  AND e.localiza LIKE %:nome% )ORDER BY e.nomeFantasia")
    List<CadastroEntity> consultar(@Param("organizacao") Integer organizacao, @Param("cliente") boolean cliente, @Param("fornecedor") boolean fornecedor, @Param("nome") String nome);
}
