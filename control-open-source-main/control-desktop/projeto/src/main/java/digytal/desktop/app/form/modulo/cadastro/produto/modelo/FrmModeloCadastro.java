package digytal.desktop.app.form.modulo.cadastro.produto.modelo;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.desktop.app.model.modulo.cadastro.produto.modelo.ModeloRequest;
import digytal.desktop.app.model.modulo.cadastro.produto.modelo.ModeloResponse;
import digytal.desktop.app.service.modulo.cadastro.produto.ModeloService;
import digytal.desktop.components.desktop.FormularioCadastro;
import digytal.desktop.components.desktop.ss.SSCampoTexto;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.util.utils.business.BusinessException;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmModeloCadastro extends FormularioCadastro {
	private SSCampoTexto cNome = new SSCampoTexto();
	private SSCampoTexto cSigla = new SSCampoTexto();
	private SSCampoTexto cNomeAbreviado = new SSCampoTexto();
	@Autowired
	private ModeloService service;
	private ModeloResponse registro;

	public FrmModeloCadastro() {
		super.setTitulo("Modelo");
		super.setDescricao("Cadastro de Modelos");
		cNome.setRotulo("Nome Completo");
		cSigla.setColunas(4);
		cSigla.setRotulo("Sigla");
		cNomeAbreviado.setRotulo("Nome Abreviado");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		getConteudo().add(cNome, gbc);
		
		gbc = new GridBagConstraints();
		gbc.weightx = 0.4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 0);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 1;
		getConteudo().add(cSigla, gbc);
		
		gbc = new GridBagConstraints();
		gbc.weightx = 0.6;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 1;
		gbc.gridy = 1;
	}

	@Override
	public void exibirRegistro(Object entidade) {
		if (entidade != null) {
			registro = (ModeloResponse) entidade;
			cNome.setText(registro.getNome());
			cNomeAbreviado.setText(registro.getNomeAbreviado());
			cSigla.setText(registro.getSigla());
		}
	}
	@Override
	public void confirmar() {
		try {
			
			ModeloRequest request = new ModeloRequest();
			request.setNome(cNome.getText());
			request.setSigla(cSigla.getText());
			request.setNomeAbreviado(cNomeAbreviado.getText());
			
			if(registro==null) 
				service.incluir(request);
			else
				service.alterar(registro.getId(), request);
			
			SSMensagem.informa("Cadastro realizado com sucesso");
			fechar();

		} catch (BusinessException e) {
			SSMensagem.erro(e.getMessage());
		}

	}


}
