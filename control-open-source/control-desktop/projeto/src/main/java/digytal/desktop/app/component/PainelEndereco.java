package digytal.desktop.app.component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;

import javax.swing.JPanel;

import digytal.desktop.app.context.Context;
import digytal.desktop.app.model.modulo.param.CodigoPostal;
import digytal.desktop.app.service.modulo.param.CepService;
import digytal.desktop.components.desktop.ss.SSCampoMascara;
import digytal.desktop.components.desktop.ss.SSCampoTexto;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.components.desktop.ss.evento.SSPesquisaEvento;
import digytal.desktop.components.desktop.ss.evento.SSPesquisaListener;


public class PainelEndereco extends JPanel {
	private SSCampoMascara cCep = new SSCampoMascara();
	private SSCampoTexto cPais = new SSCampoTexto();
	private SSCampoTexto cUf = new SSCampoTexto();
	private SSCampoTexto cComplemento = new SSCampoTexto();
	private SSCampoTexto cNumero = new SSCampoTexto();
	private SSCampoTexto cLogradouro = new SSCampoTexto();	
	private SSCampoTexto cCidade = new SSCampoTexto();
	private SSCampoMascara cTelefone = new SSCampoMascara();
	private SSCampoTexto cBairro = new SSCampoTexto();
	private SSCampoTexto cReferencia = new SSCampoTexto();	
	
