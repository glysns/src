package com.digytal.control.repository.modulo.fincanceiro;

import com.digytal.control.infra.business.BusinessException;
import com.digytal.control.infra.business.ErroNaoMapeadoException;
import com.digytal.control.infra.persistence.QueryRepository;
import com.digytal.control.infra.sql.StringSQL;
import com.digytal.control.model.consulta.lancamento.PagamentoFiltro;
import com.digytal.control.model.consulta.lancamento.TransacaoFiltro;
import com.digytal.control.model.modulo.financeiro.pagamento.response.PagamentoResponse;
import com.digytal.control.model.modulo.financeiro.transacao.TransacaoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class TransacaoRepositoryImpl extends QueryRepository {
    public List<TransacaoResponse> pesquisar(Integer empresa, TransacaoFiltro filtro){
        return pesquisar(empresa, elaborarSql(null, null),filtro, "e.data.dataHora");
    }
    public List<TransacaoResponse> pesquisarCompleto(Integer empresa, TransacaoFiltro filtro){
        StringBuilder novosCampos = new StringBuilder("n.id as natureza_id, n.id as natureza_identificador, n.nome as natureza_descricao, ");
        novosCampos.append("a.id as area_id, a.id as area_identificador, a.nome as area_descricao ");

        StringBuilder joins = new StringBuilder("INNER JOIN AplicacaoEntity n ON e.aplicacao.natureza = n.id ");
        joins.append("INNER JOIN AplicacaoEntity a ON e.aplicacao.area = a.id ");

        String select = elaborarSql(novosCampos.toString(), joins.toString());

        return pesquisar(empresa,  select , filtro,"e.data.dataHora");
    }

    private List<TransacaoResponse> pesquisar(Integer empresa, String select, TransacaoFiltro filtro, String orderBy) {
        try {
            StringSQL sql = new StringSQL();
            sql.select(select);
            Map<String, Object> filters = new LinkedHashMap<>();
            filters.put("empresa", empresa);
            filters.put("diaInicial", filtro.getDataInicial());
            filters.put("diaFinal", filtro.getDataFinal());

            sql.setFilters(filters)
                    .where("e.partes.empresa").equal("empresa").integer()
                    .and("e.data.dia").greaterThanEqual("diaInicial").localDate()
                    .and("e.data.dia").lessThanEquals("diaFinal").localDate();

            if (filtro.getTipo() != null) {
                filters.put("tipo", filtro.getTipo());
                sql.and("e.tipo").equal("tipo").enumeration();
            }

            sql.orderBy(orderBy);
            List<TransacaoResponse> lista = search(sql, TransacaoResponse.class);
            return lista;
        }catch (BusinessException ex){
            log.warn(BusinessException.logMessage(ex));
            throw ex;
        }catch (Exception ex){
            log.error("Erro ao tentar realizar um pagamento",ex);
            throw new ErroNaoMapeadoException();
        }
    }

    private String elaborarSql(String campos, String tabelas) {
        StringBuilder select = new StringBuilder();
        select.append(" SELECT e.id as id, e.numeroDocumento as numeroDocumento, e.titulo as titulo, e.descricao as descricao, e.tipo as tipo, e.data as data, " +
                " e.observacao as observacao, e.valor as valor, c.id as cadastro_id, c.cpfCnpj as cadastro_identificador, c.nomeFantasia as cadastro_descricao  ");

        select.append(campos != null ? ", " + campos : " ");

        select.append(" FROM TransacaoEntity e INNER JOIN CadastroEntity c ON e.partes.cadastro = c.id  ");
        select.append(tabelas != null ? tabelas : " ");

        return select.toString();
    }
}
