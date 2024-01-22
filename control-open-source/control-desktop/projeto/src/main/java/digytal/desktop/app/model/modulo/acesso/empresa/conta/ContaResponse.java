package digytal.desktop.app.model.modulo.acesso.empresa.conta;

import lombok.Data;

@Data
public class ContaResponse extends ContaRequest {
   private Integer id;
   private Integer empresa;
   private Double saldo;
}
