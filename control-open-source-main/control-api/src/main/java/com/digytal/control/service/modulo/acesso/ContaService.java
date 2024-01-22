package com.digytal.control.service.modulo.acesso;

import com.digytal.control.infra.business.RegistroDuplicadoException;
import com.digytal.control.infra.business.RegistroIncompativelException;
import com.digytal.control.infra.business.RegistroNaoLocalizadoException;
import com.digytal.control.infra.commons.validation.Entities;
import com.digytal.control.infra.commons.validation.Validations;
import com.digytal.control.model.modulo.acesso.empresa.conta.ContaEntity;
import com.digytal.control.model.modulo.acesso.empresa.conta.ContaRequest;
import com.digytal.control.model.modulo.acesso.empresa.conta.ContaResponse;
import com.digytal.control.model.modulo.acesso.empresa.pagamento.FormaPagamentoEntity;
import com.digytal.control.model.modulo.acesso.empresa.pagamento.FormaPagamentoCadastroRequest;
import com.digytal.control.model.modulo.acesso.empresa.pagamento.FormaPagamentoCadastroResponse;
import com.digytal.control.model.comum.MeioPagamento;
import com.digytal.control.model.modulo.cadastro.CadastroResponse;
import com.digytal.control.model.modulo.cadastro.produto.ProdutoEntity;
import com.digytal.control.model.modulo.cadastro.produto.ProdutoResponse;
import com.digytal.control.repository.modulo.acesso.empresa.ContaRepository;
import com.digytal.control.repository.modulo.acesso.empresa.FormaPagamentoRepository;
import com.digytal.control.service.comum.AbstractService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.digytal.control.infra.commons.validation.Attributes.*;
import static com.digytal.control.infra.commons.validation.Entities.*;

@Service
public class ContaService extends AbstractService {
    @Autowired
    private ContaRepository repository;
    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;
    public List<ContaResponse> listarContas(){
        return repository.findByEmpresa(requestInfo.getEmpresa()).stream().map(i->{
            ContaResponse item= new ContaResponse();
            BeanUtils.copyProperties(i,item);
            return item;
        }).collect(Collectors.toList());
    }
    public ContaResponse buscarContaCarteira(){
        FormaPagamentoEntity forma = formaPagamentoRepository.findByEmpresaAndMeioPagamentoAndNumeroParcelas(requestInfo.getEmpresa(), MeioPagamento.DINHEIRO, 1);
        return buscarConta(forma.getConta());
    }
    public ContaResponse buscarConta(Integer id){
        ContaEntity entity = repository.findById(id).orElseThrow(()-> new RegistroNaoLocalizadoException(CONTA_BANCO_ENTITY, ID));
        ContaResponse response= new ContaResponse();
        BeanUtils.copyProperties(entity,response);
        return response;
    }
    public List<FormaPagamentoCadastroResponse> consultarFormasPagamento(Integer id){
        return formaPagamentoRepository.findByConta(id).stream().map(i->{
            FormaPagamentoCadastroResponse item= new FormaPagamentoCadastroResponse();
            BeanUtils.copyProperties(i,item);
            return item;
        }).collect(Collectors.toList());
    }
    public List<FormaPagamentoCadastroResponse> consultarFormasPagamento(MeioPagamento meioPagamento){
       return formaPagamentoRepository.findByEmpresaAndMeioPagamento(requestInfo.getEmpresa(), meioPagamento).stream().map(this::convert).collect(Collectors.toList());
    }
    public FormaPagamentoCadastroResponse localizarFormaPagamento(MeioPagamento meioPagamento, Integer numeroParcelas){
        FormaPagamentoEntity result = formaPagamentoRepository.findByEmpresaAndMeioPagamentoAndNumeroParcelas(requestInfo.getEmpresa(), meioPagamento, numeroParcelas);
        return convert(result);
    }

    private FormaPagamentoCadastroResponse convert(FormaPagamentoEntity entity){
        FormaPagamentoCadastroResponse response = new FormaPagamentoCadastroResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    @Transactional
    public Integer alterar(Integer id, ContaRequest request){
        return gravar( id, request);
    }
    @Transactional
    public Integer incluir(ContaRequest request){
        return gravar( null, request);
    }
    private Integer gravar(Integer id, ContaRequest request){
        Validations.build(AGENCIA, NUMERO, LEGENDA, BANCO).notEmpty().check(request);
        if (request.isContaCredito()) {
            if (request.getFatura() == null)
                throw new RegistroIncompativelException("É necessário informar os campos dia intervalo e dia de vencimento para contas do tipo CRÉDITO");

            Validations.build(DIA_VENCIMENTO, DIAS_INTERVALO).notEmpty().check(request.getFatura());
        }
        ContaEntity entity = null;
        if (id != null) {
            entity = repository.findById(id).orElseThrow(() -> new RegistroNaoLocalizadoException(EMPRESA_CONTA_ENTITY, ID));
            validarIntegridadeEmpresarial(entity.getEmpresa());
            Double saldo = entity.getSaldo();
            BeanUtils.copyProperties(request, entity);
            entity.setSaldo(saldo);
        } else {
            if (repository.existsByAgenciaAndNumeroAndContaCredito(request.getAgencia(), request.getNumero(), request.isContaCredito()))
                throw new RegistroDuplicadoException(NUMERO, String.format("Agência: %s e Número: %s ", request.getAgencia(), request.getNumero()));

            entity = new ContaEntity();
            BeanUtils.copyProperties(request, entity);
            entity.setSaldo(0.0);
            entity.setEmpresa(requestInfo.getEmpresa());
        }
        repository.save(entity);
        return entity.getId();
    }
    @Transactional
    public Integer incluirFormaPagamento(Integer conta, FormaPagamentoCadastroRequest request){
        Validations.build(CONTA, MEIO_PAGAMENTO).check(request);

        if(request.getMeioPagamento() == MeioPagamento.COMPENSACAO || request.getMeioPagamento() == MeioPagamento.SALDO){
            throw new RegistroIncompativelException("Este meio de pagamento não pode ser utilizado nesta funcionalidade");
        }

        Integer numeroParcelas = request.getNumeroParcelas()==null || request.getNumeroParcelas()==0 ? 1: request.getNumeroParcelas();
        ContaEntity contaEntity = repository.findById(conta).orElseThrow(() -> new RegistroNaoLocalizadoException(EMPRESA_CONTA_ENTITY, ID));

        if(formaPagamentoRepository.existsByEmpresaAndMeioPagamentoAndNumeroParcelas(contaEntity.getEmpresa(), request.getMeioPagamento(), numeroParcelas))
            throw new RegistroDuplicadoException(FORMA_PAGAMENTO, request.getMeioPagamento().getDescricao());

        FormaPagamentoEntity entity = new FormaPagamentoEntity();
        entity.setConta(contaEntity.getId());
        entity.setEmpresa(contaEntity.getEmpresa());
        entity.setTaxa(request.getTaxa()==null?0.0: request.getTaxa());
        entity.setMeioPagamento(request.getMeioPagamento());
        entity.setNumeroParcelas(numeroParcelas);
        formaPagamentoRepository.save(entity);
        return entity.getId();
    }
    @Transactional
    public boolean excluirFormaPagamento(Integer id){
        if(formaPagamentoRepository.existsById(id)){
            formaPagamentoRepository.deleteById(id);
            return true;
        }else throw new RegistroNaoLocalizadoException(CONTA_FORMA_PAGAMENTO_ENTITY, ID);
    }

}
