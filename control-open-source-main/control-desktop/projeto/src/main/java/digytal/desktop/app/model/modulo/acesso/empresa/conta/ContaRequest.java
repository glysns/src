package digytal.desktop.app.model.modulo.acesso.empresa.conta;

import lombok.Data;

@Data
public class ContaRequest {
    private String numero;
    private String agencia;
    private String legenda;
    private Integer banco;
    private boolean contaCredito;
    private ContaFatura fatura;
    private String chavePix;
    private String descricao;
}
