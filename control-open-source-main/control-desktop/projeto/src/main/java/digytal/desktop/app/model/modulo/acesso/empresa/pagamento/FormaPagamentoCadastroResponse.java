package digytal.desktop.app.model.modulo.acesso.empresa.pagamento;

import lombok.Data;

@Data
public class FormaPagamentoCadastroResponse extends FormaPagamentoCadastroRequest {
    private Integer id;
    private Integer empresa;
    private Integer conta;
    
    public String getDescricao() {
    	return String.format("%d +%.1f%%", getNumeroParcelas(), getTaxa() );
    }
}
