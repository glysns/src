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

import digytal.desktop.components.desktop.ss.SSBotao;
import digytal.desktop.components.desktop.ss.SSGrade;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.components.desktop.ss.commons.SSBotaoAlterar;
import digytal.desktop.components.desktop.ss.commons.SSBotaoFechar;
import digytal.desktop.components.desktop.ss.commons.SSBotaoIncluir;
import digytal.desktop.components.enums.TipoOperacao;



public abstract  class FormularioConsulta extends Formulario {
	protected SSBotao bBuscar = new SSBotao();
	public SSBotaoFechar bFechar = new SSBotaoFechar();
	public SSBotaoIncluir bIncluir = new SSBotaoIncluir();
	public SSBotaoAlterar bAlterar = new SSBotaoAlterar();
	
	private SSGrade tabela = new SSGrade();
	private JScrollPane scroll;
	
	public FormularioConsulta() {
		super(new BorderLayout());
		//setSize(600, 600);
		scroll = new JScrollPane();
		scroll.setViewportView(tabela);
				
		this.tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		super.setAlinhamentoBotoes(FlowLayout.LEFT);
		conteudo.add(scroll,BorderLayout.CENTER);
		
		rodape.add(bIncluir);
		rodape.add(bAlterar);
		rodape.add(bFechar);
		
		bBuscar.setText("Buscar");
		bBuscar.setIcone("search");
		
		bBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filtrar();
			}
		});
		bFechar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
	}
	public void setScrollSize(int w, int h) {
		scroll.setPreferredSize(new Dimension(w, h));
	}
	public void fechar(ActionListener actionListener) {
		bFechar.addActionListener(actionListener);
	}
	protected GridBagConstraints getGbcBuscar(int x, int y) {
		GridBagConstraints gbc_bBuscar = new GridBagConstraints();
		gbc_bBuscar.insets = new Insets(0, 3, 3, 3);
		gbc_bBuscar.anchor = GridBagConstraints.SOUTHWEST;
		gbc_bBuscar.fill = GridBagConstraints.NONE;
		gbc_bBuscar.gridx = x;
		gbc_bBuscar.gridy = y;
		return gbc_bBuscar;
	}
	public SSGrade getTabela() {
		return tabela;
	}
	
	protected JScrollPane getScroll() {
		return scroll;
	}
	protected void alterar(FormularioCadastro formulario){
		visualizar(formulario,false);
	}
	protected void alterar(FormularioCadastro formulario, Object registro){
		visualizar(formulario,registro, false);
	}
	protected void visualizar(FormularioCadastro formulario){
		visualizar(formulario,true);
	}
	protected void visualizar(FormularioCadastro formulario,boolean somenteLeitura){
		Object registro = getTabela().getLinhaSelecionada();
		visualizar(formulario, registro, somenteLeitura);
	}
	protected void visualizar(FormularioCadastro formulario,Object registro,boolean somenteLeitura){
		if(registro==null){
			SSMensagem.avisa("Selecione um registro");
			return;
		}
		exibir(formulario, TipoOperacao.ALTERACAO, registro,somenteLeitura);
	}
	protected void incluir(FormularioCadastro formulario){
		exibir(formulario, TipoOperacao.INCLUSAO,null,false);
	}
	protected void exibir(FormularioCadastro formulario, TipoOperacao tipoOperacao, Object registro,boolean somenteLeitura){
		//formulario.setSomenteLeitura(somenteLeitura);
		formulario.setTipoOperacao(tipoOperacao);
		formulario.preExibir();
		formulario.exibirRegistro(registro);
	
		this.exibir(formulario);
		
	}
	
	protected void filtrar() {
		
	}
	public void ocultarAlterar() {
		bAlterar.setVisible(false);
	}
	protected void exibirRegistros(List lista) {
		if(lista==null || (lista!=null && lista.isEmpty()))
			SSMensagem.avisa("Nenhum registro encontrado");
		getTabela().setValue(lista);
	}
	
}
