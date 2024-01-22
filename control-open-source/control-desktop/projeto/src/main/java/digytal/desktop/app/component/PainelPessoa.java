package digytal.desktop.app.component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.EventListener;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import digytal.desktop.components.desktop.CampoImagem;
import digytal.desktop.components.desktop.ss.SSCampoDataHora;
import digytal.desktop.components.desktop.ss.SSCampoMascara;
import digytal.desktop.components.desktop.ss.SSCampoTexto;
import digytal.desktop.components.desktop.ss.util.SSFormatador;
import digytal.desktop.components.desktop.ss.util.SSTexto;
import digytal.desktop.components.desktop.ss.util.SSValidacao;

public class PainelPessoa extends JPanel {
	private PainelEndereco painelEnderecoContato = new PainelEndereco();
	private PainelContato painelContato = new PainelContato();
	
	private SSCampoMascara cCpfCnpj = new SSCampoMascara();
	private SSCampoTexto cTipo = new SSCampoTexto();
	private SSCampoTexto cRgIe = new SSCampoTexto();
	private SSCampoTexto cId = new SSCampoTexto();
	private SSCampoTexto cSobrenomeSocial = new SSCampoTexto();
	private SSCampoTexto cNomeFantasia = new SSCampoTexto();
	private SSCampoDataHora cAniversario = new SSCampoDataHora();
	
	private CampoImagem cImagem = new CampoImagem();
	
	public PainelPessoa() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		
		
		cCpfCnpj.setColunas(13);
		cCpfCnpj.setFormatarAoAplicar(true);
		cTipo.setColunas(7);
		cRgIe.setColunas(10);
		cAniversario.setColunas(6);
		GridBagConstraints gbc_cCpfCnpj = new GridBagConstraints();
		gbc_cCpfCnpj.weightx = 1.0;
		gbc_cCpfCnpj.anchor = GridBagConstraints.NORTHEAST;
		gbc_cCpfCnpj.insets = new Insets(3, 3, 0, 0);
		gbc_cCpfCnpj.fill = GridBagConstraints.HORIZONTAL;
		gbc_cCpfCnpj.gridx = 0;
		gbc_cCpfCnpj.gridy = 0;
		add(cCpfCnpj, gbc_cCpfCnpj);
		
		
		//cTipo.setColunas(10);
		
		GridBagConstraints gbc_cTipo = new GridBagConstraints();
		gbc_cTipo.weightx = 1.0;
		gbc_cTipo.anchor = GridBagConstraints.NORTHEAST;
		gbc_cTipo.insets = new Insets(3, 3, 0, 0);
		gbc_cTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_cTipo.gridx = 1;
		gbc_cTipo.gridy = 0;
		add(cTipo, gbc_cTipo);
		
		GridBagConstraints gbc_cRgIe = new GridBagConstraints();
		gbc_cRgIe.weightx = 1.0;
		gbc_cRgIe.anchor = GridBagConstraints.NORTHEAST;
		gbc_cRgIe.insets = new Insets(3, 3, 0, 0);
		gbc_cRgIe.fill = GridBagConstraints.HORIZONTAL;
		gbc_cRgIe.gridx = 2;
		gbc_cRgIe.gridy = 0;
		add(cRgIe, gbc_cRgIe);
		
		GridBagConstraints gbc_cId = new GridBagConstraints();
		gbc_cId.anchor = GridBagConstraints.NORTHEAST;
		gbc_cId.insets = new Insets(3, 3, 0, 0);
		gbc_cId.fill = GridBagConstraints.HORIZONTAL;
		gbc_cId.gridx = 3;
		gbc_cId.gridy = 0;
		cId.setEditavel(false);
		cId.setRotulo("ID");
		add(cId, gbc_cId);
		
		GridBagConstraints gbc_cImagen = new GridBagConstraints();
		gbc_cImagen.gridheight = 4;
		gbc_cImagen.anchor = GridBagConstraints.NORTHEAST;
		gbc_cImagen.insets = new Insets(3, 3, 0, 3);
		gbc_cImagen.fill = GridBagConstraints.BOTH;
		gbc_cImagen.gridx = 4;
		gbc_cImagen.gridy = 0;
		add(cImagem, gbc_cImagen);
		
		GridBagConstraints gbc_cNomeRazao = new GridBagConstraints();
		gbc_cNomeRazao.weightx = 1.0;
		gbc_cNomeRazao.anchor = GridBagConstraints.NORTHEAST;
		gbc_cNomeRazao.gridwidth = 3;
		gbc_cNomeRazao.insets = new Insets(3, 3, 0, 0);
		gbc_cNomeRazao.fill = GridBagConstraints.HORIZONTAL;
		gbc_cNomeRazao.gridx = 0;
		gbc_cNomeRazao.gridy = 2;
		add(cSobrenomeSocial, gbc_cNomeRazao);
		
