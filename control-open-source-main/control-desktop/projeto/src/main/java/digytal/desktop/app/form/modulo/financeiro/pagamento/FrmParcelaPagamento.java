package digytal.desktop.app.form.modulo.financeiro.pagamento;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.desktop.app.component.PainelParcelaDetalhe;
import digytal.desktop.app.model.modulo.comum.MeioPagamento;
import digytal.desktop.app.model.modulo.financeiro.request.ParcelaPagamentoRequest;
import digytal.desktop.app.model.modulo.financeiro.response.parcelamento.BoletoResponse;
import digytal.desktop.app.model.modulo.financeiro.response.parcelamento.ParcelaResponse;
import digytal.desktop.app.service.modulo.financeiro.ParcelamentoService;
import digytal.desktop.components.ColorUtil;
import digytal.desktop.components.Formato;
import digytal.desktop.components.desktop.Formulario;
import digytal.desktop.components.desktop.ss.SSBotao;
import digytal.desktop.components.desktop.ss.SSCampoNumero;
import digytal.desktop.components.desktop.ss.SSGrade;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.components.desktop.ss.util.SSFormatador;
import digytal.desktop.util.utils.business.BusinessException;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmParcelaPagamento extends Formulario {
	private PainelParcelaDetalhe parcelaDetalhe = new PainelParcelaDetalhe();
	private SSBotao bFechar = new SSBotao();
	private SSBotao bConcluir = new SSBotao();
	private SSBotao bDinheiro = new SSBotao();
	private SSBotao bPix = new SSBotao();
	private SSBotao bDebito = new SSBotao();
	private SSBotao cBoleto = new SSBotao();
	private SSCampoNumero cValorPago = new SSCampoNumero();
	private JPanel pFormaPagamento = new JPanel(new GridBagLayout());
	private List<ParcelaPagamentoRequest> pagamentos = new ArrayList();
	private SSGrade tPagamentos = new SSGrade();
	@Autowired
	private ParcelamentoService service;

	public FrmParcelaPagamento() {
		this.setTitulo("Parcela pagamento");
		this.setDescricao("Pagamento ou amortização de parcelas");
		conteudo.setLayout(new BorderLayout());
		conteudo.add(parcelaDetalhe, BorderLayout.CENTER);

		conteudo.add(pFormaPagamento, BorderLayout.SOUTH);

		pFormaPagamento.setBorder(
				new TitledBorder(null, "Forma de Pagamento", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		bFechar.setText("Fechar");
		bFechar.setIcone("close");
		bFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechar();
			}
		});
		parcelaDetalhe.setBorder(
				new TitledBorder(null, "Dados da parcela", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		getRodape().add(bConcluir);
		getRodape().add(bFechar);

		bDinheiro.setText("Dinheiro");
		bDinheiro.setIcone("money2");
		bPix.setText("Pix");
		bPix.setIcone("pix");
		cBoleto.setText("Boleto");
		cBoleto.setIcone("barcode");
		bConcluir.setText("Concluir");
		bConcluir.setIcone("ok");

		bDebito.setText("Débito");
		bDebito.setIcone("debit-card");
		cValorPago.setComponenteFonte(new Font("Tahoma", Font.BOLD, 15));
		cValorPago.setRotulo("R$ Á Pagar");
		cValorPago.setFormato(Formato.MOEDA);
		cValorPago.setMascara(Formato.MOEDA);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 2;
		gbc.weighty = 1.0;
		gbc.weightx = 1.0;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;

		pFormaPagamento.add(cValorPago, gbc);

		GridBagConstraints gbc_bDinheiro = gbc(0, 1);
		pFormaPagamento.add(bDinheiro, gbc_bDinheiro);
		GridBagConstraints gbc_bPix = gbc(1, 1);
		pFormaPagamento.add(bPix, gbc_bPix);
		GridBagConstraints gbc_bDebito = gbc(0, 2);
		pFormaPagamento.add(bDebito, gbc_bDebito);
		GridBagConstraints gbc_bBoleto = gbc(1, 2);
		pFormaPagamento.add(cBoleto, gbc_bBoleto);

		cValorPago.setComponenteCorFonte(ColorUtil.BLUE);

		JScrollPane scrollPagamentos = new JScrollPane();
		scrollPagamentos.setPreferredSize(new Dimension(0, 80));
		scrollPagamentos.setViewportView(tPagamentos);
		tPagamentos.adicionarColuna(0, "Pagamento", "meioPagamento");
		tPagamentos.adicionarColuna(1, "R$ Valor", "valor");
		tPagamentos.definirLarguraColunas(120, 120);
		tPagamentos.getModeloColuna().setFormato(1, Formato.MOEDA);

		GridBagConstraints gbcPag = new GridBagConstraints();
		gbcPag.gridwidth = 2;
		gbcPag.weighty = 1.0;
		gbcPag.weightx = 1.0;
		gbcPag.anchor = GridBagConstraints.NORTHWEST;
		gbcPag.insets = new Insets(5, 5, 5, 5);
		gbcPag.fill = GridBagConstraints.HORIZONTAL;
		gbcPag.gridx = 0;
		gbcPag.gridy = 3;

		pFormaPagamento.add(scrollPagamentos, gbcPag);

		bDinheiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adicionar(MeioPagamento.DINHEIRO);
			}
		});
		bPix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adicionar(MeioPagamento.PIX);
			}
		});
		bDebito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adicionar(MeioPagamento.DEBITO);
			}
		});
		cBoleto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					if (parcelaResponse.getBoleto().getNumeroAutorizacao() != null) {
						/*
						if ()
							gerarExibirBoleto();
						else
						*/
							
						if(SSMensagem.pergunta("Já existe um boleto vinculado a esta parcela, deseja imprimir?"))
							openWebpage(new URL(parcelaResponse.getBoleto().getUrlImpressao()).toURI());
					
					} else {
						gerarExibirBoleto();
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		bConcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				concluir();
			}
		});
		tPagamentos.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				if (mouseEvent.getClickCount() == 2) {
					removerPagamento();
				}
			}
		});
	}

	private void removerPagamento() {
		if (SSMensagem.pergunta("Deseja remover este pagamento?")) {
			ParcelaPagamentoRequest pagto = (ParcelaPagamentoRequest) tPagamentos.getLinhaSelecionada();
			pagamentos.remove(pagto);
			tPagamentos.setValue(pagamentos);
			cValorPago.setNumero(getRestante());

		}
	}

	private Double getRestante() {
		Double valorPago = pagamentos.stream().collect(Collectors.summingDouble(o -> o.getValor()));
		return total - valorPago;
	}

	private static GridBagConstraints gbc(int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 0.5;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = x;
		gbc.gridy = y;

		return gbc;
	}

	private Double total = 0.0;
	private ParcelaResponse parcelaResponse;

	private void adicionar(MeioPagamento formaPagamento) {
		if (Double.compare(cValorPago.getDouble(), getRestante()) > 0) {
			SSMensagem.avisa(
					"O valor não pode ser superior que o valor restante de " + SSFormatador.formatar(getRestante()));
			return;
		}
		if (SSMensagem.pergunta("Confirma o recebimento do pagamento")) {
			ParcelaPagamentoRequest pagto = new ParcelaPagamentoRequest();
			pagto.setMeioPagamento(formaPagamento);
			pagto.setValor(cValorPago.getDouble());

			if (pagamentos.contains(pagto)) {
				SSMensagem.avisa("Já existe um item com a forma de pagamento: " + formaPagamento.getDescricao());
				return;
			}

			pagamentos.add(pagto);
			tPagamentos.setValue(pagamentos);
			cValorPago.setNumero(getRestante());

		}
	}

	private void gerarExibirBoleto() {
		try {

			if (SSMensagem.pergunta("Confirma gerar um boleto para esta parcela")) {

				BoletoResponse boleto = service.gerarBoleto(parcelaResponse.getId(), cValorPago.getDouble());
				SSMensagem.informa("Solicitação de geração de BOLETO confirmada");

				openWebpage(new URL(boleto.getBankSlipUrl()).toURI());
				this.fechar(true);

			}

		} catch (BusinessException ne) {
			SSMensagem.avisa(ne.getErrorCode(), ne.getMessage());
		} catch (Exception ex) {
			// LOGGER.error("ERRO AO REQUISITAR API", ex);
			SSMensagem.erro("Erro ao tentar executar a operação");
		}

	}

	private void concluir() {
		try {
			if (SSMensagem.pergunta("Confirma concluir o pagamento desta parcela ?")) {

				service.realizarCompensacao(parcelaResponse.getId(), pagamentos);
				SSMensagem.informa("Pagamento compensando com sucesso !!");
				this.fechar(true);

			}
		} catch (BusinessException ne) {
			SSMensagem.avisa(ne.getErrorCode(), ne.getMessage());
		} catch (Exception ex) {
			// LOGGER.error("ERRO AO REQUISITAR API", ex);
			SSMensagem.erro("Erro ao tentar executar a operação");
		}
	}

	public void iniciar(MeioPagamento meioPagamento, ParcelaResponse parcelaResponse) {
		this.parcelaResponse = parcelaResponse;
		parcelaDetalhe.setParcela(parcelaResponse);
		total = parcelaResponse.getDetalhe().getValorAtual();
		cValorPago.setNumero(total);
		boolean pagar = meioPagamento != MeioPagamento.CREDITO && !parcelaResponse.getQuitacao().isEfetuada();
		pFormaPagamento.setVisible(pagar);
		bConcluir.setVisible(pagar);

	}

	public static boolean openWebpage(URI uri) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(uri);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static boolean openWebpage(URL url) {
		try {
			return openWebpage(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return false;
	}

}
