package digytal.desktop.app.form.modulo.acesso.empresa;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.desktop.app.model.modulo.acesso.empresa.aplicacao.AplicacaoRequest;
import digytal.desktop.app.model.modulo.acesso.empresa.aplicacao.AplicacaoResponse;
import digytal.desktop.app.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import digytal.desktop.app.service.modulo.acesso.AplicacaoService;
import digytal.desktop.components.desktop.FormularioCadastro;
import digytal.desktop.components.desktop.ss.SSCampoTexto;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.util.utils.business.BusinessException;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmAplicaoCadastro extends FormularioCadastro {
	private AplicacaoTipo tipo;
	private SSCampoTexto cNome = new SSCampoTexto();
	@Autowired
	private AplicacaoService service;
	private AplicacaoResponse registro;

	public FrmAplicaoCadastro() {
		super.setTitulo("Centro de Custo");
		super.setDescricao("Manutenção de Centro de Custo");
		cNome.setRotulo("Nome");

		GridBagConstraints gbc_cNome = new GridBagConstraints();
		gbc_cNome.weightx = 1.0;
		gbc_cNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_cNome.insets = new Insets(5, 5, 5, 5);
		gbc_cNome.anchor = GridBagConstraints.NORTHWEST;
		gbc_cNome.gridx = 0;
		gbc_cNome.gridy = 0;
		getConteudo().add(cNome, gbc_cNome);
		cNome.setTudoMaiusculo(false);
	}

	public void inicializar(AplicacaoTipo tipo) {
		this.tipo = tipo;
		if (tipo == AplicacaoTipo.DESPESA) {
			super.setTitulo("Despesas");
			super.setDescricao("Manutenção de Tipos de Despesas");
		} else if (tipo == AplicacaoTipo.RECEITA) {
			super.setTitulo("Receitas");
			super.setDescricao("Manutenção de Tipos de Receitas");
		}
	}

	@Override
	public void exibirRegistro(Object entidade) {
		if (entidade != null) {
			registro = (AplicacaoResponse) entidade;
			cNome.setText(registro.getNome());
		}

	}

	@Override
	public void confirmar() {
		try {
			if (registro != null) {
				service.alterarNome(registro.getId(), cNome.getText());
			} else {
				AplicacaoRequest request = new AplicacaoRequest();
				request.setNome(cNome.getText());

				if (tipo == AplicacaoTipo.DESPESA)
					service.incluirDespesa(request);
				else if (tipo == AplicacaoTipo.RECEITA)
					service.incluirReceita(request);
				else
					service.incluirArea(request);

			}
			SSMensagem.informa("Cadastro realizado com sucesso");
			fechar();

		} catch (BusinessException e) {
			SSMensagem.erro(e.getMessage());
		}

	}

}
