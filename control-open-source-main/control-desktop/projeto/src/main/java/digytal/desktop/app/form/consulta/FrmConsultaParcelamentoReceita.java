package digytal.desktop.app.form.consulta;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.desktop.app.component.PainelParcelamentoDetalhe;
import digytal.desktop.app.context.Context;
import digytal.desktop.app.form.modulo.financeiro.pagamento.FrmParcelaPagamento;
import digytal.desktop.app.model.consulta.filtro.LancamentoFiltro;
import digytal.desktop.app.model.modulo.financeiro.response.ParcelamentoResponse;
import digytal.desktop.app.model.modulo.financeiro.response.parcelamento.ParcelaResponse;
import digytal.desktop.app.service.consulta.lancamento.ParcelamentoConsultaService;
import digytal.desktop.components.Formato;
import digytal.desktop.components.desktop.Formulario;
import digytal.desktop.components.desktop.FormularioGestao;
import digytal.desktop.components.desktop.ss.SSCampoDataHora;
import digytal.desktop.components.desktop.ss.SSGrade;
import digytal.desktop.components.desktop.ss.SSMensagem;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmConsultaParcelamentoReceita extends FormularioGestao {
	private SSCampoDataHora cDataInicial = new SSCampoDataHora();
	private SSCampoDataHora cDataFinal = new SSCampoDataHora();
	private PainelParcelamentoDetalhe pParcelamentoDetalhe = new PainelParcelamentoDetalhe();
	private SSGrade tParcelas = new SSGrade();
	
	@Autowired
	private ParcelamentoConsultaService service;
	
	//@Autowired
	//private ReportParcelamentoService reportService;
	private ParcelamentoResponse parcelamento;
	public FrmConsultaParcelamentoReceita() {
		setTitulo("Pagamentos À Receber");
		setDescricao("Listagem dos parcelamentos de receitas");	
		scroll.setPreferredSize(new Dimension(850, 300));
		
		GridBagConstraints gbc_cDataInicial = new GridBagConstraints();
		gbc_cDataInicial.fill = GridBagConstraints.HORIZONTAL;
		gbc_cDataInicial.insets = new Insets(5, 5, 5, 0);
		gbc_cDataInicial.anchor = GridBagConstraints.NORTHWEST;
		gbc_cDataInicial.gridx = 0;
		gbc_cDataInicial.gridy = 0;
		cDataInicial.setRotulo("Data Inicial");
		pFiltros.add(cDataInicial, gbc_cDataInicial);
		
	
		GridBagConstraints gbc_cDataFinal = new GridBagConstraints();
		gbc_cDataFinal.fill = GridBagConstraints.HORIZONTAL;
		gbc_cDataFinal.insets = new Insets(5, 5, 5, 0);
		gbc_cDataFinal.anchor = GridBagConstraints.NORTHWEST;
		gbc_cDataFinal.gridx = 1;
		gbc_cDataFinal.gridy = 0;
		cDataFinal.setRotulo("Data Final");
		pFiltros.add(cDataFinal, gbc_cDataFinal);
		
		GridBagConstraints gbc_cFiltrar = new GridBagConstraints();
		gbc_cFiltrar.weightx = 1.0;
		gbc_cFiltrar.insets = new Insets(10, 5, 3, 5);
		gbc_cFiltrar.anchor = GridBagConstraints.SOUTHWEST;
		gbc_cFiltrar.gridx = 2;
		gbc_cFiltrar.gridy = 0;
		pFiltros.add(getbFiltrar(), gbc_cFiltrar);
		
		tabela.adicionarColuna(0, "Cód", "id");
		tabela.adicionarColuna(1, "Data", "data.dia");
		tabela.adicionarColuna(2, "Cadastro", "cadastro.descricao");
		tabela.adicionarColuna(3, "N° Parc", "detalhe.numeroParcela");
		tabela.adicionarColuna(4, "Vencto", "detalhe.dataVencimento");
		tabela.adicionarColuna(5, "R$ Original", "detalhe.valorOriginal");
		tabela.adicionarColuna(6, "R$ Multa", "detalhe.valorMulta");
		tabela.adicionarColuna(7, "R$ Juros", "detalhe.valorJuros");
		tabela.adicionarColuna(8, "R$ Corr", "detalhe.valorCorrecao");
		tabela.adicionarColuna(9, "R$ Desc.", "detalhe.valorDesconto");
		tabela.adicionarColuna(10, "R$ Amort.", "detalhe.valorAmortizado");
		tabela.adicionarColuna(11, "R$ Atual", "detalhe.valorAtual");
		tabela.adicionarColuna(12, "Forma", "meioPagamento");
		
		tabela.definirLarguraColunas(40, 55, 165,47, 55,70,55,55,50,55,60,70,70);
		tabela.getModeloColuna().setFormato(5, Formato.MOEDA);
		tabela.getModeloColuna().setFormato(6, Formato.MOEDA);
		tabela.getModeloColuna().setFormato(7, Formato.MOEDA);
		tabela.getModeloColuna().setFormato(8, Formato.MOEDA);
		tabela.getModeloColuna().setFormato(9, Formato.MOEDA);
		tabela.getModeloColuna().setFormato(10, Formato.MOEDA);
		tabela.getModeloColuna().setFormato(11, Formato.MOEDA);
	
		tabela.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        if (mouseEvent.getClickCount() == 2) {
		        	parcelamento = (ParcelamentoResponse) tabela.getLinhaSelecionada();
		        	detalhar(parcelamento);
		        }
		    }
		});
		definirValorPadrao();
		
		tParcelas.adicionarColuna(0, "Paga", "quitacao.efetuada");
		tParcelas.adicionarColuna(1, "Descrição", "descricao");
		tParcelas.adicionarColuna(2, "N°", "detalhe.numeroParcela");
		tParcelas.adicionarColuna(3, "Dt. Vencto", "detalhe.dataVencimento");
		tParcelas.adicionarColuna(4, "R$ Original", "detalhe.valorOriginal");
		tParcelas.adicionarColuna(5, "R$ Atual", "detalhe.valorAtual");
		tParcelas.adicionarColuna(6, "Pend", "pendencia.atrasada");
		tParcelas.adicionarColuna(7, "Bol", "boleto.solicitado");
		tParcelas.adicionarColuna(8, "Dia", "pendencia.diasAtraso");
		tParcelas.definirLarguraColunas(40,80,25,70,70,70,35,35,35);
		tParcelas.getModeloColuna().setFormato(4, Formato.MOEDA);
		tParcelas.getModeloColuna().setFormato(5, Formato.MOEDA);
		JScrollPane scrollParcelas = new JScrollPane();
		scrollParcelas.setPreferredSize(new Dimension(100, 100));
		scrollParcelas.setViewportView(tParcelas);
		
		
		JPanel pDetalhe = new JPanel(new BorderLayout());
		pDetalhe.add(pParcelamentoDetalhe,BorderLayout.WEST);
		pDetalhe.add(scrollParcelas,BorderLayout.CENTER);
		pDetalhe.setBorder(new TitledBorder(null, "Detalhe do Parcelamento \\ Lista de Parcelas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		conteudo.add(pDetalhe, BorderLayout.SOUTH);
		
		tParcelas.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        if (mouseEvent.getClickCount() == 2) {
		        	ParcelaResponse parcela = (ParcelaResponse) tParcelas.getLinhaSelecionada();
		        	exibirPagamento(parcela);
		        }
		    }
		});
		
		imprimirListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				imprimir();
				
			}
		});

	}
	private void exibirPagamento(ParcelaResponse parcela) {
		FrmParcelaPagamento frm = Context.getBean(FrmParcelaPagamento.class);
    	frm.iniciar(parcelamento.getMeioPagamento(), parcela);
    	Object resposta =  Formulario.dialogo(frm);
    	if(resposta!=null) {
    		this.filtrar();
        	tParcelas.setValue(null);
    	}
	}
	private void imprimir() {
		try {
			/*
			ParcelamentoResponse item = (ParcelamentoResponse) tabela.getLinhaSelecionada();
			if(item!=null) {
				byte[] file = reportService.visualizarParcelamentoDetalhe(item.getId());
				imprimir(file, "parcelamento_detalhe");
			}else
				SSMensagem.avisa("Selecione um parcelamento para a impressão");
			*/
		} catch (Exception e) {
			SSMensagem.erro(e.getMessage());
		}
	}
	private void definirValorPadrao() {
		cDataInicial.setColunas(8);
		cDataFinal.setColunas(8);
		LocalDate initial = LocalDate.now().withDayOfMonth(1);
		cDataInicial.setData(initial);
		cDataFinal.setData(LocalDate.now().withDayOfMonth(initial.getMonth().length(initial.isLeapYear())));
		
	}
	private void detalhar(ParcelamentoResponse parcelamento) {
		
		pParcelamentoDetalhe.setParcelamento(parcelamento);
		
		List<ParcelaResponse> parcelas = service.consultarPacelas(parcelamento.getId());
		tParcelas.setValue(parcelas);
		
	}
	@Override
	public void filtrar() {
		LancamentoFiltro filtro = new LancamentoFiltro();
		filtro.setDataInicial(cDataInicial.getLocalDate());
		filtro.setDataFinal(cDataFinal.getLocalDate());
	
		List<ParcelamentoResponse> response = service.pesquisarReceitas(filtro);
		if(response.size()==0) {
			SSMensagem.informa("Nenhum registro localizado na data de hoje");
			return;
		}else {
			tabela.setValue(response);
			
		}
		
	}
}
