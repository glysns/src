package digytal.backend.api.infra.security.jwt;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class JwtObject {
    private String subject; //nome do usuario
    private LocalDateTime issuedAt; //data de criação do token
    private LocalDateTime expiration; // data de expiração do token
    private List<String> roles; //perfis de acesso

    private static JwtObject build=null;
    public static JwtObject builder(){
        build = new JwtObject();
        return build;
    }
    public String getSubject() {
        return subject;
    }

    public JwtObject subject(String subject) {
        this.build.subject = subject;
        return this;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public JwtObject issuedAt(LocalDateTime issuedAt) {
        this.build.issuedAt = issuedAt;
        return this;
    }
    public JwtObject issuedAt() {
        this.build.issuedAt = LocalDateTime.now();
        return this;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public JwtObject expirationHours(long hours) {
        this.build.expiration = this.build.issuedAt.plusHours(hours);
        return this;
    }
    public JwtObject expiration(LocalDateTime expiration) {
        this.build.expiration = expiration;
        return this;
    }

    public List<String> getRoles() {
        return roles;
    }

    public JwtObject roles(List<String> roles) {
        this.build.roles = roles;
        return this;
    }

    public JwtObject roles(String... roles){
        this.build.roles = Arrays.asList(roles);
        return this;
    }
}