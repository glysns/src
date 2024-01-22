package digytal.backend.api.infra.security.jwt;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import digytal.backend.api.infra.handler.ResponseFactory;
import digytal.backend.api.infra.handler.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JwtFilter extends OncePerRequestFilter {
    private static ObjectMapper mapper = new ObjectMapper();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //obtem o token da request com AUTHORIZATION
        String token =  request.getHeader(JwtFactory.HEADER_AUTHORIZATION);
        //esta implementação só esta validando a integridade do token
        try {
            if(token!=null && !token.isEmpty() && !token.trim().equals(JwtProperties.PREFIX)) {
                JwtObject tokenObject = JwtFactory.create(token,JwtProperties.PREFIX, JwtProperties.KEY);

                List<SimpleGrantedAuthority> authorities = authorities(tokenObject.getRoles());

                UsernamePasswordAuthenticationToken userToken =
                        new UsernamePasswordAuthenticationToken(
                                tokenObject.getSubject(),
                                null,
                                authorities);

                SecurityContextHolder.getContext().setAuthentication(userToken);
            }else {
                SecurityContextHolder.clearContext();
            }
            filterChain.doFilter(request, response);
        }catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException  e) {
            SecurityContextHolder.clearContext();
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json");
            String json = mapper.writeValueAsString(ResponseFactory.exception(new BusinessException("Erro de autenticação","403","Favor revise seus credenciais")));
            response.getWriter().write(json);
        }
    }
    private List<SimpleGrantedAuthority> authorities(List<String> roles){
        return roles.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    /**
     * String scheme = req.getScheme();             // http
     *     String serverName = req.getServerName();     // hostname.com
     *     int serverPort = req.getServerPort();        // 80
     *     String contextPath = req.getContextPath();   // /mywebapp
     *     String servletPath = req.getServletPath();   // /servlet/MyServlet
     *     String pathInfo = req.getPathInfo();         // /a/b;c=123
     *     String queryString = req.getQueryString();          // d=789
     */
}