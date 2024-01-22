package digytal.desktop.app.form.modulo.acesso.empresa;

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

import digytal.desktop.app.context.Context;
import digytal.desktop.app.model.modulo.acesso.empresa.aplicacao.AplicacaoResponse;
import digytal.desktop.app.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import digytal.desktop.app.model.modulo.cadastro.CadastroResponse;
import digytal.desktop.app.service.modulo.acesso.AplicacaoService;
import digytal.desktop.components.desktop.FormularioConsulta;
import digytal.desktop.components.desktop.ss.SSCampoTexto;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.components.desktop.ss.SSPosicaoRotulo;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmAplicacaoConsulta extends FormularioConsulta {
	@Autowired
	private AplicacaoService service;
	private SSCampoTexto cNome = new SSCampoTexto();
	private AplicacaoTipo tipo;
	public FrmAplicacaoConsulta() {
		super.setTitulo("Centro de Custo");
		super.setDescricao("Manutenção de Centro de Custo");
		
		
		cNome.setRotulo("Nome");
		cNome.setRotuloPosicao(SSPosicaoRotulo.ESQUERDA);
		//cNome.setColunas(20);
		
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
		
		getTabela().adicionarColuna(0, "ID", "id");
		getTabela().adicionarColuna(1, "Nome", "nome");
		
		getTabela().definirLarguraColunas(40, 210);
		super.setAlinhamentoBotoes(FlowLayout.RIGHT);
		
		bIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluir(iniciarFormulario());
			}
		});
		
		bAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AplicacaoResponse registro = (AplicacaoResponse) getTabela().getLinhaSelecionada();
				alterar(iniciarFormulario(), registro);
			}
		});
	}
	private FrmAplicaoCadastro iniciarFormulario() {
		FrmAplicaoCadastro frm = Context.getBean(FrmAplicaoCadastro.class);
		frm.inicializar(tipo);
		return frm;
	}
	public void inicializar(AplicacaoTipo tipo) {
		this.tipo = tipo;
		if(tipo==AplicacaoTipo.DESPESA) {
			super.setTitulo("Despesas");
			super.setDescricao("Manutenção de Tipos de Despesas");
		}else if(tipo==AplicacaoTipo.RECEITA) {
			super.setTitulo("Receitas");
			super.setDescricao("Manutenção de Tipos de Receitas");
		}
	}
	public void filtrar() {
		try {
			String nome = cNome.getText();
			List<AplicacaoResponse> registros = null;
			if(tipo==AplicacaoTipo.RECEITA)
				registros= service.listarReceitas(nome);
			else if(tipo==AplicacaoTipo.DESPESA)
				registros= service.listarDespesas(nome);
			else
				registros= service.listarAreas(nome);
			
			exibirRegistros(registros);
		} catch (Exception e) {
			e.printStackTrace();
			SSMensagem.erro(e.getMessage());
		}
	}
}
