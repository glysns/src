package digytal.backend.api.infra.security;

import lombok.Data;

@Data
public class Session {
    private String username;
    private String token;
}
