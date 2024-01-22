package com.digytal.control.repository.modulo.fincanceiro;

import com.digytal.control.infra.business.BusinessException;
import com.digytal.control.infra.business.ErroNaoMapeadoException;
import com.digytal.control.infra.persistence.QueryRepository;
import com.digytal.control.infra.sql.StringSQL;
import com.digytal.control.model.consulta.lancamento.PagamentoFiltro;
import com.digytal.control.model.modulo.financeiro.pagamento.response.PagamentoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class PagamentoRepositoryImpl extends QueryRepository {

    public List<PagamentoResponse> pesquisar(Integer empresa, PagamentoFiltro filtro){
        return pesquisar(empresa, filtro, elaborarSql(null, null),"t.data.dataHora");
    }
    public List<PagamentoResponse> pesquisarCompleto(Integer empresa, PagamentoFiltro filtro){
        StringBuilder novosCampos = new StringBuilder("c.id as cadastro_id, n.id as natureza_id, n.id as natureza_identificador, n.nome as natureza_descricao, ");
        novosCampos.append("a.id as area_id, a.id as area_identificador, a.nome as area_descricao, ");

        StringBuilder joins = new StringBuilder("INNER JOIN AplicacaoEntity n ON t.aplicacao.natureza = n.id");
        joins.append("INNER JOIN AplicacaoEntity a ON t.aplicacao.area = a.id ");

        String select = elaborarSql(novosCampos.toString(), joins.toString());

        return pesquisar(empresa, filtro, select ,"t.data.dataHora");
    }
    private List<PagamentoResponse> pesquisar(Integer empresa, PagamentoFiltro filtro, String select, String orderBy){
        try {
            StringSQL sql = new StringSQL();
            sql.select(select);
            Map<String, Object> filters = new LinkedHashMap<>();
            filters.put("empresa", empresa);
            filters.put("diaInicial", filtro.getDataInicial());
            filters.put("diaFinal", filtro.getDataFinal());

            sql.setFilters(filters)
                    .where("t.partes.empresa").equal("empresa").integer()
                    .and("t.data.dia").greaterThanEqual("diaInicial").localDate()
                    .and("t.data.dia").lessThanEquals("diaFinal").localDate();

            if (filtro.getMeioPagamento() != null) {
                filters.put("meioPagamento", filtro.getMeioPagamento());
                sql.and("e.meioPagamento").equal("meioPagamento").enumeration();
            }
            if (filtro.getTipo() != null) {
                filters.put("tipo", filtro.getTipo());
                sql.and("t.tipo").equal("tipo").enumeration();
            }
            if (filtro.getConta() != null) {
                filters.put("conta", filtro.getConta());
                sql.and("e.conta").equal("conta").integer();
            }
            if (filtro.getCadastro() != null) {
                filters.put("cadastro", filtro.getCadastro());
                sql.and("t.partes.cadastro").equal("cadastro").integer();
            }
            sql.orderBy(orderBy);
            List<PagamentoResponse> lista = search(sql, PagamentoResponse.class);
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
        select.append(" SELECT e.id as id, t.numeroDocumento as numeroDocumento, e.id as numeroTransacao, t.titulo as titulo, e.descricao as descricao, t.tipo as tipo, t.data as data, " +
                " e.meioPagamento as meioPagamento, t.observacao as observacao, e.valor as valor, c.cpfCnpj as cadastro_identificador, c.nomeFantasia as cadastro_descricao ");

        select.append(campos!=null?", " + campos:" ");

        select.append(" FROM PagamentoEntity e INNER JOIN CadastroEntity c ON e.cadastro = c.id INNER JOIN TransacaoEntity t ON e.transacao = t.id ");
        select.append("  ");
        select.append(tabelas!=null?tabelas:" ");

        return select.toString();
    }
}
