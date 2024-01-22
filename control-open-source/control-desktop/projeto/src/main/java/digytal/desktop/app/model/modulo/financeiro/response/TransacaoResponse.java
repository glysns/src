package digytal.desktop.app.model.modulo.financeiro.response;

import digytal.desktop.app.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import digytal.desktop.app.model.modulo.comum.Associacao;
import digytal.desktop.app.model.modulo.financeiro.RegistroData;
import digytal.desktop.app.model.modulo.financeiro.Transacao;
import digytal.desktop.app.model.modulo.financeiro.TransacaoValor;
import lombok.Data;

@Data
public class TransacaoResponse extends Transacao {
	private Integer id;
	private TransacaoValor valor;
	private AplicacaoTipo tipo;
	private RegistroData data;
	private Associacao cadastro;
	private Associacao area;
	private Associacao natureza;
}
