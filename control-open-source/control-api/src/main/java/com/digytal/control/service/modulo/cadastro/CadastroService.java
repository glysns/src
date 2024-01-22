package com.digytal.control.service.modulo.cadastro;

import com.digytal.control.infra.business.RegistroDuplicadoException;
import com.digytal.control.infra.business.RegistroIncompativelException;
import com.digytal.control.infra.business.RegistroNaoLocalizadoException;
import com.digytal.control.infra.commons.definition.Definition;
import com.digytal.control.infra.commons.definition.Text;
import com.digytal.control.infra.commons.validation.Entities;
import com.digytal.control.model.comum.cadastramento.CadastroPerfil;
import com.digytal.control.model.comum.cadastramento.CadastroTipo;
import com.digytal.control.model.modulo.cadastro.CadastroEntity;
import com.digytal.control.model.modulo.cadastro.CadastroResponse;
import com.digytal.control.model.comum.EntidadeCadastral;
import com.digytal.control.model.modulo.cadastro.CadastroRequest;
import com.digytal.control.repository.modulo.cadastro.CadastroRepository;
import com.digytal.control.service.comum.CadastroFactory;
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
public class CadastroService extends CadastroFactory {
    @Autowired
    private CadastroRepository repository;
    public Integer alterar(Integer id, CadastroRequest request){
        return gravar(id,request);
    }
    public Integer incluir(CadastroRequest request){
        return gravar(null,request);
    }
    @Transactional
    private Integer gravar(Integer id, CadastroRequest request){
        CadastroPerfil perfil = request.getPerfil();
        if(!perfil.isCliente() && !perfil.isFornecedor())
            throw new RegistroIncompativelException("Para concluir o cadastro é necessário definí-lo como Cliente ou Fornecedor");

        request.setCpfCnpj(Definition.seNuloOuVazio(request.getCpfCnpj(), CPF_PADRAO));
        request.setEmail(Definition.seNuloOuVazio(request.getEmail(), EMAIL_PADRAO));

        EntidadeCadastral registroEntity = build(request);

        CadastroEntity entity = Optional.ofNullable(id).isPresent() ? repository.findById(id).
                orElseThrow(() -> new RegistroNaoLocalizadoException(Entities.CATEGORIA_ENTITY, ID))
                : new CadastroEntity(requestInfo.getOrganizacao());
        if (id==null){
            if(!request.getCpfCnpj().equals(CPF_PADRAO) && repository.existsByCpfCnpjAndOrganizacao(request.getCpfCnpj(), requestInfo.getOrganizacao()))
                throw new RegistroDuplicadoException(CPF_CNPJ, request.getCpfCnpj());

            if(!request.getEmail().equals(EMAIL_PADRAO) && repository.existsByEmailAndOrganizacao(request.getEmail(), requestInfo.getOrganizacao()))
                throw new RegistroDuplicadoException(EMAIL, request.getEmail());

            entity.setIncompleto(CPF_PADRAO.equals(request.getCpfCnpj()) || EMAIL_PADRAO.equals(request.getEmail()) || CEP_PADRAO.equals(request.getEndereco().getCep()));
        }else{
            registroEntity.setEmail(entity.getEmail());
            registroEntity.setCpfCnpj(entity.getCpfCnpj());
        }
        checarIntegridadeOrganizacional(entity.getOrganizacao());
        BeanUtils.copyProperties(registroEntity, entity);
        BeanUtils.copyProperties(request.getPerfil(), entity.getPerfil());
        entity.setLocaliza(normalizar(registroEntity.getNomeFantasia()));

        repository.save(entity);
        return entity.getId();
    }
    public List<CadastroResponse> consultar(CadastroTipo tipo, String nome){
        nome = Objects.toString(nome,"").toUpperCase();
        CadastroPerfil perfil = definirPerfil(tipo);
        List<CadastroResponse> response = repository.consultar(requestInfo.getOrganizacao(), perfil.isCliente(), perfil.isFornecedor(), nome).stream().map(this::convert).collect(Collectors.toList());
        return response;
    }
    public CadastroResponse buscar(String cpfCnpj){
        cpfCnpj = Text.onlyDigits(cpfCnpj);
        return repository.findByOrganizacaoAndCpfCnpj(requestInfo.getOrganizacao(), cpfCnpj).map(this::convert).orElse(null);
    }
    private CadastroResponse convert(CadastroEntity entity){
        CadastroResponse response = new CadastroResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
    private CadastroPerfil definirPerfil(CadastroTipo tipo){
        CadastroPerfil perfil = new CadastroPerfil();
        perfil.setFornecedor(true);
        perfil.setCliente(true);
        if(tipo==CadastroTipo.FORNECEDOR)
            perfil.setCliente(false);
        if(tipo==CadastroTipo.CLIENTE)
            perfil.setFornecedor(false);
        return perfil;
    }
}
