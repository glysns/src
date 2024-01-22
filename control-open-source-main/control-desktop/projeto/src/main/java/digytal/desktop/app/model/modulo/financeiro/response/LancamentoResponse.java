package digytal.desktop.app.model.modulo.financeiro.response;

import digytal.desktop.app.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import digytal.desktop.app.model.modulo.comum.Associacao;
import digytal.desktop.app.model.modulo.comum.MeioPagamento;
import digytal.desktop.app.model.modulo.comum.Participante;
import digytal.desktop.app.model.modulo.financeiro.RegistroData;
import lombok.Data;

@Data
public class LancamentoResponse {
	private Integer id;
	private String numeroDocumento;
	private String titulo;
	private String descricao;
	private AplicacaoTipo tipo;
	private RegistroData data;
	private Participante partes;
	private MeioPagamento meioPagamento;
	private String observacao;
	private Associacao cadastro;
}
