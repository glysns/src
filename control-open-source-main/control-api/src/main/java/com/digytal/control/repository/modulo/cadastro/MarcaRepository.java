
package com.digytal.control.repository.modulo.cadastro;

import com.digytal.control.model.modulo.cadastro.produto.marca.MarcaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MarcaRepository extends JpaRepository<MarcaEntity, Integer>{
    List<MarcaEntity> findByOrganizacaoAndLocalizaContaining(Integer organizacao, String nome);
}
