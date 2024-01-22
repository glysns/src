package digytal.backend.api.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Configuration
@ConfigurationProperties(prefix = "cadastro")
@Data
public class CadastroProperties {
    private String nome;
    private String cpf;
    private String email;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataNascimento;
}
