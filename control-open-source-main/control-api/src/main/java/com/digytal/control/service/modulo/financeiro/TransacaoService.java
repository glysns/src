package com.digytal.control.service.modulo.financeiro;


import com.digytal.control.infra.business.RegistroIncompativelException;
import com.digytal.control.infra.commons.definition.Definition;
import com.digytal.control.infra.commons.validation.Validations;
import com.digytal.control.infra.utils.Calculos;
import com.digytal.control.model.comum.MeioPagamento;
import com.digytal.control.model.comum.RegistroData;
import com.digytal.control.model.modulo.acesso.empresa.aplicacao.AplicacaoEntity;
import com.digytal.control.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import com.digytal.control.model.modulo.financeiro.Aplicacao;
import com.digytal.control.model.modulo.financeiro.transacao.TransacaoValor;
import com.digytal.control.model.modulo.financeiro.transacao.TransacaoEntity;
import com.digytal.control.model.modulo.financeiro.transacao.rateio.TransacaoRateioEntity;
import com.digytal.control.model.modulo.financeiro.transacao.pagamento.FormaPagamentoRequest;
import com.digytal.control.model.modulo.financeiro.transacao.TransacaoRequest;
import com.digytal.control.repository.modulo.acesso.empresa.AplicacaoRepository;
import com.digytal.control.repository.modulo.acesso.empresa.ContaRepository;
import com.digytal.control.repository.modulo.acesso.empresa.FormaPagamentoRepository;
import com.digytal.control.repository.modulo.fincanceiro.TransacaoRepository;
import com.digytal.control.service.comum.AbstractService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.digytal.control.infra.commons.validation.Attributes.*;

@Service
public  class TransacaoService extends AbstractService {
    @Autowired
    private TransacaoRepository repository;
    @Autowired
    private AplicacaoRepository aplicacaoRepository;
    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private PagamentoService pagamentoService;
    @Autowired
    private ParcelamentoService parcelamentoService;

    @Transactional
    public void incluir(AplicacaoTipo tipo, TransacaoRequest request){

        Validations.build(DESCRICAO, VALOR).notEmpty().check(request);
        request.setCadastro(Definition.seNulo(request.getCadastro(),1));
        Validations.build(TITULO).maxLen(40).check(request);
        Validations.build(DESCRICAO).maxLen(200).check(request);
        Validations.build(OBSERVACAO).maxLen(100).check(request);

        TransacaoEntity entity = new TransacaoEntity();
        entity.setTipo(tipo);
        entity.setTitulo(Definition.seNuloOuVazio(request.getTitulo(), request.getDescricao(),40));
        entity.setDescricao(request.getDescricao());
        entity.setObservacao(request.getObservacao());
        entity.setNumeroDocumento(Definition.seNuloOuVazio(request.getNumeroDocumento(), gerarLocalizador()));
        entity.setData(RegistroData.of(request.getData()));
        entity.setPartes(definirParticipantes(request.getCadastro()));
        entity.setValor(TransacaoValor.of(tipo, request.getValor()));
        entity.setObservacao(request.getObservacao());
        Aplicacao aplicacao = new Aplicacao();
        aplicacao.setArea(Definition.seNulo(request.getArea(), 1));

        if(request.getNatureza()==null) {
            AplicacaoEntity aplicacaoEntity = aplicacaoRepository.buscarNaturezaPrincipal(requestInfo.getOrganizacao(), tipo);
            aplicacao.setNatureza(aplicacaoEntity.getId());
        }else
            aplicacao.setNatureza(request.getNatureza());

        entity.setAplicacao(aplicacao);

        if( Calculos.compararIgualMenorZero(request.getValor()))
            throw new RegistroIncompativelException("O valor do pagamento não pode ser menor ou igual a zero");

        definirPagamentoPadrao(request);
        Integer cadastro = entity.getPartes().getCadastro();
        for (FormaPagamentoRequest rateio : request.getFormasPagamento()) {
            if (Arrays.stream(new MeioPagamento[]{MeioPagamento.SALDO, MeioPagamento.COMPENSACAO}).anyMatch(rateio.getMeioPagamento()::equals))
                throw new RegistroIncompativelException("Não é permitido utilizar este meio de pagamento");

            rateio.setValorOriginal(Calculos.seNuloOuZero(rateio.getValorOriginal(), rateio.getValorPago()));
            rateio.setTaxaPagamento(Calculos.seNuloZera(rateio.getTaxaPagamento()));
            if (rateio.getMeioPagamento().isInstantaneo()) {
                entity.getPagamentos().add(pagamentoService.criarPagamento(tipo, cadastro, rateio, entity.getTitulo(),entity.getPartes().getEmpresa()));
            }else{
                entity.getParcelamentos().add(parcelamentoService.criarParcelamento(tipo, cadastro, rateio,entity.getTitulo()));
            }

            TransacaoRateioEntity rateioEntity = new TransacaoRateioEntity();
            BeanUtils.copyProperties(rateio,rateioEntity);
            if(rateio.getParcelamento()!=null) {
                Double numeroParcela = rateio.getParcelamento().getNumeroParcelas()==null ? 1.0 : Double.valueOf(rateio.getParcelamento().getNumeroParcelas().toString());
                numeroParcela = Calculos.seNuloOuZero(numeroParcela, 1.0);

                Double valorParcela = Calculos.dividir(Calculos.ESCALA4, rateio.getValorPago(), numeroParcela);
                BeanUtils.copyProperties(rateio.getParcelamento(), rateioEntity.getParcelamento());
                rateioEntity.getParcelamento().setValorParcela(valorParcela);

            }
            entity.getRateios().add(rateioEntity);
        }

        Double totalPagamentos = Calculos.aplicarEscala4(entity.getPagamentos().stream().mapToDouble(f -> f.getValor().getValorInformado()).sum());
        Double totalParcelamentos = Calculos.aplicarEscala4(entity.getParcelamentos().stream().mapToDouble(f -> f.getDetalhe().getValorOriginal()).sum());
        Double total = Calculos.somar(Calculos.ESCALA4, totalPagamentos, totalParcelamentos);

        if ( ! Calculos.compararIgualdade(total, request.getValor()))
            throw new RegistroIncompativelException("Os valores informados no rateiro de pagamento diferem do valor total");

        repository.save(entity);
    }
    private void definirPagamentoPadrao(TransacaoRequest request){
        if(request.getFormasPagamento()==null || request.getFormasPagamento().size()==0){
            request.setFormasPagamento(definirPagamentoPadrao(request.getValor()));
        }
    }
    private List<FormaPagamentoRequest> definirPagamentoPadrao(Double valor){
        FormaPagamentoRequest rateio = new FormaPagamentoRequest();
        rateio.setMeioPagamento(MeioPagamento.DINHEIRO);
        rateio.setValorPago(valor);
        rateio.setValorOriginal(valor);
        rateio.setTaxaPagamento(0.0);
        return Collections.singletonList(rateio);
    }


}
