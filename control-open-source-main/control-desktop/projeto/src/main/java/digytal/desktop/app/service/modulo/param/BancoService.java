package digytal.desktop.app.service.modulo.param;

import java.util.List;

import org.springframework.stereotype.Service;

import digytal.desktop.app.model.modulo.param.BancoResponse;
import digytal.desktop.app.service.core.HttpClient;


@Service
public class BancoService extends HttpClient {
	public BancoService() {
        super("bancos");
    }
	public List<BancoResponse> listar(String nome ){
        return get(nome).list(BancoResponse.class);
    }
    
}
