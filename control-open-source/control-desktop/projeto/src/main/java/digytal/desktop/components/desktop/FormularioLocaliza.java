package digytal.desktop.components.desktop;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import digytal.desktop.components.desktop.ss.SSBotao;
import digytal.desktop.components.desktop.ss.SSGrade;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.components.desktop.ss.commons.SSBotaoAlterar;
import digytal.desktop.components.desktop.ss.commons.SSBotaoFechar;
import digytal.desktop.components.desktop.ss.commons.SSBotaoIncluir;
import digytal.desktop.components.enums.TipoOperacao;



public abstract  class FormularioLocaliza extends Formulario {
	protected SSBotao bBuscar = new SSBotao();
	protected SSBotao bConfirmar = new SSBotao();
	protected SSBotaoFechar bFechar = new SSBotaoFechar();
	
	private SSGrade tabela = new SSGrade();
	private JScrollPane scroll;
	
	public FormularioLocaliza() {
		super(new BorderLayout());
		setSize(600, 600);
		scroll = new JScrollPane();
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(300, 200));
		scroll.setViewportView(tabela);
				
		this.tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		super.setAlinhamentoBotoes(FlowLayout.LEFT);
		conteudo.add(scroll,BorderLayout.CENTER);
		
		rodape.add(bConfirmar);
		rodape.add(bFechar);
		
		bBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filtrar();
			}
		});
		
		bConfirmar.setText("OK");
		bFechar.setText("Fechar");
		bFechar.setIcone("close");
		
		bBuscar.setText("Buscar");
		bBuscar.setIcone("search");
	}
	
	public GridBagConstraints getGbcBuscar(int x, int y) {
		GridBagConstraints gbc_bBuscar = new GridBagConstraints();
		gbc_bBuscar.insets = new Insets(0, 3, 3, 3);
		gbc_bBuscar.anchor = GridBagConstraints.SOUTHWEST;
		gbc_bBuscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_bBuscar.gridx = x;
		gbc_bBuscar.gridy = y;
		return gbc_bBuscar;
	}
	public void setScrollSize(int w, int h) {
		scroll.setPreferredSize(new Dimension(w, h));
	}
	public SSGrade getTabela() {
		return tabela;
	}
	
	protected JScrollPane getScroll() {
		return scroll;
	}
	
	protected void filtrar() {
		
	}
	
	protected void exibirResultado(List lista) {
		if(lista==null || (lista!=null && lista.isEmpty()))
			SSMensagem.avisa("Nenhum registro encontrado");
		getTabela().setValue(lista);
	}
	
}
