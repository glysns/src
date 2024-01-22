package digytal.desktop.app.form.modulo.param;

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

import digytal.desktop.app.model.modulo.param.BancoResponse;
import digytal.desktop.app.service.modulo.acesso.EmpresaService;
import digytal.desktop.app.service.modulo.param.BancoService;
import digytal.desktop.components.desktop.FormularioLocaliza;
import digytal.desktop.components.desktop.ss.SSCampoTexto;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.components.desktop.ss.SSPosicaoRotulo;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmLocalizaBanco extends FormularioLocaliza {
	
	private SSCampoTexto cNome = new SSCampoTexto();
	
	@Autowired
	private BancoService service;
	
	public FrmLocalizaBanco() {
		init();
	}

	public void init() {
		super.setTitulo("Clientes");
		super.setDescricao("Localiza Clientes");
		
		
		cNome.setRotulo("Nome");
		cNome.setColunas(35);
		cNome.setRotuloPosicao(SSPosicaoRotulo.ESQUERDA);
		
		JPanel filtros = new JPanel(new GridBagLayout());
		GridBagConstraints gbc_cNome = new GridBagConstraints();
		gbc_cNome.weightx = 1.0;
		gbc_cNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_cNome.insets = new Insets(3, 3, 0, 0);
		gbc_cNome.anchor = GridBagConstraints.NORTHWEST;
		gbc_cNome.gridx = 0;
		gbc_cNome.gridy = 0;
		filtros.add(cNome, gbc_cNome);
		
		filtros.add(bBuscar, getGbcBuscar(1, 0));
		getConteudo().add(filtros,BorderLayout.NORTH);
	
			
		getTabela().adicionarColuna(0, "COMPE id", "compe");
		getTabela().adicionarColuna(1, "Legenda", "legenda");
		
		getTabela().definirLarguraColunas(60, 220);
		
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
	
	private void ok() {
		BancoResponse entidade = (BancoResponse) getTabela().getLinhaSelecionada();
		fechar(entidade);
	}

	public void filtrar() {
		try {
			String nome = cNome.getText();
			if (nome.isEmpty()) {
				SSMensagem.avisa("Informe um nome para a pesquisa");
				return;
			}
			List<BancoResponse> lista = service.listar(nome);
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
