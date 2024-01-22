package com.digytal.control.repository.modulo.cadastro;

import com.digytal.control.model.consulta.produto.ProdutoFiltro;
import com.digytal.control.model.modulo.cadastro.produto.ProdutoEntity;
import com.digytal.control.model.modulo.cadastro.produto.ProdutoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {
    List<ProdutoEntity> findByOrganizacaoAndLocalizaContaining(Integer organizacao, String nome);
    List<ProdutoResponse> pesquisar(Integer organizacao, ProdutoFiltro filtro);
}
