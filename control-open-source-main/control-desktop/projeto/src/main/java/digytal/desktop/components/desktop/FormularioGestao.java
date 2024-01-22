package digytal.desktop.components.desktop;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import digytal.desktop.components.desktop.ss.SSBotao;
import digytal.desktop.components.desktop.ss.SSGrade;
import digytal.desktop.components.desktop.ss.SSMensagem;

public abstract  class FormularioGestao extends Formulario {
	protected SSGrade tabela = new SSGrade();
	protected JPanel pFiltros = new JPanel();
	private SSBotao bFiltrar = new SSBotao();
	private SSBotao bFechar = new SSBotao();
	private SSBotao bImprimir = new SSBotao();
	private SSBotao bExportar = new SSBotao();
	protected JScrollPane scroll = new JScrollPane();
	protected JPanel pSubtotal = new JPanel();
	
	public FormularioGestao() {
		super(new BorderLayout());
		pFiltros.setLayout(new GridBagLayout());
		conteudo.add(pFiltros,BorderLayout.NORTH);
		scroll.setViewportView(tabela);
			
		this.tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		super.setAlinhamentoBotoes(FlowLayout.LEFT);
		conteudo.add(scroll,BorderLayout.CENTER);
		conteudo.add(pSubtotal,BorderLayout.SOUTH);
		
		bFiltrar.addActionListener(new ActionListener() {
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
		bFiltrar.setText("Buscar");
		bFiltrar.setIcone("search");
		
		bFechar.setText("Fechar");
		bFechar.setIcone("close");
		
		//bImprimir.setVisible(false);
		bImprimir.setText("Imprimir");
		bImprimir.setIcone("print");
		
		//bExportar.setVisible(false);
		bExportar.setText("Exportar");
		bExportar.setIcone("xls");
		rodape.add(bImprimir);
		rodape.add(bExportar);
		rodape.add(bFechar);
		pSubtotal.setVisible(false);
		pSubtotal.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lConta = new JLabel();
		lConta.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
		pSubtotal.add(lConta);
		
	}
	public void setSubtotalTitulo(String titulo) {
		pSubtotal.setVisible(true);
		pSubtotal.setBorder(new TitledBorder(titulo));
	}
	public void setSubtotalLegenda(String legenda) {
		((JLabel) pSubtotal.getComponent(0)).setText(legenda);
	}

	public void setScrollSize(int w, int h) {
		scroll.setPreferredSize(new Dimension(w, h));
	}
	protected void imprimirListener (ActionListener listener) {
		bImprimir.addActionListener(listener);
		bImprimir.setVisible(true);
	}
	protected void exportarListener (ActionListener listener) {
		bExportar.addActionListener(listener);
		bExportar.setVisible(true);
	}
	protected SSBotao getbFiltrar() {
		return bFiltrar;
	}
	protected SSBotao getbFechar() {
		return bFechar;
	}
	public abstract void filtrar();
	protected void imprimir(byte[] file, String nome) {
		try {
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
			String horario = formatter.format(now);
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int option = fileChooser.showDialog(null,"Selecione o diretório");
	
			if (option == JFileChooser.APPROVE_OPTION) {
			    File f = fileChooser.getSelectedFile();
			    if (!f.isDirectory()) {
			        f = f.getParentFile();
			    }
			    Path path = Paths.get(f.getAbsolutePath(), String.format("%s_%s.pdf",nome,horario ));
			    Files.write(path, file,StandardOpenOption.CREATE);
			    SSMensagem.informa("Relatório gerado com sucesso em: " + f.getAbsolutePath() +"\\" + path.getFileName());
			}
		}catch (Exception e) {
			SSMensagem.erro("Erro na tentativa de gerar o relatório");
		}
	}
}

