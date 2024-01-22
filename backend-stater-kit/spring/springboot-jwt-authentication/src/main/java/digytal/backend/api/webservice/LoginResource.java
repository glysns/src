package digytal.backend.api.webservice;

import digytal.backend.api.infra.handler.Response;
import digytal.backend.api.infra.handler.ResponseFactory;
import digytal.backend.api.infra.handler.exceptions.BusinessException;
import digytal.backend.api.infra.security.Login;
import digytal.backend.api.infra.security.Session;
import digytal.backend.api.infra.security.jwt.JwtFactory;
import digytal.backend.api.infra.security.jwt.JwtObject;
import digytal.backend.api.infra.security.jwt.JwtProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class LoginResource {
    @PostMapping("/login")
    public Response login(@RequestBody Login login){
        if(login.getUsername().equals("gleyson") && login.getPassword().equals("Jwt@123") ){
            Session session = new Session();
            session.setUsername(login.getUsername());

            JwtObject jwtObject = JwtObject.builder()
                    .subject(login.getUsername())
                    .issuedAt()
                    .expirationHours(4)
                    .roles("USER_ADMIN");

            session.setToken(JwtFactory.create(JwtProperties.PREFIX, JwtProperties.KEY, jwtObject));
            return ResponseFactory.ok(session,"Login realizado com sucesso");
        }else{
            throw new BusinessException("Usuário Inválido","403","Confirma seu usuário e senha");
        }
    }
}
