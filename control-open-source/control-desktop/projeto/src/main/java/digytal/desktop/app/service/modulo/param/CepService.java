package digytal.desktop.app.service.modulo.param;

import digytal.desktop.app.model.modulo.param.CodigoPostal;
import digytal.desktop.app.service.core.HttpClient;
import digytal.desktop.util.utils.business.BusinessException;

import org.springframework.stereotype.Service;

@Service
public class CepService extends HttpClient {
    public CepService() {
        super("ceps");
    }

    public CodigoPostal buscarCep(String cep ) throws BusinessException {
        return get(cep).one(CodigoPostal.class);
    }
}
