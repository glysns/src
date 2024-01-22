package digytal.backend.api.infra.handler;

import digytal.backend.api.infra.handler.exceptions.BusinessException;
import org.springframework.cglib.proxy.UndeclaredThrowableException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.annotation.Resource;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {
    @Resource
    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> handleGeneral(Exception e, WebRequest request) {
        String message = "";
        if (e.getClass().isAssignableFrom(UndeclaredThrowableException.class)) {
            UndeclaredThrowableException exception = (UndeclaredThrowableException) e;
            Class<? extends Throwable> exceptionClass = exception.getUndeclaredThrowable().getClass();
            return handleBusinessException((BusinessException) exception.getUndeclaredThrowable(), request);
        } else {

            Response error = ResponseFactory.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro não catalogado", "Contacte o administrador do sistema");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return handleExceptionInternal(e, error, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
        }

    }
    @ExceptionHandler({BusinessException.class})
    private ResponseEntity<Object> handleBusinessException(BusinessException be, WebRequest request) {

        Response error = ResponseFactory.error(be.getErrorCode(), be.getMessage(), be.getSuggestion());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity response = handleExceptionInternal(be, error, headers, HttpStatus.CONFLICT, request);

        return response;
    }
}
