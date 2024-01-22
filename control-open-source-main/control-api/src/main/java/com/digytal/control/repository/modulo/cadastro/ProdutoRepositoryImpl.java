package com.digytal.control.repository.modulo.cadastro;

import com.digytal.control.infra.business.BusinessException;
import com.digytal.control.infra.business.ErroNaoMapeadoException;
import com.digytal.control.infra.persistence.QueryRepository;
import com.digytal.control.infra.sql.StringSQL;
import com.digytal.control.model.consulta.produto.ProdutoFiltro;
import com.digytal.control.model.modulo.cadastro.produto.ProdutoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class ProdutoRepositoryImpl extends QueryRepository {
    public List<ProdutoResponse> pesquisar(Integer organizacao, ProdutoFiltro filtro){
        return pesquisar(organizacao,  elaborarSql(null, null), filtro,"e.nome");
    }
    private List<ProdutoResponse> pesquisar(Integer organizacao, String select, ProdutoFiltro filtro, String orderBy){
        try {
            StringSQL sql = new StringSQL();
            sql.select(select);

            Map<String, Object> filters = new LinkedHashMap<>();
            filters.put("organizacao", organizacao);

            sql.setFilters(filters)
                    .where("e.organizacao").equal("organizacao").integer();

            if (filtro.getNome() != null) {
                filters.put("localiza", filtro.getNome().toUpperCase());
                sql.and("e.localiza").like("localiza").similar();
            }
            if (filtro.getCategoria() != null) {
                filters.put("categoria", filtro.getCategoria());
                sql.and("e.categoria").equal("categoria").integer();
            }
            if (filtro.getMarca() != null) {
                filters.put("marca", filtro.getMarca());
                sql.and("e.marca").equal("marca").integer();
            }
            if (filtro.getModelo() != null) {
                filters.put("modelo", filtro.getMarca());
                sql.and("e.modelo").equal("modelo").integer();
            }
            sql.orderBy(orderBy);
            List<ProdutoResponse> lista = search(sql, ProdutoResponse.class);
            return lista;
        }catch (BusinessException ex){
            log.warn(BusinessException.logMessage(ex));
            throw ex;
        }catch (Exception ex){
            log.error("Erro ao tentar realizar um pagamento",ex);
            throw new ErroNaoMapeadoException();
        }
    }
    private String elaborarSql(String campos, String tabelas){
        StringBuilder select = new StringBuilder();
        select.append(" SELECT e.id as id, e.nome as nome, e.nomeAbreviado as nomeAbreviado, e.valor as valor, e.taxaLiquidacao as taxaLiquidacao, e.atualizaSaldo as atualizaSaldo, " +
                " e.interno as interno, e.servico as servico, " +
                " e.codigoBarras as codigoBarras, e.sku as sku, e.descricao as descricao, " +
                " u.id as unidadeMedida_id, u.id as unidadeMedida_identificador, u.nome as unidadeMedida_descricao, u.sigla as unidadeMedida_abreviacao "
        );

        /*
         " m.id as marca_id, m.id as marca_identificador, m.nome as marca_descricao, m.sigla as marca_abreviacao, " +
                " l.id as modelo_id, l.id as modelo_identificador, l.nome as modelo_descricao, l.sigla as modelo_abreviacao, " +
                " c.id as categoria_id, c.id as categoria_identificador, c.nome as categoria_descricao, c.sigla as categoria_abreviacao "
         */

        select.append(campos!=null?", " + campos:" ");

        select.append(" FROM ProdutoEntity e INNER JOIN UnidadeMedidaEntity u ON e.unidadeMedida = u.id ");
        //select.append(" LEFT JOIN MarcaEntity m ON e.marca = m.id LEFT JOIN ModeloEntity l ON e.modelo = l.id ");
        //select.append(" LEFT JOIN CategoriaEntity c ON e.categoria = c.id");

        select.append(tabelas!=null?tabelas:" ");

        return select.toString();
    }
}
