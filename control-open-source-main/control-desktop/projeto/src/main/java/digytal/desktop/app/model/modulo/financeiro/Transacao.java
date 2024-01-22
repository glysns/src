package digytal.desktop.app.model.modulo.financeiro;

import lombok.Data;

@Data
public class Transacao {
	private String numeroDocumento;
	private String titulo;
	private String descricao;
	private String observacao;
}