		GridBagConstraints gbc_cNomeFantasia = new GridBagConstraints();
		gbc_cNomeFantasia.weightx = 1.0;
		gbc_cNomeFantasia.anchor = GridBagConstraints.NORTHEAST;
		gbc_cNomeFantasia.gridwidth = 4;
		gbc_cNomeFantasia.insets = new Insets(3, 3, 0, 0);
		gbc_cNomeFantasia.fill = GridBagConstraints.HORIZONTAL;
		gbc_cNomeFantasia.gridx = 0;
		gbc_cNomeFantasia.gridy = 1;
		add(cNomeFantasia, gbc_cNomeFantasia);
		
		GridBagConstraints gbc_cEmail = new GridBagConstraints();
		gbc_cEmail.gridwidth = 4;
		gbc_cEmail.weightx = 1.0;
		gbc_cEmail.anchor = GridBagConstraints.NORTHEAST;
		gbc_cEmail.insets = new Insets(3, 3, 3, 0);
		gbc_cEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_cEmail.gridx = 0;
		gbc_cEmail.gridy = 3;
		add(painelContato, gbc_cEmail);
		
		GridBagConstraints gbc_cAniversario = new GridBagConstraints();
		gbc_cAniversario.insets = new Insets(3, 3, 0, 0);
		gbc_cAniversario.anchor = GridBagConstraints.NORTHEAST;
		gbc_cAniversario.fill = GridBagConstraints.HORIZONTAL;
		gbc_cAniversario.gridx = 3;
		gbc_cAniversario.gridy = 2;
		add(cAniversario, gbc_cAniversario);
		
		JTabbedPane tEnderecos = new JTabbedPane();
		tEnderecos.addTab("Endereço", painelEnderecoContato);
		
		
		GridBagConstraints gbc_tEnderecos = new GridBagConstraints();
		gbc_tEnderecos.gridwidth = 5;
		gbc_tEnderecos.weighty = 1.0;
		gbc_tEnderecos.weightx = 1.0;
		gbc_tEnderecos.anchor = GridBagConstraints.NORTHEAST;
		gbc_tEnderecos.insets = new Insets(3, 3, 3, 3);
		gbc_tEnderecos.fill = GridBagConstraints.BOTH;
		gbc_tEnderecos.gridx = 0;
		gbc_tEnderecos.gridy = 4;
		add(tEnderecos, gbc_tEnderecos);
		
		cTipo.setEditavel(false);
		
		cTipo.setRotulo("Tipo");
		cCpfCnpj.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				try {
					String cpfCnpj = cCpfCnpj.getText();
					if (cpfCnpj != null && cpfCnpj.trim().length() > 0)
						cCpfCnpj.setText(SSTexto.retiraSeparadores(cpfCnpj));
				} catch (Exception el) {
					el.printStackTrace();
				}
			}

			public void focusLost(FocusEvent e) {
				formataCpfCnpj(cCpfCnpj.getText());
			}
		});
		
		cId.setRotulo("ID");
		cCpfCnpj.setRotulo("CPF  / CNPJ");
		cAniversario.setRotulo("Aniversário");
		cNomeFantasia.setRotulo("Primeiro Nome (nome fantasia)");
		cSobrenomeSocial.setRotulo("Sobrenome (razão social)");
		cRgIe.setRotulo("RG / I.E.");
		
		rotuloCpf();
		
	}
	
	private void rotuloCpf() {
		cTipo.setText("FISICA");
		
	}
	private void rotuloCnpj() {
		cTipo.setText("JURIDICA");
	}
	private void formataCpfCnpj(String texto) {
		rotuloCpf();
		if (!SSValidacao.nuloOuVazio(texto)) {
			String cpfCnpj = SSTexto.retiraSeparadores(texto);
			if(cpfCnpj.length()> 11)
				rotuloCnpj();
			else
				rotuloCpf();
			
			cCpfCnpj.setText(SSFormatador.formatarCpfCnpj(cpfCnpj));
		}
	}

	public PainelContato getPainelContato() {
		return painelContato;
	}
	public PainelEndereco getPainelEnderecoContato() {
		return painelEnderecoContato;
	}
	public SSCampoDataHora getcAniversario() {
		return cAniversario;
	}
	public SSCampoTexto getcTipo() {
		return cTipo;
	}
	public void setCpfCnpj(String cpfCnpj) {
		formataCpfCnpj(cpfCnpj);
	}
	public void setId(Object id) {
		cId.setValue(id);
		cCpfCnpj.setEditavel(false);
		painelContato.getcEmail().setEditavel(false);
	}
	public SSCampoMascara getcCpfCnpj() {
		return cCpfCnpj;
	}
	public SSCampoTexto getcNomeFantasia() {
		return cNomeFantasia;
	}
	public SSCampoTexto getcNomeRazao() {
		return cSobrenomeSocial;
	}
	public SSCampoTexto getcRgIe() {
		return cRgIe;
	}
	public SSCampoTexto getcId() {
		return cId;
	}
	
}
