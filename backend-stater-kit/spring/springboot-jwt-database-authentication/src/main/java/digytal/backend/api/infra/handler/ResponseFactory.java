package digytal.backend.api.infra.handler;

import digytal.backend.api.infra.handler.exceptions.BusinessException;
import digytal.backend.api.infra.handler.exceptions.RegistroNaoLocalizadoException;
import org.springframework.http.HttpStatus;
import java.io.Serializable;
import java.util.Optional;

public class ResponseFactory {
    public static Response okOrNotFound(Object value) {
        return okOrNotFound(value, "Registro localizado com sucesso");
    }
    public static Response okOrNotFound(Object value, String message) {
        RegistroNaoLocalizadoException exception = new RegistroNaoLocalizadoException("Registro","Campo");
        Optional.ofNullable(value).orElseThrow(() -> exception );
        return ok(value,message) ;
    }
    public static Response ok(Object body) {
        return ok(body,"Consulta realizada com sucesso");
    }
    public static Response ok(Object body, String message) {
        return response(HttpStatus.OK.value(), body,message);
    }
    public static Response create(Object body, String message) {
        return response(HttpStatus.CREATED.value(), body,message);
    }
    private static Response response(Serializable code, Object body, String message) {
        Response response = new Response();

        response.body = body;
        response.code =code;
        response.message =message;
        response.success = true;
        response.suggestion = "";

        return response;
    }

    public static Response error() {
        return error("Error","Contacte o Suporte Técnico");
    }
    public static Response exception(BusinessException be) {
        return error(be.getErrorCode(), be.getMessage(),be.getSuggestion());
    }
    public static Response error(String message, String suggestion) {
        return error(500,message,suggestion);
    }
    public static Response error(Serializable code, String message, String suggestion) {
        Response response = new Response();
        response.code =code;
        response.message =message;
        response.success = false;
        response.suggestion = suggestion;
        return response;
    }
}