package digytal.backend.api.webservice;

import digytal.backend.api.infra.handler.Response;
import digytal.backend.api.infra.handler.ResponseFactory;
import digytal.backend.api.infra.handler.exceptions.BusinessException;
import digytal.backend.api.infra.security.Login;
import digytal.backend.api.infra.security.Session;
import digytal.backend.api.infra.security.jwt.JwtFactory;
import digytal.backend.api.infra.security.jwt.JwtObject;
import digytal.backend.api.infra.security.jwt.JwtProperties;
import digytal.backend.api.model.usuario.UsuarioEntity;
import digytal.backend.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class LoginResource {

    public static final BusinessException USUARIO_INVALIDO_EXCEPTION = new BusinessException("Usuário Inválido","403","Confirma seu usuário e senha");
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;
    @PostMapping("/login")
    public Response login(@RequestBody Login login){
        UsuarioEntity entity = repository.findByLogin(login.getUsername());
        if(entity!=null ){
            Session session = new Session();
            session.setUsername(login.getUsername());

            boolean senhaValida = encoder.matches(login.getPassword(), entity.getSenha());

            if(!senhaValida)
                throw USUARIO_INVALIDO_EXCEPTION;

            JwtObject jwtObject = JwtObject.builder()
                    .subject(login.getUsername())
                    .issuedAt()
                    .expirationHours(4)
                    .roles("USER_ADMIN");

            session.setToken(JwtFactory.create(JwtProperties.PREFIX, JwtProperties.KEY, jwtObject));
            return ResponseFactory.ok(session,"Login realizado com sucesso");
        }else{
            throw USUARIO_INVALIDO_EXCEPTION;
        }
    }
}
