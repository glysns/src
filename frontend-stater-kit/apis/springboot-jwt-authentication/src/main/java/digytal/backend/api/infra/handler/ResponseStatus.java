package digytal.backend.api.infra.handler;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(name="ResponseStatus", description="Representação padrão do status das respostas HTTP disponíveis na API")
public class ResponseStatus {
    @Schema(description="Confirmação de sucesso da resposta da requisição", nullable = false,example = "true", allowableValues ={"true","false"} )
    boolean success;
    @Schema(description="Mensagem que detalha a resposta devolvida", nullable = false,example = "Operação realizada com sucesso" )
    String message;
    @Schema(description="Código de sucesso ou baseado ao dicionário de erros da aplicação", nullable = false,example = "200" )
    Serializable code;
    @Schema(description="Mensagem que representa uma sugestão em caso de erro na requisição", nullable = false,example = "O campo: Nome é obrigatório" )
    String suggestion;
}