package com.digytal.control.service.modulo.acesso;

import com.digytal.control.infra.business.RegistroDuplicadoException;
import com.digytal.control.infra.business.RegistroNaoLocalizadoException;
import com.digytal.control.infra.commons.definition.Text;
import com.digytal.control.infra.commons.validation.Entities;
import com.digytal.control.infra.model.usuario.EmpresaSimplificadaResponse;
import com.digytal.control.infra.security.jwt.JwtCreator;
import com.digytal.control.infra.security.jwt.JwtObject;
import com.digytal.control.infra.security.jwt.SecurityConfig;
import com.digytal.control.infra.utils.Textos;
import com.digytal.control.model.modulo.acesso.empresa.EmpresaEntity;
import com.digytal.control.model.modulo.acesso.empresa.EmpresaIntegracao;
import com.digytal.control.model.modulo.acesso.empresa.EmpresaRequest;
import com.digytal.control.model.modulo.acesso.empresa.EmpresaResponse;
import com.digytal.control.model.comum.EntidadeCadastral;
import com.digytal.control.model.comum.endereco.Endereco;
import com.digytal.control.service.comum.CadastroFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.digytal.control.infra.commons.validation.Attributes.*;

@Service
public class EmpresaService extends CadastroFactory {
    public List<EmpresaSimplificadaResponse> listarUsuarioEmpresas(){
        return empresaRepository.listarEmpresas(requestInfo.getUsuario());
    }
    @Transactional
    public void alterarAsaasToken(String token){
        EmpresaEntity empresaEntity = buscarEntity(requestInfo.getEmpresa());
        empresaEntity.getIntegracao().setAsaasToken(token);
        empresaEntity.getIntegracao().setContaEmpresa(true);
        empresaRepository.save(empresaEntity);
    }
    @Transactional
    public void alterarAsaasWebhookToken(String webhookToken){
        EmpresaEntity empresaEntity = buscarEntity(requestInfo.getEmpresa());
        empresaEntity.getIntegracao().setAsaasWebhookToken(webhookToken);
        empresaRepository.save(empresaEntity);
    }
    @Transactional
    public void alterarAsaasTaxaBoleto(Double taxaEmissaoBoleto){
        EmpresaEntity empresaEntity = buscarEntity(requestInfo.getEmpresa());
        empresaEntity.getIntegracao().setAsaasTaxaEmissaoBoleto(taxaEmissaoBoleto);
        empresaRepository.save(empresaEntity);
    }

    private EmpresaEntity buscarEntity(Integer id){
        return  empresaRepository.findById(id).orElseThrow(()->new RegistroNaoLocalizadoException(Entities.EMPRESA_ENTITY, ID));
    }

    public EmpresaResponse buscar(Integer id){
        EmpresaEntity entity = buscarEntity(id);
        EmpresaResponse response = new EmpresaResponse();
        BeanUtils.copyProperties(entity,response);
        return response;
    }
    public String selecionarEmpresa(Integer empresa, String token){
        JwtObject tokenObject = JwtCreator.create(token, SecurityConfig.PREFIX, SecurityConfig.KEY);
        EmpresaEntity entity = empresaRepository.findById(empresa).orElseThrow(() -> new RegistroNaoLocalizadoException(Entities.EMPRESA_ENTITY, ID));
        tokenObject.empresa(entity.getId());
        tokenObject.organizacao(entity.getOrganizacao());
        tokenObject.valido(true);
        return JwtCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, tokenObject);
    }

    public Integer alterar(Integer id, EmpresaRequest request){
        return gravar(id,request);
    }
    public Integer incluir(EmpresaRequest request){
        return gravar(null,request);
    }
    @Transactional
    private Integer gravar(Integer id, EmpresaRequest request){
        String cpfCnpj = Text.onlyDigits(request.getCpfCnpj());

        EntidadeCadastral registroEntity = build(request);
        EmpresaEntity entity = new EmpresaEntity();

        if(id!=null) {
            entity = empresaRepository.findById(id).orElseThrow(() -> new RegistroNaoLocalizadoException(Entities.EMPRESA_ENTITY, ID));
            registroEntity.setOrganizacao(entity.getOrganizacao());
            checarIntegridadeOrganizacional(registroEntity.getOrganizacao());
        }else {
            if(empresaRepository.existsByCpfCnpj(request.getCpfCnpj()))
                throw new RegistroDuplicadoException(CPF_CNPJ, cpfCnpj);

            if(empresaRepository.existsByEmail(request.getEmail()))
                throw new RegistroDuplicadoException(EMAIL, request.getEmail());

            registroEntity.setOrganizacao(requestInfo.getOrganizacao());
        }
        BeanUtils.copyProperties(registroEntity, entity);
        entity.setCpfCnpj(cpfCnpj);
        entity.setIncompleto(false);
        empresaRepository.save(entity);
        return entity.getId();
    }
    public String gerarCabecalho(Integer empresa){
        EmpresaResponse response = buscar(empresa);
        StringBuilder cabecalho = new StringBuilder();
        cabecalho.append(response.getNomeFantasia() + "\n");
        cabecalho.append(String.format("%-40sIE.: %s\n", Textos.formatarCpfCnpj(response.getCpfCnpj()), response.getRgIe()));
        Endereco endereco = response.getEndereco();
        cabecalho.append(String.format("%s, %s - %s\n", endereco.getLogradouro(), endereco.getBairro(), endereco.getNumero()));
        cabecalho.append(String.format("%s - %s / %s\n", Textos.formatarCep(endereco.getCep()),  endereco.getCidade().getNome(), endereco.getCidade().getUf()));


        return cabecalho.toString();
    }
}
