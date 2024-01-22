
package com.digytal.control.repository.modulo.cadastro;

import com.digytal.control.model.modulo.cadastro.produto.modelo.ModeloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ModeloRepositoty extends JpaRepository<ModeloEntity, Integer> {
    List<ModeloEntity> findByOrganizacaoAndNomeContaining(Integer organizacao, String nome);
    List<ModeloEntity> findByOrganizacaoAndLocalizaContaining(Integer organizacao, String nome);
}
