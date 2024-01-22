package com.digytal.control.service.modulo.contrato;

import com.digytal.control.infra.business.BusinessException;
import com.digytal.control.infra.business.ErroNaoMapeadoException;
import com.digytal.control.model.modulo.contrato.ContratoEntity;
import com.digytal.control.model.modulo.contrato.ContratoTipo;
import com.digytal.control.model.modulo.contrato.request.ContratoRequest;
import com.digytal.control.repository.contratos.ContratoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ContratoVendaService extends ContratoService{
    @Autowired
    private ContratoRepository repository;
    @Transactional
    public Integer gerarContratoVenda(ContratoRequest request){
        try {
            ContratoEntity entity = build(ContratoTipo.VENDA, request);
            repository.save(entity);
            return entity.getId();
        }catch (BusinessException ex){
            log.warn(BusinessException.logMessage(ex));
            throw ex;
        }catch (Exception ex){
            log.error("Erro ao tentar gerar um contrato de venda",ex);
            throw new ErroNaoMapeadoException();
        }
    }
}
