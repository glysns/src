package com.digytal.control.service.comum;
import com.digytal.control.infra.business.*;
import com.digytal.control.infra.commons.definition.Definition;
import com.digytal.control.infra.commons.definition.Text;
import com.digytal.control.infra.email.MessageTemplate;
import com.digytal.control.infra.email.SendEmail;
import com.digytal.control.infra.model.CredenciamentoResponse;
import com.digytal.control.infra.model.TipoLogin;
import com.digytal.control.model.modulo.acesso.usuario.UsuarioEntity;
import com.digytal.control.model.comum.EntidadeCadastral;
import com.digytal.control.model.comum.cadastramento.CadastroCompletoRequest;
import com.digytal.control.model.comum.cadastramento.CadastroSimplificadoRequest;
import com.digytal.control.model.comum.endereco.Endereco;
import com.digytal.control.repository.modulo.acesso.OrganizacaoRepository;
import com.digytal.control.repository.modulo.acesso.UsuarioRepository;
import com.digytal.control.repository.modulo.acesso.empresa.EmpresaRepository;
import com.digytal.control.service.modulo.param.CepService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.digytal.control.infra.commons.definition.Definitions;
import com.digytal.control.infra.commons.validation.Validation;
import com.digytal.control.infra.commons.validation.Validations;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

import static com.digytal.control.infra.commons.validation.Attributes.*;

@Service
public class CadastroFactory extends AbstractService {
    @Autowired
    protected EmpresaRepository empresaRepository;
    @Autowired
    protected OrganizacaoRepository organizacaoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CepService cepService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private SendEmail sendEmail;
    @Autowired
    private MessageTemplate template;
    public static String CEP_PADRAO = "99999999";
    public static String CPF_PADRAO = "99999999999";
    public static String EMAIL_PADRAO = "email@email.com";
    public EntidadeCadastral build(CadastroSimplificadoRequest request){
        CadastroCompletoRequest cadastro = new CadastroCompletoRequest();
        cadastro.setEmail(request.getEmail());
        cadastro.setNomeFantasia(request.getNomeFantasia());
        cadastro.setSobrenomeSocial(request.getSobrenomeSocial());
        Endereco endereco = cepService.obterEndereco(CEP_PADRAO);
        cadastro.setEndereco(endereco);
        cadastro.getTelefone().setCelular(99999999999L);
        EntidadeCadastral entity = build(cadastro);
        entity.setIncompleto(true);
        return entity;
    }

    public EntidadeCadastral build(CadastroCompletoRequest request){
        Validations.build(NOME_FANTASIA).notEmpty().check(request);
        request.setSobrenomeSocial(Definition.seNuloOuVazio(request.getSobrenomeSocial(), request.getNomeFantasia()));

        Validations.build(NOME_FANTASIA).rangeLen(2,80).check(request);
        Validations.build(SOBRENOME_SOCIAL).rangeLen(2,80).check(request);

        if(Validation.isNotEmpty(request.getEmail())) {
            Validations.build(EMAIL).rangeLen(10, 80).check(request);
            if(!Validation.email(request.getEmail()))
                throw new EmailInvalidoException();
        }

        if(Validation.isNotEmpty(request.getCpfCnpj())) {
            if (!request.getCpfCnpj().equals(CPF_PADRAO) && !Validation.cpfCnpj(request.getCpfCnpj()))
                throw new CpfCnpjInvalidoException();
        }

        if(request.getEndereco()!=null && Validation.isNotEmpty(request.getEndereco().getCep())) {
            Definitions.build().onlyDigits(CEP).define(request.getEndereco());
            Validations.build(NUMERO).maxLen(8).check(request.getEndereco());
        }else
            request.setEndereco(enderecoPadrao());

        Definitions.build().lowerCase(EMAIL).define(request);

        Endereco endereco = cepService.obterEndereco(request.getEndereco().getCep());

        endereco.setLogradouro(Definition.seNuloOuVazio(request.getEndereco().getLogradouro(), endereco.getLogradouro(), 100));
        endereco.setBairro(Definition.seNuloOuVazio(request.getEndereco().getBairro(), endereco.getBairro(), 80));
        endereco.setComplemento(Definition.seNuloOuVazio(request.getEndereco().getComplemento(), endereco.getComplemento(), 40));
        endereco.setReferencia(Definition.seNuloOuVazio(request.getEndereco().getReferencia(),"",100));
        endereco.setNumero(Definition.seNuloOuVazio(request.getEndereco().getNumero(),"SN",8));
        endereco.setTelefone(request.getEndereco().getTelefone());

        EntidadeCadastral entity = new EntidadeCadastral();

        BeanUtils.copyProperties(request,entity);
        BeanUtils.copyProperties(endereco,entity.getEndereco());
        BeanUtils.copyProperties(endereco.getCidade(),entity.getEndereco().getCidade());
        if(request.getTelefone()!=null)
            BeanUtils.copyProperties(request.getTelefone(),entity.getTelefone());

        entity.setAniversario(request.getAniversario());
        entity.setAtividadeComecialProfissional(request.getAtividadeComecialProfissional());
        entity.setOrganizacao(requestInfo.getOrganizacao());
        return entity;
    }

    protected CredenciamentoResponse cadastrarUsuario(EntidadeCadastral entity, TipoLogin tipoLogin){

        String login = tipoLogin == TipoLogin.CPF_CNPJ? entity.getCpfCnpj():entity.getEmail();
        if(usuarioRepository.existsByLogin(login))
            throw new RegistroDuplicadoException(LOGIN, login);

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        String senhaTemporaria =  UUID.randomUUID().toString().substring(0, 8);
        usuarioEntity.setSenha(encoder.encode(senhaTemporaria));
        usuarioEntity.setExpirado(true);
        usuarioEntity.setNome(Text.maxLength(entity.getNomeFantasia(), 50) );
        usuarioEntity.setSobrenome(Text.maxLength(entity.getSobrenomeSocial(),50));
        usuarioEntity.setDocumento(Objects.toString(entity.getCpfCnpj(),""));
        usuarioEntity.setEmail(entity.getEmail());
        usuarioEntity.setLogin(login);

        Integer entityId = entity.getId();
        if(tipoLogin == TipoLogin.CPF_CNPJ){
            usuarioEntity.setEmpresas(Collections.singletonList(entityId));
        }else{
            usuarioEntity.setCadastro(entityId);
        }

        System.out.println(senhaTemporaria);
        usuarioRepository.save(usuarioEntity);

        if(usuarioEntity.getId()!=null) {
            Long expiracao = LocalDateTime.now().plusHours(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            try {
                sendEmail.send(template.novoAcessoTitulo(), usuarioEntity.getNome(), usuarioEntity.getEmail(), usuarioEntity.getId(), senhaTemporaria, expiracao, usuarioEntity.getLogin());
            } catch (Exception e) {
                throw new BusinessException("Erro ao tentar enviar o e-mail com o credenciamento do usuário");
            }

            CredenciamentoResponse credencial = new CredenciamentoResponse();
            credencial.setExpiracao(expiracao);
            credencial.setToken(senhaTemporaria);
            credencial.setLogin(usuarioEntity.getLogin());
            credencial.setUsuario(usuarioEntity.getId());
            credencial.setNome(usuarioEntity.getNome());

            return credencial;
        }else
            throw new BusinessException("Erro ao tentar criar sua conta, contecte o suporte");
    }
    public static Endereco enderecoPadrao(){
        Endereco endereco = new Endereco();
        endereco.setCep(CEP_PADRAO);
        return endereco;
    }
}
