package com.digytal.control.repository.modulo.acesso.empresa;

import com.digytal.control.model.modulo.acesso.empresa.aplicacao.AplicacaoEntity;
import com.digytal.control.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AplicacaoRepository extends JpaRepository<AplicacaoEntity, Integer> {
    @Query(value = "SELECT e FROM AplicacaoEntity e WHERE e.area=true AND e.organizacao = :organizacao AND nome LIKE %:nome% ")
    List<AplicacaoEntity> listarAreas(@Param("organizacao") Integer organizacao, @Param("nome") String nome);

    @Query(value = "SELECT e FROM AplicacaoEntity e WHERE e.natureza=true AND e.organizacao = :organizacao AND e.tipo = :tipo AND nome LIKE %:nome% ")
    List<AplicacaoEntity> listarNaturezas(@Param("organizacao") Integer organizacao, @Param("tipo") AplicacaoTipo tipo, @Param("nome") String nome);

    @Query(value = "SELECT e FROM AplicacaoEntity e WHERE e.natureza=true AND e.principal=true AND e.tipo = :tipo AND e.organizacao = :organizacao")
    AplicacaoEntity buscarNaturezaPrincipal(@Param("organizacao") Integer organizacao, @Param("tipo") AplicacaoTipo tipo);
}
