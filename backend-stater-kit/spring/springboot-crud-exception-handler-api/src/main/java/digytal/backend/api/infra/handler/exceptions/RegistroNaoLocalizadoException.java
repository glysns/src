package digytal.backend.api.infra.handler.exceptions;

public class RegistroNaoLocalizadoException extends BusinessException{
    public RegistroNaoLocalizadoException(String entidade, String campo) {
        super("Não existe um(a) %s com o(a) %s informado(a) ", "100", "O registro deve existir previamente",entidade,campo);
    }

}
