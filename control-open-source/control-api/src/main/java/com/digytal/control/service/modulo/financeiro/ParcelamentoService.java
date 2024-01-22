package com.digytal.control.service.modulo.financeiro;

import com.digytal.control.infra.business.RegistroIncompativelException;
import com.digytal.control.infra.business.RegistroNaoLocalizadoException;
import com.digytal.control.infra.commons.validation.Entities;
import com.digytal.control.infra.commons.validation.Validation;
import com.digytal.control.infra.utils.Calculos;
import com.digytal.control.model.comum.MeioPagamento;
import com.digytal.control.model.comum.RegistroData;
import com.digytal.control.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import com.digytal.control.model.modulo.acesso.empresa.pagamento.FormaPagamentoEntity;
import com.digytal.control.model.modulo.financeiro.parcelamento.ParcelamentoEntity;
import com.digytal.control.model.modulo.financeiro.parcelamento.ParcelamentoDetalhe;
import com.digytal.control.model.modulo.financeiro.parcelamento.parcela.ParcelaEntity;
import com.digytal.control.model.modulo.financeiro.parcelamento.parcela.liquidacao.ParcelaPagamentoEntity;
import com.digytal.control.model.modulo.financeiro.parcelamento.parcela.liquidacao.ParcelaPagamentoRequest;
import com.digytal.control.model.modulo.financeiro.transacao.TransacaoEntity;
import com.digytal.control.model.modulo.financeiro.transacao.pagamento.FormaParcelamentoRequest;
import com.digytal.control.model.modulo.financeiro.transacao.pagamento.FormaPagamentoRequest;
import com.digytal.control.repository.modulo.acesso.empresa.FormaPagamentoRepository;
import com.digytal.control.repository.modulo.fincanceiro.ParcelaPagamentoRepository;
import com.digytal.control.repository.modulo.fincanceiro.ParcelaRepository;
import com.digytal.control.repository.modulo.fincanceiro.ParcelamentoRepository;
import com.digytal.control.service.comum.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.digytal.control.infra.commons.validation.Attributes.ID;
import static com.digytal.control.infra.utils.Calculos.ESCALA2;

@Service
public class ParcelamentoService extends AbstractService {
    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;
    @Autowired
    private ParcelamentoRepository parcelamentoRepository;
    @Autowired
    private ParcelaPagamentoRepository parcelaPagamentoRepository;