	public PainelEndereco( ) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);		
		
		GridBagConstraints gbc_cCep = new GridBagConstraints();
		gbc_cCep.weightx = 0.1;
		gbc_cCep.insets = new Insets(3, 3, 0, 0);
		gbc_cCep.anchor = GridBagConstraints.NORTHWEST;
		gbc_cCep.gridx = 0;
		gbc_cCep.gridy = 0;
		add(cCep, gbc_cCep);

		GridBagConstraints gbc_pnlUsarComo = new GridBagConstraints();
		gbc_pnlUsarComo.insets = new Insets(20, 0, 0, 0);
		gbc_pnlUsarComo.gridwidth = 3;
		gbc_pnlUsarComo.anchor = GridBagConstraints.NORTHWEST;
		gbc_pnlUsarComo.fill = GridBagConstraints.HORIZONTAL;
		gbc_pnlUsarComo.gridx = 2;
		gbc_pnlUsarComo.gridy = 0;
		cBairro.setObrigatorio(true);

		GridBagConstraints gbc_cBairro = new GridBagConstraints();
		gbc_cBairro.gridwidth = 3;
		gbc_cBairro.anchor = GridBagConstraints.NORTHWEST;
		gbc_cBairro.insets = new Insets(3, 3, 0, 0);
		gbc_cBairro.fill = GridBagConstraints.HORIZONTAL;
		gbc_cBairro.gridx = 0;
		gbc_cBairro.gridy = 2;
		add(cBairro, gbc_cBairro);
		
		GridBagConstraints gbc_cTelefone = new GridBagConstraints();
		gbc_cTelefone.anchor = GridBagConstraints.NORTHWEST;
		gbc_cTelefone.insets = new Insets(3, 3, 0, 3);
		gbc_cTelefone.fill = GridBagConstraints.HORIZONTAL;
		gbc_cTelefone.gridx = 3;
		gbc_cTelefone.gridy = 2;
		add(cTelefone, gbc_cTelefone);
		
		GridBagConstraints gbc_cCIdade = new GridBagConstraints();
		gbc_cCIdade.gridwidth = 2;
		gbc_cCIdade.anchor = GridBagConstraints.NORTHWEST;
		gbc_cCIdade.weightx = 1.0;
		gbc_cCIdade.insets = new Insets(5, 5, 0, 0);
		gbc_cCIdade.fill = GridBagConstraints.HORIZONTAL;
		gbc_cCIdade.gridx = 0;
		gbc_cCIdade.gridy = 3;
		cCidade.setEditavel(false);
		add(cCidade, gbc_cCIdade);
		
		
		GridBagConstraints gbc_cReferencia = new GridBagConstraints();
		gbc_cReferencia.gridwidth = 4;
		gbc_cReferencia.anchor = GridBagConstraints.NORTHWEST;
		gbc_cReferencia.weighty = 1.0;
		gbc_cReferencia.weightx = 1.0;
		gbc_cReferencia.insets = new Insets(5, 5, 5, 5);
		gbc_cReferencia.fill = GridBagConstraints.HORIZONTAL;
		gbc_cReferencia.gridx = 0;
		gbc_cReferencia.gridy = 4;
		cReferencia.setRotulo("Referências");
		add(cReferencia, gbc_cReferencia);
		
		
		GridBagConstraints gbc_cNome = new GridBagConstraints();
		gbc_cNome.gridwidth = 2;
		gbc_cNome.anchor = GridBagConstraints.NORTHWEST;
		gbc_cNome.weightx = 1.0;
		gbc_cNome.insets = new Insets(3, 3, 0, 0);
		gbc_cNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_cNome.gridx = 0;
		gbc_cNome.gridy = 1;
		add(cLogradouro, gbc_cNome);
				GridBagConstraints gbc_cNumero = new GridBagConstraints();
		gbc_cNumero.anchor = GridBagConstraints.NORTHWEST;
		gbc_cNumero.insets = new Insets(3, 3, 0, 0);
		gbc_cNumero.fill = GridBagConstraints.HORIZONTAL;
		gbc_cNumero.gridx = 2;
		gbc_cNumero.gridy = 1;
		add(cNumero, gbc_cNumero);

		GridBagConstraints gbc_cComplemento = new GridBagConstraints();
		gbc_cComplemento.anchor = GridBagConstraints.NORTHWEST;
		gbc_cComplemento.insets = new Insets(3, 3, 0, 3);
		gbc_cComplemento.fill = GridBagConstraints.HORIZONTAL;
		gbc_cComplemento.gridx = 3;
		gbc_cComplemento.gridy = 1;
		add(cComplemento, gbc_cComplemento);

		GridBagConstraints gbc_cUf = new GridBagConstraints();
		gbc_cUf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cUf.anchor = GridBagConstraints.NORTHWEST;
		gbc_cUf.insets = new Insets(5, 5, 0, 0);
		gbc_cUf.gridx = 2;
		gbc_cUf.gridy = 3;
		add(cUf, gbc_cUf);
				
		GridBagConstraints gbc_cPais = new GridBagConstraints();
		gbc_cPais.insets = new Insets(5, 5, 0, 5);
		gbc_cPais.anchor = GridBagConstraints.NORTHEAST;
		gbc_cPais.fill = GridBagConstraints.HORIZONTAL;
		gbc_cPais.gridx = 3;
		gbc_cPais.gridy = 3;
		add(cPais, gbc_cPais);
		
		try {
			cCep.setMascara("##.###-###");
			cTelefone.setMascara("(##)####-####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		definirValorPadrao();
		definirEventos();
	}
	private void definirValorPadrao() {
		//cLocal.setItens(LocalEndereco.values(),"nome");
		cCep.setColunas(9);
		cComplemento.setColunas(12);
		cComplemento.setRotulo("Complemento");
		cNumero.setObrigatorio(true);
		cNumero.setRotulo("Número");

		cUf.setRotulo("UF");
		
		//cPais.setColunas(12);
		cPais.setRotulo("Pais");

		cLogradouro.setObrigatorio(true);
		cLogradouro.setRotulo("Logradouro");
		cCidade.setObrigatorio(true);
		cCidade.setRotulo("Cidade");

		cBairro.setRotulo("Bairro");
		cTelefone.setObrigatorio(true);
		//cTelefone.setColunas(12);
		cTelefone.setRotulo("Telefone");
		cCep.setObrigatorio(true);
		//cCep.setColunas(8);
		cCep.setRotulo("CEP");
		cCep.setPesquisa(true);
		cUf.setEditavel(false);
		cPais.setEditavel(false);
		cLogradouro.setEditavel(false);
		cBairro.setEditavel(false);
		cCidade.setEditavel(false,true);
	}
	
	private void definirEventos() {
		cCep.addPesquisaListener(new SSPesquisaListener() {
			@Override
			public void pesquisaListener(SSPesquisaEvento evento) {
				pesquisarCep();
			}
		});
	}
	private void pesquisarCep() {
		String cep = cCep.getClipText();
		if(cep==null || cep.isEmpty()) {
			SSMensagem.avisa("Digite o número do CEP");
			return;
		}
		CepService service = Context.getBean(CepService.class);
		CodigoPostal endereco = service.buscarCep(cCep.getClipText());
	
		cPais.setText(endereco.getPais());
		cLogradouro.setText(endereco.getLogradouro());
		cCep.setText(endereco.getCep());
		cComplemento.setText(endereco.getComplemento());
		cBairro.setText(endereco.getBairro());
		cCidade.setText(endereco.getLocalidade());
		cUf.setText(endereco.getUf());
		
	}
	public SSCampoTexto getcLogradouro() {
		return cLogradouro;
	}
	public SSCampoTexto getcNumero() {
		return cNumero;
	}
	public SSCampoTexto getcBairro() {
		return cBairro;
	}
	public SSCampoMascara getcCep() {
		return cCep;
	}
	public SSCampoTexto getcComplemento() {
		return cComplemento;
	}
	public SSCampoTexto getcCidade() {
		return cCidade;
	}
	public SSCampoTexto getcUf() {
		return cUf;
	}
	public SSCampoTexto getcPais() {
		return cPais;
	}
	public SSCampoMascara getcTelefone() {
		return cTelefone;
	}
	public SSCampoTexto getcReferencia() {
		return cReferencia;
	}
}