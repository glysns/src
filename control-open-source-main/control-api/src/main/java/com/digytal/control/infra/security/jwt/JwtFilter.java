package com.digytal.control.infra.security.jwt;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.digytal.control.infra.business.login.TokenInvalidoException;
import com.digytal.control.infra.config.RequestInfo;
import com.digytal.control.infra.http.response.ResponseFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.web.util.UrlPathHelper;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final ObjectMapper mapper = new ObjectMapper();
    private final UrlPathHelper urlHelper = new UrlPathHelper();

    @Autowired
    private RequestInfo requestInfo;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //obtem o token da request com AUTHORIZATION
        String token =  request.getHeader(JwtCreator.HEADER_AUTHORIZATION);

        //esta implementação só esta validando a integridade do token
        try {
            if(token!=null && !token.isEmpty() && !token.trim().equals("Bearer")) {
                JwtObject tokenObject = JwtCreator.create(token,SecurityConfig.PREFIX, SecurityConfig.KEY);
                if(!tokenObject.isValido() && (!request.getRequestURI().contains("public")))
                    throw new UnsupportedJwtException("Este token não possui as credencias necessárias");

                List<SimpleGrantedAuthority> authorities = authorities(tokenObject.getRoles());

                UsernamePasswordAuthenticationToken userToken =
                        new UsernamePasswordAuthenticationToken(
                                tokenObject.getSubject(),
                                null,
                                authorities);

                if(tokenObject.isValido()) {
                    SecurityContextHolder.getContext().setAuthentication(userToken);
                    requestInfo.setUsuario(tokenObject.getUsuario());
                    requestInfo.setEmpresa(tokenObject.getEmpresa());
                    requestInfo.setOrganizacao(tokenObject.getOrganizacao());
                }else
                    throw new UnsupportedJwtException("");

            }else {
                SecurityContextHolder.clearContext();
            }
            filterChain.doFilter(request, response);
        }catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException  e) {
            SecurityContextHolder.clearContext();
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json");
            String json = mapper.writeValueAsString(ResponseFactory.exception(new TokenInvalidoException()));
            //String json = mapper.writeValueAsString(new TokenInvalidoException());
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