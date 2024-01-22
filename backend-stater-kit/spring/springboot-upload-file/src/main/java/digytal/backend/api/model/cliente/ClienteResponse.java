package digytal.backend.api.model.cliente;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ClienteResponse extends ClienteRequest{
    @Schema(description="ID gerado pelo banco de dados", example = "1")
    private Integer id;
}
