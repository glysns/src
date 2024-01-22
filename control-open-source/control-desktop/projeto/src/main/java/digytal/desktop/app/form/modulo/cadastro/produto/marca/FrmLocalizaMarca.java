package digytal.desktop.app.form.modulo.cadastro.produto.marca;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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

import digytal.desktop.app.model.modulo.cadastro.produto.ProdutoResponse;
import digytal.desktop.app.model.modulo.cadastro.produto.marca.MarcaResponse;
import digytal.desktop.app.model.modulo.comum.Associacao;
import digytal.desktop.app.service.modulo.cadastro.produto.MarcaService;
import digytal.desktop.components.desktop.FormularioLocaliza;
import digytal.desktop.components.desktop.ss.SSCampoSelecao;
import digytal.desktop.components.desktop.ss.SSCampoTexto;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.components.desktop.ss.SSPosicaoRotulo;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmLocalizaMarca extends FormularioLocaliza {
	
	private SSCampoTexto cNome = new SSCampoTexto();
	private SSCampoSelecao cInterno = new SSCampoSelecao();
	@Autowired
	private MarcaService service;
	
	public FrmLocalizaMarca() {
		init();
	}

	public void init() {
		super.setTitulo("Marcas");
		super.setDescricao("Localização de Marcas");
		
		
		cNome.setRotulo("Nome");
		cNome.setColunas(15);
		cNome.setRotuloPosicao(SSPosicaoRotulo.ESQUERDA);
		
		cInterno.setRotulo("Uso Interno?");
		cInterno.setRotuloPosicao(SSPosicaoRotulo.ESQUERDA);
		
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
		gbc_cInterno.fill = GridBagConstraints.HORIZONTAL;
		gbc_cInterno.insets = new Insets(5, 5, 5, 5);
		gbc_cInterno.anchor = GridBagConstraints.NORTHWEST;
		gbc_cInterno.gridx = 1;
		gbc_cInterno.gridy = 0;
		filtros.add(cInterno, gbc_cInterno);
		
		
		filtros.add(bBuscar, getGbcBuscar(2, 0));
		
		getConteudo().add(filtros,BorderLayout.NORTH);			
		getTabela().adicionarColuna(0, "ID", "id");
		getTabela().adicionarColuna(1, "Nome", "descricao");
		
		getTabela().definirLarguraColunas(30, 270);
		
		super.setAlinhamentoBotoes(FlowLayout.RIGHT);
		//super.setFiltros(pFiltros, 1, 0);
	
		bConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ok();
			}
		});
		
		
		bFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		
	}
	public void exibir(boolean comInternos) {
		cInterno.setSelected(comInternos);
	}
	private void ok() {
		Associacao entidade = (Associacao) getTabela().getLinhaSelecionada();
		fechar(entidade);
	}

	public void filtrar() {
		try {
			String nome = cNome.getText();
			if (nome.isEmpty()) {
				SSMensagem.avisa("Informe um nome para a pesquisa");
				return;
			}
			List<Associacao> lista = service.listar(nome);
			if (lista.size() == 0) {
				SSMensagem.informa("Não há bancos com este nome");
			}
			getTabela().setValue(lista);
		} catch (Exception e) {
			e.printStackTrace();
			SSMensagem.erro(e.getMessage());
		}

	}

}
