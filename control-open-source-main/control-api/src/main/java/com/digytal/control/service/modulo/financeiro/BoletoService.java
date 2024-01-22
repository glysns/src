package com.digytal.control.service.modulo.financeiro;

import com.digytal.control.infra.business.CampoObrigatorioException;
import com.digytal.control.infra.business.ParametroInvalidoException;
import com.digytal.control.infra.business.RegistroNaoLocalizadoException;
import com.digytal.control.infra.commons.validation.Entities;
import com.digytal.control.infra.commons.validation.Validation;
import com.digytal.control.infra.utils.Calculos;
import com.digytal.control.integracao.asaas.IntegradorPagamento;
import com.digytal.control.integracao.asaas.model.BoletoRequest;
import com.digytal.control.integracao.asaas.model.BoletoResponse;
import com.digytal.control.integracao.asaas.model.Cadastro;
import com.digytal.control.integracao.asaas.model.PagamentoEvent;
import com.digytal.control.model.comum.MeioPagamento;
import com.digytal.control.model.comum.RegistroData;
import com.digytal.control.model.comum.cadastramento.CadastroIntegracao;
import com.digytal.control.model.modulo.acesso.empresa.pagamento.FormaPagamentoEntity;
import com.digytal.control.model.modulo.cadastro.CadastroEntity;
import com.digytal.control.model.modulo.financeiro.parcelamento.ParcelamentoEntity;
import com.digytal.control.model.modulo.financeiro.parcelamento.parcela.ParcelaEntity;
import com.digytal.control.model.modulo.financeiro.parcelamento.parcela.liquidacao.ParcelaPagamentoEntity;
import com.digytal.control.model.modulo.financeiro.transacao.TransacaoEntity;
import com.digytal.control.model.modulo.financeiro.transacao.pagamento.FormaPagamentoRequest;
import com.digytal.control.repository.modulo.acesso.empresa.FormaPagamentoRepository;
import com.digytal.control.repository.modulo.cadastro.CadastroRepository;
import com.digytal.control.repository.modulo.fincanceiro.ParcelaPagamentoRepository;
import com.digytal.control.repository.modulo.fincanceiro.ParcelaRepository;
import com.digytal.control.repository.modulo.fincanceiro.ParcelamentoRepository;
import com.digytal.control.model.modulo.financeiro.parcelamento.boleto.*;
import com.digytal.control.repository.modulo.fincanceiro.TransacaoRepository;
import com.digytal.control.service.comum.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import static com.digytal.control.infra.commons.validation.Attributes.ID;
import static com.digytal.control.infra.commons.validation.Attributes.NUMERO_AUTORIZACAO;

@Service
@Slf4j
public class BoletoService extends AbstractService {
    @Autowired
    private ParcelaRepository parcelaRepository;
    @Autowired
    private ParcelamentoRepository parcelamentoRepository;
    @Autowired
    private CadastroRepository cadastroRepository;
    @Autowired
    private IntegradorPagamento integrador;
    @Autowired
    private PagamentoService pagamentoService;
    @Autowired
    private ParcelaPagamentoRepository parcelaPagamentoRepository;
    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;
    @Autowired
    private TransacaoRepository transacaoRepository;

