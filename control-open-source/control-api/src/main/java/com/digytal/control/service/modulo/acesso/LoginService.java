package com.digytal.control.service.modulo.acesso;


import com.digytal.control.infra.business.login.LoginException;
import com.digytal.control.infra.business.login.SenhaExpiradaException;
import com.digytal.control.infra.business.login.UsuarioBloqueadoException;
import com.digytal.control.infra.commons.validation.Validations;
import com.digytal.control.infra.model.*;
import com.digytal.control.infra.model.usuario.UsuarioCadastroResponse;
import com.digytal.control.infra.model.usuario.UsuarioEmpresaResponse;
import com.digytal.control.infra.security.jwt.JwtCreator;
import com.digytal.control.infra.security.jwt.JwtObject;
import com.digytal.control.infra.security.jwt.SecurityConfig;
import com.digytal.control.infra.model.usuario.EmpresaSimplificadaResponse;
import com.digytal.control.model.modulo.acesso.usuario.UsuarioEntity;
import com.digytal.control.repository.modulo.acesso.UsuarioRepository;
import com.digytal.control.repository.modulo.acesso.empresa.EmpresaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

import static com.digytal.control.infra.commons.validation.Attributes.*;

@Service
public class LoginService {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private PasswordEncoder encoder;

    public SessaoResponse autenticar(LoginRequest login){
        Validations.build(USUARIO, SENHA).notEmpty().check(login);

        String username = login.getUsuario().toLowerCase();
        UsuarioEntity entity = repository.findByLogin(username);
        if(entity!=null){

            boolean passwordOk = encoder.matches(login.getSenha(), entity.getSenha());
            if (!passwordOk) {
                throw new LoginException();
            }
            if(entity.isExpirado())
                throw new SenhaExpiradaException();

            if(entity.isBloqueado())
                throw new UsuarioBloqueadoException();

            final int HORAS_EXPIRACAO = 4;

            Integer empresa = null;
            Integer organizacao = null;
            boolean valido=false;
            SessaoResponse session = new SessaoResponse();
            session.setInicioSessao(LocalDateTime.now());
            session.setFimSessao(session.getInicioSessao().plusHours(HORAS_EXPIRACAO));

            if(entity.getCadastro()!=null){
                UsuarioCadastroResponse response = new UsuarioCadastroResponse();
                BeanUtils.copyProperties(entity, response);
                response.setCadastro(entity.getCadastro());
                session.setUsuario(response);
                valido=true;
            }else{
                UsuarioEmpresaResponse response = new UsuarioEmpresaResponse();
                BeanUtils.copyProperties(entity, response);
                response.setEmpresas(empresaRepository.listarEmpresas(entity.getId()));
                session.setUsuario(response);
                if(response.getEmpresas()!=null && response.getEmpresas().size()==1){
                    EmpresaSimplificadaResponse emp = response.getEmpresas().get(0);
                    empresa = emp.getId();
                    organizacao = emp.getOrganizacao();
                    valido=true;
                }
            }
            JwtObject jwtObject = JwtObject.builder()
                    .usuario(entity.getId())
                    .empresa(empresa)
                    .organizacao(organizacao)
                    .valido(valido)
                    .subject(login.getUsuario())
                    .issuedAt()
                    .expirationHours(HORAS_EXPIRACAO)
                    .roles("ADMIN");


            session.setToken(JwtCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));

            return session;

        }else
            throw new LoginException();
    }
}
