package digytal.backend.api.infra.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import digytal.backend.api.infra.handler.exceptions.TokenInvalidoException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessDeniedEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(403);
        ObjectMapper mapper = new ObjectMapper();
        res.getWriter().write(mapper.writeValueAsString(ResponseFactory.exception(new TokenInvalidoException())));
    }
}
//expirado
//Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkaWd5dGFsIiwiaWF0IjoxNjgyMTcyOTk1LCJleHAiOjE2ODIxODczOTUsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUl9BRE1JTiJdfQ.pSSkmUmVHtzNLCR7xn71SpnhxLRKa8jffWAI4Xj1r710u06zoe-Bb5GPRZ5eXA0zQPj7oRq6n2KzfgIPrGp-Ww
