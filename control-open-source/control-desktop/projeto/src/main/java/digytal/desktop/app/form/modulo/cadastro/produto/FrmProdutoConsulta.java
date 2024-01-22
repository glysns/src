package digytal.desktop.app.form.modulo.cadastro.produto;


import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.desktop.app.context.Context;
import digytal.desktop.app.model.modulo.cadastro.produto.ProdutoResponse;
import digytal.desktop.app.service.modulo.cadastro.produto.ProdutoService;
import digytal.desktop.components.Formato;
import digytal.desktop.components.desktop.FormularioConsulta;
import digytal.desktop.components.desktop.ss.SSCampoSelecao;
import digytal.desktop.components.desktop.ss.SSCampoTexto;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.components.desktop.ss.SSPosicaoRotulo;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmProdutoConsulta extends FormularioConsulta {
	@Autowired
	private ProdutoService service;
	private SSCampoTexto cNome = new SSCampoTexto();
	private SSCampoSelecao cInterno = new SSCampoSelecao();
	public FrmProdutoConsulta() {
		setTitulo("Produtos e Serviços");
		setDescricao("Listagem de Produtos e Serviços");
		
		
		
		JPanel filtros = new JPanel(new GridBagLayout());
		GridBagConstraints gbc_cNome = new GridBagConstraints();
		gbc_cNome.weightx = 1.0;
		gbc_cNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_cNome.insets = new Insets(5, 5, 5, 0);
		gbc_cNome.anchor = GridBagConstraints.NORTHWEST;
		gbc_cNome.gridx = 0;
		gbc_cNome.gridy = 0;
		filtros.add(cNome, gbc_cNome);
		
		GridBagConstraints gbc_cInterno = new GridBagConstraints();
		gbc_cInterno.fill = GridBagConstraints.NONE;
		gbc_cInterno.insets = new Insets(5, 5, 5, 5);
		gbc_cInterno.anchor = GridBagConstraints.NORTHWEST;
		gbc_cInterno.gridx = 1;
		gbc_cInterno.gridy = 0;
		filtros.add(cInterno, gbc_cInterno);
		
		
		filtros.add(bBuscar, getGbcBuscar(2, 0));
		
		getConteudo().add(filtros,BorderLayout.NORTH);
		
		getTabela().adicionarColuna(0, "ID", "id");
		getTabela().adicionarColuna(1, "Nome", "nome");
		getTabela().adicionarColuna(2, "UND", "unidadeMedida");
		getTabela().adicionarColuna(3, "Serviço", "servico");
		getTabela().adicionarColuna(4, "R$ Valor", "valor");
		getTabela().adicionarColuna(5, "Interno?", "interno");
		
		getTabela().getModeloColuna().setFormato(4, Formato.MOEDA);
		getTabela().definirLarguraColunas(30, 280, 40, 70, 70,70);
		
		bIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluir(Context.getBean(FrmProdutoCadastro.class));
			}
		});
		
		bAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alterar(Context.getBean(FrmProdutoCadastro.class));
			}
		});
		setScrollSize(600, 400);
		definir();
	}
	private void definir() {
		cNome.setRotulo("Nome");
		cNome.setRotuloPosicao(SSPosicaoRotulo.ESQUERDA);
		cNome.setColunas(15);
		cInterno.setRotulo("Uso Interno?");
		cInterno.setRotuloPosicao(SSPosicaoRotulo.ESQUERDA);
	}
	public void filtrar() {
		try {
			String nome = cNome.getText();
			if (nome.isEmpty()) {
				SSMensagem.avisa("Informe um nome do produto para a pesquisa");
				return;
			}
			List<ProdutoResponse> lista = service.consultar(nome);
			exibirRegistros(lista);
		} catch (Exception e) {
			e.printStackTrace();
			SSMensagem.erro(e.getMessage());
		}
	}
}
