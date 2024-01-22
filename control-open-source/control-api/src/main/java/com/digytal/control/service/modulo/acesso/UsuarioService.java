package com.digytal.control.service.modulo.acesso;

import com.digytal.control.infra.business.BusinessException;
import com.digytal.control.infra.business.CampoObrigatorioException;
import com.digytal.control.infra.business.RegistroNaoLocalizadoException;
import com.digytal.control.infra.business.TamanhoMinimoException;
import com.digytal.control.infra.business.login.DefinicaoSenhaException;
import com.digytal.control.infra.business.login.LoginException;
import com.digytal.control.infra.business.login.TokenInvalidoException;
import com.digytal.control.infra.business.login.UsuarioBloqueadoException;
import com.digytal.control.infra.commons.validation.Entities;
import com.digytal.control.infra.commons.validation.Validation;
import com.digytal.control.infra.email.Message;
import com.digytal.control.infra.email.MessageTemplate;
import com.digytal.control.infra.email.SendEmail;
import com.digytal.control.infra.model.CredenciamentoResponse;
import com.digytal.control.infra.model.LoginRequest;
import com.digytal.control.infra.model.SessaoResponse;
import com.digytal.control.model.modulo.acesso.usuario.SenhaAlteracaoRequest;
import com.digytal.control.model.modulo.acesso.usuario.UsuarioEntity;
import com.digytal.control.repository.modulo.acesso.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import static com.digytal.control.infra.commons.validation.Attributes.LOGIN_EMAIL;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    private MessageTemplate template;

    @Autowired
    private LoginService loginService;

    public CredenciamentoResponse solicitarNovaSenha(String login) {
        UsuarioEntity entity = repository.findByLogin(login);
        if(entity==null)
            throw new RegistroNaoLocalizadoException(Entities.USUARIO_ENTITY, LOGIN_EMAIL);

        CredenciamentoResponse response = solicitarNovaSenha(entity.getId());
        return response;
    }

    @Transactional
    public CredenciamentoResponse solicitarNovaSenha(Integer id){
        try {
            UsuarioEntity entity = repository.findById(id).orElseThrow(()-> new RegistroNaoLocalizadoException(Entities.USUARIO_ENTITY, LOGIN_EMAIL));
            String senhaTemporaria =  UUID.randomUUID().toString().substring(0, 8);
            entity.setSenha(encoder.encode(senhaTemporaria));
            entity.setExpirado(true);
            repository.save(entity);

            sendEmail.sendResetSenha(template.redefinicaoSenhaTitulo(), entity.getNome(), entity.getEmail(), entity.getId(), senhaTemporaria );

            Long expiracao = LocalDateTime.now().plusHours(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            CredenciamentoResponse credencial = new CredenciamentoResponse();
            credencial.setExpiracao(expiracao);
            credencial.setToken(senhaTemporaria);
            credencial.setLogin(entity.getLogin());
            credencial.setUsuario(entity.getId());
            credencial.setNome(entity.getNome());
            return credencial;

        } catch (Exception e) {
            throw new BusinessException("Erro ao tentar criar sua conta, contecte o suporte");
        }
    }

    @Transactional
    public SessaoResponse alterarSenha(Long expiracao, SenhaAlteracaoRequest request) {
        LocalDateTime dataExpiracao = Instant.ofEpochMilli(expiracao).atZone(ZoneId.systemDefault()).toLocalDateTime();

        if(LocalDateTime.now().isAfter(dataExpiracao))
            throw new TokenInvalidoException();

        if (Validation.isEmpty(request.getUsuario()))
            throw new CampoObrigatorioException("ID do usuario");

        if (Validation.isEmpty(request.getSenhaAtual()))
            throw new CampoObrigatorioException("Senha Atual");

        if (Validation.isEmpty(request.getNovaSenha()))
            throw new CampoObrigatorioException("Nova Senha");

        if (Validation.isEmpty(request.getNovaSenhaConfirmacao()))
            throw new CampoObrigatorioException("Senha Confirmação");

        if (!Validation.minLength(request.getNovaSenha(), 8))
            throw new TamanhoMinimoException("Nova Senha", 8);

        if (!request.getNovaSenha().equals(request.getNovaSenhaConfirmacao()))
            throw DefinicaoSenhaException.equal();

        if (request.getNovaSenha().equals(request.getSenhaAtual()))
            throw DefinicaoSenhaException.notEqual();


        UsuarioEntity entity = repository.findById(request.getUsuario()).orElseThrow(()-> new RegistroNaoLocalizadoException(Entities.USUARIO_ENTITY, LOGIN_EMAIL));
        if (entity.isBloqueado())
            throw new UsuarioBloqueadoException();

        boolean passwordOk = encoder.matches(request.getSenhaAtual(), entity.getSenha());
        if (!passwordOk) {
            throw new LoginException();
        }
        entity.setExpirado(false);
        entity.setSenha(criptografarSenha(request.getNovaSenha()));
        repository.save(entity);

        Message m = new Message();
        m.setTo(entity.getEmail());
        m.setTitle(template.alteracaoSenhaTitulo());
        m.setBody(template.alteracaoSenhaMensagem(entity.getNome()));
        sendEmail.send(m);

        LoginRequest login = new LoginRequest();
        login.setUsuario(entity.getLogin());
        login.setSenha(request.getNovaSenha());
        SessaoResponse sessaoResponse = loginService.autenticar(login);
        return sessaoResponse;
    }
    private String criptografarSenha(String senha){
        //criptografar a senha
        String password = encoder.encode(senha);
        return password;
    }

}
