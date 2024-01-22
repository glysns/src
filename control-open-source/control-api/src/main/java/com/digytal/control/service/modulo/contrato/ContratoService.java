package com.digytal.control.service.modulo.contrato;

import com.digytal.control.infra.business.RegistroIncompativelException;
import com.digytal.control.infra.business.RegistroNaoLocalizadoException;
import com.digytal.control.infra.commons.validation.Attributes;
import com.digytal.control.infra.commons.validation.Entities;
import com.digytal.control.infra.utils.Calculos;
import com.digytal.control.model.comum.RegistroData;
import com.digytal.control.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import com.digytal.control.model.modulo.cadastro.produto.ProdutoEntity;
import com.digytal.control.model.modulo.cadastro.produto.ProdutoItem;
import com.digytal.control.model.modulo.contrato.*;
import com.digytal.control.model.modulo.contrato.request.ContratoItemRequest;
import com.digytal.control.model.modulo.contrato.request.ContratoRequest;
import com.digytal.control.model.modulo.financeiro.transacao.TransacaoRequest;
import com.digytal.control.repository.modulo.cadastro.ProdutoRepository;
import com.digytal.control.service.comum.AbstractService;
import com.digytal.control.service.modulo.financeiro.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class ContratoService extends AbstractService {
    @Autowired
    private ProdutoRepository produtoRepository;
    protected ContratoEntity build(ContratoTipo tipo, ContratoRequest request){
        return build(tipo, ContratoSituacao.CONCLUIDO, request);
    }
    protected ContratoEntity build(ContratoTipo tipo, ContratoSituacao situacao, ContratoRequest request){
        ContratoEntity entity = new ContratoEntity();
        if(request.getData()!=null && !request.getData().isEqual(LocalDate.now()))
            throw new RegistroIncompativelException("Não é permitido o lançamento de contratos com dia diferente de hoje");

        entity.setTipo(tipo);
        entity.setSituacao(situacao);
        entity.setNumero(gerarLocalizador());
        entity.setDescricao(request.getDescricao());
        entity.setPartes(definirParticipantes(request.getCadastro()));
        entity.setIntermediador(request.getIntermediador() == null ? entity.getPartes().getUsuario() : request.getIntermediador());
        entity.setData(RegistroData.of(request.getData()));
        //entity.setPagamentos(conferirPagamentos(request));

        ContratoCalculoItem calculoItem = calcularItens(request.getItens());
        entity.setItens(calculoItem.getItens());

        ContratoValor valor = new ContratoValor();
        valor.setPrevisto(calculoItem.getItensTotalPrevisto());
        valor.setAcrescimoItens(calculoItem.getItensTotalAcrescimo());
        valor.setDescontoItens(calculoItem.getItensTotalDesconto());
        valor.setDescontoManual(request.getValorDescontoManual());
        valor.setAcrescimoPagamento(0.0);
        valor.setAplicado(Calculos.aplicarEscala(Calculos.ESCALA4, request.getValorAplicado()));
        valor.setAcrescimoPagamento(request.getFormasPagamento().stream().mapToDouble(p -> Calculos.subtrair(Calculos.ESCALA4, p.getValorPago(), p.getValorOriginal())).sum());
        entity.setValor(valor);
        //conferirValores(valor, calculo.getItensTotalAplicado() );
        processarTransacao(tipo.getAplicacao(), request);
        return entity;
    }
    private void conferirValores(ContratoValor valor){

        /*
        Double  t = va
        Double subtotal = Calculos.subtrair(valor.getAplicado(), valor.getAcrescimoPagamento());

        if(!Calculos.compararIgualdade(subtotal, valorTotalItens))
            throw new RegistroIncompativelException("É necessário revisar os valores informados");
        */
    }
    private ContratoCalculoItem calcularItens(List<ContratoItemRequest> itens){
        if(itens==null || itens.size()==0)
            throw new RegistroIncompativelException("É necessário informar ao menos um produto ou serviço para a geração do contrato");

        ContratoCalculoItem calculo = new ContratoCalculoItem();

        List<ContratoItemEntity> list = new ArrayList<>();
        itens.stream().forEach(i->{
            ContratoItemEntity item = new ContratoItemEntity();
            item.setDescricao(i.getDescricao());
            item.setProduto(produtoItem(i.getProduto()));
            item.setQuantidade(Calculos.aplicarEscala(Calculos.ESCALA4,i.getQuantidade()));
            item.setValorUnitario(Calculos.aplicarEscala(Calculos.ESCALA4,i.getValorUnitario()));
            item.setValorAplicado(Calculos.aplicarEscala(Calculos.ESCALA4,i.getValorAplicado()));
            item.setValorPrevisto(Calculos.multiplicar(Calculos.ESCALA4,item.getProduto().getPreco(), i.getQuantidade()) );
            Double variacao = Calculos.subtrair(Calculos.ESCALA4,item.getValorAplicado(),item.getValorPrevisto());
            item.setValorVariacao(variacao);
            calculo.setItensTotalPrevisto(Calculos.somar(Calculos.ESCALA4,calculo.getItensTotalPrevisto(), item.getValorPrevisto()));
            calculo.setItensTotalAplicado(Calculos.somar(Calculos.ESCALA4,calculo.getItensTotalAplicado(), item.getValorAplicado()));
            if (Calculos.compararMenorQue(variacao, 0.0))
                calculo.setItensTotalDesconto(Math.abs(Calculos.somar(Calculos.ESCALA4,calculo.getItensTotalDesconto(), variacao)));
            else
                calculo.setItensTotalAcrescimo(Calculos.somar(Calculos.ESCALA4,calculo.getItensTotalAcrescimo(), variacao));
            list.add(item);
        });
        calculo.setItens(list);
        return calculo;
    }
    private ProdutoItem produtoItem(Integer produto){
        ProdutoEntity entity = produtoRepository.findById(produto).orElseThrow(()-> new RegistroNaoLocalizadoException(Entities.PRODUTO_ENTITY, Attributes.ID));
        ProdutoItem item = new ProdutoItem();
        item.setId(entity.getId());
        item.setSaldo(entity.getSaldo());
        item.setCodigoBarras(entity.getCodigoBarras());
        item.setTaxaLiquidacao(entity.getTaxaLiquidacao());
        item.setUnidadeMedidaSigla(String.valueOf(entity.getUnidadeMedidaSigla()));
        item.setPreco(entity.getValor());
        return item;
    }
    @Autowired
    private TransacaoService transacaoService;
    private void processarTransacao(AplicacaoTipo tipo, ContratoRequest request){
        String titulo = tipo ==AplicacaoTipo.RECEITA ? "Venda \\ Serviço" : "Outros";
        TransacaoRequest transacao = new TransacaoRequest();
        transacao.setData(request.getData());
        transacao.setValor(request.getValorAplicado());
        transacao.setCadastro(request.getCadastro());
        transacao.setDescricao(titulo);
        transacao.setObservacao("");
        transacao.setTitulo(titulo);
        transacao.setFormasPagamento(request.getFormasPagamento());
        transacaoService.incluir(tipo,transacao);
    }
}
