package com.digytal.control.service.modulo.financeiro;
import com.digytal.control.infra.business.RegistroNaoLocalizadoException;
import com.digytal.control.infra.business.SaldoInsuficienteException;
import com.digytal.control.infra.commons.validation.Entities;
import com.digytal.control.model.comum.MeioPagamento;
import com.digytal.control.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import com.digytal.control.model.modulo.acesso.empresa.conta.ContaEntity;
import com.digytal.control.model.modulo.acesso.empresa.pagamento.FormaPagamentoEntity;
import com.digytal.control.model.modulo.financeiro.transacao.TransacaoEntity;
import com.digytal.control.model.modulo.financeiro.transacao.TransacaoValor;
import com.digytal.control.model.modulo.financeiro.pagamento.PagamentoEntity;
import com.digytal.control.model.modulo.financeiro.transacao.pagamento.FormaPagamentoRequest;
import com.digytal.control.repository.modulo.acesso.empresa.ContaRepository;
import com.digytal.control.repository.modulo.fincanceiro.PagamentoRepository;
import com.digytal.control.service.comum.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.digytal.control.infra.commons.validation.Attributes.ID;

@Service
@Slf4j
public class PagamentoService extends AbstractService {
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private PagamentoRepository repository;
    public void criarPagamentoParcelamento(AplicacaoTipo tipo, FormaPagamentoRequest rateio, String descricao, Integer parcelamento, TransacaoEntity transacao){
        PagamentoEntity entity = criarPagamento(tipo, transacao.getPartes().getCadastro(), rateio, descricao, transacao.getPartes().getEmpresa());
        entity.setParcelamento(parcelamento);
        entity.setTransacao(transacao.getId());
        repository.save(entity);
    }
    private void gravarPagamento(AplicacaoTipo tipo, Integer cadastro, FormaPagamentoRequest rateio, String descricao,Integer empresa){
        PagamentoEntity pagamento = criarPagamento(tipo,cadastro, rateio, descricao,empresa);
        repository.save(pagamento);
    }
    public PagamentoEntity criarPagamento(AplicacaoTipo tipo, Integer cadastro, FormaPagamentoRequest rateio, String descricao,Integer empresa){
        Double valor = rateio.getValorPago();
        MeioPagamento meioPagamento = rateio.getMeioPagamento();
        PagamentoEntity entity = new PagamentoEntity();
        entity.setDescricao(descricao);
        entity.setMeioPagamento(meioPagamento);
        atualizarSaldoConta(tipo, meioPagamento, valor, entity, empresa);
        entity.setCadastro(cadastro);
        return  entity;
    }
    private void atualizarSaldoConta(AplicacaoTipo tipo, MeioPagamento meioPagamento, Double valor, PagamentoEntity entity, Integer empresa){
        FormaPagamentoEntity formaPagamento = consultarFormaPagamento(empresa, meioPagamento);

        ContaEntity conta = contaRepository.findById(formaPagamento.getConta()).orElseThrow(()-> new RegistroNaoLocalizadoException(Entities.EMPRESA_CONTA_ENTITY, ID));
        if(tipo == AplicacaoTipo.DESPESA &&  valor > conta.getSaldo())
            throw new SaldoInsuficienteException();

        entity.setValor(TransacaoValor.of(tipo, valor));
        entity.setConta(conta.getId());
        conta.setSaldo(conta.getSaldo() + entity.getValor().getValorOperacional());

        contaRepository.save(conta);
    }

}
