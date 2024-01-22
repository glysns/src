package digytal.desktop.app.form.consulta;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.desktop.app.model.consulta.filtro.TransacaoFiltro;
import digytal.desktop.app.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import digytal.desktop.app.model.modulo.comum.MeioPagamento;
import digytal.desktop.app.model.modulo.financeiro.response.TransacaoResponse;
import digytal.desktop.app.service.consulta.lancamento.TransacaoConsultaService;
import digytal.desktop.components.Formato;
import digytal.desktop.components.desktop.FormularioGestao;
import digytal.desktop.components.desktop.ss.SSCaixaCombinacao;
import digytal.desktop.components.desktop.ss.SSCampoDataHora;
import digytal.desktop.components.desktop.ss.SSCampoTexto;
import digytal.desktop.components.desktop.ss.SSMensagem;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmConsultaTransacaoReceita extends FormularioGestao {
	private SSCampoDataHora cDataInicial = new SSCampoDataHora();
	private SSCampoDataHora cDataFinal = new SSCampoDataHora();
	private SSCampoTexto cTipo = new SSCampoTexto();
	
	@Autowired
	private TransacaoConsultaService service;
	public FrmConsultaTransacaoReceita() {
		setTitulo("Consulta de Receitas");
		setDescricao("Consulta gerencial das transações de receitas");
		
		cDataFinal.setRotulo("Data Final");
		cDataInicial.setRotulo("Data Inicial");
		cTipo.setRotulo("Cadastro");
		
		scroll.setPreferredSize(new Dimension(800, 350));
		
		GridBagConstraints gbc_cDataInicial = new GridBagConstraints();
		gbc_cDataInicial.weightx = 0.1;
		gbc_cDataInicial.fill = GridBagConstraints.HORIZONTAL;
		gbc_cDataInicial.insets = new Insets(5, 5, 5, 0);
		gbc_cDataInicial.anchor = GridBagConstraints.NORTHWEST;
		gbc_cDataInicial.gridx = 0;
		gbc_cDataInicial.gridy = 0;
		
		pFiltros.add(cDataInicial, gbc_cDataInicial);
		
		GridBagConstraints gbc_cDataFinal = new GridBagConstraints();
		gbc_cDataFinal.weightx = 0.1;
		gbc_cDataFinal.fill = GridBagConstraints.HORIZONTAL;
		gbc_cDataFinal.insets = new Insets(5, 5, 5, 0);
		gbc_cDataFinal.anchor = GridBagConstraints.NORTHWEST;
		gbc_cDataFinal.gridx = 1;
		gbc_cDataFinal.gridy = 0;
		pFiltros.add(cDataFinal, gbc_cDataFinal);
		
		GridBagConstraints gbc_cTipo = new GridBagConstraints();
		gbc_cTipo.weightx = 0.1;
		gbc_cTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_cTipo.insets = new Insets(5, 5, 5, 0);
		gbc_cTipo.anchor = GridBagConstraints.NORTHWEST;
		gbc_cTipo.gridx = 2;
		gbc_cTipo.gridy = 0;
		pFiltros.add(cTipo, gbc_cTipo);
		
	
		GridBagConstraints gbc_cFiltrar = new GridBagConstraints();
		gbc_cFiltrar.weightx = 1.0;
		gbc_cFiltrar.insets = new Insets(10, 5, 3, 5);
		gbc_cFiltrar.anchor = GridBagConstraints.SOUTHWEST;
		gbc_cFiltrar.gridx = 4;
		gbc_cFiltrar.gridy = 0;
		pFiltros.add(getbFiltrar(), gbc_cFiltrar);
		
		tabela.adicionarColuna(0, "Cód", "id");
		tabela.adicionarColuna(1, "Data", "data.dia");
		tabela.adicionarColuna(2, "Título", "titulo");
		tabela.adicionarColuna(3, "R$ Valor", "valor.valorInformado");
		tabela.adicionarColuna(4, "Cadastro", "cadastro.descricao");
		
		tabela.definirLarguraColunas(40, 55,180,80, 200);
		
		tabela.getModeloColuna().setFormato(3, Formato.MOEDA);
		definirValorPadrao();
	}
	private void definirValorPadrao() {
		LocalDate initial = LocalDate.now().withDayOfMonth(1);
		cDataInicial.setData(initial);
		cDataFinal.setData(LocalDate.now().withDayOfMonth(initial.getMonth().length(initial.isLeapYear())));
	}
	@Override
	public void filtrar() {
		TransacaoFiltro filtro = new TransacaoFiltro();
		filtro.setDataInicial(cDataInicial.getLocalDate());
		filtro.setDataFinal(cDataFinal.getLocalDate());
		filtro.setTipo(AplicacaoTipo.RECEITA);
		tabela.setValue(null);
		List<TransacaoResponse> response = service.pesquisar(filtro);
		if(response.size()==0) {
			SSMensagem.informa("Nenhum registro localizado, revise os filtros informados");
			return;
		}else {
			tabela.setValue(response);
			
		}
		
	}
	
}