    @Autowired
    private ParcelaRepository parcelaRepository;
    public ParcelamentoEntity criarParcelamento(AplicacaoTipo tipo, Integer cadastro, FormaPagamentoRequest rateio, String descricao) {
        ParcelamentoEntity entity = new ParcelamentoEntity();
        entity.setDescricao(descricao);
        MeioPagamento meioPagamento = rateio.getMeioPagamento();
        FormaPagamentoEntity formaPagamento = formaPagamentoRepository.findByEmpresaAndMeioPagamentoAndNumeroParcelas(requestInfo.getEmpresa(),meioPagamento,1);
        if(formaPagamento==null)
            throw new RegistroIncompativelException("O meio de pagamento " + meioPagamento.getDescricao() + " não se encontra vinculado a nenhuma conta empresa");

        FormaParcelamentoRequest parcelamento = conferirParcelamento(rateio.getParcelamento());
        ParcelamentoDetalhe valor = ParcelamentoDetalhe.of(parcelamento.getNumeroParcelas(), rateio.getValorPago(), parcelamento.getDataPrimeiroVencimento());
        entity.setDetalhe(valor);
        entity.setMeioPagamento(rateio.getMeioPagamento());
        entity.setParcelas(parcelar(rateio.getValorPago(), parcelamento));
        entity.setCadastro(cadastro);
        return entity;
    }
    private FormaParcelamentoRequest conferirParcelamento(FormaParcelamentoRequest parcelamento){
        if(parcelamento ==null)
            parcelamento = new FormaParcelamentoRequest();

        parcelamento.setDataPrimeiroVencimento(parcelamento.getDataPrimeiroVencimento()==null ? LocalDate.now().plusDays(30) : parcelamento.getDataPrimeiroVencimento());
        parcelamento.setNumeroParcelas(parcelamento.getNumeroParcelas()==null ? 1: parcelamento.getNumeroParcelas());
        return  parcelamento;
    }
    private List<ParcelaEntity> parcelar(Double valor, FormaParcelamentoRequest parcelamento){
        BigDecimal valorReal= BigDecimal.valueOf(valor);
        Integer numeroParcelas = parcelamento.getNumeroParcelas();
        BigDecimal valorParcela = valorReal.divide(BigDecimal.valueOf(numeroParcelas),4, RoundingMode.HALF_EVEN);
        List<ParcelaEntity> parcelas = new ArrayList<>();

        Integer diaVencimento = parcelamento.getDataPrimeiroVencimento().getDayOfMonth();
        LocalDate dataVencimento = parcelamento.getDataPrimeiroVencimento();
        if(dataVencimento==null)
            dataVencimento = definirDataVencimento(null,diaVencimento);

        if(dataVencimento.isBefore(LocalDate.now()))
            throw new RegistroIncompativelException("A data de vencimento é inferior a data atual");

        for(int p=1; p<=numeroParcelas; p++){
            ParcelaEntity parcela = new ParcelaEntity();
            parcela.setDescricao(String.format("PARC.NR.%03d", p) );
            ParcelamentoDetalhe parcelamentoValor = parcela.getDetalhe();
            parcelamentoValor.setDataVencimento(dataVencimento);
            parcelamentoValor.setNumeroParcela(p);

            if(p<numeroParcelas){
                valorReal = valorReal.subtract(Calculos.aplicarEscala(valorParcela));
                parcelamentoValor.setValorOriginal(Calculos.aplicarEscala(valorParcela).doubleValue());
            }else
                parcelamentoValor.setValorOriginal(Calculos.aplicarEscala(valorReal).doubleValue());

            parcelamentoValor.setValorAtual(parcelamentoValor.getValorOriginal());

            parcelas.add(parcela);

            dataVencimento = definirDataVencimento(dataVencimento,diaVencimento);
        }
        return parcelas;
    }
    private LocalDate definirDataVencimento(LocalDate dataInicial, Integer diaVencimento){
        if(dataInicial==null)
            return LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), diaVencimento);
        else{
            return dataInicial.plusMonths(1);
        }
    }
    @Transactional
    public void realizarCompensacao(Integer id, List<ParcelaPagamentoRequest> requests){
        for(ParcelaPagamentoRequest request: requests){
            ParcelaPagamentoRequest pagamento = new ParcelaPagamentoRequest();
            pagamento.setMeioPagamento(request.getMeioPagamento());
            pagamento.setValor(request.getValor());
            if(request.getMeioPagamento() == MeioPagamento.CREDITO)
                throw new RegistroIncompativelException("Não é permitido pagar uma parcela com a forma de pagamento CREDITO");

            realizarCompensacao(id,pagamento,pagamento.getMeioPagamento());
        }
    }
    @Autowired
    private PagamentoService pagamentoService;
    private void realizarCompensacao(Integer id, ParcelaPagamentoRequest request, MeioPagamento meioPagamento){

        if(!meioPagamento.isInstantaneo())
            throw new RegistroIncompativelException("O Meio de Pagamento selecionado não pode ser utilizado para a confirmação do pagamento");


        ParcelaEntity parcela = parcelaRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoLocalizadoException(Entities.PARCELA, ID));

        ParcelamentoEntity parcelamento = parcelamentoRepository.findById(parcela.getParcelamento())
                .orElseThrow(() -> new RegistroNaoLocalizadoException(Entities.LANCAMENTO, ID));


        TransacaoEntity transacao = globalRepository.findById(TransacaoEntity.class, parcelamento.getTransacao());
        FormaPagamentoEntity formaPagamento = consultarFormaPagamento(transacao.getPartes().getEmpresa(), meioPagamento);

        if(parcelamento.getMeioPagamento()== MeioPagamento.CREDITO)
            throw new RegistroIncompativelException("Não é permitida a compensão manual de parcela de Cartão de Crédito");

        if(parcela.getQuitacao().isEfetuada())
            throw new RegistroIncompativelException("Esta parcela já está com status quitada, N° " + parcela.getDetalhe().getNumeroParcela());

        if(Calculos.compararMenorQueZero(request.getValor()))
            throw new RegistroIncompativelException("O valor recebido não pode ser negativo");

        Double valor = Calculos.aplicarEscala(request.getValor());

        if(Calculos.compararMenorQue(parcela.getDetalhe().getValorAtual(), valor))
            throw new RegistroIncompativelException("O valor recebido precisa ser igual ou inferior ao valor atual");

        //etapa de gerar a linha de pagamento
        ParcelaPagamentoEntity pagamento = new ParcelaPagamentoEntity();
        pagamento.setData(LocalDate.now());
        pagamento.setCompetencia(RegistroData.periodo(pagamento.getData()));
        pagamento.setContaBanco(formaPagamento.getConta());

        pagamento.setParcela(id);
        pagamento.setParcelamento(parcelamento.getId());
        pagamento.setValor(valor);
        pagamento.setMeioPagamento(request.getMeioPagamento());
        pagamento.setUsuario(requestInfo.getUsuario());
        parcelaPagamentoRepository.save(pagamento);

        //atualiza o valor da parcela o total do parcelamento
        parcelamento.getDetalhe().setValorAmortizado( Calculos.somar(ESCALA2, parcelamento.getDetalhe().getValorAmortizado(), valor ));
        parcelamento.getDetalhe().setValorAtual(Math.abs(Calculos.subtrair(ESCALA2, parcelamento.getDetalhe().getValorAtual(), valor)));
        parcelamentoRepository.save(parcelamento);

        parcela.getDetalhe().setValorAmortizado( Calculos.somar(ESCALA2, parcela.getDetalhe().getValorAmortizado(), valor));
        parcela.getDetalhe().setValorAtual(Math.abs( Calculos.subtrair(ESCALA2, parcela.getDetalhe().getValorAtual(), valor)));

        parcela.getQuitacao().setEfetuada(Validation.isZero(parcela.getDetalhe().getValorAtual()));
        parcela.getQuitacao().setData(parcela.getQuitacao().isEfetuada()?LocalDate.now():null);
        parcelaRepository.save(parcela);


        FormaPagamentoRequest rateio = new FormaPagamentoRequest();
        rateio.setTaxaPagamento(0.0);
        rateio.setValorPago(request.getValor());
        rateio.setValorOriginal(request.getValor());
        rateio.setMeioPagamento(request.getMeioPagamento());

        String descricao = String.format("%s Prct/Prcl %d/%d", (transacao.getTipo() == AplicacaoTipo.DESPESA?"Pagto.":"Recto."), parcelamento.getId(), parcela.getDetalhe().getNumeroParcela());
        pagamentoService.criarPagamentoParcelamento(transacao.getTipo(), rateio, descricao,parcelamento.getId(), transacao);

    }

}