package com.digytal.control.repository.modulo.cadastro;

import com.digytal.control.model.modulo.cadastro.produto.categoria.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity,Integer> {
    @Query(value = "SELECT e FROM CategoriaEntity e WHERE e.organizacao = :organizacao AND nome LIKE %:nome% ")
    List<CategoriaEntity> listar(Integer organizacao, String nome);
    List<CategoriaEntity> findByOrganizacaoAndNomeContaining(Integer organizacao, String nome);

    List<CategoriaEntity> findByOrganizacaoAndLocalizaContaining(Integer organizacao, String nome);
}
