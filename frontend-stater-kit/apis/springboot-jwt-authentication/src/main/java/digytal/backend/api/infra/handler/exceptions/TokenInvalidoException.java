package digytal.backend.api.infra.handler.exceptions;

public class TokenInvalidoException extends BusinessException{
    public TokenInvalidoException() {
        super("Token Inválido ou Expirado","403","Realize uma nova autenticaçao");
    }
}
