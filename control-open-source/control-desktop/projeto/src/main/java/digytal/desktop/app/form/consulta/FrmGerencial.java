package digytal.desktop.app.form.consulta;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JLabel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.desktop.app.model.consulta.filtro.PagamentoFiltro;
import digytal.desktop.app.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import digytal.desktop.app.model.modulo.acesso.empresa.conta.ContaResponse;
import digytal.desktop.app.model.modulo.comum.MeioPagamento;
import digytal.desktop.app.model.modulo.financeiro.response.PagamentoResponse;
import digytal.desktop.app.service.consulta.lancamento.PagamentoConsultaService;
import digytal.desktop.app.service.modulo.acesso.conta.ContaService;
import digytal.desktop.components.Formato;
import digytal.desktop.components.desktop.FormularioGestao;
import digytal.desktop.components.desktop.ss.SSCaixaCombinacao;
import digytal.desktop.components.desktop.ss.SSCampoDataHora;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.components.desktop.ss.util.SSFormatador;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmGerencial extends FormularioGestao {
	private SSCampoDataHora cDataInicial = new SSCampoDataHora();
	private SSCampoDataHora cDataFinal = new SSCampoDataHora();
	private SSCaixaCombinacao cTipo = new SSCaixaCombinacao();
	private SSCaixaCombinacao cMeioPagamento = new SSCaixaCombinacao();
	
	@Autowired
	private PagamentoConsultaService service;
	
	@Autowired
	private ContaService contaService;
	private List<ContaResponse> contas;
	public FrmGerencial() {
		setTitulo("Gerêncial");
		setDescricao("Consulta gerencial dos pagamentos e recebimentos");
		
		cDataFinal.setRotulo("Data Final");
		cDataInicial.setRotulo("Data Inicial");
		cTipo.setRotulo("Tipo");
		cMeioPagamento.setRotulo("Meio Pagamento");
		
		scroll.setPreferredSize(new Dimension(800, 350));
		
		GridBagConstraints gbc_cDataInicial = new GridBagConstraints();
		gbc_cDataInicial.fill = GridBagConstraints.HORIZONTAL;
		gbc_cDataInicial.insets = new Insets(5, 5, 5, 0);
		gbc_cDataInicial.anchor = GridBagConstraints.NORTHWEST;
		gbc_cDataInicial.gridx = 0;
		gbc_cDataInicial.gridy = 0;
		
		pFiltros.add(cDataInicial, gbc_cDataInicial);
		
		GridBagConstraints gbc_cDataFinal = new GridBagConstraints();
		gbc_cDataFinal.fill = GridBagConstraints.HORIZONTAL;
		gbc_cDataFinal.insets = new Insets(5, 5, 5, 0);
		gbc_cDataFinal.anchor = GridBagConstraints.NORTHWEST;
		gbc_cDataFinal.gridx = 1;
		gbc_cDataFinal.gridy = 0;
		pFiltros.add(cDataFinal, gbc_cDataFinal);
		
		GridBagConstraints gbc_cTipo = new GridBagConstraints();
		gbc_cTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_cTipo.insets = new Insets(5, 5, 5, 0);
		gbc_cTipo.anchor = GridBagConstraints.NORTHWEST;
		gbc_cTipo.gridx = 2;
		gbc_cTipo.gridy = 0;
		pFiltros.add(cTipo, gbc_cTipo);
		
		GridBagConstraints gbc_cMeio = new GridBagConstraints();
		gbc_cMeio.fill = GridBagConstraints.HORIZONTAL;
		gbc_cMeio.insets = new Insets(5, 5, 5, 0);
		gbc_cMeio.anchor = GridBagConstraints.NORTHWEST;
		gbc_cMeio.gridx = 3;
		gbc_cMeio.gridy = 0;
		pFiltros.add(cMeioPagamento, gbc_cMeio);
		
		GridBagConstraints gbc_cFiltrar = new GridBagConstraints();
		gbc_cFiltrar.weightx = 1.0;
		gbc_cFiltrar.insets = new Insets(10, 5, 3, 5);
		gbc_cFiltrar.anchor = GridBagConstraints.SOUTHWEST;
		gbc_cFiltrar.gridx = 4;
		gbc_cFiltrar.gridy = 0;
		pFiltros.add(getbFiltrar(), gbc_cFiltrar);
		
		tabela.adicionarColuna(0, "Cód", "id");
		tabela.adicionarColuna(1, "Data", "data.dia");
		tabela.adicionarColuna(2, "Tipo", "tipo");
		tabela.adicionarColuna(3, "Descrição", "descricao");
		tabela.adicionarColuna(4, "R$ Valor", "valor.valorInformado");
		tabela.adicionarColuna(5, "R$ Valor Op.", "valor.valorOperacional");
		tabela.adicionarColuna(6, "Meio Pagto", "meioPagamento");
		
		tabela.definirLarguraColunas(40, 55,60, 200,90, 90,90);
		
		tabela.getModeloColuna().setFormato(4, Formato.MOEDA);
		tabela.getModeloColuna().setFormato(5, Formato.MOEDA);
		tabela.getModeloColuna().definirPositivoNegativo(5);
		cTipo.setItens(AplicacaoTipo.values(),"descricao");
		cMeioPagamento.setItens(MeioPagamento.values(),"descricao");
		setSubtotalTitulo("Contas \\ Saldo");
		
		definirValorPadrao();
	}
	private void definirValorPadrao() {
		cTipo.setPrimeiroElementoVazio(true);
		cMeioPagamento.setPrimeiroElementoVazio(true);
		LocalDate initial = LocalDate.now(); //LocalDate.now().withDayOfMonth(1);
		cDataInicial.setData(initial);
		cDataFinal.setData(initial);
		
	}
	@Override
	public void filtrar() {
		contas = contaService.listar();
		//https://byexample.xyz/java/8/forEach/?gclid=CjwKCAjwkY2qBhBDEiwAoQXK5ch_4YbIhkwUC5Rfug94UrH_br3GFjeIQDItqVXzBHGpeDSULC6xPhoC1H0QAvD_BwE
		StringBuilder subtotal = new StringBuilder();
		contas.stream().forEach((ContaResponse c) -> {
			subtotal.append(c.getLegenda() + " = " + SSFormatador.formatar(c.getSaldo()) + " ");
		});
		
		setSubtotalLegenda(subtotal.toString());
		this.repaint();
		PagamentoFiltro filtro = new PagamentoFiltro();
		filtro.setDataInicial(cDataInicial.getLocalDate());
		filtro.setDataFinal(cDataFinal.getLocalDate());
		MeioPagamento meio = (MeioPagamento) cMeioPagamento.getValue();
		AplicacaoTipo tipo = (AplicacaoTipo) cTipo.getValue();
		filtro.setMeioPagamento(meio);
		filtro.setTipo(tipo);
		tabela.setValue(null);
		List<PagamentoResponse> response = service.listarPagamentos(filtro);
		if(response.size()==0) {
			SSMensagem.informa("Nenhum registro localizado, revise os filtros informados");
			return;
		}else {
			tabela.setValue(response);
			
		}
		
	}
	
}
