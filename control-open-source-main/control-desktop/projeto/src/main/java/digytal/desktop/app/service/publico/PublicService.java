package digytal.desktop.app.service.publico;

import org.springframework.stereotype.Service;

import digytal.desktop.app.model.modulo.acesso.CredenciamentoResponse;
import digytal.desktop.app.model.modulo.acesso.usuario.SenhaAlteracaoRequest;
import digytal.desktop.app.model.modulo.comum.cadastramento.CadastroSimplificadoRequest;
import digytal.desktop.app.service.core.HttpClient;
import digytal.desktop.components.model.Login;
import digytal.desktop.components.model.Sessao;
import digytal.desktop.util.utils.business.BusinessException;
import digytal.desktop.util.utils.http.Response;

@Service
public class PublicService extends HttpClient {
	//https://www.baeldung.com/java-string-interpolation
	public PublicService() {
        super("/public");
	}

	public Response logar(Login login) throws BusinessException {
	    return post(login,"/login").body().one(Sessao.class);
	}
	public Response selecionarEmpresa(Integer empresa) throws BusinessException {
	    return get("empresas","selecao", empresa).body();
	}
	public Response solicitarNovaSenha(String login) throws BusinessException {
	    return patch(login,"solicitacao-nova-senha/login", login).body().one(CredenciamentoResponse.class);
	}
	public Response alterarSenha(Long expiracao, SenhaAlteracaoRequest request) throws BusinessException {
        return patch(request, "/alteracao-senha", expiracao).body().one(Sessao.class);
    }
	public Response realizarPrimeiroAcesso(String cpfCnpj, CadastroSimplificadoRequest request) throws BusinessException {
        return post(request,"empresa","primeiro-acesso",cpfCnpj).body().one(CredenciamentoResponse.class);
    }
	
	
}
