package digytal.desktop.app.service.modulo.financeiro;

import java.util.List;

import org.springframework.stereotype.Service;

import digytal.desktop.app.model.modulo.financeiro.request.ParcelaPagamentoRequest;
import digytal.desktop.app.model.modulo.financeiro.response.parcelamento.BoletoResponse;
import digytal.desktop.app.service.core.HttpClient;
import digytal.desktop.util.utils.business.BusinessException;
import digytal.desktop.util.utils.http.Response;

@Service
public class ParcelamentoService extends HttpClient {
	public ParcelamentoService() {
		super("parcelamentos");
	}

	public Response realizarCompensacao(Integer parcela, List<ParcelaPagamentoRequest> request)
			throws BusinessException {
		return post(request, "parcelas", parcela, "pagamento").body();
	}

	public BoletoResponse gerarBoleto(Integer parcela, Double valorBoleto) throws BusinessException {
		return patch(null, "parcelas", parcela, "valor", valorBoleto, "boleto").one(BoletoResponse.class);
	}
}
