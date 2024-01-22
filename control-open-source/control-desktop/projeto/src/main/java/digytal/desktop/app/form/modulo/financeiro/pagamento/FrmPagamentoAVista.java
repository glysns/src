package digytal.desktop.app.form.modulo.financeiro.pagamento;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.desktop.app.model.modulo.comum.MeioPagamento;
import digytal.desktop.app.model.modulo.financeiro.request.FormaPagamentoRequest;
import digytal.desktop.components.Formato;
import digytal.desktop.components.desktop.ss.SSBotao;
import digytal.desktop.components.desktop.ss.SSCampoNumero;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.util.utils.business.BusinessException;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmPagamentoAVista extends FrmPagamento {
	private SSCampoNumero cValorPago = new SSCampoNumero();

	private SSBotao bDinheiro = new SSBotao();
	private SSBotao bPix = new SSBotao();
	private SSBotao bFechar = new SSBotao();
	
	public FrmPagamentoAVista() {
		init();
	}

	public void init() {
		
		super.setTitulo("Pagamento À Vista");
		super.setDescricao("Dinheiro ou PIX");
		
		GridBagConstraints gbc_cValorPago = new GridBagConstraints();
		gbc_cValorPago.weightx = 1.0;
		gbc_cValorPago.gridwidth = 2;
		gbc_cValorPago.insets = new Insets(5, 5, 5, 0);
		gbc_cValorPago.fill = GridBagConstraints.HORIZONTAL;
		gbc_cValorPago.anchor = GridBagConstraints.NORTHWEST;
		gbc_cValorPago.gridx = 0;
		gbc_cValorPago.gridy = 0;
		cValorPago.getComponente().setFont(new Font("Tahoma", Font.PLAIN, 14));
		cValorPago.setComponenteFonte(new Font("Tahoma", Font.PLAIN, 14));
		cValorPago.setComponenteNegrito(true);
		getConteudo().add(cValorPago, gbc_cValorPago);
		
		GridBagConstraints gbc_bDinheiro = new GridBagConstraints();
		gbc_bDinheiro.weightx = 0.5;
		gbc_bDinheiro.anchor = GridBagConstraints.NORTHWEST;
		gbc_bDinheiro.insets = new Insets(5, 5, 5, 0);
		gbc_bDinheiro.fill = GridBagConstraints.HORIZONTAL;
		gbc_bDinheiro.gridx = 0;
		gbc_bDinheiro.gridy = 1;
		
		getConteudo().add(bDinheiro, gbc_bDinheiro);
		
		GridBagConstraints gbc_bPix = new GridBagConstraints();
		gbc_bPix.weightx = 0.5;
		gbc_bPix.insets = new Insets(5, 5, 5, 5);
		gbc_bPix.anchor = GridBagConstraints.NORTHWEST;
		gbc_bPix.fill = GridBagConstraints.HORIZONTAL;
		gbc_bPix.gridx = 1;
		gbc_bPix.gridy = 1;
		bPix.setText("Pix");
		bPix.setIcone("pix");
		getConteudo().add(bPix, gbc_bPix);
		
		
		getRodape().add(bFechar);
		definiRotulos();
		definirEventos();
		
	}
	public void iniciar(Double valorInformado) {
		this.valorInformado = valorInformado;
		cValorPago.setValue(valorInformado);
		cValorPago.requestFocus();
		
	}
	private void definiRotulos() {
		cValorPago.setRotulo("R$ Á Pagar");
		
		cValorPago.setFormato(Formato.MOEDA);
		cValorPago.setMascara(Formato.MOEDA);
	
		bDinheiro.setText("Dinheiro");
		bDinheiro.setIcone("money2");
		bFechar.setText("Fechar");
		bFechar.setIcone("close");
	}
	private void definirEventos() {
		bDinheiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmar(MeioPagamento.DINHEIRO);
			}
		});
		bPix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmar(MeioPagamento.PIX);
			}
		});
		
		
		bFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		
		
	}
	
	private void confirmar(MeioPagamento formaPagamento) {
		try {
			if(SSMensagem.confirma("Confirma adicionar este pagamento")) {
				if(valorInformado.compareTo(cValorPago.getDouble()) < 0) {
					SSMensagem.avisa("Valor informado é superior ao valor restante");
					return;
				}
				valorInformado = cValorPago.getDouble();
				FormaPagamentoRequest novoPagamento = calcularValorPagar(formaPagamento, 1);
				fechar(novoPagamento);
			}
		}catch (BusinessException be) {
			SSMensagem.avisa(be.getMessage());
		}catch (Exception be) {
			SSMensagem.erro(be.getMessage());
		}
	}

}
