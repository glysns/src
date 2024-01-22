package digytal.desktop.app.form.modulo.financeiro.pagamento;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JLabel;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.desktop.app.utils.Cores;
import digytal.desktop.components.Formato;
import digytal.desktop.components.desktop.ss.SSBotao;
import digytal.desktop.components.desktop.ss.SSCampoNumero;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.components.desktop.ss.util.SSFormatador;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmPagamentoDesconto extends FrmPagamento {
	private JLabel cDescontoItem = new JLabel("0,00");
	private SSCampoNumero cDescontoManual = new SSCampoNumero();
	private SSCampoNumero cAliquota = new SSCampoNumero();
	private SSBotao bConfirmar = new SSBotao();
	private SSBotao bFechar = new SSBotao();
	
	public FrmPagamentoDesconto() {
		
		setTitulo("Desconto Manual");
		setDescricao("Aplique um desconto");
		
		cDescontoItem.setFont(new Font("Tahoma", Font.BOLD, 16));
		cDescontoItem.setForeground(Cores.AZUL);
		GridBagConstraints gbc_cDescontoItem = new GridBagConstraints();
		gbc_cDescontoItem.gridwidth = 2;
		gbc_cDescontoItem.weightx = 1.0;
		gbc_cDescontoItem.insets = new Insets(5, 5, 5, 5);
		gbc_cDescontoItem.gridx = 0;
		gbc_cDescontoItem.gridy = 0;
		getConteudo().add(cDescontoItem, gbc_cDescontoItem);
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weighty = 1.0;
		gbc.weightx = 0.1;
		gbc.insets = new Insets(5, 5, 10, 5);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		cAliquota.setColunas(4);
		getConteudo().add(cAliquota, gbc);
		cAliquota.setRotulo("%");
		cAliquota.setMascara(Formato.MOEDA);
		cAliquota.setFormato(Formato.MOEDA);
		
		gbc = new GridBagConstraints();
		gbc.weighty = 1.0;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(5, 5, 10, 5);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 1;
		getConteudo().add(cDescontoManual, gbc);
		cDescontoManual.setRotulo("Desconto Manual");
		cDescontoManual.setMascara(Formato.MOEDA);
		cDescontoManual.setFormato(Formato.MOEDA);
		
		bConfirmar.setText("OK");
		bConfirmar.setIcone("ok");
		bFechar.setText("Sair");
		bFechar.setIcone("close");
		
		getRodape().add(bConfirmar);
		getRodape().add(bFechar);
		definirEventos();
	}

	private void definirEventos() {
		bConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmar();
			}
		});
		
		bFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar(0.0);
			}
		});
		cAliquota.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

			}

			public void focusLost(FocusEvent e) {
				calcularValorDesconto();
			}
		});
	}
	private void calcularValorDesconto() {
		BigDecimal aliquota = new BigDecimal(cAliquota.getDouble());
		if(aliquota==null || aliquota == BigDecimal.ZERO) {
			cDescontoManual.setNumero(0.0);
		}else {
			BigDecimal valorDesconto = new BigDecimal(valor.toString()).multiply(aliquota).divide(new BigDecimal(100.0),2, RoundingMode.HALF_EVEN); 
			cDescontoManual.setNumero(valorDesconto.doubleValue());
		}
		
	}
	private void confirmar() {
		if(SSMensagem.pergunta("Confirmar aplicar este desconto?")) {
			Double desconto = cDescontoManual.getDouble();
			if(desconto==null) {
				SSMensagem.avisa("Informe o valor do desconto");
				return;
			}
			fechar(desconto);
		}
	}
	private Double valor;
	@Override
	public void iniciar(Double valor) {
		this.valor = valor;
		cDescontoItem.setText("R$ " + SSFormatador.formatar(valor));
		cDescontoManual.setNumero(0.0);
		cAliquota.setNumero(0.0);
	}
	
	

}
