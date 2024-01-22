package digytal.backend.api.infra.handler;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name="Response", description="Representação padrão do conteúdo das respostas HTTP disponíveis na API")
public class Response<T> {
    @Schema(description="Data\\Hora da resposta", nullable = false,example = "2022-06-30 16:10:21")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime dateTime = LocalDateTime.now();
    ResponseStatus status;
    @Schema(description="Corpo da resposta da requisição que pode ser uma lista, um objeto ou um elemento", nullable = false,example = "{\"id\":1,\"nome\":\"ADMINISTRADOR\"}" )
    T body;
    Response() {

    }

}