    @Transactional
    public BoletoResponse gerarBoleto( Integer parcelaId,Double valorBoleto){
        return gerarBoleto(parcelaId, valorBoleto,null, requestInfo.getEmpresa());
    }
    @Transactional
    public BoletoResponse gerarBoleto(Integer parcelaId, Double valorBoleto, LocalDate dataVencimento , Integer empresa){
        ParcelaEntity parcela = parcelaRepository.findById(parcelaId).orElseThrow(()-> new RegistroNaoLocalizadoException(Entities.PARCELA, ID));
        ParcelamentoEntity parcelamento = parcelamentoRepository.findById(parcela.getParcelamento()).orElseThrow(()-> new RegistroNaoLocalizadoException(Entities.PARCELAMENTO, ID));
        CadastroEntity cadastro = cadastroRepository.findById(parcelamento.getCadastro()).orElseThrow(()-> new RegistroNaoLocalizadoException(Entities.CADASTRO_ENTITY, ID));

        Double valor = valorBoleto == null || valorBoleto.equals(0.0)? parcela.getDetalhe().getValorAtual(): valorBoleto;
        dataVencimento = dataVencimento==null? parcela.getDetalhe().getDataVencimento() : dataVencimento;
        return gerarBoleto(cadastro, parcela, dataVencimento, valor, empresa);
    }
    private BoletoResponse gerarBoleto(CadastroEntity cadastro, ParcelaEntity parcela, LocalDate dataVencimento, Double valor , Integer empresa){
        integrador.atualizarCredenciamento(empresa);
        ParcelaBoleto boleto = parcela.getBoleto();
        if(boleto.isSolicitado() && boleto.getStatus()==ParcelaBoletoStatus.EMITIDO){
            BoletoResponse response = new BoletoResponse();
            return response;
        }
        parcela.getBoleto().setSolicitado(true);
        parcela.getBoleto().setStatus(ParcelaBoletoStatus.SOLICITADO);
        parcela.getBoleto().setTipoRepasse(integrador.isContaEmpresa() ? "R" : "P");// Receber / Pagar
        parcela.getBoleto().setValorOriginal(valor);
        parcela.getBoleto().setValorTaxaImpressao(integrador.getTaxaEmissaoBoleto());
        parcela.getBoleto().setValorImpresso(Calculos.somar(valor, parcela.getBoleto().getValorTaxaImpressao()));
        parcela.getDetalhe().setDataVencimento(dataVencimento);

        if(cadastro.getEmail()==null || cadastro.getCpfCnpj()==null || !Validation.cpfCnpj(cadastro.getCpfCnpj()))
            throw new CampoObrigatorioException("É necessário informar o CPF/CNPJ e e-mail no cadastro do cliente");

        Cadastro cad = new Cadastro();
        cad.setCpfCnpj(cad.getCpfCnpj());
        cad.setId(cadastro.getIntegracao()==null ? null : cadastro.getIntegracao().getAsaas());
        if(Validation.isEmpty(cad.getId())){
            cad.setDeleted(false);
            cad.setEmail(cadastro.getEmail());
            cad.setName(cadastro.getNomeFantasia());
            cad.setCpfCnpj(cadastro.getCpfCnpj());
            cad = integrador.cadastrar(empresa,cad);
            CadastroIntegracao integracao = new CadastroIntegracao();
            integracao.setAsaas(cad.getId());
            cadastro.setIntegracao(integracao);
            integrador.desativarNotificacoes(empresa, cadastro.getIntegracao().getAsaas());
            cadastroRepository.save(cadastro);
        }

        BoletoRequest boletoRequest = new BoletoRequest();
        boletoRequest.setDescription(parcela.getDescricao());
        boletoRequest.setValue(parcela.getBoleto().getValorImpresso());
        boletoRequest.setDueDate(dataVencimento.toString());
        boletoRequest.setCustomer(cadastro.getIntegracao().getAsaas());
        boletoRequest.setExternalReference(parcela.getId().toString());

        BoletoResponse response = integrador.gerarBoleto(empresa, boletoRequest);
        if(response!=null){
            boleto.setStatus(ParcelaBoletoStatus.EMITIDO);
            boleto.setNumeroAutorizacao(response.getId());
            boleto.setUrlImpressao(response.getBankSlipUrl());
            parcelaRepository.save(parcela);
        }
        return response;
    }
    @Transactional
    public String compensarViaWebhook(String accessToken, PagamentoEvent evento){
        if(evento.getEvent().equals("PAYMENT_RECEIVED")){
            log.info("Obtendo um evento de recebimento de pagamento");
            ParcelaEntity parcelaEntity = parcelaRepository.findByBoletoNumeroAutorizacao(evento.getPayment().getId());
            if(parcelaEntity==null)
                throw new RegistroNaoLocalizadoException(Entities.PARCELA,NUMERO_AUTORIZACAO);
            return compensar(true, accessToken, parcelaEntity);
        }
        return "";
    }
    @Transactional
    public String compensar(Integer parcela){
        log.info("Obtendo um evento de recebimento de pagamento");
        ParcelaEntity parcelaEntity = parcelaRepository.findById(parcela).orElseThrow(()-> new RegistroNaoLocalizadoException(Entities.PARCELA,ID));
        return compensar(false, null, parcelaEntity);

    }
    @Transactional
    public String compensarViaJob(ParcelaEntity parcelaEntity){
        log.info("Obtendo um evento de recebimento de pagamento");
        return compensar(false, null, parcelaEntity);
    }
    private String compensar(boolean webHook, String accessToken, ParcelaEntity parcela){
        String email = "gleyson@digytal.com.br";
        if(parcela.getQuitacao()!=null && !parcela.getQuitacao().isEfetuada()){

            if(parcela.getBoleto()!=null && parcela.getBoleto().getStatus() == ParcelaBoletoStatus.EMITIDO ) {
                ParcelamentoEntity parcelamento = parcelamentoRepository.findById(parcela.getParcelamento()).orElseThrow(() -> new RegistroNaoLocalizadoException(Entities.PARCELAMENTO, ID));
                TransacaoEntity transacaoEntity = transacaoRepository.findById(parcelamento.getTransacao()).orElse(null);
                integrador.atualizarCredenciamento(transacaoEntity.getPartes().getEmpresa());
                email = integrador.getEmail();
                if (webHook && (integrador.getWebHookAccessToken() != null || integrador.getWebHookAccessToken().trim().length() > 0)) {
                    if (!integrador.getWebHookAccessToken().equals(accessToken))
                        throw new ParametroInvalidoException("Não foi possível validar o token obtido na requisição do pagamento " + parcela.getBoleto().getNumeroAutorizacao());
                }

                BoletoResponse response = integrador.obterBoleto(transacaoEntity.getPartes().getEmpresa(), parcela.getBoleto().getNumeroAutorizacao());
                if (response != null && response.getStatus().equals("RECEIVED")) {
                    ParcelaBoleto boleto = parcela.getBoleto();
                    Double valorAmortizado = boleto.getValorOriginal();

                    boleto.setDataCompensacao(response.getPaymentDate());
                    boleto.setDataPagamento(response.getClientPaymentDate());
                    boleto.setValorCompensado(response.getNetValue());
                    boleto.setStatus(ParcelaBoletoStatus.PAGO);
                    boleto.setTipoCompensacao(response.getBillingType());

                    FormaPagamentoRequest rateio = new FormaPagamentoRequest();
                    rateio.setMeioPagamento(MeioPagamento.BOLETO);
                    rateio.setTaxaPagamento(0.0);
                    rateio.setValorOriginal(valorAmortizado);
                    rateio.setValorPago(valorAmortizado);
                    String descricao = String.format("Comp.Aut.Bol Parc.:%d/%d - asaas", parcelamento.getId(), parcela.getId());
                    pagamentoService.criarPagamentoParcelamento(transacaoEntity.getTipo(), rateio, descricao, parcelamento.getId(), transacaoEntity);

                    //atualiza o valor da parcela o total do parcelamento
                    parcelamento.getDetalhe().setValorAmortizado(Calculos.somar(parcelamento.getDetalhe().getValorAmortizado(), valorAmortizado));
                    parcelamento.getDetalhe().setValorAtual(Math.abs(Calculos.subtrair(parcelamento.getDetalhe().getValorAtual(), valorAmortizado)));
                    parcelamentoRepository.save(parcelamento);

                    parcela.getDetalhe().setValorAmortizado(Calculos.somar(parcela.getDetalhe().getValorAmortizado(), valorAmortizado));

                    parcela.getDetalhe().setValorAtual(Calculos.subtrair(parcela.getDetalhe().getValorAtual(), valorAmortizado));
                    parcela.getQuitacao().setEfetuada(Calculos.compararIgualZero(parcela.getDetalhe().getValorAtual()));
                    parcela.getQuitacao().setData(parcela.getQuitacao().isEfetuada() ? LocalDate.now() : null);
                    parcelaRepository.save(parcela);

                    FormaPagamentoEntity formaPagamento = formaPagamentoRepository.findByEmpresaAndMeioPagamentoAndNumeroParcelas(transacaoEntity.getPartes().getEmpresa(), MeioPagamento.BOLETO, 1);
                    //etapa de gerar a linha de pagamento
                    ParcelaPagamentoEntity pagamento = new ParcelaPagamentoEntity();
                    pagamento.setData(LocalDate.now());
                    pagamento.setCompetencia(RegistroData.periodo(pagamento.getData()));
                    pagamento.setContaBanco(formaPagamento.getConta());
                    pagamento.setParcela(parcela.getId());
                    pagamento.setParcelamento(parcelamento.getId());
                    pagamento.setBoletoValorOriginal(boleto.getValorImpresso());
                    pagamento.setValor(valorAmortizado);
                    pagamento.setMeioPagamento(MeioPagamento.BOLETO);// COMPENSACAO
                    pagamento.setUsuario(1);
                    pagamento.setBoletoNumeroAutorizacao(boleto.getNumeroAutorizacao());
                    parcelaPagamentoRepository.save(pagamento);
                }
            }

        }else{
            log.info("{BOLETO}{ATENCAO} Houve uma tentativa de compensação da parcela já PAGA via Asaas {} {} ", parcela.getId(),  parcela.getBoleto().getNumeroAutorizacao() );
        }


        return email;
        //return String.format("Parc./N°: %d/%d Nosso Número: %s, R$ Valor: %.2f - Status: %s\n", parcela.getId(), parcela.getDetalhe().getNumeroParcela(), response.getNossoNumero(), response.getValue(), response.getStatus());
    }

}
