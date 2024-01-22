package digytal.backend.api.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mpc")
@Data
public class MercadoPagoCredencial {
    private String accessToken;
    private String clientId;
    private String clientSecret;
    private String publicKey;
    private String planId;
}
