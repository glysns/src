package com.digytal.control.service.modulo.cadastro.produto;

import com.digytal.control.infra.business.BusinessException;
import com.digytal.control.infra.business.ErroNaoMapeadoException;
import com.digytal.control.infra.business.RegistroNaoLocalizadoException;
import com.digytal.control.infra.commons.definition.Definition;
import com.digytal.control.infra.commons.validation.Entities;
import com.digytal.control.infra.commons.validation.Validations;
import com.digytal.control.model.comum.Associacao;
import com.digytal.control.model.modulo.cadastro.produto.categoria.CategoriaEntity;
import com.digytal.control.model.modulo.cadastro.produto.categoria.CategoriaRequest;
import com.digytal.control.model.modulo.cadastro.produto.categoria.CategoriaResponse;
import com.digytal.control.model.modulo.cadastro.produto.modelo.ModeloEntity;
import com.digytal.control.repository.GlobalRepository;
import com.digytal.control.repository.modulo.cadastro.CategoriaRepository;
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
public class CategoriaService extends AbstractService {
    @Autowired
    private CategoriaRepository repository;
    @Autowired
    protected GlobalRepository globalRepository;
    public Integer incluir( CategoriaRequest request) {
        return gravar(null, request);
    }

    public Integer alterar(Integer id, CategoriaRequest request) {
        return gravar(id,request);
    }
    @Transactional
    private Integer gravar(Integer id, CategoriaRequest request) {
        try {
            request.setNomeAbreviado(Definition.seNuloOuVazio(request.getNomeAbreviado(), request.getNome(), 20));
            request.setSigla(Definition.seNuloOuVazio(request.getSigla(), request.getNome(), 6));

            Validations.build(NOME).notEmpty().minLen(2).maxLen(30).check(request);
            Validations.build(NOME_ABREVIADO).minLen(2).maxLen(20).check(request);
            Validations.build(SIGLA).minLen(2).maxLen(6).check(request);

            CategoriaEntity entity = Optional.ofNullable(id).isPresent() ? repository.findById(id).
                    orElseThrow(() -> new RegistroNaoLocalizadoException(Entities.CATEGORIA_ENTITY, ID))
                    : new CategoriaEntity(requestInfo.getOrganizacao());

            checarIntegridadeOrganizacional(entity.getOrganizacao());
            BeanUtils.copyProperties(request, entity);
            entity.setLocaliza(normalizar(request.getNome()));
            repository.save(entity);
            return entity.getId();
        }catch (BusinessException ex){
            log.warn(BusinessException.logMessage(ex));
            throw ex;
        }catch (Exception ex){
            log.error(BusinessException.errorMessage("Não foi possível incluir ou alterar a categoria [ %s ]", request.getNome()), ex);
            throw new ErroNaoMapeadoException();
        }
    }
    public List<CategoriaResponse> consultar(String nome) {
        nome = normalizar(Objects.toString(nome, ""));
        List<CategoriaEntity> list = repository.findByOrganizacaoAndLocalizaContaining(requestInfo.getOrganizacao(), nome);
        List<CategoriaResponse> response = list.stream().map(this::convert).collect(Collectors.toList());
        if (response.isEmpty()) {
            throw new RegistroNaoLocalizadoException(Entities.CATEGORIA_ENTITY, NOME);
        }
        return response;

    }
    public CategoriaResponse buscar(Integer id){
        return repository.findById(id).map(this::convert).orElseThrow(()-> new RegistroNaoLocalizadoException(Entities.CATEGORIA_ENTITY, ID));
    }
    private CategoriaResponse convert(CategoriaEntity entity){
        CategoriaResponse response = new CategoriaResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
    public List<Associacao> listar(String nome) {
        return consultar(nome).stream().map(i -> {
            Associacao item = new Associacao();
            item.setId(i.getId());
            item.setIdentificador(i.getId());
            item.setDescricao(i.getNome());
            return item;
        }).collect(Collectors.toList());

    }
    
}
