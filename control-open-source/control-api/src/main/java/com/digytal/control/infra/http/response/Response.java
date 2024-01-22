package com.digytal.control.infra.http.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(name="Resposta da requisição", description="Representação padrão do conteúdo das respostas HTTP disponíveis na API")
public class Response<T> {
	ResponseStatus status;
	@Schema(description="Corpo da resposta da requisição que pode ser uma lista, um objeto ou um elemento", nullable = false,example = "{\"id\":1,\"nome\":\"ADMINISTRADOR\"}" )
	T body;
	Response() {
		
	}

}
