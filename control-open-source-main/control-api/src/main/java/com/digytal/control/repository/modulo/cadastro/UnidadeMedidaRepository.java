package com.digytal.control.repository.modulo.cadastro;

import com.digytal.control.model.modulo.cadastro.produto.unidademedida.UnidadeMedidaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UnidadeMedidaRepository extends JpaRepository<UnidadeMedidaEntity, Integer> {
    List<UnidadeMedidaEntity> findByOrganizacaoAndLocalizaContaining(Integer organizacao, String nome);
    List<UnidadeMedidaEntity> findByOrganizacaoAndEmbalagem(Integer organizacao, boolean embalagem);
    boolean existsByOrganizacaoAndId(Integer organizacao, Integer id);
    Optional<UnidadeMedidaEntity> findByOrganizacaoAndId(Integer organizacao, Integer id);
}
