package digytal.desktop.app.form.modulo.cadastro.cadastro;

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
import digytal.desktop.app.model.modulo.cadastro.CadastroResponse;
import digytal.desktop.app.model.modulo.comum.cadastramento.CadastroTipo;
import digytal.desktop.app.service.modulo.cadastro.CadastroService;
import digytal.desktop.components.desktop.FormularioConsulta;
import digytal.desktop.components.desktop.ss.SSCaixaCombinacao;
import digytal.desktop.components.desktop.ss.SSCampoTexto;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.components.desktop.ss.SSPosicaoRotulo;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmCadastroConsulta extends FormularioConsulta {
	@Autowired
	private CadastroService service;
	private SSCampoTexto cNome = new SSCampoTexto();
	private GridBagConstraints gbc_1;
	private SSCaixaCombinacao cTipo = new SSCaixaCombinacao();
	public FrmCadastroConsulta() {
		//setSize(500, 600);
		setTitulo("Cadastros");
		setDescricao("Listagem dos cadastros de cliente e fornecedores");
		
		cTipo.setRotulo("Tipo Cadastro");
		cTipo.setRotuloPosicao(SSPosicaoRotulo.ESQUERDA);
		
		cNome.setRotulo("Nome");
		cNome.setRotuloPosicao(SSPosicaoRotulo.ESQUERDA);
		
		//cNome.setColunas(20);
		
		JPanel filtros = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 0);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		filtros.add(cNome, gbc);
		
		gbc_1 = new GridBagConstraints();
		gbc_1.weightx = 0.2;
		gbc_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_1.insets = new Insets(5, 5, 5, 5);
		gbc_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_1.gridx = 1;
		gbc_1.gridy = 0;
		filtros.add(cTipo, gbc_1);
		
		filtros.add(bBuscar, getGbcBuscar(2, 0));
		getConteudo().add(filtros,BorderLayout.NORTH);
		
		getTabela().adicionarColuna(0, "ID", "id");
		getTabela().adicionarColuna(1, "CPF/CNPJ", "cpfCnpj");
		getTabela().adicionarColuna(2, "Nome/Nome Fantasia", "nomeFantasia");
		getTabela().adicionarColuna(3, "E-mail", "email");
		getTabela().adicionarColuna(4, "Endereço", "endereco.domicilio");
		
		getTabela().definirLarguraColunas(30, 100, 240,160,280);
		
		bIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluir(Context.getBean(FrmCadastroCompleto.class));
			}
		});
		
		bAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alterar(Context.getBean(FrmCadastroCompleto.class));
			}
		});
		cTipo.setPrimeiroElementoVazio(false);
		cTipo.setItens(CadastroTipo.values(), "descricao");
		setScrollSize(850, 500);
	}
	public void filtrar() {
		try {
			String nome = cNome.getText();
			if (nome.isEmpty()) {
				SSMensagem.avisa("Informe um nome para a pesquisa");
				return;
			}
			CadastroTipo tipo = (CadastroTipo) cTipo.getValue();
			List<CadastroResponse> registros = service.consultar(tipo, nome);
			exibirRegistros(registros);
		} catch (Exception e) {
			e.printStackTrace();
			SSMensagem.erro(e.getMessage());
		}
	}
}
