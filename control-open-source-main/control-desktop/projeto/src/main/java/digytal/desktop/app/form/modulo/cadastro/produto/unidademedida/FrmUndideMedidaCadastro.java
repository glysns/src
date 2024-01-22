package digytal.desktop.app.form.modulo.cadastro.produto.unidademedida;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.desktop.app.model.modulo.cadastro.produto.unidademedida.UnidadeMedidaRequest;
import digytal.desktop.app.model.modulo.cadastro.produto.unidademedida.UnidadeMedidaResponse;
import digytal.desktop.app.service.modulo.cadastro.produto.UnidadeMedidaService;
import digytal.desktop.components.desktop.FormularioCadastro;
import digytal.desktop.components.desktop.ss.SSCampoNumero;
import digytal.desktop.components.desktop.ss.SSCampoSelecao;
import digytal.desktop.components.desktop.ss.SSCampoTexto;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.util.utils.business.BusinessException;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmUndideMedidaCadastro extends FormularioCadastro {
	private SSCampoTexto cNome = new SSCampoTexto();
	private SSCampoTexto cSigla = new SSCampoTexto();
	private SSCampoTexto cDescricao = new SSCampoTexto();
	private SSCampoNumero cConteudo = new SSCampoNumero();
	private SSCampoSelecao cEmbalagem = new SSCampoSelecao();
	@Autowired
	private UnidadeMedidaService service;
	private UnidadeMedidaResponse registro;
	private GridBagConstraints gbc_1;

	public FrmUndideMedidaCadastro() {
		super.setTitulo("Unidade Medida");
		super.setDescricao("Cadastro de Unidades de Medida");
		cNome.setRotulo("Nome");
		cSigla.setColunas(4);
		cSigla.setRotulo("Sigla");
		cDescricao.setRotulo("Descrição");
		cConteudo.setRotulo("Cont.");
		cEmbalagem.setRotulo("Embalagem");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 3;
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
		getConteudo().add(cConteudo, gbc);
		
		gbc = new GridBagConstraints();
		gbc.weightx = 0.6;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 2;
		gbc.gridy = 1;
		getConteudo().add(cEmbalagem, gbc);
		
		
		gbc_1 = new GridBagConstraints();
		gbc_1.gridwidth = 3;
		gbc_1.weightx = 0.6;
		gbc_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_1.insets = new Insets(5, 5, 5, 5);
		gbc_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_1.gridx = 0;
		gbc_1.gridy = 2;
		getConteudo().add(cDescricao, gbc_1);
	}

	@Override
	public void exibirRegistro(Object entidade) {
		if (entidade != null) {
			registro = (UnidadeMedidaResponse) entidade;
			cNome.setText(registro.getNome());
			cDescricao.setText(registro.getDescricao());
			cSigla.setText(registro.getSigla());
			cConteudo.setNumero(registro.getConteudo());
			cEmbalagem.setSelected(registro.isEmbalagem());
		}
	}
	@Override
	public void confirmar() {
		try {
			
			UnidadeMedidaRequest request = new UnidadeMedidaRequest();
			request.setNome(cNome.getText());
			request.setSigla(cSigla.getText());
			request.setDescricao(cDescricao.getText());
			request.setConteudo(cConteudo.getDouble());
			request.setEmbalagem(cEmbalagem.isSelected());
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
