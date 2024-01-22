package com.digytal.control.repository.modulo.fincanceiro;

import com.digytal.control.infra.business.BusinessException;
import com.digytal.control.infra.business.ErroNaoMapeadoException;
import com.digytal.control.infra.persistence.QueryRepository;
import com.digytal.control.infra.sql.StringSQL;
import com.digytal.control.model.consulta.lancamento.LancamentoFiltro;
import com.digytal.control.model.modulo.financeiro.parcelamento.parcela.ParcelaResponse;
import com.digytal.control.model.modulo.financeiro.parcelamento.response.ParcelamentoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class ParcelamentoRepositoryImpl extends QueryRepository {
    public List<ParcelamentoResponse> pesquisar(Integer empresa, LancamentoFiltro filtro){
        return pesquisar(empresa, filtro, elaborarSql(null, null),"t.data.dataHora");
    }
    public List<ParcelamentoResponse> pesquisarCompleto(Integer empresa, LancamentoFiltro filtro){
        return null;
    }
    private List<ParcelamentoResponse> pesquisar(Integer empresa, LancamentoFiltro filtro, String select, String orderBy){
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

            sql.orderBy(orderBy);
            List<ParcelamentoResponse> lista = search(sql, ParcelamentoResponse.class);
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
        select.append(" SELECT e.id as id, t.numeroDocumento as numeroDocumento, e.id as numeroTransacao, " +
                " t.titulo as titulo, e.descricao as descricao, t.tipo as tipo, t.data as data, e.detalhe as detalhe, " +
                " e.meioPagamento as meioPagamento, t.observacao as observacao, " +
                " c.cpfCnpj as cadastro_identificador, c.nomeFantasia as cadastro_descricao");

        select.append(campos!=null?", " + campos:" ");

        select.append(" FROM ParcelamentoEntity e INNER JOIN TransacaoEntity t ON e.transacao = t.id ");
        select.append(" INNER JOIN CadastroEntity c ON t.partes.cadastro = c.id ");
        select.append(tabelas!=null?tabelas:" ");

        return select.toString();
    }

    public List<ParcelaResponse> listarParcelas(Integer parcelamento){
        //e.negociacao as negociacao, e.pendencia as pendencia, e.boleto as boleto,
        StringSQL sql = new StringSQL();
        sql.select(" SELECT e.id as id, e.descricao as descricao, e.aliquota as aliquota, e.detalhe as detalhe, e.boleto as boleto,  " +
                " e.quitacao as quitacao, e.parcelamento as parcelamento, e.observacao as observacao, e.pendencia as pendencia" +
                " FROM ParcelaEntity e");
        sql.where("e.parcelamento").equal().integer(parcelamento);
        sql.orderBy("e.detalhe.numeroParcela");
        return search(sql, ParcelaResponse.class);
    }
}
