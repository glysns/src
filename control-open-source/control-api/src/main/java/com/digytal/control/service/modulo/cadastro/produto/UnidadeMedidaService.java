package com.digytal.control.service.modulo.cadastro.produto;

import com.digytal.control.infra.business.*;
import com.digytal.control.infra.commons.definition.Definition;
import com.digytal.control.infra.commons.validation.Entities;
import com.digytal.control.infra.commons.validation.Validations;
import com.digytal.control.infra.utils.Calculos;
import com.digytal.control.model.comum.Associacao;
import com.digytal.control.model.modulo.cadastro.produto.unidademedida.UnidadeMedidaEntity;
import com.digytal.control.model.modulo.cadastro.produto.unidademedida.UnidadeMedidaRequest;
import com.digytal.control.model.modulo.cadastro.produto.unidademedida.UnidadeMedidaResponse;
import com.digytal.control.repository.modulo.cadastro.UnidadeMedidaRepository;
import com.digytal.control.service.comum.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.digytal.control.infra.commons.validation.Attributes.*;

@Service
@Slf4j
public class UnidadeMedidaService extends AbstractService {
    @Autowired
    private UnidadeMedidaRepository repository;

    public Integer incluir(UnidadeMedidaRequest request) {
        return gravar(null, request);
    }

    public Integer alterar(Integer id, UnidadeMedidaRequest request) {
        return gravar(id, request);
    }

    @Transactional
    private Integer gravar(Integer id, UnidadeMedidaRequest request) {
        try {
            request.setDescricao(Definition.seNuloOuVazio(request.getDescricao(), request.getNome(), 25));

            Validations.build(SIGLA).notEmpty().maxLen(8).check(request);
            Validations.build(NOME).notEmpty().minLen(2).maxLen(25).check(request);
            Validations.build(DESCRICAO).maxLen(100).check(request);
            UnidadeMedidaEntity entity = Optional.ofNullable(id).isPresent() ? repository.findById(id).orElseThrow(() -> new RegistroNaoLocalizadoException(Entities.UNIDADE_MEDIDA_ENTITY, ID)) : new UnidadeMedidaEntity(requestInfo.getOrganizacao());
            BeanUtils.copyProperties(request, entity);
            entity.setConteudo(Calculos.seNuloOuZero(request.getConteudo(), 1.0));
            entity.setLocaliza(normalizar(request.getNome()));
            repository.save(entity);
            return entity.getId();
        } catch (BusinessException ex) {
            log.warn(BusinessException.logMessage(ex));
            throw ex;
        } catch (Exception ex) {
            log.error(BusinessException.errorMessage("Não foi possível incluir ou alterar a unidade de medida [ %s ]", request.getNome()), ex);
            throw new ErroNaoMapeadoException();
        }
    }
    public List<UnidadeMedidaResponse> consultar(String nome) {
        nome = normalizar(Objects.toString(nome, ""));
        List<UnidadeMedidaEntity> list = repository.findByOrganizacaoAndLocalizaContaining(requestInfo.getOrganizacao(), nome);
        List<UnidadeMedidaResponse> response = list.stream().map(this::convert).collect(Collectors.toList());
        if (response.isEmpty()) {
            throw new RegistroNaoLocalizadoException(Entities.UNIDADE_MEDIDA_ENTITY, NOME);
        }
        return response;

    }
    public UnidadeMedidaResponse buscar(Integer id){
        return repository.findById(id).map(this::convert).orElseThrow(()-> new RegistroNaoLocalizadoException(Entities.UNIDADE_MEDIDA_ENTITY, ID));
    }
    private UnidadeMedidaResponse convert(UnidadeMedidaEntity entity){
        UnidadeMedidaResponse response = new UnidadeMedidaResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
    public List<Associacao> listar(String nome) {
        return consultar(nome).stream().map(i -> {
            Associacao item = new Associacao();
            item.setId(i.getId());
            item.setIdentificador(i.getId());
            item.setDescricao(i.getNome());
            item.setAbreviacao(i.getSigla());
            return item;
        }).collect(Collectors.toList());

    }
}
