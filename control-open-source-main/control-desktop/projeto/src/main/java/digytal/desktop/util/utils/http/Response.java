package digytal.desktop.util.utils.http;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Response<T> {

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dateTime = LocalDateTime.now();
	private ResponseStatus status;
	private T body;
	Response() {

	}
	public Response<T> list(Class type){
		this.body = (T) Mapper.mapList(body, type);
		return this;
	}
	public Response<T> one(Class type){
		this.body = (T) Mapper.mapObjet(body, type);
		return this;
	}
	public String text() {
		return body.toString();
	}
}