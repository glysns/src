package digytal.desktop.app.model.modulo.financeiro.response;

import digytal.desktop.app.model.modulo.financeiro.response.parcelamento.ParcelamentoDetalhe;
import lombok.Data;

@Data
public class ParcelamentoResponse extends LancamentoResponse {
    private ParcelamentoDetalhe detalhe = new ParcelamentoDetalhe();
}