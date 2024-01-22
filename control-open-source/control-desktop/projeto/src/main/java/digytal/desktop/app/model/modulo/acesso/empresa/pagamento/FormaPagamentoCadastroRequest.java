package digytal.desktop.app.model.modulo.acesso.empresa.pagamento;

import digytal.desktop.app.model.modulo.comum.MeioPagamento;
import lombok.Data;

@Data
public class FormaPagamentoCadastroRequest {
    private MeioPagamento meioPagamento;
    private Integer numeroParcelas;
    private Double taxa;
}
