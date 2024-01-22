package digytal.desktop.app.model.modulo.financeiro.response.parcelamento;

import lombok.Data;

@Data
public class BoletoResponse {
	private String bankSlipUrl;
    private String status;
    private String id;
    private String nossoNumero;
    private boolean deleted;
}
