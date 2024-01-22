package digytal.backend.api.infra.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.time.LocalDateTime;

@Schema(name="Response", description="Representação padrão do conteúdo das respostas HTTP disponíveis na API")
public class Response<E> {
    @Schema(description="Data\\Hora da resposta", nullable = false,example = "2022-06-30 16:10:21")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime dateTime = LocalDateTime.now();
    @Schema(description="Confirmação de sucesso da resposta da requisição", nullable = false,example = "true", allowableValues ={"true","false"} )
    boolean success;
    @Schema(description="Mensagem que detalha a resposta devolvida", nullable = false,example = "Operação realizada com sucesso" )
    String message;
    @Schema(description="Código de sucesso ou baseado ao dicionário de erros da aplicação", nullable = false,example = "200" )
    Serializable code;
    @Schema(description="Corpo da resposta da requisição que pode ser uma lista, um objeto ou um elemento", nullable = false,example = "{\"id\":1,\"nome\":\"ADMINISTRADOR\"}" )
    E body;
    @Schema(description="Mensagem que representa uma sugestão em caso de erro na requisição", nullable = false,example = "O campo: Nome é obrigatório" )
    String suggestion;
    Response() {

    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code.toString();
    }

    public E getBody() {
        return body;
    }

    public String getSuggestion() {
        return suggestion;
    }

}
