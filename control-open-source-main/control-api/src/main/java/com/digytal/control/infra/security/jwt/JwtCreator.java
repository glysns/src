package com.digytal.control.infra.security.jwt;

import java.util.List;
import java.util.stream.Collectors;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JwtCreator {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String ROLES_AUTHORITIES = "authorities";
    public static final String USER_ID="usuario";
    public static final String EMPRESA_ID="empresa";
    public static final String ORGANIZACAO_ID="organizacao";
    public static final String VALIDO="valido";
    public static String create(String prefix,String key, JwtObject jwtObject) {
        String token = Jwts.builder().setSubject(jwtObject.getSubject()).setIssuedAt(java.sql.Timestamp.valueOf(jwtObject.getIssuedAt())).setExpiration(java.sql.Timestamp.valueOf(jwtObject.getExpiration()))
                .claim(ROLES_AUTHORITIES, checkRoles(jwtObject.getRoles())).signWith(SignatureAlgorithm.HS512, key)
                .claim(USER_ID, jwtObject.getUsuario())
                .claim(EMPRESA_ID, jwtObject.getEmpresa())
                .claim(ORGANIZACAO_ID, jwtObject.getOrganizacao())
                .claim(VALIDO, jwtObject.isValido())
                .compact();

        return token;
    }
    public static JwtObject create(String token, String prefix, String key)
            throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException {
        JwtObject object = JwtObject.builder();
        token = token.replace(prefix, "");
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        object.subject(claims.getSubject());
        object.usuario(getParam(claims.get(USER_ID)));
        object.empresa(getParam(claims.get(EMPRESA_ID)));
        object.organizacao(getParam(claims.get(ORGANIZACAO_ID)));
        object.valido(claims.get(VALIDO)==null ? false : Boolean.parseBoolean( claims.get(VALIDO).toString() ) );
        object.expiration(new java.sql.Timestamp(claims.getExpiration().getTime()).toLocalDateTime() );
        object.issuedAt(new java.sql.Timestamp(claims.getIssuedAt().getTime()).toLocalDateTime() );
        object.roles((List) claims.get(ROLES_AUTHORITIES));
        return object;
    }
    private static Integer getParam(Object value){
        return value==null? null : Integer.valueOf(value.toString());
    }
    private static List<String> checkRoles(List<String> roles) {
        return roles.stream().map(s -> "ROLE_".concat(s.replaceAll("ROLE_",""))).collect(Collectors.toList());
    }
}